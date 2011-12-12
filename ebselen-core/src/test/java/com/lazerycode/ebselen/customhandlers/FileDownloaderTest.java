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

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class FileDownloaderTest {

    @Test
    public void downloadAFile() throws Exception {
        WebDriver driver = new HtmlUnitDriver();
        try {
            FileDownloader downloadTestFile = new FileDownloader(driver);
            driver.get("http://www.ardescosolutions.com");
            driver.findElement(By.xpath("//ul[@id='nav']/li/descendant::a[.='Terms & Conditions']")).click();
            WebElement downloadLink = driver.findElement(By.xpath("//a[.='Click Here']"));
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
            driver.get("http://www.ardescosolutions.com");
            WebElement image = driver.findElement(By.xpath("//table[@id='header_table']/descendant::tr/td/a/img"));
            downloadTestFile.imageDownloader(image);
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            driver.close();
        }
    }


}
