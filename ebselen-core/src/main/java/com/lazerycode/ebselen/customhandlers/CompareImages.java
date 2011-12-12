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

