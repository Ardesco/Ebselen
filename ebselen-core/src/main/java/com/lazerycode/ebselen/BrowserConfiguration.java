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

import com.lazerycode.ebselen.exceptions.UnknownBrowserException;

import java.io.File;

public class BrowserConfiguration {

    public enum selectedBrowser {

        FIREFOX, IE6, IE7, IE8, IE9, SAFARI, OPERA, GOOGLECHROME, ANDROID, IPHONE, HTMLUNIT
    }

    ;

    public enum htmlUnitEmulation {

        NONE, FIREFOX, IE6, IE7, IE8
    }

    private selectedBrowser browserSetting;
    private htmlUnitEmulation emulationSetting;
    private String chromeDriverLocation;

    public BrowserConfiguration() {
        setBrowser(selectedBrowser.HTMLUNIT);
        setHTMLUnitEmulation(htmlUnitEmulation.NONE);
    }

    public BrowserConfiguration(String browser) {
        setBrowser(browser);
        setHTMLUnitEmulation(htmlUnitEmulation.NONE);
    }

    public BrowserConfiguration(String browser, String htmlUnitEmulation) {
        setBrowser(browser);
        setHTMLUnitEmulation(htmlUnitEmulation);
    }

    /**
     * Set selected browser via Enum
     *
     * @param value
     */
    public void setBrowser(selectedBrowser value) {
        this.browserSetting = value;
    }

    /**
     * Set selected browser
     * If emulation type is not recognised it will default to none.
     *
     * @param value
     */
    public void setBrowser(String value) {
        for (selectedBrowser browser : selectedBrowser.values()) {
            if (browser.toString().toLowerCase().equals(value.toLowerCase())) {
                setBrowser(browser);
                return;
            }
        }
        throw new UnknownBrowserException("'" + value + "' is an unknown browser type!");
    }

    /**
     * Return selected browser as a selectedBrowser value
     *
     * @return
     */
    public selectedBrowser getBrowser() {
        return this.browserSetting;
    }

    /**
     * Set HTMLUnit emulation via Enum
     *
     * @param value
     */
    public void setHTMLUnitEmulation(htmlUnitEmulation value) {
        this.emulationSetting = value;
    }

    /**
     * Set HTMLUnit emulation.
     * If emulation type is not recognised it will default to none.
     *
     * @param value
     */
    public void setHTMLUnitEmulation(String value) {
        for (htmlUnitEmulation emulation : htmlUnitEmulation.values()) {
            if (emulation.toString().toLowerCase().equals(value.toLowerCase())) {
                setHTMLUnitEmulation(emulation);
                return;
            }
        }
        setHTMLUnitEmulation(htmlUnitEmulation.NONE);
    }

    /**
     * Return HTMLEmulation as a htmlUnitEmulation value
     *
     * @return
     */
    public htmlUnitEmulation getHTMLUnitEmulation() {
        return this.emulationSetting;
    }

    /**
     * Set the location of the chromedriver binaries
     *
     * @param value
     */
    public void setChromeDriverLocation(String value) {
        this.chromeDriverLocation = value.replaceAll("/", File.separator);//.replaceAll("\\", File.separator);
    }

    /**
     * Get the location of the chrome driver binaries
     *
     * @return
     */
    public String getChromeDriverLocation() {
        return this.chromeDriverLocation;
    }
}
