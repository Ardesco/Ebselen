package com.lazerycode.ebselen.commands;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: Mark Collin
 * Date: 24/11/11
 * Time: 11:19
 * To change this template use File | Settings | File Templates.
 */
public interface Windows {
    /**
     * This function will switch to the next window if you currently have two windows.
     * If you have more or less than two windows this will return a false.
     *
     * @return Boolean - True if successful, otherwise false
     * @throws Exception
     */
    Boolean switchBetweenWindows() throws Exception;

    /**
     * Shortcut for driver.switchTo().window(windowTitle) which will return a boolean you can assert/verify on
     *
     * @param windowTitle - Title of window you want to try and switch to
     * @return Boolean - True if successful, otherwise false
     * @throws Exception
     */
    Boolean switchToWindowTitled(String windowTitle) throws Exception;

    /**
     * Take a screenshot of the current window.
     *
     * @return
     */
    File takeScreenshot();
}
