package com.lazerycode.ebselen.customhandlers;

import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

import com.lazerycode.ebselen.EbselenCore;
import com.lazerycode.ebselen.handlers.UserHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.vfs.AllFileSelector;
import org.apache.commons.vfs.FileObject;
import org.apache.commons.vfs.FileSystemException;
import org.apache.commons.vfs.FileSystemManager;
import org.apache.commons.vfs.FileSystemOptions;
import org.apache.commons.vfs.provider.sftp.*;
import org.apache.commons.vfs.UserAuthenticator;
import org.apache.commons.vfs.VFS;
import org.apache.commons.vfs.auth.StaticUserAuthenticator;
import org.apache.commons.vfs.impl.DefaultFileSystemConfigBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to upload files to a server via FTP or SFTP
 */
public class fileUpload {

    private HashMap uploadblock = new HashMap();
    private HashMap uploadfileData = new HashMap();
    private HashMap assetRecords = new HashMap();
    private HashMap documentRecords = new HashMap();
    private HashMap assetResults = new HashMap();
    private HashMap documentResults = new HashMap();
    private HashMap assetFileInformation = new HashMap();
    private HashMap documentFileInformation = new HashMap();
    private HashMap fileSizes = new HashMap();
    private HashMap fileList = new HashMap();
    private UserHandler fileUser;// = new UserHandler("foo", "ardescocode.com", "ffw");
    private String fileDIrectory;// = get("docSitePath") + "files/downloads/";
    private String fileDirForDropbox;// = get("dropboxPath");
    private String username;//= get("docUser");
    private String password;//= get("docPassword");
    private String remoteSFTPServer;
    private int remoteSFTPServerPort;
    private String remoteFTPServer;
    private int remoteFTPServerPort;
    private FileSystemManager fsManager = null;
    private FileSystemOptions opts = null;
    private File[] privateKeys;
    private static final Logger logger = LoggerFactory.getLogger(EbselenCore.class);

    public fileUpload(String type) {
        if (type.equals("sftp")) {
            try {
                createFileUploadObject();
            } catch (Exception Ex) {
                logger.debug("Error creating a fileUpload object: " + Ex.getLocalizedMessage());
            }
        } else if (type.equals("ftp")) {
            //Do nothing, no setup required as this point
        } else {
            logger.error("Unknown type supplied...");
        }
    }

    public fileUpload() {
        try {
            createFileUploadObject();
        } catch (Exception Ex) {
            logger.debug("Error creating a fileUpload object: " + Ex.getLocalizedMessage());
        }
    }
    //Constructor

    /**
     * This will set a default owner and upload user for file uploads.
     *
     * @throws Exception
     */
    private void createFileUploadObject() throws Exception {
        //Create the file upload HashMap
        uploadblock.put("method", "bulk_upload"); //always this at the moment
        uploadblock.put("upload", null);
        //Set the base information to create the file records
        uploadfileData.put("assets", assetRecords);
        uploadfileData.put("documents", documentRecords);
        File keysDir = new File("src/docCore/keys");
        FileFilter fileFilter = new FileFilter() {

            public boolean accept(File file) {
                return file.isFile();
            }
        };
        privateKeys = keysDir.listFiles(fileFilter);
    }

    /**
     * Upload all of the files held in the uploadBlock HashMap
     *
     * @throws Exception
     */
    public void performFileUpload() throws Exception {
        Iterator it;
        String resultofPost = "";
        HttpClient client = new HttpClient();
        //Copy the file from the file directory to DoC
        initialiseSFTPConnection(fileDIrectory);
        processFileUpload();
    }

    //**************************************************************************
    //Setters
    //**************************************************************************
    public void setSFTPServer(String serverAddress) throws Exception {
        remoteSFTPServer = serverAddress;
    }

    public String getSFTPServer() throws Exception {
        return remoteSFTPServer;
    }

    public void setSFTPServerPort(int port) throws Exception {
        remoteSFTPServerPort = port;
    }

    public int getSFTPServerPort() throws Exception {
        return remoteSFTPServerPort;
    }

    public void setFTPServer(String serverAddress) throws Exception {
        remoteFTPServer = serverAddress;
    }

    public String getFTPServer() throws Exception {
        return remoteFTPServer;
    }

