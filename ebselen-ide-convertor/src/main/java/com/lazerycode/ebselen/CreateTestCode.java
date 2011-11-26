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

import com.lazerycode.ebselen.codegenerators.*;
import org.openqa.selenium.Keys;
import com.lazerycode.ebselen.exceptions.IDECommandNotFoundException;
import com.lazerycode.ebselen.handlers.LocatorHandler;

import java.util.concurrent.TimeUnit;

public class CreateTestCode {

    public enum ideCommand {

        ADDLOCATIONSTRATEGY, ADDLOCATIONSTRATEGYANDWAIT, ADDSCRIPT, ADDSCRIPTANDWAIT, ADDSELECTION, ADDSELECTIONANDWAIT, ALLOWNATIVEXPATH, ALLOWNATIVEXPATHANDWAIT, ALTKEYDOWN, ALTKEYDOWNANDWAIT, ALTKEYUP, ALTKEYUPANDWAIT, ANSWERONNEXTPROMPT, ASSERTALERT, ASSERTALERTNOTPRESENT, ASSERTALERTPRESENT, ASSERTALLBUTTONS, ASSERTALLFIELDS, ASSERTALLLINKS, ASSERTALLWINDOWIDS, ASSERTALLWINDOWNAMES, ASSERTALLWINDOWTITLES, ASSERTATTRIBUTE, ASSERTATTRIBUTEFROMALLWINDOWS, ASSERTBODYTEXT, ASSERTCHECKED, ASSERTCONFIRMATION, ASSERTCONFIRMATIONNOTPRESENT, ASSERTCONFIRMATIONPRESENT, ASSERTCOOKIE, ASSERCOOKIEBYNAME, ASSERTCOOKIENOTPRESENT, ASSERTCOOKIEPRESENT, ASSERTCSSCOUNT, ASSERTCURSORPOSITION, ASSERTEDITABLE, ASSERTELEMENTHEIGHT, ASSERTELEMENTINDEX, ASSERTELEMENTNOTPRESENT, ASSERTELEMENTPOSITIONLEFT, ASSERTELEMENTPOSITIONTOP, ASSERTELEMENTPRESENT, ASSETELEMENTWIDTH, ASSERTEVAL, ASSERTEXPRESSION, ASSERTHTMLSOURCE, ASSERTLOCATION, ASSERTMOUSESPEED, ASSERTNOTALERT, ASSERTNOTALLBUTTONS, ASSERTNOTALLFIELDS, ASSERTNOTALLLINKS, ASSERTNOTALLWINDOWIDS, ASSERTNOTALLWINDOWNAMES, ASSERTNOTALLWINDOWTITLES, ASSERTNOTATTRIBUTE, ASSERTNOTATTRIBUTEFROMALLWINDOWS, ASSERTNOTBODYTEXT, ASSERTNOTCHECKED, ASSERTNOTCONFIRMATION, ASSERTNOTCOOKIE, ASSERTNOTCOOKIEBYNAME, ASSERTNOTCSSCOUNT, ASSERTNOTCURSORPOSITION, ASSERTNOTEDITABLE, ASSERTNOTELEMENTHEIGHT, ASSERTNOTELEMENTINDEX, ASSERTNOTELEMENTPOSITIONLEFT, ASSERTNOTELEMENTPOSITIONTOP, ASSERTNOTELEMENTWIDTH, ASSERTNOTEVAL, ASSERTNOTEXPRESSION, ASSERTNOTHTMLSOURCE, ASSERTNOTLOCATION, ASSERTNOTMOUSESPEED, ASSERTNOTORDERED, ASSERTNOTPROMPT, ASSERTNOTSELECTOPTIONS, ASSERTNOTSELECTEDID, ASSERTNOTSELECTEDIDS, ASSERTNOTSELECTEDINDEX, ASSERTNOTSELECTEDINDEXES, ASSERTNOTSELECTELABEL, ASSERTNOTSELECTEDLABELS, ASSERTNOTSELECTEDVALUE, ASSERTNOTSELECTEDVALUES, ASSERTNOTSOMETHINGSELECTED, ASSERTNOTSPEED, ASSERTNOTTABLE, ASSERTNOTTEXT, ASSERTNOTTITLE, ASSERTNOTVALUE, ASSERTNOTVISIBLE, ASSERTNOTWHETHERTHISFRAMEMATCHFRAMEEXPRESSION, ASSERTNOTWHETHERTHISWINDOWMATCHWINDOWEXPRESSION, ASSERTNOTXPATHCOUNT, ASSERTORDERED, ASSERTPROMPT, ASSERTPROMPTNOTPRESENT, ASSERTPROMPTPRESENT, ASSERTSELECTOPTIONS, ASSERTSELECTEDID, ASSERTSELECTEDIDS, ASSERTSELECTEDINDEX, ASSERTSELECTEDINDEXES, ASSERTSELECTEDLABEL, ASSERTSELECTEDLABELS, ASSERTSELECTEDVALUE, ASSERTSELECTEDVALUES, ASSERTSOMETHINGSELECTED, ASSERTSPEED, ASSERTTABLE, ASSERTTEXT, ASSERTTEXTNOTPRESENT, ASSERTTEXTPRESENT, ASSERTTITLE, ASSERTVALUE, ASSERTVISIBLE, ASSERTWHETHERTHISFRAMEMATCHEFRAMEEXPRESSION, ASSERTWHETHERTHISWINDOWMATCHEWINDOWEXPRESSION, ASSERTXPATHCOUNT, ASSIGNID, ASSIGNIDANDWAIT, CAPTUREENTIREPAGESCREENSHOT, CAPTUREENTIREPAGESCREENSHOTANDWAIT, CHECK, CHECKANDWAIT, CHOOSECANCELONNEXTCONFIRMATION, CHOOSEOKONNEXTCONFIRMATION, CHOOSEOKONNEXTCONFIRMATIONANDWAIT, CLICK, CLICKANDWAIT, CLICKAT, CLICKATANDWAIT, CLOSE, CONTEXTMENU, CONTEXTMENUANDWAIT, CONTEXTMENUAT, CONTEXTMENUATANDWAIT, CREATECOOKIE, CREATECOOKIEANDWAIT, DELETEALLVISIBLECOOKIES, DELETEALLVISIBLECOOKIESANDWAIT, DELETECOOKIE, DELETECOOKIEANDWAIT, DESELECTPOPUP, DESELECTPOPUPANDWAIT, DOUBLECLICK, DOUBLECLICKANDWAIT, DOUBLECLICKAT, DOUBLECLICKATANDWAIT, DRAGANDDROP, DRAGANDDROPANDWAIT, DRAGANDDROPTOOBJECT, DRAGANDDROPTOOBJECTANDWAIT, DRAGDROP, DRAGDROPANDWAIT, ECHO, FIREEVENT, FIREEVENTANDWAIT, FOCUS, FOCUSANDWAIT, GOBACK, GOBACKANDWAIT, HIGHLIGHT, HIGHLIGHTANDWAIT, IGNOREATTRIBUTESWITHOUTVALUE, IGNOREATTRIBUTESWITHOUTVALUEANDWAIT, KEYDOWN, KEYDOWNANDWAIT, KEYPRESS, KEYPRESSANDWAIT, KEYUP, KEYUPANDWAIT, METAKEYDOWN, METAKEYDOWNANDWAIT, METAKEYUP, METAKEYUPANDWAIT, MOUSEDOWN, MOUSEDOWNANDWAIT, MOUSEDOWNAT, MOUSEDOWNATANDWAIT, MOUSEDOWNRIGHT, MOUSEDOWNRIGHTANDWAIT, MOUSEDOWNRIGHTAT, MOUSEDOWNRIGHTATANDWAIT, MOUSEMOVE, MOUSEMOVEANDWAIT, MOUSEMOVEAT, MOUSEMOVEATANDWAIT, MOUSEOUT, MOUSEOUTANDWAIT, MOUSEOVER, MOUSEOVERANDWAIT, MOUSEUP, MOUSEUPANDWAIT, MOUSEUPAT, MOUSEUPATANDWAIT, MOUSEUPRIGHT, MOUSEUPRIGHTANDWAIT, MOUSEUPRIGHTAT, MOUSEUPRIGHTATANDWAIT, OPEN, OPENWINDOW, OPENWINDOWANDWAIT, PAUSE, REFRESH, REFRESHANDWAIT, REMOVEALLSELECTIONS, REMOVEALLSELECTIONSANDWAIT, REMOVESCRIPT, REMOVESCRIPTANDWAIT, REMOVESELECTION, REMOVESELECTIONANDWAIT, ROLLUP, ROLLUPANDWAIT, RUNSCRIPT, RUNSCRIPTANDWAIT, SELECT, SELECTANDWAIT, SELECTFRAME, SELECTPOPUP, SELECTPOPUPANDWAIT, SELECTWINDOW, SETBROWSERLOGLEVEL, SETBROWSERLOGLEVELANDWAIT, SETMOUSESPEED, SETMOUSESPEEDANDWAIT, SETSPEED, SETSPEEDANDWAIT, SETTIMEOUT, SHIFTKEYDOWN, SHIFTKEYDOWNANDWAIT, SHIFTKEYUP, SHIFTKEYUPANDWAIT, STORE, STOREALERT, STOREALERTPRESENT, STOREALLBUTTONS, STOREALLFIELDS, STOREALLLINKS, STOREALLWINDOWIDS, STOREALLWINDOWNAMES, STOREALLWINDOWTITLES, STOREATTRIBUTE, STOREATTRIBUTEFROMALLWINDOWS, STOREBODYTEXT, STORECHECKED, STORECONFIRMATION, STORECONFIRMATIONPRESENT, STORECOOKIE, STORECOOKIEBYNAME, STORECOOKIEPRSENT, STORECSSCOUNT, STORECURSORPOSITION, STOREEDITABLE, STOREELEMENTHEIGHT, STOREELEMENTINDEX, STOREELEMENTPOSITIONLEFT, STOREELEMENTPOSITIONTOP, STOREELEMENTPRESENT, STOREELEMENTWIDTH, STOREEVAL, STOREEXPRESSION, STOREHTMLSOURCE, STORELOCATION, STOREMOUSESPEED, STOREORDERED, STOREPROMPT, STOREPROMPTPRESENT, STORESELECTOPTIONS, STORESELECTEDID, STORESELECTEDIDS, STORESELECTEDINDEX, STORESELECTEDINDEXES, STORESELECTEDLABEL, STORESELECTEDLABELS, STORESELECTEDVALUE, STORESELECTEDVALUES, STORESOMETHINGSELECTED, STORESPEED, STORETABLE, STORETEXT, STORETEXTPRESENT, STORETITLE, STOREVALUE, STOREVISIBLE, STOREWHETHERTHISFRAMEMATCHFRAMEEXPRESSION, STOREWHETHERTHISWINDOWMATCHWINDOWEXPRESSION, STOREXPATHCOUNT, SUBMIT, SUBMITANDWAIT, TYPE, TYPEANDWAIT, TYPEKEYS, TYPEKEYSANDWAIT, UNCHECK, UNCHECKANDWAIT, USEXPATHLIBRARY, USEXPATHLIBRARYANDWAIT, VERIFYALERT, VERIFYALERTNOTPRESENT, VERIFYALERTPRESENT, VERIFYALLBUTTONS, VERIFYALLFIELDS, VERIFYALLLINKS, VERIFYALLWINDOWIDS, VERIFYALLWINDOWNAMES, VERIFYALLWINDOWTITLES, VERIFYATTRIBUTE, VERIFYATTRIBUTEFROMALLWINDOWS, VERIFYBODYTEXT, VERIFYCHECKED, VERIFYCONFIRMATION, VERIFYCONFIRMATIONNOTPRESENT, VERIFYCONFIRMATIONPRESENT, VERIFYCOOKIE, VERIFYCOOKIEBYNAME, VERIFYCOOKIENOTPRESENT, VERIFYCOOKIEPRESENT, VERIFYCSSCOUNT, VERIFYCURSORPOSITION, VERIFYEDITABLE, VERIFYELEMENTHEIGHT, VERIFYELEMENTINDEX, VERIFYELEMENTNOTPRESENT, VERIFYELEMENTPOSITIONLEFT, VERIFYELEMENTPOSITIONTOP, VERIFYELEMENTPRESENT, VERIFYELEMENTWIDTH, VERIFYEVAL, VERIFYEXPRESSION, VERIFYHTMLSOURCE, VERIFYLOCATION, VERIFYMOUSESPEED, VERIFYNOTALERT, VERIFYNOTALLBUTTONS, VERIFYNOTALLFIELDS, VERIFYNOTALLLINKS, VERIFYNOTALLWINDOWIDS, VERIFYNOTALLWINDOWNAMES, VERIFYNOTALLWINDOWTITLES, VERIFYNOTATTRIBUTE, VERIFYNOTATTRIBUTEFROMALLWINDOWS, VERIFYNOTBODYTEXT, VERIFYNOTCHECKED, VERIFYNOTCONFIRMATION, VERIFYNOTCOOKIE, VERIFYNOTCOOKIEBYNAME, VERIFYNOTCSSCOUNT, VERIFYNOTCURSORPOSITION, VERIFYNOTEDITABLE, VERIFYNOTELEMENTHEIGHT, VERIFYNOTELEMENTINDEX, VERIFYNOTELEMENTPOSITIONLEFT, VERIFYNOTELEMENTPOSITIONTOP, VERIFYNOTELEMENTWIDTH, VERIFYNOTEVAL, VERIFYNOTEXPRESSION, VERIFYNOTHTMLSOURCE, VERIFYNOTLOCATION, VERIFYNOTMOUSESPEED, VERIFYNOTORDERED, VERIFYNOTPROMPT, VERIFYNOTSELECTOPTIONS, VERIFYNOTSELECTEDID, VERIFYNOTSELECTEDIDS, VERIFYNOTSELECTEDINDEX, VERIFYNOTSELECTEDINDEXES, VERIFYNOTSELECTEDLABEL, VERIFYNOTSELECTEDLABELS, VERIFYNOTSELECTEDVALUE, VERIFYNOTSELECTEDVALUES, VERIFYNOTSOMTHINGSELECTED, VERIFYNOTSPEED, VERIFYNOTTABLE, VERIFYNOTTEXT, VERIFYNOTTITLE, VERIFYNOTVALUE, VERIFYNOTVISIBLE, VERIFYNOTWHETHERTHISFRAMEMATCHFRAMEEXPRESSION, VERIFYNOTWHETHERTHISWINDOWMATCHWINDOWEXPRESSION, VERIFYNOTXPATHCOUNT, VERIFYORDERED, VERIFYPROMPT, VERIFYPROMPTNOTPRESENT, VERIFYPROMPTPRESENT, VERIFYSELECTOPTIONS, VERIFYSELECTEDID, VERIFYSELECTEDIDS, VERIFYSELECTEDINDEX, VERIFYSELECTEDINDEXES, VERIFYSELECTEDLABEL, VERIFYSELECTEDLABELS, VERIFYSELECTEDVALUE, VERIFYSELECTEDVALUES, VERIFYSOMETHINGSELECTED, VERIFYSPEED, VERIFYTABLE, VERIFYTEXT, VERIFYTEXTNOTPRESENT, VERIFYTEXTPRESENT, VERIFYTITLE, VERIFYVALUE, VERIFYVISIBLE, VERIFYWHETHERTHISFRAMEMATCHFRAMEEXPRESSION, VERIFYWHETHERTHISWINDOWMATCHWINDOWEXPRESSION, VERIFYXPATHCOUNT, WAITFORALERT, WAITFORALERTNOTPRESENT, WAITFORALERTPRESENT, WAITFORALLBUTTONS, WAITFORALLFIELDS, WAITFORALLLINKS, WAITFORALLWINDOWIDS, WAITFORALLWINDOWNAMES, WAITFORALLWINDOWTITLES, WAITFORATTRIBUTE, WAITFORATTRIBUTEFROMALLWINDOWS, WAITFORBODYTEXT, WAITFORCHECKED, WAITFORCONDITION, WAITFORCONFIRMATION, WAITFORCONFIRMATIONNOTPRESENT, WAITFORCONFIRMATIONPRESENT, WAITFORCOOKIE, WAITFORCOOKIEBYNAME, WAITFORCOOKIENOTPRESENT, WAITFORCOOKIEPRESENT, WAITFORCSSCOUNT, WAITFORCURSORPOSITION, WAITFOREDITABLE, WAITFORELEMENTHEIGHT, WAITFORELEMENTINDEX, WAITFORELEMENTNOTPRESENT, WAITFORELEMENTPOSITIONLEFT, WAITFORELEMENTPOSITIONTOP, WAITFORELEMENTPRESENT, WAITFORELEMENTWIDTH, WAITFOREVAL, WAITFOREXPRESSION, WAITFORFRAMETOLOAD, WAITFORHTMLSOURCE, WAITFORLOCATION, WAITFORMOUSESPEED, WAITFORNOTALERT, WAITFORNOTALLBUTTONS, WAITFORNOTALLFIELDS, WAITFORNOTALLLINKS, WAITFORNOTALLWINDOWIDS, WAITFORNOTALLWINDOWNAMES, WAITFORNOTALLWINDOWTITLES, WAITFORNOTATTRIBUTE, WAITFORNOTATTRIBUTEFROMALLWINDOWS, WAITFORNOTBODYTEXT, WAITFORNOTCHECKED, WAITFORNOTCONFIRMATION, WAITFORNOTCOOKIE, WAITFORNOTCOOKIEBYNAME, WAITFORNOTCSSCOUNT, WAITFORNOTCURSORPOSITION, WAITFORNOTEDITABLE, WAITFORNOTELEMENTHEIGHT, WAITFORNOTELEMENTINDEX, WAITFORNOTELEMENTPOSITIONLEFT, WAITFORNOTELEMENTPOSITIONTOP, WAITFORNOTELEMENTWIDTH, WAITFORNOTEVAL, WAITFORNOTEXPRESSION, WAITFORNOTHTMLSOURCE, WAITFORNOTLOCATION, WAITFORNOTMOUSESPEED, WAITFORNOTORDERED, WAITFORNOTPROMPT, WAITFORNOTSELECTOPTIONS, WAITFORNOTSELECTEDID, WAITFORNOTSELECTEDIDS, WAITFORNOTSELECTEDINDEX, WAITFORNOTSELECTEDINDEXES, WAITFORNOTSELECTEDLABEL, WAITFORNOTSELECTEDLABELS, WAITFORNOTSELECTEDVALUE, WAITFORNOTSELECTEDVALUES, WAITFORNOTSOMETHINGSELECTED, WAITFORNOTSPEED, WAITFORNOTTABLE, WAITFORNOTTEXT, WAITFORNOTTITLE, WAITFORNOTVALUE, WAITFORNOTVISIBLE, WAITFORNOTWHETHERTHISFRAMEMATCHFRAMEEXPRESSION, WAITFORNOTWHETHERTHISWINDOWMATCHWINDOWEXPRESSION, WAITFORNOTXPATHCOUNT, WAITFORORDERED, WAITFORPAGETOLOAD, WAITFORPOPUP, WAITFORPROMPT, WAITFORPROMPTNOTPRESENT, WAITFORPROMPTPRESENT, WAITFORSELECTOPTIONS, WAITFORSELECTEDID, WAITFORSELECTEDIDS, WAITFORSELECTEDINDEX, WAITFORSELECTEDINDEXES, WAITFORSELECTEDLABEL, WAITFORSELECTEDLABELS, WAITFORSELECTEDVALUE, WAITFORSELECTEDVALUES, WAITFORSOMETHINGSELECTED, WAITFORSPEED, WAITFORTABLE, WAITFORTEXT, WAITFORTEXTNOTPRESENT, WAITFORTEXTPRESENT, WAITFORTITLE, WAITFORVALUE, WAITFORVISIBLE, WAITFORWHETERTHISFRAMEMATCHFRAMEEXPRESSION, WAITFORWHETERTHISWINDOWMATCHWINDOWEXPRESSION, WAITFORXPATHCOUNT, WINDOWFOCUS, WINDOWFOCUSANDWAIT, WINDOWMAXIMIZE, WINDOWMAXIMIZEANDWAIT
    }

