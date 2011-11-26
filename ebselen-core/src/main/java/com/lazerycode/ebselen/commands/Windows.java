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
