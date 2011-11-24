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
