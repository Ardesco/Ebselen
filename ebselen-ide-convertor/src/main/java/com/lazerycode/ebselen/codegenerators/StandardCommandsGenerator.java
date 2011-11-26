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

public class StandardCommandsGenerator {

    public String ifAction(String condition, String action) {
        return "if(" + condition + "){" +
                action +
                "}";
    }

    public String ifNotAction(String condition, String action) {
        return "if(!" + condition + "){" +
                action +
                "}";
    }

    public String assertTrue(String value) {
        return "assertTrue(" + value + ")";
    }

    public String assertFalse(String value) {
        return "assertFalse(" + value + ")";
    }

    public String assertEquals(String value, String matcher) {
        return "assertEquals(" + value + ", " + matcher + ")";
    }

    public String assertNotEquals(String value, String matcher) {
        return "assertNotEquals(" + value + ", " + matcher + ")";
    }

    public String verifyTrue(String value) {
        return "verifyTrue(" + value + ")";
    }

    public String verifyFalse(String value) {
        return "verifyFalse(" + value + ")";
    }

    public String verifyEquals(String value, String matcher) {
        return "verifyEquals(" + value + ", " + matcher + ")";
    }

    public String verifyNotEquals(String value, String matcher) {
        return "verifyNotEquals(" + value + ", " + matcher + ")";
    }

    public String tryCatch(String tryValue, String exceptionToCatch, String ifException) {
        return "try{" +
                tryValue +
                "} catch (" + exceptionToCatch + ") {" +
                ifException +
                "}";
    }
}
