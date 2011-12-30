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

import com.google.common.annotations.Beta;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

@Beta
public class TinyMCEHandler {

    private WebDriver driver;
    private Actions builder;
    private String textAreaID;


    public TinyMCEHandler(String textAreaID, WebDriver driver) {
        this.textAreaID = textAreaID;
        this.driver = driver;
        this.builder = new Actions(this.driver);
    }

    private WebElement doesElementExist() throws Exception {
        return doesElementExist(this.textAreaID);
    }

    private WebElement doesElementExist(String textAreaID) throws Exception {
        String TinyMCEIframeID = textAreaID + "_ifr";
        List<WebElement> tinyMCEFrame = this.driver.findElements(By.id(TinyMCEIframeID));
        if (tinyMCEFrame.size() < 1) {
            throw new Exception("Unable to find TinyMCE iFrame!");
        } else if (tinyMCEFrame.size() > 1) {
            throw new Exception("Found more than ont element with the id " + TinyMCEIframeID + "!");
        }
        return tinyMCEFrame.get(0);
    }

    private String getPopupID() {
        return driver.findElement(By.xpath("//div[starts-with(@id, 'mce_')][@class='clearlooks2']")).getAttribute("id");
    }

    public void clear() throws Exception {
        this.driver.findElement(By.id(this.textAreaID + "_newdocument")).click();
        String popupID = getPopupID();
        this.driver.findElement(By.id(popupID + "_ok")).click();
    }

    public void type(String value) throws Exception {
        WebElement tinyMCEFrame = doesElementExist();
        this.driver.switchTo().frame(tinyMCEFrame);
        WebElement tinyMCEEditor = driver.findElement(By.id("tinymce"));
        tinyMCEEditor.sendKeys(value);
        this.driver.switchTo().defaultContent();
    }

    public void replaceHTMLSource(String value) throws Exception {
        driver.findElement(By.id(this.textAreaID + "_code")).click();
        String sourceBlockID = driver.findElement(By.xpath("//div[@id='mceModalBlocker']/preceding-sibling::div")).getAttribute("id");
        WebElement htmlSourceFrame = doesElementExist(sourceBlockID);
        this.driver.switchTo().frame(htmlSourceFrame);
        WebElement htmlSource = driver.findElement(By.id("htmlSource"));
        htmlSource.clear();
        htmlSource.sendKeys(value);
        driver.findElement(By.id("insert")).click();
        this.driver.switchTo().defaultContent();
    }


    public String getText() throws Exception {
        WebElement tinyMCEFrame = doesElementExist();
        this.driver.switchTo().frame(tinyMCEFrame);
        String tinyMCEText = driver.findElement(By.id("tinymce")).getText();
        this.driver.switchTo().defaultContent();
        return tinyMCEText;
    }
}
