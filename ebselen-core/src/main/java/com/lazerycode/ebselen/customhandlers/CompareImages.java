package com.lazerycode.ebselen.customhandlers;

import com.lazerycode.ebselen.handlers.FileHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;

public class CompareImages {

    private FileHandler localFileObject;
    private WebElement remoteImageObject;
    private FileHandler downloadedFileObject;
    private WebDriver driver;

    public CompareImages(File localFile, WebElement remoteImage, WebDriver driver) throws Exception {
        this.localFileObject = new FileHandler(localFile);
        this.remoteImageObject = remoteImage;
        this.driver = driver;
    }

    public CompareImages(String localFile, WebElement remoteImage, WebDriver driver) throws Exception {
        this.localFileObject = new FileHandler(localFile);
        this.remoteImageObject = remoteImage;
        this.driver = driver;
    }

    public void changeLocalFileTo(File localfile) throws Exception {
        this.localFileObject = new FileHandler(localfile);
    }

    public void changeLocalFileTo(String localfile) throws Exception {
        this.localFileObject = new FileHandler(localfile);
    }

    public void changeRemoteImageTo(WebElement remoteImage) {
        this.remoteImageObject = remoteImage;
    }

    public File getLocalImage() throws Exception {
        return this.localFileObject.getFile();
    }

    public WebElement getRemoteImageWebElement() {
        return this.remoteImageObject;
    }

}

