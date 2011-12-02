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

package com.lazerycode.ebselen.customhandlers;

import com.google.common.annotations.Beta;
import com.lazerycode.ebselen.EbselenCore;

@Beta
public class drupalFunctions extends EbselenCore {
//
//    /**
//     * Take the current URL in the location bar, extract the current node and junk everything else
//     *
//     * @author MPLC
//     *
//     * @throws Exception
//     * @return The current Node, or null if no node is found
//     */
//    public String getCurrentNode() throws Exception {
//        //Get the URL of the current page
//        String currentNode = driver.getCurrentUrl().replaceAll("^" + settings.getHomePage(settings.getCurrentlySelectedSite()).replaceAll("\\.", "\\\\."), "/");
//        if (currentNode.equals("/")) {
//            return null;
//        }
//        currentNode = currentNode.replaceAll("^/(node|collection)/", "").replaceAll("/[a-zA-Z]+$", "");
//        return currentNode;
//    }
//
//    /**
//     * Take the current URL in the location bar, extract the role ID
//     *
//     * @author ravi
//     *
//     * @throws Exception
//     * @return roleId, or null if nothing  is found
//     */
//    public String getUserRoleId() throws Exception {
//        //Get the URL of the current page
//        String currentNode = driver.getCurrentUrl().replaceAll("^" + settings.getHomePage(settings.getCurrentlySelectedSite()).replaceAll("\\.", "\\\\."), "/");
//        currentNode = currentNode.replaceAll("^/admin/user/roles/edit/", "").replaceAll("/[a-zA-Z]+$", "");
//        return currentNode;
//    }
//
//    /**
//     * The function generates the regex for a menu item passed as an argument to it.
//     *
//     * @author MPLC
//     *
//     * @param ArrayList The menu headers to follow (e.g. Content management, Create content, Page)
//     * @return - the xPath for the Menu Item
//     */
//    public String generateAdminMenuXPath(ArrayList menuPath) throws Exception {
//        String menuXPath = "//div[@id='admin-menu']";
//        for (int i = 0; i < menuPath.size(); i++) {
//            if (i != 0) {
//                menuXPath += "/..";
//            }
//            if (menuPath.get(i).toString().contains("'")) {
//                menuXPath += "/descendant::ul/li/a[.=\"" + menuPath.get(i) + "\"]";
//            } else {
//                menuXPath += "/descendant::ul/li/a[.='" + menuPath.get(i) + "']";
//            }
//        }
//        return menuXPath;
//    }
//
//    /**
//     * Populates either a text area, or a Tiny MCE editor with the [value] Text.
//     * Note : xpathLocator parameter is expected with the string like  \\textarea[@id='myTextAreaId']
//     *
//     * @param String XPath to textarea
//     * @param sValue Text you would like to type into the text area
//     */
//    public void typeTextArea(String xpathLocator, String sValue) throws Exception {
//        String iframeLocator = xpathLocator.replaceAll("//textarea", "//iframe").replaceAll("']", "_ifr']");
//        if (isElementPresent(iframeLocator)) {
//            driver.switchTo().frame(iframeLocator.replaceAll("//iframe\\[\\@id='", "").replaceAll("'\\]", ""));
//            if (isElementPresent("//body[@id='tinymce']")) {
//                switchToSelenium();
//                sel.typeKeys("//body[@id='tinymce']", sValue);
//                switchBackToWebdriver();
//                driver.switchTo().defaultContent();
//            } else {
//                LOGGER.error("Element //body[@id='tinymce'] does not exist.");
//            }
//        } else {
//            if (isElementPresent(xpathLocator)) {
//                switchToSelenium();
//                sel.typeKeys(xpathLocator, sValue);
//                switchBackToWebdriver();
//            } else {
//                verifyTrue(false);
//            }
//        }
//    }
//
//    /**
//     * Clears either a text area, or a Tiny MCE editor with the [value] Text.
//     * Note : xpathLocator parameter is expected with the string like  \\textarea[@id='myTextAreaId']
//     * @author MPLC
//     *
//     * @param String XPath to textarea
//     */
//    public void clearTextArea(String xpathLocator) throws Exception {
//        String iframeLocator = xpathLocator.replaceAll("//textarea", "//iframe").replaceAll("']", "_ifr']");
//        if (isElementPresent(iframeLocator)) {
//            driver.switchTo().frame(iframeLocator.replaceAll("//iframe\\[\\@id='", "").replaceAll("'\\]", ""));
//            //Can't send a clear as this is not an input type element any more, have to send backpaces instead.
//            int numDeletes = driver.findElement(By.xpath("//body[@id='tinymce']")).getText().length();
//            driver.findElement(By.xpath("//body[@id='tinymce']")).click();
//            for (int i = 0; i <= numDeletes; i++) {
//                driver.findElement(By.xpath("//body[@id='tinymce']")).sendKeys(Keys.DELETE);
//            }
//            driver.switchTo().defaultContent();
//        } else {
//            if (isElementPresent(xpathLocator)) {
//                driver.findElement(By.xpath(xpathLocator)).clear();
//            } else {
//                verifyTrue(false);
//            }
//        }
//    }
//
//    /**
//     * Get the text held in either a text area of the TinyMCE editor
//     *Note : xpathLocator parameter is expected with the string like \\textarea[@id='myTextAreaId']
//     * @author MPLC
//     *
//     * @param xpathLocator
//     * @return String, the text held in the textarea/TinyMCE editor
//     * @throws Exception
//     */
//    public String getTextArea(String xpathLocator) throws Exception {
//        String iframeLocator = xpathLocator.replaceAll("//textarea", "//iframe").replaceAll("']", "_ifr']");
//        String textFound = "";
//        if (isElementPresent(iframeLocator)) {
//            driver.switchTo().frame(iframeLocator.replaceAll("//iframe\\[\\@id='", "").replaceAll("'\\]", ""));
//            textFound = driver.findElement(By.xpath("//body[@id='tinymce']")).getText();
//            driver.switchTo().defaultContent();
//        } else {
//            if (isElementPresent(xpathLocator)) {
//                textFound = driver.findElement(By.xpath(xpathLocator)).getText();
//            } else {
//                verifyTrue(false);
//            }
//        }
//        return textFound;
//    }
//
//    /**
//     * Check to see if a message is displayed, This functions will check each individual message that is displayed, not the overall block.
//     * If you are looking for multiple messages you will need to check for each one individually.
//     * If the text you are looking for runs over multiple lines or contains tabs replace these with a single space and set multiLine to true
//     *
//     * @author MPLC
//     *
//     * @param messageText The text you expect to see
//     * @param emMessageText If not null the emphasised text you expect to see
//     * @param messageType the class of the message (e.g. status, error, etc)
//     * @param multiLine Set to true if the text is split over multiple lines
//     * @return true if message text is found, otherwise false.  (If the message text is found but the em block is incorrect this will return false)
//     * @throws Exception
//     */
//    public Boolean checkMessage(String messageText, String emMessageText, String messageType, Boolean multiLine) throws Exception {
//        Boolean messageFound = false;
//        String textFound = "";
//        String messagePath = "//div[contains(@class, 'messages " + messageType + "')]";
//        waitForElement(messagePath);
//        if (isElementPresent(messagePath)) {
//            if (isElementPresent(messagePath + "/ul")) {
//                int messageCount = getElementCount(messagePath + "/ul/li");
//                for (int i = 1; i <= messageCount; i++) {
//                    textFound = driver.findElement(By.xpath(messagePath + "/ul/li[" + i + "]")).getText();
//                    if (multiLine) {
//                        textFound = textFound.replace("\n", "").replace("\t", "");
//                    }
//                    if (textFound.equals(messageText)) {
//                        LOGGER.debug("Found the " + messageType + " message '" + messageText + "'.");
//                        messageFound = true;
//                        if (emMessageText != null) {
//                            textFound = driver.findElement(By.xpath(messagePath + "/ul/li[" + i + "]/descendant::em")).getText();
//                            if (multiLine) {
//                                textFound = textFound.replace("\n", "").replace("\t", "");
//                            }
//                            if (textFound.equals(emMessageText)) {
//                                LOGGER.debug("Matched the emphasised text '" + emMessageText + "'.");
//                            } else {
//                                LOGGER.error("Could not match the emphasised text '" + emMessageText + "'.");
//                                messageFound = false;
//                            }
//                        }
//                        break;
//                    }
//                }
//            } else {
//                textFound = driver.findElement(By.xpath(messagePath)).getText();
//                if (multiLine) {
//                    textFound = textFound.replace("\n", "").replace("\t", "");
//                }
//                if (textFound.equals(messageText)) {
//                    messageFound = true;
//                    if (emMessageText != null) {
//                        textFound = driver.findElement(By.xpath(messagePath + "/descendant::em")).getText();
//                        if (multiLine) {
//                            textFound = textFound.replace("\n", "").replace("\t", "");
//                        }
//                        if (textFound.equals(emMessageText)) {
//                            LOGGER.debug("Matched the emphasised text '" + emMessageText + "'.");
//                        } else {
//                            LOGGER.error("Could not match the emphasised text '" + emMessageText + "'.");
//                            messageFound = false;
//                        }
//                    }
//                }
//            }
//        } else {
//            LOGGER.error("No " + messageType + " messages found!");
//        }
//        if (!messageFound) {
//            LOGGER.error("Unable to find the " + messageType + " message '" + messageText + "'.");
//        }
//        return messageFound;
//    }
//
//    /**
//     * Check to see if a status message is displayed
//     *
//     * @author MPLC
//     *
//     * @param messageText The text that you are expecting to see
//     * @param emMessageText The emphasised text you are expecting to see
//     * @return True if message is found, false if not.
//     * @throws Exception
//     */
//    public Boolean checkMessageStatus(String messageText, String emMessageText) throws Exception {
//        boolean passed = checkMessage(messageText, emMessageText, "status", false);
//        verifyTrue(passed);
//        return passed;
//    }
//
//    /**
//     * Check to see if a status message is displayed
//     *
//     * @author MPLC
//     *
//     * @param messageText The text that you are expecting to see
//     * @return True if message is found, false if not.
//     * @throws Exception
//     */
//    public Boolean checkMessageStatus(String messageText) throws Exception {
//        boolean passed = checkMessage(messageText, null, "status", false);
//        verifyTrue(passed);
//        return passed;
//    }
//
//    /**
//     * Check to see if a status message is displayed
//     *
//     * @author MPLC
//     *
//     * @param messageText The text that you are expecting to see
//     * @param emMessageText The emphasised text you are expecting to see
//     * @param multiLine Set to true if the text is split over multiple lines
//     * @return True if message is found, false if not.
//     * @throws Exception
//     */
//    public Boolean checkMessageStatus(String messageText, String emMessageText, Boolean multiLine) throws Exception {
//        boolean passed = checkMessage(messageText, emMessageText, "status", multiLine);
//        verifyTrue(passed);
//        return passed;
//    }
//
//    /**
//     * Check to see if a status message is displayed
//     *
//     * @author MPLC
//     *
//     * @param messageText The text that you are expecting to see
//     * @param multiLine Set to true if the text is split over multiple lines
//     * @return True if message is found, false if not.
//     * @throws Exception
//     */
//    public Boolean checkMessageStatus(String messageText, Boolean multiLine) throws Exception {
//        boolean passed = checkMessage(messageText, null, "status", multiLine);
//        verifyTrue(passed);
//        return passed;
//    }
//
//    /**
//     * Check to see if an error message is displayed
//     *
//     * @author MPLC
//     *
//     * @param messageText The text that you are expecting to see
//     * @param emMessageText The emphasised text you are expecting to see
//     * @return True if message is found, false if not.
//     * @throws Exception
//     */
//    public Boolean checkMessageError(String messageText, String emMessageText) throws Exception {
//        boolean passed = checkMessage(messageText, emMessageText, "error", false);
//        verifyTrue(passed);
//        return passed;
//    }
//
//    /**
//     * Check to see if an error message is displayed
//     *
//     * @author MPLC
//     *
//     * @param messageText The text that you are expecting to see
//     * @return True if message is found, false if not.
//     * @throws Exception
//     */
//    public Boolean checkMessageError(String messageText) throws Exception {
//        boolean passed = checkMessage(messageText, null, "error", false);
//        verifyTrue(passed);
//        return passed;
//    }
//
//    /**
//     * Check to see if an error message is displayed
//     *
//     * @author MPLC
//     *
//     * @param messageText The text that you are expecting to see
//     * @param emMessageText The emphasised text you are expecting to see
//     * @param multiLine Set to true if the text is split over multiple lines
//     * @return True if message is found, false if not.
//     * @throws Exception
//     */
//    public Boolean checkMessageError(String messageText, String emMessageText, Boolean multiLine) throws Exception {
//        boolean passed = checkMessage(messageText, emMessageText, "error", multiLine);
//        verifyTrue(passed);
//        return passed;
//    }
//
//    /**
//     * Check to see if an error message is displayed
//     *
//     * @author MPLC
//     *
//     * @param messageText The text that you are expecting to see
//     * @param multiLine Set to true if the text is split over multiple lines
//     * @return True if message is found, false if not.
//     * @throws Exception
//     */
//    public Boolean checkMessageError(String messageText, Boolean multiLine) throws Exception {
//        boolean passed = checkMessage(messageText, null, "error", multiLine);
//        verifyTrue(passed);
//        return passed;
//    }
//
//    /**
//     * Check to see if an warning message is displayed
//     *
//     * @author MPLC
//     *
//     * @param messageText The text that you are expecting to see
//     * @param emMessageText The emphasised text you are expecting to see
//     * @return True if message is found, false if not.
//     * @throws Exception
//     */
//    public Boolean checkMessageWarning(String messageText, String emMessageText) throws Exception {
//        boolean passed = checkMessage(messageText, emMessageText, "warning", false);
//        verifyTrue(passed);
//        return passed;
//    }
//
//    /**
//     * Check to see if an warning message is displayed
//     *
//     * @author MPLC
//     *
//     * @param messageText The text that you are expecting to see
//     * @return True if message is found, false if not.
//     * @throws Exception
//     */
//    public Boolean checkMessageWarning(String messageText) throws Exception {
//        boolean passed = checkMessage(messageText, null, "warning", false);
//        verifyTrue(passed);
//        return passed;
//    }
//
//    /**
//     * Check to see if an warning message is displayed
//     *
//     * @author MPLC
//     *
//     * @param messageText The text that you are expecting to see
//     * @param emMessageText The emphasised text you are expecting to see
//     * @param multiLine Set to true if the text is split over multiple lines
//     * @return True if message is found, false if not.
//     * @throws Exception
//     */
//    public Boolean checkMessageWarning(String messageText, String emMessageText, Boolean multiLine) throws Exception {
//        boolean passed = checkMessage(messageText, emMessageText, "warning", multiLine);
//        verifyTrue(passed);
//        return passed;
//    }
//
//    /**
//     * Check to see if an warning message is displayed
//     *
//     * @author MPLC
//     *
//     * @param messageText The text that you are expecting to see
//     * @param multiLine Set to true if the text is split over multiple lines
//     * @return True if message is found, false if not.
//     * @throws Exception
//     */
//    public Boolean checkMessageWarning(String messageText, Boolean multiLine) throws Exception {
//        boolean passed = checkMessage(messageText, null, "warning", multiLine);
//        verifyTrue(passed);
//        return passed;
//    }
//
///**
// * This function will use the Drupal 6 admin menu bar to navigate you to an admin page.
// * This function will actually emulate a user hovering their mouse of the various menu elements before clicking on the selection.
// *
// * @param menuPath ArrayList holding the menu headings, and final link that you want to navigate to
// * @param sDirectUrlPath the url path to the page. in case of a js failure
// * @throws Exception
// */
//public void navigateDrupalAdminMenu(ArrayList menuPath, String sDirectUrlPath) throws Exception {
//    try {
//        String menuXPath = "//div[@id='admin-menu']";
//        verifyTrue("BLOCKER: NO BLACK MENU BAR FOR ADMIN USERS!", isElementPresent(menuXPath));
//        String menuHoverPath = menuXPath;
//        for (int i = 0; i < menuPath.size(); i++) {
//            if (i != 0) {
//                menuXPath += "/..";
//                menuHoverPath += "/..";
//            }
//            if (menuPath.get(i).toString().contains("'")) {
//                menuXPath += "/descendant::ul/li/a[.=\"" + menuPath.get(i) + "\"]";
//                menuHoverPath += "/descendant::ul/li[a[.=\"" + menuPath.get(i) + "\"]]";
//            } else {
//                menuXPath += "/descendant::ul/li/a[.='" + menuPath.get(i) + "']";
//                menuHoverPath += "/descendant::ul/li[a[.='" + menuPath.get(i) + "']]";
//            }
//            Locatable hoverPath = (Locatable) driver.findElement(By.xpath(menuHoverPath));
//            mouse.mouseMove(hoverPath.getCoordinates());
//        }
//        driver.findElement(By.xpath(menuXPath)).click();
//    } catch (Exception x) {
//        //This will use a direct link to navigate you to the correct page anyway so that the test can continue
//        LOGGER.debug("JS Error: Unable to utilise the Drupal menu bar, using direct URL '" + settings.getHomePage(settings.getCurrentlySelectedSite()) + sDirectUrlPath + "' instead.");
//        driver.get(settings.getHomePage(settings.getCurrentlySelectedSite()) + sDirectUrlPath);
//        //If you want to throw an error here because you could not navigate to the page usng the menu uncomment the line below
//        //throw new Exception(x);
//    }
//}
//
///**
// * Example usage of navigateDrupalAdminMenu shown below.
// * This will hover over Site  Building, then hover over Menus and Then hover over and click upon Footer.
// *
// * TODO tweak this to work with a base D6 install
// * @throws Exception
// */
//public void editFooter() throws Exception {
//    ArrayList dynamicMenu = new ArrayList();
//    dynamicMenu.add("Site building");
//    dynamicMenu.add("Menus");
//    dynamicMenu.add("Footer");
//    navigateDrupalAdminMenu(dynamicMenu, "/admin/build/menu-customize/menu-doc-footer");
//}
//    public static String loginFormLink = null;  //Ignored if null, Use this if you have to click on a link to log in.
//    public static String usernameXPath = "//input[@id='edit-name']";
//    public static String passwordXPath = "//input[@id='edit-pass']";
//    public static String submitButtonXPath = "//input[@id='edit-submit']";
//    public static String submitFormXPath = "//form[@id='user-login']";
//    public static String usernameDisplay = "";//XPath to an element that displays username after a successful login.
//    public static String logoutLinkXPath = "//a[@href='/logout']"; //link that is displayed to log you out once you are logged in.
//    public static String acceptTermsXPath = null; //Ignored if null, if you are expecting to accept T&C on login enter a value here.
//    public static String acceptTermsFormXPath = null; //Ignored if null, if you are expecting to accept T&C on login enter a value here.
//
//    /**
//     * Generic login function.
//     * This function is used to log in a specific user.
//     * The username supplied to this function does not have an env token appended to it
//     *
//     * @param passedUserName Username that will be used to log in
//     * @param passedPassword Password that will be used to log in
//     *
//     * @return the name of the logged in user (So that you can check who you logged in as)
//     * @throws Exception
//     */
//    public synchronized String genericDrupalLogIn(String passedUserName, String passedPassword) throws Exception {
//        String loggedInAs = "";
//        if (loginFormLink != null) {
//            driver.findElement(By.xpath(loginFormLink)).click();
//        }
//        waitForElement(usernameXPath);
//        assertTrue(isElementPresent(usernameXPath));
//        driver.findElement(By.xpath(usernameXPath)).clear();
//        driver.findElement(By.xpath(usernameXPath)).sendKeys(passedUserName);
//        assertTrue(isElementPresent(passwordXPath));
//        driver.findElement(By.xpath(passwordXPath)).clear();
//        driver.findElement(By.xpath(passwordXPath)).sendKeys(passedPassword);
//        //Check that a submit button is available (End users will not be able to login without this)
//        verifyTrue(isElementPresent(submitButtonXPath));
//        //Name of the login form
//        driver.findElement(By.xpath(submitFormXPath)).submit();
//        //Handle T&C
//        if (acceptTermsXPath != null) {
//            if (isElementPresent(acceptTermsXPath)) {
//                if (driver.findElement(By.xpath(acceptTermsXPath)).isSelected()) {
//                    driver.findElement(By.xpath(acceptTermsXPath)).click();
//                }
//                driver.findElement(By.xpath(acceptTermsFormXPath)).submit();
//            } else {
//                LOGGER.debug("Unable to find Terms & Conditions Checkbox!");
//            }
//        }
//        try {
//            loggedInAs = driver.findElement(By.xpath(usernameDisplay)).getText();
//            settings.loggedIn(true);
//        } catch (Exception ex) {
//            LOGGER.error("Unable to detect your logged in name... Looks like the login process failed?");
//            if (!isElementPresent(logoutLinkXPath)) {
//                LOGGER.error("Unable to find a 'Log out' link...  Looks like you are not logged in at all!");
//                settings.loggedIn(false);
//            }
//            loggedInAs = "Unknown UserHandler";
//        }
//        return loggedInAs;
//    }
//
//    /**
//     * Logout of the website you are testing
//     *
//     * @return boolean able to log out?
//     * @throws Exception
//     */
//    public boolean logOut() {
//        if (!isElementPresent(logoutLinkXPath)) {
//            LOGGER.error("Unable to log out, cannot find a logout button!");
//            return false;
//        } else {
//            driver.findElement(By.xpath(logoutLinkXPath)).click();
//        }
//        //TODO Add a check here to ensure that you really have logged out, example text and xpaths below
//        assertEquals(driver.findElement(By.xpath("//div[@id='content']/descendant::h1")).getText(), "You have signed out");
//        assertEquals(driver.findElement(By.xpath("//div[@id='content']/descendant::strong")).getText(), "We hope to see you again soon");
//        settings.loggedIn(false); //The asserts above will prevent this from being actioned if a logout is not successfully detected
//        return true;
//    }
}