    ;

    private WebDriverGenerator driver = new WebDriverGenerator("driver");
    private EbselenGenerator ebselen = new EbselenGenerator("ebselen");
    private ActionsGenerator action = new ActionsGenerator("mouse", "keyboard", "builder");
    private StandardCommandsGenerator com = new StandardCommandsGenerator();
    private SeleniumSupportGenerator select = new SeleniumSupportGenerator();
    public LocatorHandler loc = new LocatorHandler();

    // The java template we use to create a java file has static HashMap called 'storedVariable' defined by default
    public <V> String store(String id, V value) {
        return "storedVariables.put(" + id + ", " + value + ")";
    }

    public String createWebElement(String variableName, String webElement) {
        return "WebElement " + variableName + " = " + webElement;
    }

    public String returnCoordinates(String xValue, String yValue) {
        return "(Coordinates) new Point(" + xValue + ", " + yValue + ")";
    }

    public String returnCoordinates(String Point) {
        return "(Coordinates) " + Point;
    }

    public String coords(String offsetXY, String locator) {
        String[] coordinates = offsetXY.split(",", 2);
        String xCoordinates = driver.findElement(loc.locatorAsString(locator)).getLocation().getX() + " + " + coordinates[0].trim();
        String yCoordinates = driver.findElement(loc.locatorAsString(locator)).getLocation().getY() + " + " + coordinates[1].trim();
        return returnCoordinates(xCoordinates, yCoordinates);
    }

