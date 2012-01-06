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

package com.lazerycode.ebselen.website.osu;

import com.lazerycode.ebselen.EbselenTestBase;
import com.lazerycode.ebselen.SeleniumTestAnnotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

@TestSuiteStatus(suiteStatus.UNDER_CONSTRUCTION)
@TestAuthor("Mark")
@TestStoriesCovered({"0"})
public class ClassSearch extends EbselenTestBase {

    @SeleniumTest
    @Order(1)
    public void openSiteHomepage() throws Exception {
        ebselen.openHomepage("https://courses.osu.edu");
        ebselen.waitFor(30000).untilWebElement(By.id("fldra_CO_EMPLOYEE_SELF_SERVICE")).exists();
    }

    @SeleniumTest
    @Order(2)
    public void navigateToSearchPage() throws Exception {
        driver.findElement(By.id("fldra_CO_EMPLOYEE_SELF_SERVICE")).click();
        ebselen.waitFor(30000).untilWebElement(By.xpath("//li[@id='crefli_HC_CLASS_SEARCH_GBL']/a")).exists();
        driver.findElement(By.xpath("//li[@id='crefli_HC_CLASS_SEARCH_GBL']/a")).click();
    }

    @SeleniumTest
    @Order(3)
    public void performClassSearch() throws Exception {
        //Select right frame
        WebElement classSearchFrame = driver.findElement(By.id("ptifrmtgtframe"));
        driver.switchTo().frame(classSearchFrame);
        //Perform Search
        WebElement termElement = driver.findElement(By.id("CLASS_SRCH_WRK2_STRM$48$"));
        Select term = new Select(termElement);
        term.selectByValue("1118");
        WebElement goButton = driver.findElement(By.id("CLASS_SRCH_WRK2_SSR_PB_SRCH$56$"));
        goButton.click();
    }
}