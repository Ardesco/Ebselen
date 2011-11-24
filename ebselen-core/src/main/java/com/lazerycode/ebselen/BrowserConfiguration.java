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
