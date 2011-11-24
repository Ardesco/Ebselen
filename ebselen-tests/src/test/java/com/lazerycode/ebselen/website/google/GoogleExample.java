/*
Copyright 2010-2012 Ardesco Solutions - http://www.ardescosolutions.com

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package com.lazerycode.ebselen.website.google;

import com.lazerycode.ebselen.EbselenConfiguration.selectSite;
import com.lazerycode.ebselen.EbselenTestBase;
import com.lazerycode.ebselen.SeleniumTestAnnotations.suiteStatus;
import com.lazerycode.ebselen.SeleniumTestAnnotations.TestSuiteStatus;
import com.lazerycode.ebselen.SeleniumTestAnnotations.TestAuthor;
import com.lazerycode.ebselen.SeleniumTestAnnotations.TestStoriesCovered;
import com.lazerycode.ebselen.SeleniumTestAnnotations.SeleniumTest;
import com.lazerycode.ebselen.SeleniumTestAnnotations.Order;
import com.lazerycode.ebselen.google.HomePage;


import org.openqa.selenium.support.PageFactory;

/**
 * This test will perform the following actions:
 * <p/>
 * <ul>
 * <li>Open the Google homepage</li>
 * <li>Perform a search</li>
 * <li>Check that we find the desired result</li>
 * </ul>
 */
@TestSuiteStatus(suiteStatus.UNDER_CONSTRUCTION)
@TestAuthor("Mark")
@TestStoriesCovered({"1", "2", "3"})
public class GoogleExample extends EbselenTestBase {

    static HomePage google;

    public GoogleExample() throws Exception {
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
        String searchTerm = "Test Search";
        google.searchFor(searchTerm);
    }
}