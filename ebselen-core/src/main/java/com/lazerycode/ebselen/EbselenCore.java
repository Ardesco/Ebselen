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

/**
 * This class is the Core class for all tests.
 * You should not need to make any changes to this document
 * If you must make changes, please be aware of the implications your changes.
 *
 * This implementation is only designed to support a single browser object at the moment.
 */

import com.lazerycode.ebselen.globals.GlobalSettings;
import com.thoughtworks.selenium.Selenium;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.htmlunit.*;
import org.openqa.selenium.ie.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import com.lazerycode.ebselen.customhandlers.handler;
import com.lazerycode.ebselen.BrowserConfiguration.htmlUnitEmulation;
import com.lazerycode.ebselen.BrowserConfiguration.selectedBrowser;
import com.opera.core.systems.OperaDriver;


import org.openqa.selenium.android.AndroidDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.iphone.IPhoneDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class EbselenCore implements EbselenConfiguration {

    public static GlobalSettings settings = new GlobalSettings();
    public static final Logger logger = LoggerFactory.getLogger(EbselenCore.class);
    private BrowserConfiguration browserDetails;
    // Selenium Objects
    protected WebDriver driver;
    protected EbselenCommands ebselen;
    protected Actions builder;

    public EbselenCore() {
        browserDetails = new BrowserConfiguration();
    }

    public EbselenCore(BrowserConfiguration browser) {
        browserDetails = browser;
    }

    public WebDriver getDriverObject() {
        return driver;
    }

    public EbselenCommands getEbselenCommandsObject() {
        return ebselen;
    }

    public Actions getActionsBuilderObject() {
        return builder;
    }


    /**
     * Start the WebDriver Instance
     */
    public void startSelenium() {
        if (driver != null) {
            logger.error("There appears to be an existing driver instance.. Details are: {}", driver);
            logger.error("Shutting down existing instance and starting up again...");
            stopSelenium(driver);
        }
        driver = setBrowser(driver);
        ebselen = new EbselenCommands(driver);
//        mouse = ((HasInputDevices) driver).getMouse();
//        keyboard = ((HasInputDevices) driver).getKeyboard();
        builder = new Actions(driver);
    }

    private DesiredCapabilities setHTMLUnitCapabilities(htmlUnitEmulation browserVersion) {
        DesiredCapabilities capabilities = DesiredCapabilities.htmlUnit();
        capabilities.setJavascriptEnabled(true);
        switch (browserVersion) {
            case FIREFOX:
                capabilities.setBrowserName("firefox");
                break;
            case IE6:
                capabilities.setBrowserName("internet explorer");
                capabilities.setVersion("6");
                break;
            case IE7:
                capabilities.setBrowserName("internet explorer");
                capabilities.setVersion("7");
                break;
            case IE8:
                capabilities.setBrowserName("internet explorer");
                capabilities.setVersion("8");
                break;
            default:
                break;
        }
        return capabilities;
    }

    /**
     * Set the driver type based upon settings scraped from Env.properties
     * run function to get release number of website being tested
     *
     * @param driverObject - object to instantiate
     * @return WebDriver
     */
    public WebDriver setBrowser(WebDriver driverObject) {
        try {
            logger.debug("Set UploadDir: {}", settings.uploadDirectory());
            logger.debug("Set TempDir: {}", settings.tempDirectory());
            switch (browserDetails.getBrowser()) {
                case FIREFOX:
                    driverObject = new FirefoxDriver(generateFirefoxProfile());
                    logger.debug("Using FIREFOX Driver...");
                    break;
                case IE6:
                case IE7:
                case IE8:
                case IE9:
                    driverObject = new InternetExplorerDriver();
                    logger.debug("Using INTERNET EXPLORER Driver...");
                    break;
                case GOOGLECHROME:
                    System.setProperty("webdriver.chrome.driver", settings.chromeDriverLocation());
                    driverObject = new ChromeDriver();
                    logger.debug("Using GOOGLECHROME Driver...");
                    break;
                case HTMLUNIT:
                    driverObject = new HtmlUnitDriver(setHTMLUnitCapabilities(browserDetails.getHTMLUnitEmulation()));
                    logger.debug("Using HTMLUNIT Driver...");
                    break;
                case SAFARI:
                    //FUTURE
                    break;
                case OPERA:
                    driverObject = new OperaDriver();
                    logger.debug("Using Opera Driver...");
                    break;
                case IPHONE:
                    driverObject = new IPhoneDriver();
                    logger.debug("Using IPhone Driver...");
                    break;
                case ANDROID:
                    driverObject = new AndroidDriver();
                    logger.debug("Using Android Driver...");
                    break;
            }
            getReleaseVersion();
        } catch (Exception x) {
            logger.error("Error in EbselenCore.setBrowser: {}", x.getMessage());
            return driverObject;
        }
        return driverObject;
    }

    /**
     * Configure the FireFox profile if using FireFox Driver
     *
     * @return FirefoxProfile
     */
    private FirefoxProfile generateFirefoxProfile() {
        FirefoxProfile prf = new FirefoxProfile();
        prf.setPreference("dom.max_chrome_script_run_time", 60);
        prf.setPreference("setTimeoutInSeconds", 60);
        prf.setPreference("dom.max_script_run_time", 60);
        prf.setPreference("dom.popup_maximum", 0);
        prf.setPreference("privacy.popups.disable_from_plugins", 3);
        prf.setPreference("browser.xul.error_pages.enabled", false);
        prf.setPreference("general.useragent.extra.firefox", "Firefox");
        return (prf);
    }

    /**
     * Shut down any browser instances still open now that tests have finished
     *
     * @param driverObject - driver object to stop
     */
    public void stopSelenium(WebDriver driverObject) {
        try {
            if (driverObject != null) {
                try {
                    driverObject.quit();
                } catch (Exception x) {
                    logger.error("Did not manage to quit driver object cleanly: {}", x.getMessage());
                }
                driverObject = null;
            }
        } catch (Exception x) {
            logger.error("Error Quitting Browser: {}", x.getMessage());
            logger.error("Killing Selenium!");
            Runtime.getRuntime().halt(1);
        }
    }

    private void getReleaseVersion() {
        //FIXME Get the version number of site in test (Value in meta tag, value in version.txt, etc)
        logger.warn("You need to implement a function that will obtain the version of the site you are testing");
    }
}