    public void setFTPServerPort(int port) throws Exception {
        remoteFTPServerPort = port;
    }

    public int getFTPServerPort() throws Exception {
        return remoteFTPServerPort;
    }

    /**
     * Add a file to the list of files to copy across to the FTP location.
     *
     * @param filepath the path of the file.
     * @param filename the name of the file.
     * @throws Exception
     */
    public void addFileToCopyList(String filepath, String filename) throws Exception {
        fileList.put(filename, filepath);
    }

    //**************************************************************************
    //Various Functions
    //**************************************************************************

    /**
     * Set up initial connection to SFTP server.
     * This function will use the DoC webserver details held in doc.properties.
     *
     * @throws Exception
     */
    public void initialiseSFTPConnection(String filepath) throws Exception {
        try {
            fsManager = VFS.getManager();
        } catch (FileSystemException Ex) {
            logger.error("Failed to get fsManager from VFS:" + Ex);

        }
        UserAuthenticator auth = new StaticUserAuthenticator(null, username, password);
        opts = new FileSystemOptions();
        try {
            DefaultFileSystemConfigBuilder.getInstance().setUserAuthenticator(opts, auth);
            SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(opts, "no");
            SftpFileSystemConfigBuilder.getInstance().setIdentities(opts, privateKeys);
        } catch (FileSystemException Ex) {
            logger.error("Authentication failed, check your login details.\n" + Ex);

        }
        try {
            fsManager.resolveFile(remoteSFTPServer + filepath, opts);
            logger.debug("SFTP connection successfully established to " + remoteSFTPServer + filepath);
        } catch (FileSystemException Ex) {
            logger.error("SFTP error parsing path " + filepath + ": " + Ex);

        }
    }

