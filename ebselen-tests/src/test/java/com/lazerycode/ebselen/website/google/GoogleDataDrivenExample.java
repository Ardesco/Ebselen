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

package com.lazerycode.ebselen.website.google;

import com.lazerycode.ebselen.EbselenTestBase;
import com.lazerycode.ebselen.SeleniumTestAnnotations.*;
import com.lazerycode.ebselen.customhandlers.ExcelHandler;
import com.lazerycode.ebselen.pagefactory.google.HomePage;
import jxl.Cell;
import org.openqa.selenium.support.PageFactory;

import javax.naming.directory.SearchControls;
import java.io.File;
import java.net.URI;
import java.net.URL;

/**
 * This test will perform the following actions:
 * <p/>
 * <ul>
 * <li>Open the Google homepage</li>
 * <li>Perform a search</li>
 * </ul>
 */
@TestSuiteStatus(suiteStatus.UNDER_CONSTRUCTION)
@TestAuthor("Mark")
@TestStoriesCovered({"1", "2", "3"})
public class GoogleDataDrivenExample extends EbselenTestBase {

    static HomePage google;

    public GoogleDataDrivenExample() throws Exception {
        google = PageFactory.initElements(driver, HomePage.class);
    }

    @SeleniumTest
    @Order(1)
    public void openSiteHomepage() throws Exception {
        ebselen.openHomepage("http://www.google.co.uk");
    }

    @SeleniumTest
    @Order(2)
    public void searchGoogle() throws Exception {
        //Pulling the file out of the maven resources directory held in the class path (Maven magic)
        File excelFile = new File(new URI(this.getClass().getResource("/DataDriven.xls").toExternalForm()));
        ExcelHandler searchData = new ExcelHandler(excelFile);
        searchData.selectSheet("Search Terms");
        for(Cell searchTerm : searchData.getColumn(1 ,true).values()){
            google.searchFor(searchTerm.getContents());
        }
    }
}