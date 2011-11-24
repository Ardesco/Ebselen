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
package com.lazerycode.ebselen.codegenerators;

public class WebElementGenerator {

    private String callingCommand = null;
    private String passedValue = null;

    public WebElementGenerator(String command, String value) {
        callingCommand = command;
        passedValue = value;
    }

    public WebElementGenerator(String command) {
        callingCommand = command;
    }

    public String getAttribute(String value) {
        return callingCommand + "(" + passedValue + ").getAttribute(\"" + value + "\")";
    }

    public String isSelected() {
        return callingCommand + "(" + passedValue + ").isSelected()";
    }

    public String isEnabled() {
        return callingCommand + "(" + passedValue + ").isEnabled()";
    }

    public String getText() {
        return callingCommand + "(" + passedValue + ").getText()";
    }

    public String isDisplayed() {
        return callingCommand + "(" + passedValue + ").isDisplayed()";
    }

    public GetLocation getLocation() {
        return new GetLocation();
    }

    public class GetLocation {

        public String toString() {
            return callingCommand + "(" + passedValue + ").getLocation()";
        }

        public String getX() {
            return callingCommand + "(" + passedValue + ").getLocation().getX()";
        }

        public String getY() {
            return callingCommand + "(" + passedValue + ").getLocation().getY()";
        }
    }

    public GetSize getSize() {
        return new GetSize();
    }

    public class GetSize {
        public String toString() {
            return callingCommand + "(" + passedValue + ").getSize()";
        }

        public String getWidth() {
            return callingCommand + "(" + passedValue + ").getSize().getWidth()";
        }

        public String getHeight() {
            return callingCommand + "(" + passedValue + ").getSize().getHeight()";
        }
    }

    public String click() {
        return callingCommand + "(" + passedValue + ").click()";
    }

    public String getCssValue(String value) {
        return callingCommand + "(" + passedValue + ").getCssValue(\"" + value + "\")";
    }

    public String sendKeys(String value) {
        return callingCommand + "(" + passedValue + ").sendKeys(\"" + value + "\")";
    }

    public String submit() {
        return callingCommand + "(" + passedValue + ").submit()";
    }

    public String toString() {
        return callingCommand + "(" + passedValue + ")";
    }
}
