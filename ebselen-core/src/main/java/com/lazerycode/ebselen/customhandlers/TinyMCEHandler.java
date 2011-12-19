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
    private String TinyMCEIframeID;


    public TinyMCEHandler(String textAreaID, WebDriver driver) {
        this.textAreaID = textAreaID;
        this.TinyMCEIframeID = textAreaID + "_ifr";
        this.driver = driver;
        this.builder = new Actions(this.driver);
    }

    private WebElement doesElementExist() throws Exception {
        List<WebElement> tinyMCEFrame = this.driver.findElements(By.id(this.TinyMCEIframeID));
        if (tinyMCEFrame.size() < 1) {
            throw new Exception("Unable to find TinyMCE iFrame!");
        } else if (tinyMCEFrame.size() > 1) {
            throw new Exception("Found more than ont element with the id " + this.TinyMCEIframeID + "!");
        }
        return tinyMCEFrame.get(1);
    }

    public void clear() throws Exception {
        WebElement tinyMCEFrame = doesElementExist();
        this.driver.switchTo().frame(tinyMCEFrame);
        this.builder.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).keyDown(Keys.DELETE).keyUp(Keys.DELETE).perform();
        this.driver.switchTo().defaultContent();
    }

    public void type(String value) throws Exception {
        WebElement tinyMCEFrame = doesElementExist();
        this.driver.switchTo().frame(tinyMCEFrame);
        //Type
        this.driver.switchTo().defaultContent();
    }

    public String getText() throws Exception {
        WebElement tinyMCEFrame = doesElementExist();
        this.driver.switchTo().frame(tinyMCEFrame);
        //Get Text
        this.driver.switchTo().defaultContent();
        return "";
    }
}
