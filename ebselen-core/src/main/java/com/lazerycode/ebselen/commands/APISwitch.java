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

import com.thoughtworks.selenium.Selenium;

/**
 * Created by IntelliJ IDEA.
 * User: Mark Collin
 * Date: 24/11/11
 * Time: 11:18
 * To change this template use File | Settings | File Templates.
 */
public interface APISwitch {
    /**
     * Creates a Selenium Instance from the driver instance to enable the calling of Selenium 1 Native Commands.
     * To revert to Selenium 2 commands, call switchBackToWebdriver after using the Seleniunm
     * object.
     */
    Selenium switchToSelenium(String homepageURL);

    /**
     * Resets the Selenium instance back to the original driver instance.
     * called after a switchToSelenium() call
     */
    void switchBackToWebdriver(Selenium seleniumObject);
}
