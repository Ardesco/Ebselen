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

import com.lazerycode.ebselen.commands.*;
import com.lazerycode.ebselen.exceptions.*;
import com.lazerycode.ebselen.handlers.LocatorHandler;
import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.internal.seleniumemulation.JavascriptLibrary;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class EbselenCommands {

    private WebDriver driver;
    private JavascriptLibrary jsLib = new JavascriptLibrary();
    private LocatorHandler loc = new LocatorHandler();
    private int defaultTimeout = 10000;
    public static final Logger logger = LoggerFactory.getLogger(EbselenCommands.class);

    public EbselenCommands(WebDriver driverInstance) {
        driver = driverInstance;
    }

    public EbselenCommands() {
    }

    /**
     * Set the default timeout used by the wait functions.
     *
     * @param timeout in ms (e.g. 10 seconds = 10000)
     */
    public void setDefaultTimeout(int timeout) {
        defaultTimeout = timeout;
    }

    /**
     * Return the currently set default timeout in ms
     *
     * @param timeout
     * @return
     */
    public int getDefaultTimeout(int timeout) {
        return defaultTimeout;
    }

    /**
     * Open site homepage but do not check that it has loaded correctly
     * This will set the currently selected site to the value of page
     *
     * @param homepageURL URL of the homepage
     */
    public void openHomepage(String homepageURL) throws Exception {
        openHomepage(homepageURL, null);
    }

    /**
     * Open site homepage and check that it has loaded correctly.
     * This will set the currently selected site to the value of page
     *
     * @param homepageURL     URL of the homepage
     * @param homePageElement If homePageElement is not null kill tests if it does not exist
     */
    public void openHomepage(String homepageURL, WebElement homePageElement) throws Exception {
        driver.get(homepageURL);
        if (homePageElement != null) {
            if (!homePageElement.isDisplayed()) {
                throw new HomepageNotLoadedException();
            }
        }
    }


    public APISwitch apiswitch() {
        return new EbselenAPISwitch();
    }

    private class EbselenAPISwitch implements APISwitch {

        public Selenium switchToSelenium(String homepageURL) {
            // Create the Selenium implementation
            return new WebDriverBackedSelenium(driver, homepageURL);
        }

        public void switchBackToWebdriver(Selenium seleniumObject) {
            driver = ((WebDriverBackedSelenium) seleniumObject).getWrappedDriver();
        }
    }

    public com.lazerycode.ebselen.commands.Windows window() {
        return new EbselenWindow();
    }

    private class EbselenWindow implements Windows {

        public Boolean switchBetweenWindows() throws Exception {
            Set listOfWindows = driver.getWindowHandles();
            if (listOfWindows.size() != 2) {
                if (listOfWindows.size() > 2) {
                    throw new TooManyWindowsException();
                } else {
                    throw new TooFewWindowsException();
                }
            }
            String currentWindow = driver.getWindowHandle();
            for (Iterator i = listOfWindows.iterator(); i.hasNext(); ) {
                String selectedWindowHandle = i.next().toString();
                if (!selectedWindowHandle.equals(currentWindow)) {
                    driver.switchTo().window(selectedWindowHandle);
                    return true;
                }
            }
            // Just in case something goes wrong
            throw new UnableToFindWindowException("Unable to switch windows!");
        }

        public Boolean switchToWindowTitled(String windowTitle) throws Exception {
            try {
                driver.switchTo().window(windowTitle);
                return true;
            } catch (Exception Ex) {
                throw new UnableToFindWindowException("Unable to find a window with the title '" + windowTitle + "'!");
            }
        }

        public File takeScreenshot() {
            WebDriver augment = new Augmenter().augment(driver);
            return ((TakesScreenshot) augment).getScreenshotAs(OutputType.FILE);
        }

        public void maximise() {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension screenResolution = new Dimension((int) toolkit.getScreenSize().getWidth(), (int) toolkit.getScreenSize().getHeight());
            driver.manage().window().setSize(screenResolution);
        }
    }

    public com.lazerycode.ebselen.commands.JavaScript javascript() {
        return new JavaScriptInteraction();
    }

    private class JavaScriptInteraction implements JavaScript {

        public void triggerJavascriptEvent(jsEvent event, WebElement element) {
            jsLib.callEmbeddedSelenium(driver, "triggerEvent", element, event.toString().toLowerCase());
        }

        public Object executeScript(String script, Object... args) {
            return ((JavascriptExecutor) driver).executeScript(script, args);
        }

    }

    public com.lazerycode.ebselen.commands.Element element() {
        return new ElementInteraction();
    }

    private class ElementInteraction implements Element {

        /**
         * Find out if an element is stale or not.
         *
         * @param element - An xpath locator
         * @return boolean - True if element location is found, otherwise false.
         * @throws Exception
         */
        public boolean isElementStale(WebElement element) {
            try {
                element.getLocation();
            } catch (StaleElementReferenceException Ex) {
                return true;
            }
            return false;
        }

        public boolean doesElementExist(By locator) {
            if (driver.findElements(locator).size() > 0) {
                return true;
            } else {
                return false;
            }
        }

        public boolean isElementDisplayed(By locator) {
            if (doesElementExist(locator)) {
                return driver.findElement(locator).isDisplayed();
            } else {
                return false;
            }
        }

        public int getElementCount(String locator) {
            List elementsFound = driver.findElements(By.xpath(locator));
            return elementsFound.size();
        }

        public void check(String locator) {
            WebElement checkBox = driver.findElement(loc.autoLocator(locator));
            if (!checkBox.getAttribute("type").toLowerCase().equals("checkbox")) {
                throw new InvalidElementTypeException("This elementLocator is not a checkbox!");
            }
            if (!checkBox.isSelected()) {
                checkBox.click();
            }
        }

        public void uncheck(String locator) {
            WebElement checkBox = driver.findElement(loc.autoLocator(locator));
            if (!checkBox.getAttribute("type").toLowerCase().equals("checkbox")) {
                throw new InvalidElementTypeException("This elementLocator is not a checkbox!");
            }
            if (checkBox.isSelected()) {
                checkBox.click();
            }
        }

        public boolean isChecked(String locator) {
            WebElement checkBox = driver.findElement(loc.autoLocator(locator));
            if (!checkBox.getAttribute("type").toLowerCase().equals("checkbox")) {
                throw new InvalidElementTypeException("This elementLocator is not a checkbox!");
            }
            if (checkBox.getAttribute("checked").equals("checked")) {
                return true;
            } else {
                return false;
            }
        }
    }

    public com.lazerycode.ebselen.commands.Utility utility() {
        return new EbselenUtility();
    }

    private class EbselenUtility implements Utility {

        public String generateRandomEmailAddress(String domain) {
            String emailAddress = "";
            // Generate random email address
            String alphabet = "abcdefghijklmnopqrstuvwxyz";
            while (emailAddress.length() < 5) {
                int character = (int) (Math.random() * 26);
                emailAddress += alphabet.substring(character, character + 1);
            }
            emailAddress += Integer.valueOf((int) (Math.random() * 99)).toString();
            emailAddress += "@" + domain;
            return emailAddress;
        }

        public String stripGarbage(String garbageFilledString) {
            return garbageFilledString.replaceAll("/", "_").replaceAll("\\W", "");
        }

        public String ucWords(String wordsToConvert) {
            wordsToConvert = wordsToConvert.toLowerCase();
            Boolean ucChar = false;
            String convertedString = "";
            for (int i = 0; i < wordsToConvert.length(); i++) {
                if (i == 0 | ucChar) {
                    convertedString += Character.toUpperCase(wordsToConvert.charAt(i));
                } else {
                    convertedString += Character.toString(wordsToConvert.charAt(i));
                }
                if (Character.isWhitespace(wordsToConvert.charAt(i))) {
                    ucChar = true;
                } else {
                    ucChar = false;
                }
            }
            return convertedString;
        }
    }

    public com.lazerycode.ebselen.commands.Wait waiting() {
        return new EbselenWait(defaultTimeout);
    }


    public com.lazerycode.ebselen.commands.Wait waitFor(int timeout) {
        return new EbselenWait(timeout);
    }

    private class EbselenWait implements Wait {

        final int timeout;

        public EbselenWait(int passedTimeout) {
            timeout = passedTimeout;
        }

        public ForWebElements untilWebElement(By elementLocator) {
            return new EbselenForWebElements(elementLocator);
        }

        private class EbselenForWebElements implements ForWebElements {
            final By elementLocator;

            public EbselenForWebElements(By passedElement) {
                elementLocator = passedElement;
            }

            public void exists() {
                new WebDriverWait(driver, timeout) {
                }.until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        return (driver.findElements(elementLocator).size() > 0);
                    }
                });
            }

            public void doesNotExist() {
                new WebDriverWait(driver, timeout) {
                }.until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        return (driver.findElements(elementLocator).size() < 1);
                    }
                });
            }

            public void instancesAreMoreThan(final int instances) {
                new WebDriverWait(driver, timeout) {
                }.until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        return (driver.findElements(elementLocator).size() > instances);
                    }
                });
            }

            public void instancesAreLessThan(final int instances) {
                new WebDriverWait(driver, timeout) {
                }.until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        return (driver.findElements(elementLocator).size() < instances);
                    }
                });
            }

            public void instancesEqual(final int instances) {
                new WebDriverWait(driver, timeout) {
                }.until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        return (driver.findElements(elementLocator).size() == instances);
                    }
                });
            }

            public void instancesDoNotEqual(final int instances) {
                new WebDriverWait(driver, timeout) {
                }.until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        return (driver.findElements(elementLocator).size() != instances);
                    }
                });
            }

            public void existsAfterRefreshingPage() {
                new WebDriverWait(driver, timeout) {
                }.until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        driver.navigate().refresh();
                        return (driver.findElements(elementLocator).size() > 0);
                    }
                });
            }

            public void doesNotExistAfterRefreshingPage() {
                new WebDriverWait(driver, timeout) {
                }.until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        driver.navigate().refresh();
                        return (driver.findElements(elementLocator).size() < 1);
                    }
                });
            }

            public void instancesAreMoreThanAfterRefreshingPage(final int instances) {
                new WebDriverWait(driver, timeout) {
                }.until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        driver.navigate().refresh();
                        return (driver.findElements(elementLocator).size() > instances);
                    }
                });
            }

            public void instancesAreLessThanAfterRefreshingPage(final int instances) {
                new WebDriverWait(driver, timeout) {
                }.until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        driver.navigate().refresh();
                        return (driver.findElements(elementLocator).size() < instances);
                    }
                });
            }

            public void instancesEqualAfterRefreshingPage(final int instances) {
                new WebDriverWait(driver, timeout) {
                }.until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        driver.navigate().refresh();
                        return driver.findElements(elementLocator).size() == instances;
                    }
                });
            }

            public void instancesDoNotEqualAfterRefreshingPage(final int instances) {
                new WebDriverWait(driver, timeout) {
                }.until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        driver.navigate().refresh();
                        return driver.findElements(elementLocator).size() != instances;
                    }
                });
            }

            public void textIsEqualTo(final String text) {
                new WebDriverWait(driver, timeout) {
                }.until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        return driver.findElement(elementLocator).getText().equals(text);
                    }
                });
            }

            public void textDoesNotEqual(final String text) {
                new WebDriverWait(driver, timeout) {
                }.until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        return !driver.findElement(elementLocator).getText().equals(text);
                    }
                });
            }

            public void textContains(final String text) {
                new WebDriverWait(driver, timeout) {
                }.until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        return driver.findElement(elementLocator).getText().contains(text);
                    }
                });
            }

            public void textDoesNotContain(final String text) {
                new WebDriverWait(driver, timeout) {
                }.until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        return !driver.findElement(elementLocator).getText().contains(text);
                    }
                });
            }

            public void titleIsEqualTo(final String text) {
                new WebDriverWait(driver, timeout) {
                }.until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        return driver.getTitle().equals(text);
                    }
                });
            }

            public void titleDoesNotEqual(final String text) {
                new WebDriverWait(driver, timeout) {
                }.until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        return !driver.getTitle().equals(text);
                    }
                });
            }

            public void titleContains(final String text) {
                new WebDriverWait(driver, timeout) {
                }.until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        return driver.getTitle().contains(text);
                    }
                });
            }

            public void titleDoesNotContain(final String text) {
                new WebDriverWait(driver, timeout) {
                }.until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        return !driver.getTitle().contains(text);
                    }
                });
            }
        }

        public ForWindows untilWindow() {
            return new EbselenForWindows();
        }

        private class EbselenForWindows implements ForWindows {

            public void countEquals(final int count) {
                new WebDriverWait(driver, timeout) {
                }.until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        return driver.getWindowHandles().size() == count;
                    }
                });
            }

            public void countDoesNotEqual(final int count) {
                new WebDriverWait(driver, timeout) {
                }.until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        return driver.getWindowHandles().size() != count;
                    }
                });
            }

            public void countIsGreaterThan(final int count) {
                new WebDriverWait(driver, timeout) {
                }.until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        return driver.getWindowHandles().size() > count;
                    }
                });
            }

            public void countIsLessThan(final int count) {
                new WebDriverWait(driver, timeout) {
                }.until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        return driver.getWindowHandles().size() < count;
                    }
                });
            }
        }

        public ForEvent untilEvent() {
            return new EbselenForEvent();
        }

        private class EbselenForEvent implements ForEvent {

            public void untilJQueryProcessingHasCompleted() {
                new WebDriverWait(driver, timeout) {
                }.until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        boolean jQueryActive = (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active == 0");
                        return jQueryActive;
                    }
                });
            }
        }
    }

    public ControlObject getControlObject() {
        return new EbselenControlObject();
    }

    private class EbselenControlObject implements ControlObject {

        public Mouse getMouseObject() {
            return ((HasInputDevices) driver).getMouse();
        }

        public Keyboard getKeyboardObject() {
            return ((HasInputDevices) driver).getKeyboard();
        }
    }
}

