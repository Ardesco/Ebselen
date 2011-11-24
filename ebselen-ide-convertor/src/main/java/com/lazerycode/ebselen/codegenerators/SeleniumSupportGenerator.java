/*
Copyright 2010-2012 Ardesco Solutions - http://www.ardescosolutions.com

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package com.lazerycode.ebselen.codegenerators;

import com.lazerycode.ebselen.handlers.LocatorHandler;
import com.lazerycode.ebselen.exceptions.UnableToGenerateSelectObjectException;

import java.security.MessageDigest;
import java.util.HashMap;

public class SeleniumSupportGenerator {
    private String selectObject;
    private HashMap<String, String> selectObjects = new HashMap<String, String>();
    private WebDriverGenerator driver = new WebDriverGenerator("driver");
    private LocatorHandler loc = new LocatorHandler();

    public SeleniumSupportGenerator() {
    }

    private String hashString(String value) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(value.getBytes());
        byte byteData[] = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public String selectObjectID(String value) {
        try {
            String key = hashString(value);
            if (!this.selectObjects.containsKey(key)) {
                int selectNumber = selectObjects.size() + 1;
                this.selectObjects.put(key, "select" + selectNumber);
                return "Select " + this.selectObjects.get(key) + " = new Select(" + driver.findElement(loc.locatorAsString(value)).toString() + ");" + System.getProperty("line.separator") + selectObjects.get(key);
            }
            return selectObjects.get(key);
        } catch (Exception ex) {
            throw new UnableToGenerateSelectObjectException();
        }
    }

    public String isMultiple(String locator) {
        return selectObjectID(locator) + ".isMultiple()";
    }

    public String getOptions(String locator) {
        return selectObjectID(locator) + ".getOptions()";
    }

    public String getAllSelectedOptions(String locator) {
        return selectObjectID(locator) + ".getAllSelectedOptions()";
    }

    public String getFirstSelectedOption(String locator) {
        return selectObjectID(locator) + ".getFirstSelectedOption()";
    }

    public String selectByVisibleText(String locator, String text) {
        return selectObjectID(locator) + ".selectByVisibleText(\"" + text + "\")";
    }

    public String selectByIndex(String locator, String indexNumber) {
        return selectObjectID(locator) + ".selectByIndex(\"" + indexNumber + "\")";
    }

    public String selectByValue(String locator, String value) {
        return selectObjectID(locator) + ".selectByValue(\"" + value + "\")";
    }

    public String deselectAll(String locator) {
        return selectObjectID(locator) + ".deselectAll()";
    }

    public String deselectByValue(String locator, String value) {
        return selectObjectID(locator) + ".deselectByValue(\"" + value + "\")";
    }

    public String deselectByIndex(String locator, String indexNumber) {
        return selectObjectID(locator) + ".deselectByIndex(\"" + indexNumber + "\")";
    }

    public String deselectByVisibleText(String locator, String text) {
        return selectObjectID(locator) + ".deselectByVisibleText(\"" + text + "\")";
    }
}
