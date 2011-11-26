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
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class LocatorAsStringTest {

    private LocatorHandler loc = new LocatorHandler();

    public List<String> listOfLocators(String[] locators) {
        return Arrays.asList(locators);
    }

    @Test
    public void givenAXPathLocatorCodeReturnsCorrectType() throws Exception {
        for (String XPathLocator : listOfLocators(new String[]{"xpath=//div", "//div", "(//div)[1]"})) {
            String[] result = XPathLocator.split("=");
            assertThat(loc.locatorAsString(XPathLocator), is(equalTo("By.xpath(\"" + result[result.length - 1] + "\")")));
        }
    }

    @Test
    @Ignore
    // TODO Remove DOM support?  Could be anything coming in...
    public void givenADOMLocatorCodeReturnsCorrectType() throws Exception {
        assertThat(loc.locatorAsString("dom=document.getElementsByTagName('span');"), is(equalTo("By.xpath(\"dom=document.getElementsByTagName('span');\")")));
    }

    @Test
    public void givenACSSLocatorCodeReturnsCorrectType() throws Exception {
        for (String CSSLocator : listOfLocators(new String[]{"css=#foo", "#foo", ".bar"})) {
            String[] result = CSSLocator.split("=");
            assertThat(loc.locatorAsString(CSSLocator), is(equalTo("By.cssSelector(\"" + result[result.length - 1] + "\")")));
        }
    }

    @Test
    public void givenAClassLocatorCodeReturnsCorrectType() throws Exception {
        assertThat(loc.locatorAsString("class=foo"), is(equalTo("By.className(\"foo\")")));
    }

    @Test
    public void givenAIDLocatorCodeReturnsCorrectType() throws Exception {
        for (String IDLocator : listOfLocators(new String[]{"lst-lib", "foo$bar", "id=lst-lib"})) {
            String[] result = IDLocator.split("=");
            assertThat(loc.locatorAsString(IDLocator), is(equalTo("By.id(\"" + result[result.length - 1] + "\")")));
        }
    }

    @Test
    public void givenALinkTextLocatorCodeReturnsCorrectType() throws Exception {
        assertThat(loc.locatorAsString("link=home"), is(equalTo("By.linkText(\"home\")")));
    }

    @Test
    public void givenATagNameLocatorCodeReturnsCorrectType() throws Exception {
        assertThat(loc.locatorAsString("tag=div"), is(equalTo("By.tagName(\"div\")")));
    }

    @Test
    public void givenANameLocatorCodeReturnsCorrectType() throws Exception {
        assertThat(loc.locatorAsString("name=foo"), is(equalTo("By.name(\"foo\")")));
    }
}
