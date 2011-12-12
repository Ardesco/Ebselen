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

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class FileDownloaderTest {

    private static JettyServer localWebServer = new JettyServer();
    private static int webServerPort = 8081;

    @BeforeClass
    public static void start() throws Exception {
        localWebServer.startJettyServer(webServerPort);
    }

    @AfterClass
    public static void stop() throws Exception {
        localWebServer.stopJettyServer();
    }

    @Test
    public void downloadAFile() throws Exception {
        WebDriver driver = new HtmlUnitDriver();
        try {
            FileDownloader downloadTestFile = new FileDownloader(driver);
            driver.get("http://localhost:8081/downloadTest.html");
            WebElement downloadLink = driver.findElement(By.id("fileToDownload"));
            downloadTestFile.fileDownloader(downloadLink);
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            driver.close();
        }
    }

    @Test
    public void downloadAnImage() throws Exception {
        WebDriver driver = new HtmlUnitDriver();
        try {
            FileDownloader downloadTestFile = new FileDownloader(driver);
            driver.get("http://localhost:8081/downloadTest.html");
            WebElement image = driver.findElement(By.id("ebselenImage"));
            downloadTestFile.imageDownloader(image);
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            driver.close();
        }
    }


}
