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

import com.lazerycode.ebselen.globals.GlobalSettings;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage {

    public static GlobalSettings settings = new GlobalSettings();
    @FindBy(how = How.XPATH, using = "//input[@id='lst-ib']")
    private WebElement searchTerms;
    @FindBy(how = How.XPATH, using = "//div[@id='sblsbb']/button")
    private WebElement performSearch;

    public HomePage() {
        //Set the homepage element, this will be checked every time openHomepage is called
        //settings.setHomepageElement(performSearch);
    }

    /**
     * Perform a Google search
     *
     * @param searchString Search query
     */
    public void searchFor(String searchString) {
        searchTerms.clear();
        searchTerms.sendKeys(searchString);
        performSearch.click();
    }
}
