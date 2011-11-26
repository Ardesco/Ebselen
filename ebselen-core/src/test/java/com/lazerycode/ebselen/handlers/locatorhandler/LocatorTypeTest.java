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

package com.lazerycode.ebselen.handlers.locatorhandler;

import com.lazerycode.ebselen.handlers.LocatorHandler;
import com.lazerycode.ebselen.handlers.LocatorHandler.locatorType;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class LocatorTypeTest {

    private LocatorHandler loc = new LocatorHandler();

    public List<String> listOfLocators(String[] locators) {
        return Arrays.asList(locators);
    }

    @Test
    public void givenAXPathLocatorCodeReturnsCorrectType() throws Exception {
        for (String XPathLocator : listOfLocators(new String[]{"xpath=//div", "//div", "(//div)[1]"})) {
            assertThat(loc.getLocatorType(XPathLocator), is(equalTo(locatorType.XPATH)));
        }
    }

    @Test
    public void givenADOMLocatorCodeReturnsCorrectType() throws Exception {
        assertThat(loc.getLocatorType("dom=document.getElementsByTagName('span');"), is(equalTo(locatorType.DOM)));
    }

    @Test
    public void givenACSSLocatorCodeReturnsCorrectType() throws Exception {
        for (String CSSLocator : listOfLocators(new String[]{"css=#foo", "#foo", ".bar"})) {
            assertThat(loc.getLocatorType(CSSLocator), is(equalTo(locatorType.CSS_SELECTOR)));
        }
    }

    @Test
    public void givenAClassLocatorCodeReturnsCorrectType() throws Exception {
        assertThat(loc.getLocatorType("class=foo"), is(equalTo(locatorType.CLASS_NAME)));
    }

    @Test
    public void givenAIDLocatorCodeReturnsCorrectType() throws Exception {
        for (String IDLocator : listOfLocators(new String[]{"lst-lib", "foo$bar", "id=lst-lib"})) {
            assertThat(loc.getLocatorType(IDLocator), is(equalTo(locatorType.ID)));
        }
    }

    @Test
    public void givenALinkTextLocatorCodeReturnsCorrectType() throws Exception {
        assertThat(loc.getLocatorType("link=home"), is(equalTo(locatorType.LINK_TEXT)));
    }

    @Test
    public void givenATagNameLocatorCodeReturnsCorrectType() throws Exception {
        assertThat(loc.getLocatorType("tag=div"), is(equalTo(locatorType.TAG_NAME)));
    }

    @Test
    public void givenANameLocatorCodeReturnsCorrectType() throws Exception {
        assertThat(loc.getLocatorType("name=foo"), is(equalTo(locatorType.NAME)));
    }
}
