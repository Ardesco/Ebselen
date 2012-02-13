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

package com.lazerycode.ebselen.commands;

import org.openqa.selenium.By;

public interface Wait {

    ForWebElements untilWebElement(By element);

    interface ForWebElements {

        public void exists();

        public void doesNotExist();

        public void instancesAreMoreThan(int instances);

        public void instancesAreLessThan(int instances);

        public void instancesEqual(int instances);

        public void existsAfterRefreshingPage();

        public void doesNotExistAfterRefreshingPage();

        public void instancesAreMoreThanAfterRefreshingPage(int instances);

        public void instancesAreLessThanAfterRefreshingPage(int instances);

        public void instancesEqualAfterRefreshingPage(int instances);

        public void textIsEqualTo(String text);

        public void textDoesNotEqual(String text);

        public void textContains(String text);

        public void titleIsEqualTo(String text);

        public void titleDoesNotEqual(String text);
    }

    ForWindows untilWindow();

    interface ForWindows {

        public void countEquals(int count);

        public void countIsGreaterThan(int count);

        public void countIsLessThan(int count);
    }
}
