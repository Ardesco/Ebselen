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

public class EbselenGenerator {

    public String ebselenObject;

    public EbselenGenerator(String value) {
        this.ebselenObject = value;
    }

    public Windows window() {
        return new Windows();
    }

    public class Windows {

        public String switchBetweenWindows() throws Exception {
            return ebselenObject + ".window().switchBetweenWindows()";
        }

        public String switchToWindowTitled(String windowTitle) throws Exception {
            return ebselenObject + ".window().switchToWindowTitled(\"" + windowTitle + "\")";
        }

        public String takeScreenshot() {
            return ebselenObject + ".window().takeScreenshot()";
        }
    }

    public JavaScript javascript() {
        return new JavaScript();
    }

    public class JavaScript {

        public String triggerJavascriptEvent(String jsEvent, String WebElement) {
            return ebselenObject + ".javascript().triggerJavascriptEvent(\"" + jsEvent + ", " + WebElement + "\")";
        }
    }

    public Element element() {
        return new Element();
    }

    public class Element {

        public String doesElementExist(String byLocator) {
            return ebselenObject + ".element().doesElementExist(" + byLocator + ")";
        }

        public String isElementDisplayed(String byLocator) {
            return ebselenObject + ".element().isElementDisplayed(" + byLocator + ")";
        }

        public String getElementCount(String locator) {
            return ebselenObject + ".element().getElementCount(" + locator + ")";
        }

        public String check(String locator) {
            return ebselenObject + ".element().check(\"" + locator + "\")";
        }

        public String uncheck(String locator) {
            return ebselenObject + ".element().uncheck(\"" + locator + "\")";
        }

        public String isChecked(String locator) {
            return ebselenObject + ".element().isChecked(\"" + locator + "\")";
        }
    }

    public Waiting waiting() {
        return new Waiting();
    }

    public class Waiting {

        public ForWebElements untilWebElement(String byLocator) {
            return new ForWebElements(byLocator);
        }

        public class ForWebElements {

            public String byLocator;

            public ForWebElements(String value) {
                byLocator = value;
            }

            public String exists() {
                return ebselenObject + ".waiting().untilWebElement(" + byLocator + ").exists()";
            }

            public String doesNotExist() {
                return ebselenObject + ".waiting().untilWebElement(" + byLocator + ").doesNotExist()";
            }

            public String instancesAreMoreThan(int instances) {
                return ebselenObject + ".waiting().untilWebElement(" + byLocator + ").instancesAreMoreThan(" + instances + ")";
            }

            public String instancesAreLessThan(int instances) {
                return ebselenObject + ".waiting().untilWebElement(" + byLocator + ").instancesAreLessThan(" + instances + ")";
            }

            public String instancesEqual(int instances) {
                return ebselenObject + ".waiting().untilWebElement(" + byLocator + ").instancesEqual(" + instances + ")";
            }

            public String existsAfterRefreshingPage() {
                return ebselenObject + ".waiting().untilWebElement(" + byLocator + ").existsAfterRefreshingPage()";
            }

            public String doesNotExistAfterRefreshingPage() {
                return ebselenObject + ".waiting().untilWebElement(" + byLocator + ").doesNotExistAfterRefreshingPage()";
            }

            public String instancesAreMoreThanAfterRefreshingPage(int instances) {
                return ebselenObject + ".waiting().untilWebElement(" + byLocator + ").instancesAreMoreThanAfterRefreshingPage(" + instances + ")";
            }

            public String instancesAreLessThanAfterRefreshingPage(int instances) {
                return ebselenObject + ".waiting().untilWebElement(" + byLocator + ").instancesAreLessThanAfterRefreshingPage(" + instances + ")";
            }

            public String instancesEqualAfterRefreshingPage(int instances) {
                return ebselenObject + ".waiting().untilWebElement(" + byLocator + ").instancesEqualAfterRefreshingPage(" + instances + ")";
            }
        }

    }
}
