package com.lazerycode.ebselen.website.google;

import com.lazerycode.ebselen.EbselenTestBase;
import com.lazerycode.ebselen.SeleniumTestAnnotations.suiteStatus;
import com.lazerycode.ebselen.SeleniumTestAnnotations.TestSuiteStatus;
import com.lazerycode.ebselen.SeleniumTestAnnotations.TestAuthor;
import com.lazerycode.ebselen.SeleniumTestAnnotations.TestStoriesCovered;
import com.lazerycode.ebselen.SeleniumTestAnnotations.SeleniumTest;
import com.lazerycode.ebselen.SeleniumTestAnnotations.Order;
import com.lazerycode.ebselen.pagefactory.google.DataDrivenHomePage;
import com.lazerycode.ebselen.pagefactory.google.HomePage;


import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.PageFactory;

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
public class GoogleExampleAltPageObject extends EbselenTestBase {

    static DataDrivenHomePage google;

    public GoogleExampleAltPageObject() throws Exception {
        google = new DataDrivenHomePage();
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