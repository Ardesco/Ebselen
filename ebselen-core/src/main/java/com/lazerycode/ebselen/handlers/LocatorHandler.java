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

package com.lazerycode.ebselen.handlers;

import org.openqa.selenium.By;

public class LocatorHandler {

    public enum locatorType {

        CLASS_NAME, CSS_SELECTOR, XPATH, ID, LINK_TEXT, NAME, PARTIAL_LINK_TEXT, TAG_NAME, DOM
    }

    /**
     * Return the raw code to search for a specific locator as a String.
     *
     * @param locator
     * @return
     */
    public final String locatorAsString(String locator) {
        String[] locatorParts = autoLocator(locator).toString().split(":", 2);
        String locatorType = locatorParts[0].trim();
        String locatorText = locatorParts[1].trim();
        if (locatorType.equals("By.selector")) {
            locatorType = "By.cssSelector";
        }
        return locatorType + "(\"" + locatorText + "\")";
    }

    /**
     * Work out the locator type based upon the start of the locator text
     *
     * @param locator - Locator to scan to work out what sort of locator it is
     * @return locatorType - The type of locator
     */
    public final locatorType getLocatorType(String locator) {
        if (locator.startsWith("xpath") || locator.startsWith("//") || locator.startsWith("(//")) {
            return locatorType.XPATH;
        } else if (locator.startsWith("dom")) {
            return locatorType.DOM;
        } else if (locator.startsWith("css=") || locator.startsWith("#") || locator.startsWith(".")) {
            return locatorType.CSS_SELECTOR;
        } else if (locator.startsWith("name=")) {
            return locatorType.NAME;
        } else if (locator.startsWith("class=")) {
            return locatorType.CLASS_NAME;
        } else if (locator.startsWith("tag=")) {
            //Not currently Supported by IDE
            return locatorType.TAG_NAME;
        } else if (locator.startsWith("link=")) {
            return locatorType.LINK_TEXT;
        } else {
            return locatorType.ID;
        }
    }

    /**
     * Take a locator and work out what sort of By.xxx should be used
     *
     * @param locator - locator to evaluate
     * @return By - a By format that sky.sns.selenium 2 can utilise
     */
    public By autoLocator(String locator) {
        switch (getLocatorType(locator)) {
            case XPATH:
                if (locator.startsWith("xpath=")) {
                    locator = locator.split("=", 2)[1];
                }
                return By.xpath(locator);
            case CSS_SELECTOR:
                if (locator.startsWith("css=")) {
                    locator = locator.split("=", 2)[1];
                }
                return By.cssSelector(locator);
            case ID:
                if (locator.startsWith("id=")) {
                    locator = locator.split("=", 2)[1];
                }
                return By.id(locator);
            case NAME:
                if (locator.startsWith("name=")) {
                    locator = locator.split("=", 2)[1];
                }
                return By.name(locator);
            case CLASS_NAME:
                if (locator.startsWith("class=")) {
                    locator = locator.split("=", 2)[1];
                }
                return By.className(locator);
            case TAG_NAME:
                if (locator.startsWith("tag=")) {
                    locator = locator.split("=", 2)[1];
                }
                return By.tagName(locator);
            case LINK_TEXT:
                if (locator.startsWith("link=")) {
                    locator = locator.split("=", 2)[1];
                }
                return By.linkText(locator);
            default:
                if (locator.startsWith("dom:name=")) {
                    locator = locator.split("=", 2)[1];
                    return By.xpath("//form[@name='" + locator + "']");
                } else if (locator.startsWith("dom:index=")) {
                    locator = locator.split("=", 2)[1];
                    return By.xpath("(//form)[" + locator + "]");
                }
        }
        return null;
    }
}