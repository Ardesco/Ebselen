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

import java.lang.annotation.*;

public interface SeleniumTestAnnotations {

    /**
     * Suite status definitions
     * <p/>
     * UNDER_CONSTRUCTION - Not yet completed and should not be expected to pass
     * MANUALLY_RUN - To be run manually by a user
     * CI_BUILD - Run test on every CI build
     * OVERNIGHT_BUILD - Run test only on overnight builds
     * ON_HOLD - Test is breaking consistently and requires investigation, do not run in CI while investigating
     * CONVERTED_FROM_IDE - created by the IDE convertor.  Needs to be checked and probably refactored.
     */
    public static enum suiteStatus {

        UNDER_CONSTRUCTION, MANUALLY_RUN, CI_BUILD, OVERNIGHT_BUILD, ON_HOLD, CONVERTED_FROM_IDE
    }

    ;

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface TestSuiteStatus {

        public suiteStatus value();
    }

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface TestStoriesCovered {

        String[] value();
    }

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface TestAuthor {

        String value();
    }

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Order {

        int value();
    }

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface SeleniumTest {

        static class None extends Throwable {
            private static final long serialVersionUID = 1L;

            private None() {
            }
        }
    }

}
