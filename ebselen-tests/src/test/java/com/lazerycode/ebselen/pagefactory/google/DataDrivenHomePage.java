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

package com.lazerycode.ebselen.pagefactory.google;

import com.lazerycode.ebselen.EbselenTestBase;
import com.lazerycode.ebselen.customhandlers.ExcelHandler;
import com.lazerycode.ebselen.handlers.LocatorHandler;
import jxl.Cell;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.HashMap;

public class DataDrivenHomePage extends EbselenTestBase {

    private final File excelFile;
    private final ExcelHandler testLocators;
    private LocatorHandler loc = new LocatorHandler();
    private HashMap<String, Cell> webElementMapping;

    public DataDrivenHomePage() throws Exception {
        excelFile = new File(this.getClass().getResource("/GoogleLocators.xls").toURI());
        testLocators = new ExcelHandler(excelFile);
        testLocators.selectSheet("Locators");
        webElementMapping = testLocators.mapTwoColumns(1, 2, true);
    }

    public WebElement returnWebElement(String dataDrivenKey) {
        return driver.findElement(loc.autoLocator(webElementMapping.get(dataDrivenKey).getContents()));
    }

    /**
     * Perform a Google search
     *
     * @param searchString Search query
     */
    public void searchFor(String searchString) {
        returnWebElement("searchTerms").clear();
        returnWebElement("searchTerms").sendKeys(searchString);
        returnWebElement("searchTerms").submit();
    }
}
