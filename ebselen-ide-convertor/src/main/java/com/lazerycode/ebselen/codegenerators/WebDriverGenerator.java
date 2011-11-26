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

import java.util.concurrent.TimeUnit;

public class WebDriverGenerator {

    private String driverObject;

    public WebDriverGenerator(String value) {
        this.driverObject = value;
    }

    public String get(String value) {
        return driverObject + ".get(\"" + value + "\")";
    }

    public String close() {
        return driverObject + ".close()";
    }

    public String getCurrentUrl() {
        return driverObject + ".getCurrentUrl()";
    }

    public String getTitle() {
        return driverObject + ".getTitle()";
    }

    public String getPageSource() {
        return driverObject + ".getPageSource()";
    }

    public String getWindowHandles() {
        return driverObject + ".getWindowHandles()";
    }

    public String getWindowHandle() {
        return driverObject + ".getWindowHandle()";
    }

    public WebElementGenerator findElement(String value) {
        return new WebElementGenerator(driverObject + ".findElement", value);
    }

    public WebElementListGenerator findElements(String value) {
        return new WebElementListGenerator(driverObject + ".findElements", value);
    }

    public Options manage() {
        return new Options();
    }

    public class Options {
        public String addCookie(String value) {
            return driverObject + ".manage().addCookie(\"" + value + "\")";
        }

        public String deleteCookieNamed(String value) {
            return driverObject + ".manage().deleteCookieNamed(\"" + value + "\")";
        }

        public String deleteCookie(String value) {
            return driverObject + ".manage().deleteCookie(\"" + value + "\")";
        }

        public String deleteAllCookies() {
            return driverObject + ".manage().deleteAllCookies()";
        }

        public String getCookies() {
            return driverObject + ".manage().getCookies()";
        }

        public String getCookieNamed(String value) {
            return driverObject + ".manage().getCookieNamed(\"" + value + "\")";
        }

        public Timeouts timeouts() {
            return new Timeouts();
        }

        public class Timeouts {
            public String implicitlyWait(long time, TimeUnit timeunit) {
                return driverObject + ".manage().timeouts().implicitlyWait(" + time + ", TimeUnit." + timeunit.name() + ")";
            }

            public String setScriptTimeout(long time, TimeUnit timeunit) {
                return driverObject + ".manage().timeouts().setScriptTimeout(" + time + ", TimeUnit." + timeunit.name() + ")";
            }
        }
    }

    public TargetLocator switchTo() {
        return new TargetLocator();
    }

    public class TargetLocator {

        public String frame(String value) {
            return driverObject + ".switchTo().frame(\"" + value + "\")";
        }

        public String window(String value) {
            return driverObject + ".switchTo().window(\"" + value + "\")";
        }

        public String defaultContent() {
            return driverObject + ".switchTo().defaultContent()";
        }

        public String activeElement() {
            return driverObject + ".switchTo().activeElement()";
        }

        public Alert alert() {
            return new Alert();
        }

        public class Alert {

            public String accept() {
                return driverObject + ".switchTo().alert().accept()";
            }

            public String dismiss() {
                return driverObject + ".switchTo().alert().dismiss()";
            }

            public String getText() {
                return driverObject + ".switchTo().alert().getText()";
            }

            public String sendKeys(String value) {
                return driverObject + ".switchTo().alert().sendKeys(\"" + value + "\")";
            }
        }
    }

    public Navigation navigate() {
        return new Navigation();
    }

    public class Navigation {
        public String back() {
            return driverObject + ".navigate().back()";
        }

        public String forward() {
            return driverObject + ".navigate().forward()";
        }

        public String to(String value) {
            return driverObject + ".navigate().to(\"" + value + "\")";
        }

        public String refresh() {
            return driverObject + ".navigate().refresh()";
        }
    }
}