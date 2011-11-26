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
package com.lazerycode.ebselen;

public class TestData {

    private String testName;
    private int failuresLogged = 0;
    private long timeTaken = 0;

    public void setTestName(String value) {
        testName = value;
    }

    public String getTestName() {
        return testName;
    }

    public void addFailure(int value) {
        failuresLogged += value;
    }

    public void setFailures(int value) {
        failuresLogged = value;
    }

    public int getFailures() {
        return failuresLogged;
    }

    public void addTimeTaken(long value) {
        timeTaken += value;
    }

    public void setTimeTaken(long value) {
        timeTaken = value;
    }

    public long getTimeTaken() {
        return timeTaken;
    }

}
