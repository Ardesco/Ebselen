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

package com.lazerycode.ebselen.commands;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by IntelliJ IDEA.
 * User: Mark Collin
 * Date: 24/11/11
 * Time: 11:20
 * To change this template use File | Settings | File Templates.
 */
public interface Element {
    /**
     * Find out if an element exists or not.
     *
     * @param locator - A By locator
     * @return boolean  - True if element is found, otherwise false.
     * @throws Exception
     */
    boolean doesElementExist(By locator);

    /**
     * Find out if an element exists or not.
     *
     * @param element - A By locator
     * @return boolean  - True if element is found, otherwise false.
     * @throws Exception
     */
    boolean isElementStale(WebElement element);

    /**
     * Function to enable us to find out if an element is visible or not.
     *
     * @param locator - A By locator
     * @return boolean  - True if element is displayed, otherwise false.
     * @throws Exception
     */
    boolean isElementDisplayed(By locator);

    /**
     * Gets the element count for a given locator
     *
     * @param locator - Locator to use for count
     * @return int - Number of instances of locator
     */
    int getElementCount(String locator);

    /**
     * Check a checkbox
     *
     * @param locator - Locator of checkbox that you want to check
     */
    void check(String locator);

    /**
     * Uncheck a checkbox
     *
     * @param locator - Locator of checkbox that you want to uncheck
     */
    void uncheck(String locator);

    /**
     * Check to see if a checkbox is checked or not
     *
     * @param locator - Locator of checkbox that you want to check
     * @return boolean True or false
     */
    boolean isChecked(String locator);
}
