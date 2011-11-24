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

import org.openqa.selenium.Keys;

public class ActionsGenerator {


    private String mouseActionObject;
    private String keyboardActionObject;
    private String builderActionObject;

    public ActionsGenerator(String mouseObject, String keyboardObject, String actionObject) {
        mouseActionObject = mouseObject;
        keyboardActionObject = keyboardObject;
        builderActionObject = actionObject;
    }

    public class Keyboard {
        public String pressKey(Keys theKey) {
            return keyboardActionObject + ".pressKey(Keys." + theKey.name() + ")";
        }

        public String releaseKey(Keys theKey) {
            return keyboardActionObject + ".releaseKey(Keys." + theKey.name() + ")";
        }

        public String sendKeys(String value) {
            return keyboardActionObject + ".sendKeys(\"" + value + "\")";
        }

    }

    public class Mouse {

        public String click(String coordinates) {
            return mouseActionObject + ".click(" + coordinates + ")";
        }

        public String contextClick(String coordinates) {
            return mouseActionObject + ".contextClick(" + coordinates + ")";
        }

        public String doubleClick(String coordinates) {
            return mouseActionObject + ".doubleClick(" + coordinates + ")";
        }

        public String mouseDown(String coordinates) {
            return mouseActionObject + ".mouseDown(" + coordinates + ")";
        }

        public String mouseMove(String coordinates) {
            return mouseActionObject + ".mouseMove(" + coordinates + ")";
        }

        public String mouseMove(String coordinates, long x, long y) {
            return mouseActionObject + ".mouseMove(" + coordinates + ", " + x + ", " + y + ")";
        }

        public String mouseUp(String coordinates) {
            return mouseActionObject + ".mouseUp(" + coordinates + ")";
        }

    }


    //TODO make this chainable
    public class Actions {


        public String click() {
            return builderActionObject + ".click()";
        }

        public String click(String webElement) {
            return builderActionObject + ".click(" + webElement + ")";
        }

        public String clickAndHold() {
            return builderActionObject + ".clickAndHold()";
        }

        public String clickAndHold(String webElement) {
            return builderActionObject + ".clickAndHold(" + webElement + ")";
        }

        public String contextClick(String webElement) {
            return builderActionObject + ".contextClick(" + webElement + ")";
        }

        public String doubleClick() {
            return builderActionObject + ".doubleClick()";
        }

        public String doubleClick(String webElement) {
            return builderActionObject + ".doubleClick(" + webElement + ")";
        }

        public String dragAndDrop(String webElementFrom, String webElementTo) {
            return builderActionObject + ".dragAndDrop(" + webElementFrom + ", " + webElementTo + ")";
        }

        public String dragAndDrop(String webElementFrom, String xOffset, String yOffset) {
            return builderActionObject + ".dragAndDrop(" + webElementFrom + ", " + xOffset + ", " + yOffset + ")";
        }

        public String keyDown(Keys keys) {
            return builderActionObject + ".keyDown(Keys." + keys.name() + ")";
        }

        public String keyDown(String webElement, Keys keys) {
            return builderActionObject + ".keyDown(" + webElement + ", Keys." + keys.name() + ")";
        }

        public String keyUp(Keys keys) {
            return builderActionObject + ".keyUp(Keys." + keys.name() + ")";
        }

        public String keyUp(String webElement, Keys keys) {
            return builderActionObject + ".keyUp(" + webElement + ", Keys." + keys.name() + ")";
        }

        public String moveByOffset(String xOffset, String yOffset) {
            return builderActionObject + ".moveByOffset(" + xOffset + ", " + yOffset + ")";
        }

        public String moveToElement(String webElement) {
            return builderActionObject + ".moveToElement(" + webElement + ")";
        }

        public String moveToElement(String webElement, String xOffset, String yOffset) {
            return builderActionObject + ".moveToElement(" + webElement + ", " + xOffset + ", " + yOffset + ")";
        }

        public String build() {
            return builderActionObject + ".build()";
        }

        public String perform() {
            return builderActionObject + ".perform()";
        }

        public String release() {
            return builderActionObject + ".release()";
        }

        public String release(String webElement) {
            return builderActionObject + ".release(" + webElement + ")";
        }

        public String sendKeys(String value) {
            return builderActionObject + ".sendKeys(\"" + value + "\")";
        }

        public String sendKeys(String webElement, String value) {
            return builderActionObject + ".sendKeys(" + webElement + ", \"" + value + "\")";
        }
    }

    public Keyboard keyboard() {
        return new Keyboard();
    }

    public Mouse mouse() {
        return new Mouse();
    }

    public Actions builder() {
        return new Actions();
    }
}