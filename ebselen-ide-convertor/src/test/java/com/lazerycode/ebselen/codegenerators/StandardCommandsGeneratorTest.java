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

public class StandardCommandsGeneratorTest {

    private StandardCommandsGenerator com = new StandardCommandsGenerator();
    private String condition = "condition.equals(true)";
    private String action = "Do this action";

    @Test
    public void checkIfConditionReturnsValidCode() {
        assertThat(com.ifAction(condition, action), is(equalTo("if(" + condition + "){" + action + "}")));
    }

    @Test
    public void checkIfNotConditionReturnsValidCode() {
        assertThat(com.ifNotAction(condition, action), is(equalTo("if(!" + condition + "){" + action + "}")));
    }

    @Test
    public void checkAssertTrueReturnsValidCode() {
        assertThat(com.assertTrue(condition), is(equalTo("assertTrue(" + condition + ")")));
    }

    @Test
    public void checkAssertFalseReturnsValidCode() {
        assertThat(com.assertFalse(condition), is(equalTo("assertFalse(" + condition + ")")));
    }

    @Test
    public void checkAssertEqualsReturnsValidCode() {
        assertThat(com.assertEquals(condition, condition), is(equalTo("assertEquals(" + condition + ", " + condition + ")")));
    }

    @Test
    public void checkAssertNotEqualsReturnsValidCode() {
        assertThat(com.assertNotEquals(condition, condition), is(equalTo("assertNotEquals(" + condition + ", " + condition + ")")));
    }

    @Test
    public void checkVerifyTrueReturnsValidCode() {
        assertThat(com.verifyTrue(condition), is(equalTo("verifyTrue(" + condition + ")")));
    }

    @Test
    public void checkVerifyFalseReturnsValidCode() {
        assertThat(com.verifyFalse(condition), is(equalTo("verifyFalse(" + condition + ")")));
    }

    @Test
    public void checkVerifyEqualsReturnsValidCode() {
        assertThat(com.verifyEquals(condition, condition), is(equalTo("verifyEquals(" + condition + ", " + condition + ")")));
    }

    @Test
    public void checkVerifyNotEqualsReturnsValidCode() {
        assertThat(com.verifyNotEquals(condition, condition), is(equalTo("verifyNotEquals(" + condition + ", " + condition + ")")));
    }


    @Test
    public void checkTryCatchReturnsValidCode() {
        assertThat(com.tryCatch(action, "Exception ex", "Exception has been thrown"), is(equalTo("try{" + action + "} catch (Exception ex) {Exception has been thrown}")));
    }
}
