/*
 * Copyright (c) 2010-2011 Ardesco Solutions - http://www.ardescosolutions.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lazerycode.ebselen.customhandlers;

import com.lazerycode.ebselen.EbselenCore;
import com.lazerycode.ebselen.handlers.UserHandler;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class fileDownloader {

    private UserHandler downloadUser;// = new UserHandler("foo", "ardescocode.com", "ffw");
    private String username = "seleniumadministratoruser";
    private String downloadPath = "/tmp/";
    private String s3URL;// = "http://" + get("s3bucket") + get("s3domain");
    private String s3KeyID;//get("s3keyid")
    private String s3SecretKey;// get("s3secretkey")
    private static final Logger logger = LoggerFactory.getLogger(EbselenCore.class);
    private String domain;
    private String subDomain;
    private String fullURL;

    //**************************************************************************
    //Constructor
    //**************************************************************************
    public fileDownloader(String domain, String subDomain) {
        this.domain = domain;
        this.subDomain = subDomain;
        this.fullURL = subDomain + domain;
    }
    //**************************************************************************
    //Getter
    //**************************************************************************

    /**
     * Get the user account that will will used to log into the website to download the file.
     *
     * @return The username that will be used to log into the website
     * @throws Exception
     * @author MPLC
     */
    public String getAuthUser() throws Exception {
        return this.username;
    }

    /**
     * Get the directory that the requested file will be downloaded to.
     *
     * @return The filepath that the file will be downloaded to.
     * @throws Exception
     * @author MPLC
     */
    public String getDownloadPath() throws Exception {
        return this.downloadPath;
    }
    //**************************************************************************
    //Setter
    //**************************************************************************

    /**
     * Set the user account that will will used to log into the website to download the file.
     *
     * @param authUsername A user held in the test database
     * @throws Exception
     * @author MPLC
     */
    public void setAuthUser(String authUsername) throws Exception {
        this.username = authUsername;
    }

    /**
     * Set the path that files will be downloaded to.
     *
     * @param filePath The filepath that the file will be downloaded to.
     * @throws Exception
     * @author MPLC
     */
    public void setDownloadPath(String filePath) throws Exception {
        this.downloadPath = filePath;
    }

    /**
     * Download a specified file from the DoC website.
     *
     * @param fileURL  The URL of the file we want to download.
     * @param filename The filename (including extension) to save the file as.
     * @return The absolute path and filename of the file.
     * @throws Exception
     * @author MPLC
     */
    public String downloadFile(String fileURL, String filename) throws Exception {
        return fileDownloader(fileURL, filename, true);
    }

    /**
     * Download a specified file from the S3 Bucket.
     *
     * @param fileURL  The URL of the file we want to download.
     * @param filename The filename (including extension) to save the file as.
     * @return The absolute path and filename of the file.
     * @throws Exception
     * @author MPLC
     */
    public String downloadS3File(String fileURL, String filename) throws Exception {
        return fileDownloader(fileURL, filename, false);
    }

    /**
     * Will download a file from either the DoC website of the DoC S3 bucket.
     *
     * @param fileURL  The URL of the file we want to download.
     * @param filename The filename (including extension) to save the file as.
     * @param doc      True if download from DoC, otherwise false.
     * @return The absolute path and filename of the file.
     * @throws Exception
     * @author MPLC
     */
    public String fileDownloader(String fileURL, String filename, Boolean doc) throws Exception {
        HttpClient client = new HttpClient();
        //TODO Workaround for Defect #13648 needs to be removed
        fileURL = fileURL.replaceAll("\\[", "%5B").replaceAll("\\]", "%5D");
        GetMethod getRequest;
        if (doc) {
            client.getParams().setAuthenticationPreemptive(true);
            client.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
            Credentials defaultcreds = new UsernamePasswordCredentials(downloadUser.getUserName(), downloadUser.getPassword());
            client.getState().setCredentials(new AuthScope(this.subDomain + this.domain, 80, AuthScope.ANY_REALM), defaultcreds);
            getRequest = new GetMethod(this.fullURL + fileURL);
        } else {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy kk:ee:ss zzz");  //Format of Wed, 28 Oct 2009 22:32:00 GMT
            getRequest = new GetMethod(s3URL + fileURL.replaceAll("^downloads/", ""));
            getRequest.addRequestHeader("Host", this.subDomain + this.domain);
            getRequest.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            getRequest.addRequestHeader("Authorization", "AWS " + s3KeyID + ":" + s3SecretKey);
            getRequest.addRequestHeader("Date", sdf.format(calendar.getTime()));
        }
        try {
            //Download file from DoC
            int status = client.executeMethod(getRequest);
            logger.debug("[MPLC] HTTP Status " + status + " when getting '" + this.fullURL + fileURL + filename + "'");
            OutputStream out = new FileOutputStream(new File(downloadPath + filename));
            BufferedInputStream in = new BufferedInputStream(getRequest.getResponseBodyAsStream());
            int offset = 0;
            int len = 4096;
            int bytes = 0;
            byte[] block = new byte[len];
            while ((bytes = in.read(block, offset, len)) > -1) {
                out.write(block, 0, bytes);
            }
            out.close();
            in.close();
        } catch (Exception Ex) {
            logger.error("[MPLC] Download failed: " + Ex);
        } finally {
            getRequest.releaseConnection();
        }

        return downloadPath + filename;
    }
}