    public Boolean processFTPUpload() throws Exception {
        FTPClient ftp = new FTPClient();
        try {
            //Connect to FTP server
            ftp.connect(remoteFTPServer);
            ftp.login(username, password);
            int reply = ftp.getReplyCode();
            //Display error if connection is refused
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                logger.error("Unable to connect to FTP server...");

            }
            //Transfer files
            Iterator uploadFiles = fileList.entrySet().iterator();
            while (uploadFiles.hasNext()) {
                Map.Entry fileData = (Map.Entry) uploadFiles.next();
                logger.debug("Uploading " + fileData.getKey().toString() + "...");
                InputStream in = new FileInputStream(fileData.getValue().toString() + fileData.getKey().toString());
                ftp.setFileType(ftp.BINARY_FILE_TYPE);
                ftp.storeFile(fileData.getKey().toString(), in);
                in.close();
            }
            //log out of FTP Server
            ftp.logout();
            ftp.disconnect();
        } catch (Exception Ex) {
            logger.error("Error processing file upload...");
            Ex.printStackTrace();
            return false;
        }
        logger.debug("Files uploaded successfully!");
        return true;
    }

    /**
     * Upload a list of files via SFTP to the dropbox upload directory on the DoC server
     *
     * @return
     * @throws Exception
     */
    public Boolean processSFTPUpload() throws Exception {
        Boolean success = true;
        File fileLocLocal;
        FileObject remoteFile, localFile = null;
        //Initialise connection to SFTP server
        initialiseSFTPConnection(fileDirForDropbox);
        //Upload Files
        Iterator filesToUpload = fileList.entrySet().iterator();
        while (filesToUpload.hasNext()) {
            Map.Entry fileData = (Map.Entry) filesToUpload.next();
            fileLocLocal = new File(fileData.getValue().toString() + fileData.getKey().toString());
            remoteFile = fsManager.resolveFile(remoteSFTPServer + fileDirForDropbox + fileData.getKey().toString(), opts);
            localFile = fsManager.toFileObject(fileLocLocal);
            try {
                logger.debug("Copying " + fileData.getKey().toString() + " to DoC Website...");
                //Delete log file if it exists (error is supressed if it doesn't
                deleteFile(remoteSFTPServer + fileDirForDropbox + fileData.getKey().toString() + ".log");
                remoteFile.copyFrom(localFile, new AllFileSelector());
            } catch (Exception Ex) {
                logger.error("Unable to copy file to remote location: " + Ex);
                success = false;
            }
            fsManager.closeFileSystem(remoteFile.getFileSystem());
            fsManager.closeFileSystem(localFile.getFileSystem());
        }
        return success;
    }

    public Boolean collectDropboxLogFile(String zipName) throws Exception {
        FileObject remoteFile = null;
        String fileContents = "";
        String lineRead = "";
        //Initialise connection to SFTP server
        initialiseSFTPConnection(fileDirForDropbox);
        //Upload Files
        FileObject zipFile = fsManager.resolveFile(remoteSFTPServer + fileDirForDropbox + zipName, opts);
        logger.debug("Waiting for dropbox to process the zipfile...");
        long start = System.currentTimeMillis();
        long timeout = 60000;
        while (zipFile.exists() && ((System.currentTimeMillis() - start) < timeout)) {
            fsManager.closeFileSystem(zipFile.getFileSystem());
            zipFile = fsManager.resolveFile(remoteSFTPServer + fileDirForDropbox + zipName, opts);
        }
        //Drop out of function if zip file is not processed
        if (zipFile.exists()) {
            logger.error("Dropbox has not processed the zip file within " + (timeout / 1000) + "Seconds...");
            fsManager.closeFileSystem(zipFile.getFileSystem());
            return null;
        }
        try {
            //open file
            remoteFile = fsManager.resolveFile(remoteSFTPServer + fileDirForDropbox + zipName + ".log", opts);
            BufferedReader readFile = new BufferedReader(new InputStreamReader((new BufferedInputStream(remoteFile.getContent().getInputStream()))));
            //Read in file line by line
            while ((lineRead = readFile.readLine()) != null) {
                if (lineRead != null) {
                    fileContents += lineRead;
                } else {
                    break;
                }
            }
            readFile.close();
        } catch (Exception Ex) {
            logger.error("Unable to read dropbox log file: " + Ex.toString());
        } finally {
            fsManager.closeFileSystem(remoteFile.getFileSystem());
            fsManager.closeFileSystem(zipFile.getFileSystem());
        }
        if (fileContents.contains("RESULT:SUCCESS")) {
            logger.debug("Dropbox has processed the zipfile successfully!");
            return true;
        } else {
            return false;
        }
    }

    /**
     * This will create a directory on the remote server (if required) and then upload files files into that directory as specified
     *
     * @throws Exception
     */
    public void processFileUpload() throws Exception {
        HashMap orderedFiles = new HashMap();
        String fileExtension, fileLocRemote;
        File fileLocLocal;
        FileObject extDir, remoteFile, localFile = null;
        Iterator filesToUpload;
        orderedFiles.put("source", new HashMap());
        orderedFiles.put("destination", new HashMap());
        ArrayList fileExtensions = new ArrayList();
        //Get all documents we need to upload
        filesToUpload = documentFileInformation.entrySet().iterator();
        while (filesToUpload.hasNext()) {
            Map.Entry fieldData = (Map.Entry) filesToUpload.next();
            //This assumes that all filenames only have one . in them
            fileExtension = fieldData.getKey().toString().split("\\.")[1];
            if (!fileExtensions.contains(fileExtension)) {
                fileExtensions.add(fileExtension);
            }
            ((HashMap) orderedFiles.get("source")).put(fieldData.getKey().toString(), fieldData.getValue().toString());
            ((HashMap) orderedFiles.get("destination")).put(fieldData.getKey().toString(), new HashMap());
            ((HashMap) ((HashMap) orderedFiles.get("destination")).get(fieldData.getKey().toString())).put("path", remoteSFTPServer + fileDIrectory);
            //TODO below is rubbish probably
            //((HashMap) ((HashMap) orderedFiles.get("destination")).get(fieldData.getKey().toString())).put("filename", _cf.getFileHash(fieldData.getValue().toString(), fieldData.getKey().toString()) + "." + fileExtension);
            ((HashMap) ((HashMap) orderedFiles.get("destination")).get(fieldData.getKey().toString())).put("extensionDir", fileExtension);
        }
        //Get all images we need to upload
        filesToUpload = assetFileInformation.entrySet().iterator();
        while (filesToUpload.hasNext()) {
            Map.Entry fieldData = (Map.Entry) filesToUpload.next();
            //This assumes that all filenames only have one . in them
            fileExtension = fieldData.getKey().toString().split("\\.")[1];
            if (!fileExtensions.contains(fileExtension)) {
                fileExtensions.add(fileExtension);
            }
            ((HashMap) orderedFiles.get("source")).put(fieldData.getKey().toString(), fieldData.getValue().toString());
            ((HashMap) orderedFiles.get("destination")).put(fieldData.getKey().toString(), new HashMap());
            ((HashMap) ((HashMap) orderedFiles.get("destination")).get(fieldData.getKey().toString())).put("path", remoteSFTPServer + fileDIrectory);
            ((HashMap) ((HashMap) orderedFiles.get("destination")).get(fieldData.getKey().toString())).put("filename", fieldData.getKey().toString());
            ((HashMap) ((HashMap) orderedFiles.get("destination")).get(fieldData.getKey().toString())).put("extensionDir", fileExtension);
        }
        //Create required extension directories
        logger.debug("creating any required extension directories on the remote webserver...");
        for (int i = 0; i < fileExtensions.size(); i++) {
            try {
                extDir = fsManager.resolveFile(remoteSFTPServer + fileDIrectory + fileExtensions.get(i).toString(), opts);
                extDir.createFolder();
                fsManager.closeFileSystem(extDir.getFileSystem());
            } catch (Exception Ex) {
                logger.error("Unable to create the '" + fileExtensions.get(i).toString() + "' directory!");

            }
        }
        //Perform the physical file upload for all files.
        filesToUpload = ((HashMap) orderedFiles.get("source")).entrySet().iterator();
        while (filesToUpload.hasNext()) {
            Map.Entry fileData = (Map.Entry) filesToUpload.next();
            fileLocLocal = new File(fileData.getValue().toString() + fileData.getKey().toString());
            fileLocRemote = ((HashMap) ((HashMap) orderedFiles.get("destination")).get(fileData.getKey().toString())).get("path").toString() + ((HashMap) ((HashMap) orderedFiles.get("destination")).get(fileData.getKey().toString())).get("extensionDir").toString() + "/" + ((HashMap) ((HashMap) orderedFiles.get("destination")).get(fileData.getKey().toString())).get("filename").toString();
            remoteFile = fsManager.resolveFile(fileLocRemote, opts);
            localFile = fsManager.toFileObject(fileLocLocal);
            //Capture the filesize for DB check later on
            fileSizes.put(fileLocRemote, fileLocLocal.length());
            try {
                logger.debug("Copying " + fileData.getKey().toString() + " to DoC Website...");
                remoteFile.copyFrom(localFile, new AllFileSelector());
            } catch (Exception Ex) {
                logger.error("Unable to copy file to remote location: " + Ex);
            }
            fsManager.closeFileSystem(remoteFile.getFileSystem());
            fsManager.closeFileSystem(localFile.getFileSystem());
        }
    }

    /**
     * This will delete a file from the DoC remote server
     *
     * @param filename The name of the file you wish to delete
     * @throws Exception
     */
    public void deleteFile(String filename) throws Exception {
        FileObject remoteFile = fsManager.resolveFile(filename, opts);
        try {
            if (remoteFile.exists()) {
                logger.error("Deleting file '" + filename + "'...");
                remoteFile.delete();
            } else {
                logger.error("Unable to delete " + filename + " as it does not exist...");
            }
        } catch (Exception Ex) {
            logger.error("Unable to delete file: " + Ex);
        }
        fsManager.closeFileSystem(remoteFile.getFileSystem());
    }

    /**
     * This will delete a file from a filestore on the remote server
     *
     * @param filename The name of the file you wish to delete
     * @throws Exception
     */
    public void deleteFileFromFileStore(String filename) throws Exception {
        initialiseSFTPConnection(fileDIrectory);
        String fileExtension = filename.toLowerCase().split("\\.")[1];
        FileObject remoteFile = fsManager.resolveFile(remoteSFTPServer + fileDIrectory + fileExtension + "/" + filename, opts);
        try {
            if (remoteFile.exists()) {
                logger.debug("Deleting file '" + filename + "'...");
                remoteFile.delete();
            } else {
                logger.error("Unable to delete file as is does not exist...");
            }
        } catch (Exception Ex) {
            logger.error("Unable to delete file: " + Ex);
        }
        fsManager.closeFileSystem(remoteFile.getFileSystem());
    }
}