    public String coords(String locator) {
        String xCoordinates = driver.findElement(loc.locatorAsString(locator)).getLocation().getX();
        String yCoordinates = driver.findElement(loc.locatorAsString(locator)).getLocation().getY();
        return returnCoordinates(xCoordinates, yCoordinates);
    }

    public String convertCommandToEbselenCode(String seleniumCommand, String target, String value) throws Exception {
        ideCommand command;
        try {
            command = ideCommand.valueOf(seleniumCommand.trim().toUpperCase());
        } catch (Exception ex) {
            throw new IDECommandNotFoundException("Unable to match command to known list of commands!");
        }
        //TODO remove variable below
        String testData = "";
        switch (command) {
            case ADDLOCATIONSTRATEGY:
            case ADDLOCATIONSTRATEGYANDWAIT:
                return "LOGGER.warn(\"Custom locator strategies are not supported!\")";
            case ADDSCRIPT:
            case ADDSCRIPTANDWAIT:
//                    LOGGER.error("Function not implemented yet.  Raise a request to get it implemented");
                //TODO
            case ADDSELECTION:
            case ADDSELECTIONANDWAIT:
                return select.selectByVisibleText(target, value);
            case ALLOWNATIVEXPATH:
            case ALLOWNATIVEXPATHANDWAIT:
                return "LOGGER.warn(\"Modifying the XPath implementation mid test is not supported!\")";
            case ALTKEYDOWN:
            case ALTKEYDOWNANDWAIT:
                return action.keyboard().pressKey(Keys.ALT);
            case ALTKEYUP:
            case ALTKEYUPANDWAIT:
                return action.keyboard().releaseKey(Keys.ALT);
            case ANSWERONNEXTPROMPT:
                return driver.switchTo().alert().sendKeys(target);
            case ASSERTALERT:
                return com.assertEquals(driver.switchTo().alert().getText(), value);
            case ASSERTALERTNOTPRESENT:

                //TODO
            case ASSERTALERTPRESENT:
                //TODO
            case ASSERTALLBUTTONS:
                //TODO
            case ASSERTALLFIELDS:
                //TODO
            case ASSERTALLLINKS:
                //TODO
            case ASSERTALLWINDOWIDS:
                //TODO
            case ASSERTALLWINDOWNAMES:
                //TODO
            case ASSERTALLWINDOWTITLES:
                //TODO
            case ASSERTATTRIBUTE:
                //TODO
            case ASSERTATTRIBUTEFROMALLWINDOWS:
                //TODO
            case ASSERTBODYTEXT:
                //TODO
            case ASSERTCHECKED:
                return com.assertTrue(ebselen.element().isChecked(target));
            case ASSERTCONFIRMATION:
                //TODO
            case ASSERTCONFIRMATIONNOTPRESENT:
                //TODO
            case ASSERTCONFIRMATIONPRESENT:
                //TODO
            case ASSERTCOOKIE:

                //TODO
            case ASSERCOOKIEBYNAME:
                return com.assertEquals(driver.manage().getCookieNamed(target), value);
            case ASSERTCOOKIENOTPRESENT:
                //TODO
            case ASSERTCOOKIEPRESENT:
                //TODO
            case ASSERTCSSCOUNT:
                return com.assertEquals(driver.findElements(loc.locatorAsString(target)).size(), value);
            case ASSERTCURSORPOSITION:
                //TODO only for input element of textarea gets numerical position of cursor in field
            case ASSERTEDITABLE:
                return com.assertTrue(driver.findElement(loc.locatorAsString(target)).isEnabled());
            case ASSERTELEMENTHEIGHT:
                return com.assertEquals(driver.findElement(loc.locatorAsString(target)).getCssValue("height"), value);
            case ASSERTELEMENTINDEX:
                //TODO
            case ASSERTELEMENTNOTPRESENT:
                return com.assertFalse(ebselen.element().doesElementExist(loc.locatorAsString(target)));
            case ASSERTELEMENTPOSITIONLEFT:
                //TODO
            case ASSERTELEMENTPOSITIONTOP:
                //TODO
            case ASSERTELEMENTPRESENT:
                return com.assertTrue(ebselen.element().doesElementExist(loc.locatorAsString(target)));
            case ASSETELEMENTWIDTH:
                return com.assertEquals(driver.findElement(loc.locatorAsString(target)).getCssValue("width"), value);
            case ASSERTEVAL:
                //TODO
            case ASSERTEXPRESSION:
                //TODO
            case ASSERTHTMLSOURCE:
                //TODO
            case ASSERTLOCATION:
                return com.assertEquals(driver.getCurrentUrl(), target);
            case ASSERTMOUSESPEED:
                //TODO
            case ASSERTNOTALERT:
                //TODO
            case ASSERTNOTALLBUTTONS:
                //TODO
            case ASSERTNOTALLFIELDS:
                //TODO
            case ASSERTNOTALLLINKS:
                //TODO
            case ASSERTNOTALLWINDOWIDS:
                //TODO
            case ASSERTNOTALLWINDOWNAMES:
                //TODO
            case ASSERTNOTALLWINDOWTITLES:
                //TODO
            case ASSERTNOTATTRIBUTE:
                //TODO
            case ASSERTNOTATTRIBUTEFROMALLWINDOWS:
                //TODO
            case ASSERTNOTBODYTEXT:
                //TODO
            case ASSERTNOTCHECKED:
                return com.assertFalse(ebselen.element().isChecked(target));
            case ASSERTNOTCONFIRMATION:
                //TODO
            case ASSERTNOTCOOKIE:
                //TODO
            case ASSERTNOTCOOKIEBYNAME:
                //TODO
            case ASSERTNOTCSSCOUNT:
                return com.assertNotEquals(driver.findElements(loc.locatorAsString(target)).size(), value);
            case ASSERTNOTCURSORPOSITION:
                //TODO
            case ASSERTNOTEDITABLE:
                return com.assertFalse(driver.findElement(loc.locatorAsString(target)).isEnabled());
            case ASSERTNOTELEMENTHEIGHT:
                //TODO
            case ASSERTNOTELEMENTINDEX:
                //TODO
            case ASSERTNOTELEMENTPOSITIONLEFT:
                //TODO
            case ASSERTNOTELEMENTPOSITIONTOP:
                //TODO
            case ASSERTNOTELEMENTWIDTH:
                //TODO
            case ASSERTNOTEVAL:
                //TODO
            case ASSERTNOTEXPRESSION:
                //TODO
            case ASSERTNOTHTMLSOURCE:
                //TODO
            case ASSERTNOTLOCATION:
                //TODO
            case ASSERTNOTMOUSESPEED:
                //TODO
            case ASSERTNOTORDERED:
                //TODO
            case ASSERTNOTPROMPT:
                //TODO
            case ASSERTNOTSELECTOPTIONS:
                //TODO
            case ASSERTNOTSELECTEDID:
                //TODO
            case ASSERTNOTSELECTEDIDS:
                //TODO
            case ASSERTNOTSELECTEDINDEX:
                //TODO
            case ASSERTNOTSELECTEDINDEXES:
                //TODO
            case ASSERTNOTSELECTELABEL:
                //TODO
            case ASSERTNOTSELECTEDLABELS:
                //TODO
            case ASSERTNOTSELECTEDVALUE:
                //TODO
            case ASSERTNOTSELECTEDVALUES:
                //TODO
            case ASSERTNOTSOMETHINGSELECTED:
                //TODO
            case ASSERTNOTSPEED:
                //TODO
            case ASSERTNOTTABLE:
                //TODO
            case ASSERTNOTTEXT:
                //TODO
            case ASSERTNOTTITLE:
                //TODO
            case ASSERTNOTVALUE:
                //TODO
            case ASSERTNOTVISIBLE:
                //TODO
            case ASSERTNOTWHETHERTHISFRAMEMATCHFRAMEEXPRESSION:
                //TODO
            case ASSERTNOTWHETHERTHISWINDOWMATCHWINDOWEXPRESSION:
                //TODO
            case ASSERTNOTXPATHCOUNT:
                return com.assertNotEquals(value, driver.findElements(loc.locatorAsString(target)).size());
            case ASSERTORDERED:
                //TODO
            case ASSERTPROMPT:
                //TODO
            case ASSERTPROMPTNOTPRESENT:
                //TODO
            case ASSERTPROMPTPRESENT:
                //TODO
            case ASSERTSELECTOPTIONS:
                //TODO
            case ASSERTSELECTEDID:
                //TODO
            case ASSERTSELECTEDIDS:
                //TODO
            case ASSERTSELECTEDINDEX:
                //TODO
            case ASSERTSELECTEDINDEXES:
                //TODO
            case ASSERTSELECTEDLABEL:
                //TODO
            case ASSERTSELECTEDLABELS:
                //TODO
            case ASSERTSELECTEDVALUE:
                //TODO
            case ASSERTSELECTEDVALUES:
                //TODO
            case ASSERTSOMETHINGSELECTED:
                //TODO
            case ASSERTSPEED:
                //TODO
            case ASSERTTABLE:
                //TODO
            case ASSERTTEXT:
                return com.assertEquals(driver.findElement(loc.locatorAsString(target)).getText(), value);
            case ASSERTTEXTNOTPRESENT:
                testData += "assertFalse(isElementPresent(\"//*[contains(, '" + value + "')]\"));";
                //TODO
            case ASSERTTEXTPRESENT:
                //TODO
            case ASSERTTITLE:
                return com.assertEquals(driver.findElement(loc.locatorAsString("tag=title")).getText(), value);
            case ASSERTVALUE:
                return com.assertEquals(value, driver.findElement(loc.locatorAsString(target)).getAttribute("value"));
            case ASSERTVISIBLE:
                return com.assertTrue(driver.findElement(loc.locatorAsString(target)).isDisplayed());
            case ASSERTWHETHERTHISFRAMEMATCHEFRAMEEXPRESSION:
                //TODO
            case ASSERTWHETHERTHISWINDOWMATCHEWINDOWEXPRESSION:
                //TODO
            case ASSERTXPATHCOUNT:
                return com.assertEquals(value, driver.findElements(loc.locatorAsString(target)).size());
            case ASSIGNID:
            case ASSIGNIDANDWAIT:
                return createWebElement(value, driver.findElement(loc.locatorAsString(target)).toString());
            case CAPTUREENTIREPAGESCREENSHOT:
            case CAPTUREENTIREPAGESCREENSHOTANDWAIT:
                return ebselen.window().takeScreenshot();
            case CHECK:
            case CHECKANDWAIT:
                return ebselen.element().check(target);
            case CHOOSECANCELONNEXTCONFIRMATION:
//                return driver.switchTo().alert().dismiss();
                //TODO
            case CHOOSEOKONNEXTCONFIRMATION:
            case CHOOSEOKONNEXTCONFIRMATIONANDWAIT:
//                return driver.switchTo().alert().accept();
                //TODO
            case CLICK:
            case CLICKANDWAIT:
                return driver.findElement(loc.locatorAsString(target)).click();
            case CLICKAT:
            case CLICKATANDWAIT:
                action.mouse().click(coords(value, target));
            case CLOSE:
                return driver.close();
            case CONTEXTMENU:
            case CONTEXTMENUANDWAIT:
                return action.mouse().contextClick(returnCoordinates(driver.findElement(loc.locatorAsString(target)).getLocation().toString()));
            case CONTEXTMENUAT:
            case CONTEXTMENUATANDWAIT:
                return action.mouse().contextClick(coords(value, target));
            case CREATECOOKIE:
            case CREATECOOKIEANDWAIT:
//                driver.manage().addCookie()
                //TODO
            case DELETEALLVISIBLECOOKIES:
            case DELETEALLVISIBLECOOKIESANDWAIT:
                return driver.manage().deleteAllCookies();
            case DELETECOOKIE:
            case DELETECOOKIEANDWAIT:
                return driver.manage().deleteCookieNamed(target);
            case DESELECTPOPUP:
            case DESELECTPOPUPANDWAIT:
                return driver.switchTo().defaultContent();
            case DOUBLECLICK:
            case DOUBLECLICKANDWAIT:
                return action.mouse().doubleClick(returnCoordinates(driver.findElement(loc.locatorAsString(target)).getLocation().toString()));
            case DOUBLECLICKAT:
            case DOUBLECLICKATANDWAIT:
                return action.mouse().doubleClick(coords(value, target));
            case DRAGDROP:
            case DRAGANDDROP:
            case DRAGDROPANDWAIT:
            case DRAGANDDROPANDWAIT:
                String[] dragAndDropCoords = value.split(",", 1);
                String dragAndDropX = driver.findElement(loc.locatorAsString(target)).getLocation().getX() + "+" + dragAndDropCoords[0];
                String dragAndDropY = driver.findElement(loc.locatorAsString(target)).getLocation().getY() + "+" + dragAndDropCoords[1];
//            return action.builder().clickAndHold(driver.findElement(loc.locatorAsString(target)))
//                    .moveByOffset(Integer.parseInt(coords[0]), Integer.parseInt(coords[1])).click();";
                //TODO
            case DRAGANDDROPTOOBJECT:
            case DRAGANDDROPTOOBJECTANDWAIT:
                testData += "coords = value.split(\",\");"
                        + "builder.clickAndHold(driver.findElement(" + loc.locatorAsString(target) + ")).moveByOffset(Integer.parseInt(coords[0]), Integer.parseInt(coords[1])).release(driver.findElement(" + loc.locatorAsString(value) + "));";
                //TODO
            case ECHO:
                return "LOGGER.info(\"{}\", " + value + ");";
            case FIREEVENT:
            case FIREEVENTANDWAIT:
                return ebselen.javascript().triggerJavascriptEvent(value, driver.findElement(loc.locatorAsString(target)).toString());
            case FOCUS:
            case FOCUSANDWAIT:
                //TODO focus on the element found below
                //((Locatable) driver.findElement(" + loc.locatorAsString(target) + ")).getLocationOnScreenOnceScrolledIntoView();
            case GOBACK:
            case GOBACKANDWAIT:
                return driver.navigate().back();
            case HIGHLIGHT:
            case HIGHLIGHTANDWAIT:
                //TODO
            case IGNOREATTRIBUTESWITHOUTVALUE:
            case IGNOREATTRIBUTESWITHOUTVALUEANDWAIT:
                //TODO
            case KEYDOWN:
            case KEYDOWNANDWAIT:
                return action.builder().keyDown(driver.findElement(loc.locatorAsString(target)).toString(), Keys.valueOf(value));
            case KEYPRESS:
            case KEYPRESSANDWAIT:
                return driver.findElement(loc.locatorAsString(target)).sendKeys(value);
            case KEYUP:
            case KEYUPANDWAIT:
                return action.builder().keyUp(driver.findElement(loc.locatorAsString(target)).toString(), Keys.valueOf(value));
            case METAKEYDOWN:
            case METAKEYDOWNANDWAIT:
                return action.builder().keyDown(driver.findElement(loc.locatorAsString(target)).toString(), Keys.META);
            case METAKEYUP:
            case METAKEYUPANDWAIT:
                return action.builder().keyUp(driver.findElement(loc.locatorAsString(target)).toString(), Keys.META);
            case MOUSEDOWN:
            case MOUSEDOWNANDWAIT:
                return action.mouse().mouseDown(returnCoordinates(driver.findElement(loc.locatorAsString(target)).getLocation().toString()));
            case MOUSEDOWNAT:
            case MOUSEDOWNATANDWAIT:
                return action.mouse().mouseDown(coords(value, target));
            case MOUSEDOWNRIGHT:
            case MOUSEDOWNRIGHTANDWAIT:
                //return action.mouse().("(Coordinates)" + driver.findElement(loc.locatorAsString(target)).getLocation());
                //TODO likemousedown but with context
            case MOUSEDOWNRIGHTAT:
            case MOUSEDOWNRIGHTATANDWAIT:
                //TODO
            case MOUSEMOVE:
            case MOUSEMOVEANDWAIT:
                return action.mouse().mouseMove(returnCoordinates(driver.findElement(loc.locatorAsString(target)).getLocation().toString()));
            case MOUSEMOVEAT:
            case MOUSEMOVEATANDWAIT:
                return action.mouse().mouseMove(coords(value, target));
            case MOUSEOUT:
            case MOUSEOUTANDWAIT:
                //TODO
            case MOUSEOVER:
            case MOUSEOVERANDWAIT:
                return action.mouse().mouseMove(returnCoordinates(driver.findElement(loc.locatorAsString(target)).getLocation().toString()));
            case MOUSEUP:
            case MOUSEUPANDWAIT:
                return action.mouse().mouseUp(returnCoordinates(driver.findElement(loc.locatorAsString(target)).getLocation().toString()));
            case MOUSEUPAT:
            case MOUSEUPATANDWAIT:
                return action.mouse().mouseUp(coords(value, target));
            case MOUSEUPRIGHT:
            case MOUSEUPRIGHTANDWAIT:
                //TODO
            case MOUSEUPRIGHTAT:
            case MOUSEUPRIGHTATANDWAIT:
                //TODO
            case OPEN:
                return driver.get(target);
            case OPENWINDOW:
            case OPENWINDOWANDWAIT:
                //TODO
            case PAUSE:
                return "Thread.sleep((long) Integer.parseInt(" + value + "));";
            case REFRESH:
            case REFRESHANDWAIT:
                return driver.navigate().refresh();
            case REMOVEALLSELECTIONS:
            case REMOVEALLSELECTIONSANDWAIT:
                return select.deselectAll(target);
            case REMOVESCRIPT:
            case REMOVESCRIPTANDWAIT:
                //TODO
            case REMOVESELECTION:
            case REMOVESELECTIONANDWAIT:
                return select.deselectByVisibleText(target, value);
            case ROLLUP:
            case ROLLUPANDWAIT:
                //TODO
            case RUNSCRIPT:
            case RUNSCRIPTANDWAIT:
                //TODO
            case SELECT:
            case SELECTANDWAIT:
                return select.selectByVisibleText(target, value);
            case SELECTFRAME:
                return driver.switchTo().frame(driver.findElement(loc.locatorAsString(target)).toString());
            case SELECTPOPUP:
            case SELECTPOPUPANDWAIT:
                //TODO
            case SELECTWINDOW:
                //TODO
            case SETBROWSERLOGLEVEL:
            case SETBROWSERLOGLEVELANDWAIT:
                return "LOGGER.warn(\"Modifying the Log Level mid test is not supported!\")";
            case SETMOUSESPEED:
            case SETMOUSESPEEDANDWAIT:
                //TODO
            case SETSPEED:
            case SETSPEEDANDWAIT:
                //TODO
            case SETTIMEOUT:
                return driver.manage().timeouts().setScriptTimeout(Long.parseLong(value), TimeUnit.MILLISECONDS);
            case SHIFTKEYDOWN:
            case SHIFTKEYDOWNANDWAIT:
                return action.keyboard().pressKey(Keys.SHIFT);
            case SHIFTKEYUP:
            case SHIFTKEYUPANDWAIT:
                return action.keyboard().releaseKey(Keys.SHIFT);
            case STORE:
                return store(value, target);
            case STOREALERT:
                return store(value, driver.switchTo().alert().getText());
            case STOREALERTPRESENT:
                testData += "boolean alertVisible = false;"
                        + "try {"
                        + "driver.switchTo().alert();"
                        + "alertVisible = true;"
                        + "} catch (Exception Ex) {"
                        + "//Alert doesn't exist"
                        + "}"
                        + "storedVariables.put(" + value + ", alertVisible);";
                //TODO
            case STOREALLBUTTONS:
                //TODO check implementation...  The IDE implementation stores ID's, not Elements
                return store(value, driver.findElements(loc.locatorAsString("(//input[@type='button'])|(//button)")).toString());
            case STOREALLFIELDS:
                //TODO check implementation...  The IDE implementation stores ID's, not Elements
                //TODO check if this should just be input[@type='text'] or textarea as well?
                return store(value, driver.findElements(loc.locatorAsString("//input[@type='text']")).toString());
            case STOREALLLINKS:
                //TODO check implementation...  The IDE implementation stores ID's, not Elements
                return store(value, driver.findElements(loc.locatorAsString("tag=a")).toString());
            case STOREALLWINDOWIDS:
            case STOREALLWINDOWNAMES:
            case STOREALLWINDOWTITLES:
                return store(value, driver.getWindowHandles());
            case STOREATTRIBUTE:
                String[] locatorDetails = target.split("@", 1);
                return store(value, driver.findElement(loc.locatorAsString(locatorDetails[0])).getAttribute(locatorDetails[1]));
            case STOREATTRIBUTEFROMALLWINDOWS:
                //TODO
            case STOREBODYTEXT:
                return store(value, driver.findElement(loc.locatorAsString("tag=body")).getText());
            case STORECHECKED:
                return store(value, ebselen.element().isChecked(target));
            case STORECONFIRMATION:
                //TODO
            case STORECONFIRMATIONPRESENT:
                //TODO
            case STORECOOKIE:
                //TODO
            case STORECOOKIEBYNAME:
                //TODO
            case STORECOOKIEPRSENT:
                //TODO
            case STORECSSCOUNT:
                return store(value, driver.findElements(loc.locatorAsString(target)).size());
            case STORECURSORPOSITION:
                //TODO
            case STOREEDITABLE:
                return store(value, driver.findElement(loc.locatorAsString(target)).isEnabled());
            case STOREELEMENTHEIGHT:
                return store(value, driver.findElement(loc.locatorAsString(target)).getSize().getHeight());
            case STOREELEMENTINDEX:
                //TODO
            case STOREELEMENTPOSITIONLEFT:
                //TODO
            case STOREELEMENTPOSITIONTOP:
                //TODO
            case STOREELEMENTPRESENT:
                return store(value, ebselen.element().doesElementExist(loc.locatorAsString(target)));
            case STOREELEMENTWIDTH:
                return store(value, driver.findElement(loc.locatorAsString(target)).getSize().getWidth());
            case STOREEVAL:
                //TODO
            case STOREEXPRESSION:
                //TODO
            case STOREHTMLSOURCE:
                return store(value, driver.getPageSource());
            case STORELOCATION:
                return store(value, driver.getCurrentUrl());
            case STOREMOUSESPEED:
                //TODO
            case STOREORDERED:
                //TODO
            case STOREPROMPT:
                //TODO
            case STOREPROMPTPRESENT:
                //TODO
            case STORESELECTOPTIONS:
                //TODO
            case STORESELECTEDID:
                //TODO
            case STORESELECTEDIDS:
                //TODO
            case STORESELECTEDINDEX:
                //TODO
            case STORESELECTEDINDEXES:
                //TODO
            case STORESELECTEDLABEL:
                //TODO
            case STORESELECTEDLABELS:
                //TODO
            case STORESELECTEDVALUE:
                //TODO
            case STORESELECTEDVALUES:
                //TODO
            case STORESOMETHINGSELECTED:
                //TODO
            case STORESPEED:
                //TODO
            case STORETABLE:
                //TODO
            case STORETEXT:
                //TODO
            case STORETEXTPRESENT:
                //TODO
            case STORETITLE:
                //TODO
            case STOREVALUE:
                //TODO
            case STOREVISIBLE:
                //TODO
            case STOREWHETHERTHISFRAMEMATCHFRAMEEXPRESSION:
                //TODO
            case STOREWHETHERTHISWINDOWMATCHWINDOWEXPRESSION:
                //TODO
            case STOREXPATHCOUNT:
                return store(value, driver.findElements(loc.locatorAsString(target)).size());
            case SUBMIT:
            case SUBMITANDWAIT:
                driver.findElement(loc.locatorAsString(target)).submit();
            case TYPE:
            case TYPEANDWAIT:
            case TYPEKEYS:
            case TYPEKEYSANDWAIT:
                return driver.findElement(loc.locatorAsString(target)).sendKeys(value);
            case UNCHECK:
            case UNCHECKANDWAIT:
                return ebselen.element().uncheck(target);
            case USEXPATHLIBRARY:
            case USEXPATHLIBRARYANDWAIT:
                //TODO
            case VERIFYALERT:
                return com.verifyEquals(driver.switchTo().alert().getText(), value);
            case VERIFYALERTNOTPRESENT:
                //TODO
            case VERIFYALERTPRESENT:
                //TODO
            case VERIFYALLBUTTONS:
                //TODO
            case VERIFYALLFIELDS:
                //TODO
            case VERIFYALLLINKS:
                //TODO
            case VERIFYALLWINDOWIDS:
                //TODO
            case VERIFYALLWINDOWNAMES:
                //TODO
            case VERIFYALLWINDOWTITLES:
                //TODO
            case VERIFYATTRIBUTE:
                //TODO
            case VERIFYATTRIBUTEFROMALLWINDOWS:
                //TODO
            case VERIFYBODYTEXT:
                //TODO
            case VERIFYCHECKED:
                return com.verifyTrue(com.assertFalse(ebselen.element().isChecked(target)));
            case VERIFYCONFIRMATION:
                //TODO
            case VERIFYCONFIRMATIONNOTPRESENT:
                //TODO
            case VERIFYCONFIRMATIONPRESENT:
                //TODO
            case VERIFYCOOKIE:
                //TODO
            case VERIFYCOOKIEBYNAME:
                //TODO
            case VERIFYCOOKIENOTPRESENT:
                //TODO
            case VERIFYCOOKIEPRESENT:
                //TODO
            case VERIFYCSSCOUNT:
                return com.verifyEquals(driver.findElements(loc.locatorAsString(target)).size(), value);
            case VERIFYCURSORPOSITION:
                //TODO
            case VERIFYEDITABLE:
                return com.verifyTrue(driver.findElement(loc.locatorAsString(target)).isEnabled());
            case VERIFYELEMENTHEIGHT:
                return com.verifyEquals(driver.findElement(loc.locatorAsString(target)).getCssValue("height"), value);
            case VERIFYELEMENTINDEX:
                //TODO
            case VERIFYELEMENTNOTPRESENT:
                return com.verifyFalse(ebselen.element().doesElementExist(loc.locatorAsString(target)));
            case VERIFYELEMENTPOSITIONLEFT:
                //TODO
            case VERIFYELEMENTPOSITIONTOP:
                //TODO
            case VERIFYELEMENTPRESENT:
                return com.verifyTrue(ebselen.element().doesElementExist(loc.locatorAsString(target)));
            case VERIFYELEMENTWIDTH:
                return com.verifyEquals(driver.findElement(loc.locatorAsString(target)).getCssValue("width"), value);
            case VERIFYEVAL:
                //TODO
            case VERIFYEXPRESSION:
                //TODO
            case VERIFYHTMLSOURCE:
                //TODO
            case VERIFYLOCATION:
                //TODO
            case VERIFYMOUSESPEED:
                //TODO
            case VERIFYNOTALERT:
                //TODO
            case VERIFYNOTALLBUTTONS:
                //TODO
            case VERIFYNOTALLFIELDS:
                //TODO
            case VERIFYNOTALLLINKS:
                //TODO
            case VERIFYNOTALLWINDOWIDS:
                //TODO
            case VERIFYNOTALLWINDOWNAMES:
                //TODO
            case VERIFYNOTALLWINDOWTITLES:
                //TODO
            case VERIFYNOTATTRIBUTE:
                //TODO
            case VERIFYNOTATTRIBUTEFROMALLWINDOWS:
                //TODO
            case VERIFYNOTBODYTEXT:
                //TODO
            case VERIFYNOTCHECKED:
                return com.verifyFalse(com.assertFalse(ebselen.element().isChecked(target)));
            case VERIFYNOTCONFIRMATION:
                //TODO
            case VERIFYNOTCOOKIE:
                //TODO
            case VERIFYNOTCOOKIEBYNAME:
                //TODO
            case VERIFYNOTCSSCOUNT:
                return com.verifyNotEquals(driver.findElements(loc.locatorAsString(target)).size(), value);
            case VERIFYNOTCURSORPOSITION:
                //TODO
            case VERIFYNOTEDITABLE:
                return com.verifyFalse(driver.findElement(loc.locatorAsString(target)).isEnabled());
            case VERIFYNOTELEMENTHEIGHT:
                return com.verifyNotEquals(driver.findElement(loc.locatorAsString(target)).getCssValue("height"), value);
            case VERIFYNOTELEMENTINDEX:
                //TODO
            case VERIFYNOTELEMENTPOSITIONLEFT:
                //TODO
            case VERIFYNOTELEMENTPOSITIONTOP:
                //TODO
            case VERIFYNOTELEMENTWIDTH:
                return com.verifyNotEquals(driver.findElement(loc.locatorAsString(target)).getCssValue("width"), value);
            case VERIFYNOTEVAL:
                //TODO
            case VERIFYNOTEXPRESSION:
                //TODO
            case VERIFYNOTHTMLSOURCE:
                //TODO
            case VERIFYNOTLOCATION:
                //TODO
            case VERIFYNOTMOUSESPEED:
                //TODO
            case VERIFYNOTORDERED:
                //TODO
            case VERIFYNOTPROMPT:
                //TODO
            case VERIFYNOTSELECTOPTIONS:
                //TODO
            case VERIFYNOTSELECTEDID:
                //TODO
            case VERIFYNOTSELECTEDIDS:
                //TODO
            case VERIFYNOTSELECTEDINDEX:
                //TODO
            case VERIFYNOTSELECTEDINDEXES:
                //TODO
            case VERIFYNOTSELECTEDLABEL:
                //TODO
            case VERIFYNOTSELECTEDLABELS:
                //TODO
            case VERIFYNOTSELECTEDVALUE:
                //TODO
            case VERIFYNOTSELECTEDVALUES:
                //TODO
            case VERIFYNOTSOMTHINGSELECTED:
                //TODO
            case VERIFYNOTSPEED:
                //TODO
            case VERIFYNOTTABLE:
                //TODO
            case VERIFYNOTTEXT:
                //TODO
            case VERIFYNOTTITLE:
                //TODO
            case VERIFYNOTVALUE:
                //TODO
            case VERIFYNOTVISIBLE:
                //TODO
            case VERIFYNOTWHETHERTHISFRAMEMATCHFRAMEEXPRESSION:
                //TODO
            case VERIFYNOTWHETHERTHISWINDOWMATCHWINDOWEXPRESSION:
                //TODO
            case VERIFYNOTXPATHCOUNT:
                return com.verifyNotEquals(value, driver.findElements(loc.locatorAsString(target)).size());
            case VERIFYORDERED:
                //TODO
            case VERIFYPROMPT:
                //TODO
            case VERIFYPROMPTNOTPRESENT:
                //TODO
            case VERIFYPROMPTPRESENT:
                //TODO
            case VERIFYSELECTOPTIONS:
                //TODO
            case VERIFYSELECTEDID:
                //TODO
            case VERIFYSELECTEDIDS:
                //TODO
            case VERIFYSELECTEDINDEX:
                //TODO
            case VERIFYSELECTEDINDEXES:
                //TODO
            case VERIFYSELECTEDLABEL:
                //TODO
            case VERIFYSELECTEDLABELS:
                //TODO
            case VERIFYSELECTEDVALUE:
                //TODO
            case VERIFYSELECTEDVALUES:
                //TODO
            case VERIFYSOMETHINGSELECTED:
                //TODO
            case VERIFYSPEED:
                //TODO
            case VERIFYTABLE:
                //TODO
            case VERIFYTEXT:
                //TODO
            case VERIFYTEXTNOTPRESENT:
                return com.verifyFalse(ebselen.element().doesElementExist(loc.locatorAsString("//*[contains(, '" + value + "')]")));
            case VERIFYTEXTPRESENT:
                return com.verifyTrue(ebselen.element().doesElementExist(loc.locatorAsString("//*[contains(, '" + value + "')]")));
            case VERIFYTITLE:
                //TODO
            case VERIFYVALUE:
                //TODO
            case VERIFYVISIBLE:
                //TODO
            case VERIFYWHETHERTHISFRAMEMATCHFRAMEEXPRESSION:
                //TODO
            case VERIFYWHETHERTHISWINDOWMATCHWINDOWEXPRESSION:
                //TODO
            case VERIFYXPATHCOUNT:
                return com.verifyEquals(value, driver.findElements(loc.locatorAsString(target)).size());
            case WAITFORALERT:
                //TODO
            case WAITFORALERTNOTPRESENT:
                //TODO
            case WAITFORALERTPRESENT:
                //TODO
            case WAITFORALLBUTTONS:
                //TODO
            case WAITFORALLFIELDS:
                //TODO
            case WAITFORALLLINKS:
                //TODO
            case WAITFORALLWINDOWIDS:
                //TODO
            case WAITFORALLWINDOWNAMES:
                //TODO
            case WAITFORALLWINDOWTITLES:
                //TODO
            case WAITFORATTRIBUTE:
                //TODO
            case WAITFORATTRIBUTEFROMALLWINDOWS:
                //TODO
            case WAITFORBODYTEXT:
                //TODO
            case WAITFORCHECKED:
                //TODO
            case WAITFORCONDITION:
                //TODO
            case WAITFORCONFIRMATION:
                //TODO
            case WAITFORCONFIRMATIONNOTPRESENT:
                //TODO
            case WAITFORCONFIRMATIONPRESENT:
                //TODO
            case WAITFORCOOKIE:
                //TODO
            case WAITFORCOOKIEBYNAME:
                //TODO
            case WAITFORCOOKIENOTPRESENT:
                //TODO
            case WAITFORCOOKIEPRESENT:
                //TODO
            case WAITFORCSSCOUNT:
                //TODO
            case WAITFORCURSORPOSITION:
                //TODO
            case WAITFOREDITABLE:
                //TODO
            case WAITFORELEMENTHEIGHT:
                //TODO
            case WAITFORELEMENTINDEX:
                //TODO
            case WAITFORELEMENTNOTPRESENT:
                //TODO
            case WAITFORELEMENTPOSITIONLEFT:
                //TODO
            case WAITFORELEMENTPOSITIONTOP:
                //TODO
            case WAITFORELEMENTPRESENT:
                //TODO
            case WAITFORELEMENTWIDTH:
                //TODO
            case WAITFOREVAL:
                //TODO
            case WAITFOREXPRESSION:
                //TODO
            case WAITFORFRAMETOLOAD:
                //TODO
            case WAITFORHTMLSOURCE:
                //TODO
            case WAITFORLOCATION:
                //TODO
            case WAITFORMOUSESPEED:
                //TODO
            case WAITFORNOTALERT:
                //TODO
            case WAITFORNOTALLBUTTONS:
                //TODO
            case WAITFORNOTALLFIELDS:
                //TODO
            case WAITFORNOTALLLINKS:
                //TODO
            case WAITFORNOTALLWINDOWIDS:
                //TODO
            case WAITFORNOTALLWINDOWNAMES:
                //TODO
            case WAITFORNOTALLWINDOWTITLES:
                //TODO
            case WAITFORNOTATTRIBUTE:
                //TODO
            case WAITFORNOTATTRIBUTEFROMALLWINDOWS:
                //TODO
            case WAITFORNOTBODYTEXT:
                //TODO
            case WAITFORNOTCHECKED:
                //TODO
            case WAITFORNOTCONFIRMATION:
                //TODO
            case WAITFORNOTCOOKIE:
                //TODO
            case WAITFORNOTCOOKIEBYNAME:
                //TODO
            case WAITFORNOTCSSCOUNT:
                //TODO
            case WAITFORNOTCURSORPOSITION:
                //TODO
            case WAITFORNOTEDITABLE:
                //TODO
            case WAITFORNOTELEMENTHEIGHT:
                //TODO
            case WAITFORNOTELEMENTINDEX:
                //TODO
            case WAITFORNOTELEMENTPOSITIONLEFT:
                //TODO
            case WAITFORNOTELEMENTPOSITIONTOP:
                //TODO
            case WAITFORNOTELEMENTWIDTH:
                //TODO
            case WAITFORNOTEVAL:
                //TODO
            case WAITFORNOTEXPRESSION:
                //TODO
            case WAITFORNOTHTMLSOURCE:
                //TODO
            case WAITFORNOTLOCATION:
                //TODO
            case WAITFORNOTMOUSESPEED:
                //TODO
            case WAITFORNOTORDERED:
                //TODO
            case WAITFORNOTPROMPT:
                //TODO
            case WAITFORNOTSELECTOPTIONS:
                //TODO
            case WAITFORNOTSELECTEDID:
                //TODO
            case WAITFORNOTSELECTEDIDS:
                //TODO
            case WAITFORNOTSELECTEDINDEX:
                //TODO
            case WAITFORNOTSELECTEDINDEXES:
                //TODO
            case WAITFORNOTSELECTEDLABEL:
                //TODO
            case WAITFORNOTSELECTEDLABELS:
                //TODO
            case WAITFORNOTSELECTEDVALUE:
                //TODO
            case WAITFORNOTSELECTEDVALUES:
                //TODO
            case WAITFORNOTSOMETHINGSELECTED:
                //TODO
            case WAITFORNOTSPEED:
                //TODO
            case WAITFORNOTTABLE:
                //TODO
            case WAITFORNOTTEXT:
                //TODO
            case WAITFORNOTTITLE:
                //TODO
            case WAITFORNOTVALUE:
                //TODO
            case WAITFORNOTVISIBLE:
                //TODO
            case WAITFORNOTWHETHERTHISFRAMEMATCHFRAMEEXPRESSION:
                //TODO
            case WAITFORNOTWHETHERTHISWINDOWMATCHWINDOWEXPRESSION:
                //TODO
            case WAITFORNOTXPATHCOUNT:
                //TODO
            case WAITFORORDERED:
                //TODO
            case WAITFORPAGETOLOAD:
                //TODO
            case WAITFORPOPUP:
                //TODO
            case WAITFORPROMPT:
                //TODO
            case WAITFORPROMPTNOTPRESENT:
                //TODO
            case WAITFORPROMPTPRESENT:
                //TODO
            case WAITFORSELECTOPTIONS:
                //TODO
            case WAITFORSELECTEDID:
                //TODO
            case WAITFORSELECTEDIDS:
                //TODO
            case WAITFORSELECTEDINDEX:
                //TODO
            case WAITFORSELECTEDINDEXES:
                //TODO
            case WAITFORSELECTEDLABEL:
                //TODO
            case WAITFORSELECTEDLABELS:
                //TODO
            case WAITFORSELECTEDVALUE:
                //TODO
            case WAITFORSELECTEDVALUES:
                //TODO
            case WAITFORSOMETHINGSELECTED:
                //TODO
            case WAITFORSPEED:
                //TODO
            case WAITFORTABLE:
                //TODO
            case WAITFORTEXT:
                //TODO
            case WAITFORTEXTNOTPRESENT:
                //TODO check implementation
                return ebselen.waiting().untilWebElement(loc.locatorAsString("//*[contains(., '" + target + "')]")).doesNotExist();
            case WAITFORTEXTPRESENT:
                //TODO check implementation
                return ebselen.waiting().untilWebElement(loc.locatorAsString("//*[contains(., '" + target + "')]")).exists();
            case WAITFORTITLE:

                //TODO
            case WAITFORVALUE:
                //wait for a value, returns value or on/off for checkbox/radio elements
                //TODO
            case WAITFORVISIBLE:
                //TODO
            case WAITFORWHETERTHISFRAMEMATCHFRAMEEXPRESSION:
                //TODO
            case WAITFORWHETERTHISWINDOWMATCHWINDOWEXPRESSION:
                //TODO
            case WAITFORXPATHCOUNT:
                //TODO
            case WINDOWFOCUS:
            case WINDOWFOCUSANDWAIT:
                //TODO
            case WINDOWMAXIMIZE:
            case WINDOWMAXIMIZEANDWAIT:
                //TODO
            default:
                throw new IDECommandNotFoundException();
        }
    }
}
