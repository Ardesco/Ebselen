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
package com.ardescocode.ebselen.convertedfromide;

import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import com.ardescocode.ebselen.core.*;
import com.ardescocode.ebselen.customHandlers.*;
import com.ardescocode.ebselen.testrunner.SeleniumTestAnnotations.suiteStatus;
import com.ardescocode.ebselen.testrunner.SeleniumTestAnnotations.TestSuiteStatus;
import com.ardescocode.ebselen.testrunner.SeleniumTestAnnotations.TestAuthor;
import com.ardescocode.ebselen.testrunner.SeleniumTestAnnotations.TestStoriesCovered;
import com.ardescocode.ebselen.testrunner.SeleniumTestAnnotations.Order;
import com.ardescocode.ebselen.testrunner.SeleniumTestAnnotations.SeleniumTest;
import org.openqa.selenium.support.PageFactory;

/**
 * !Describe Test Here!
 */
@TestSuiteStatus(suiteStatus.CONVERTED_FROM_IDE)
@TestAuthor("")
@TestStoriesCovered({""})
public class $template extends EbselenBase {

    // Default object used to hold data stored by the IDE storeXXX commands
    public static HashMap storedVariables = new HashMap();

    @SeleniumTest
    @Order(1)
    public void $testname() throws Exception {
        $testdata
    }

}
