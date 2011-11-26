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

package com.lazerycode.ebselen.codegenerators;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class WebElementListGeneratorTest {

    private WebDriverGenerator driver = new WebDriverGenerator("driver");
    private String locator = "By.id(\"foo\")";

    @Test
    public void checkWebElementListSizeReturnsCorrectCode() {
        assertThat(driver.findElements(locator).size(), is(equalTo("driver.findElements(" + locator + ").size()")));
    }

    @Test
    public void checkWebElementListReturnsCorrectCode() {
        assertThat(driver.findElements(locator).toString(), is(equalTo("driver.findElements(" + locator + ")")));
    }
}
