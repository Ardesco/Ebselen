package com.lazerycode.ebselen.customhandlers;

import com.lazerycode.ebselen.handlers.FileHandler;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.net.URI;
import java.net.URL;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class CompareImagesTest {

    private String locator = "ebselenImage";
    private URL localImage = this.getClass().getResource("/web/images/ebselen.png");
    private WebDriver driver = new FirefoxDriver();

    @Test
    public void updateRemoteFileLocation() throws Exception {
        WebElement remoteImage = driver.findElement(By.id(locator));
        WebElement invalidRemoteImage = driver.findElement(By.id("doesNotExist"));
        CompareImages imageCompare = new CompareImages(localImage.toExternalForm(), invalidRemoteImage, driver);
        assertThat(imageCompare.getRemoteImageWebElement(), is(equalTo(invalidRemoteImage)));
        imageCompare.changeRemoteImageTo(remoteImage);
        assertThat(imageCompare.getRemoteImageWebElement(), is(equalTo(remoteImage)));
    }

    @Test
    public void updateLocalFileLocationUsingString() throws Exception {
        String localImage = this.localImage.toExternalForm();
        String invalidLocalImage = "null";
        WebElement remoteImage = driver.findElement(By.id(locator));
        CompareImages imageCompare = new CompareImages(invalidLocalImage, remoteImage, driver);
        assertThat(imageCompare.getLocalImage(), is(equalTo(new FileHandler(invalidLocalImage).getFile())));
        imageCompare.changeLocalFileTo(localImage);
        assertThat(imageCompare.getLocalImage(), is(equalTo(new File(this.localImage.toURI()))));
    }

    @Test
    public void updateLocalFileLocationUsingFile() throws Exception {
        File localImage = new File(this.localImage.toURI());
        File invalidLocalImage = new FileHandler("null").getFile();
        WebElement remoteImage = driver.findElement(By.id(locator));
        CompareImages imageCompare = new CompareImages(invalidLocalImage, remoteImage, driver);
        assertThat(imageCompare.getLocalImage(), is(equalTo(new FileHandler(invalidLocalImage).getFile())));
        imageCompare.changeLocalFileTo(localImage);
        assertThat(imageCompare.getLocalImage(), is(equalTo(new File(this.localImage.toURI()))));
    }
}
