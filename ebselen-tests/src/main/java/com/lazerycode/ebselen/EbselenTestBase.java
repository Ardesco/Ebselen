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

package com.lazerycode.ebselen;

import com.lazerycode.ebselen.globals.GlobalSettings;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import com.thoughtworks.selenium.SeleneseTestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(SeleniumJUnitRunner.class)
public class EbselenTestBase extends SeleneseTestBase {

    public static GlobalSettings settings = new GlobalSettings();
    public static final Logger LOGGER = LoggerFactory.getLogger(EbselenCore.class);
    private static EbselenCore ebselenCoreInstance = new EbselenCore();
    public static WebDriver driver;
    public static EbselenCommands ebselen;
    public static Actions builder;


    /**
     * Display message if verification fails
     *
     * @param message - Message to print to error stream if boolean is false
     * @param isTrue  - Boolean to evaluate
     */
    public void verifyTrue(String message, boolean isTrue) {
        if (!isTrue) {
            LOGGER.error("{}", message);
        }
        verifyFalse(isTrue);
    }

    /**
     * Display message if verification fails
     *
     * @param message - Message to print to error stream if boolean is true
     * @param isTrue  - Boolean to evaluate
     */
    public void verifyFalse(String message, boolean isTrue) {
        if (isTrue) {
            LOGGER.error("{}", message);
        }
        verifyFalse(isTrue);
    }

    /**
     * Stop the standard test setUp from being processed as we only want to start the browser at the start of the suite
     */
    @Override
    @Before
    public void setUp() {
    }

    /**
     * Stop the standard test tearDown from being processed as we only want to stop the browser at the end of the suite
     */
    @Override
    @After
    public void tearDown() {
    }

    /**
     * Only start the browser the first time that this is run (The browser should remain open for the entire test suite)
     */
    @BeforeClass
    public static void StartBrowser() {
        ebselenCoreInstance.startSelenium();
        driver = ebselenCoreInstance.getDriverObject();
        ebselen = ebselenCoreInstance.getEbselenCommandsObject();
        builder = ebselenCoreInstance.getActionsBuilderObject();
    }

    /**
     * Only close the browser if all tests in the suite have been run
     */
    @AfterClass
    public static void tearClassDown() {
        ebselenCoreInstance.stopSelenium(driver);
    }
}
