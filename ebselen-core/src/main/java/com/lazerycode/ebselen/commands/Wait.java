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
    }

    ForWindows untilWindow();

    interface ForWindows {

        public void countEquals(int count);

        public void countIsGreaterThan(int count);

        public void countIsLessThan(int count);
    }
}
