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

import com.lazerycode.ebselen.handlers.FileHandler;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.io.File;
import java.net.URI;
import java.net.URL;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class CompareImagesTest {

    private String locator = "ebselenImage";
    private String wrongLocator = "fileToDownload";
    private URL localImage = this.getClass().getResource("/web/images/ebselen.png");
    private URL wrongLocalImage = this.getClass().getResource("/web/images/lazeryattack.com.png");
    private static JettyServer localWebServer = new JettyServer();
    private static int webServerPort = 8081;
    private WebDriver driver = new HtmlUnitDriver();

    @BeforeClass
    public static void start() throws Exception {
        localWebServer.startJettyServer(webServerPort);
    }

    @AfterClass
    public static void stop() throws Exception {
        localWebServer.stopJettyServer();
    }

    @After
    public void closeWebDriver() {
        driver.close();
    }

    @Test
    public void updateRemoteFileLocation() throws Exception {
        driver.get("http://localhost:8081/downloadTest.html");
        WebElement remoteImage = driver.findElement(By.id(locator));
        WebElement invalidRemoteImage = driver.findElement(By.id(wrongLocator));
        CompareImages imageCompare = new CompareImages(localImage.toURI().getPath(), invalidRemoteImage, driver);
        assertThat(imageCompare.getRemoteImageWebElement(), is(equalTo(invalidRemoteImage)));
        imageCompare.changeRemoteImageTo(remoteImage);
        assertThat(imageCompare.getRemoteImageWebElement(), is(equalTo(remoteImage)));
    }

    @Test
    public void updateLocalFileLocationUsingString() throws Exception {
        driver.get("http://localhost:8081/downloadTest.html");
        String localImage = this.localImage.toURI().getPath();
        String invalidLocalImage = this.wrongLocalImage.toURI().getPath();
        WebElement remoteImage = driver.findElement(By.id(locator));
        CompareImages imageCompare = new CompareImages(invalidLocalImage, remoteImage, driver);
        assertThat(imageCompare.getLocalImage(), is(equalTo(new FileHandler(invalidLocalImage).getFile())));
        imageCompare.changeLocalFileTo(localImage);
        assertThat(imageCompare.getLocalImage(), is(equalTo(new File(this.localImage.toURI()))));
    }

    @Test
    public void updateLocalFileLocationUsingFile() throws Exception {
        driver.get("http://localhost:8081/downloadTest.html");
        File localImage = new File(this.localImage.toURI());
        File invalidLocalImage = new File(this.wrongLocalImage.toURI());
        WebElement remoteImage = driver.findElement(By.id(locator));
        CompareImages imageCompare = new CompareImages(invalidLocalImage, remoteImage, driver);
        assertThat(imageCompare.getLocalImage(), is(equalTo(new FileHandler(invalidLocalImage).getFile())));
        imageCompare.changeLocalFileTo(localImage);
        assertThat(imageCompare.getLocalImage(), is(equalTo(new File(this.localImage.toURI()))));
    }

    @Test
    public void compareMatchingImagesUsingFile() throws Exception {
        driver.get("http://localhost:8081/downloadTest.html");
        File localImage = new File(this.localImage.toURI());
        WebElement remoteImage = driver.findElement(By.id(locator));
        CompareImages imageCompare = new CompareImages(localImage, remoteImage, driver);
        assertThat(imageCompare.compareImages(), is(equalTo(true)));
    }

    @Test
    public void compareMatchingImagesUsingString() throws Exception {
        driver.get("http://localhost:8081/downloadTest.html");
        String localImage = this.localImage.toURI().getPath();
        WebElement remoteImage = driver.findElement(By.id(locator));
        CompareImages imageCompare = new CompareImages(localImage, remoteImage, driver);
        assertThat(imageCompare.compareImages(), is(equalTo(true)));
    }

    @Test
    public void compareDifferentImagesUsingFile() throws Exception {
        driver.get("http://localhost:8081/downloadTest.html");
        File localImage = new File(this.wrongLocalImage.toURI());
        WebElement remoteImage = driver.findElement(By.id(locator));
        CompareImages imageCompare = new CompareImages(localImage, remoteImage, driver);
        assertThat(imageCompare.compareImages(), is(equalTo(false)));
    }

    @Test
    public void compareDifferentImagesUsingString() throws Exception {
        driver.get("http://localhost:8081/downloadTest.html");
        String localImage = this.wrongLocalImage.toURI().getPath();
        WebElement remoteImage = driver.findElement(By.id(locator));
        CompareImages imageCompare = new CompareImages(localImage, remoteImage, driver);
        assertThat(imageCompare.compareImages(), is(equalTo(false)));
    }
}
