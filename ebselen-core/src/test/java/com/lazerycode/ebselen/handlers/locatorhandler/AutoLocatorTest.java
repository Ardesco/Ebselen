package com.lazerycode.ebselen.handlers.locatorhandler;

import com.lazerycode.ebselen.handlers.LocatorHandler;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class AutoLocatorTest {

    private LocatorHandler loc = new LocatorHandler();

    public List<String> listOfLocators(String[] locators) {
        return Arrays.asList(locators);
    }

    @Test
    public void givenAXPathLocatorCodeReturnsCorrectType() throws Exception {
        for (String XPathLocator : listOfLocators(new String[]{"xpath=//div", "//div", "(//div)[1]"})) {
            String[] result = XPathLocator.split("=");
            assertThat(loc.autoLocator(XPathLocator), is(equalTo(By.xpath(result[result.length - 1]))));
        }
    }

    @Test
    @Ignore
    // TODO Remove DOM support?  Could be anything coming in...
    public void givenADOMLocatorCodeReturnsCorrectType() throws Exception {
        assertThat(loc.autoLocator("dom=document.getElementsByTagName('span');"), is(equalTo(By.xpath("dom=document.getElementsByTagName('span');"))));
    }

    @Test
    public void givenACSSLocatorCodeReturnsCorrectType() throws Exception {
        for (String CSSLocator : listOfLocators(new String[]{"css=#foo", "#foo", ".bar"})) {
            String[] result = CSSLocator.split("=");
            assertThat(loc.autoLocator(CSSLocator), is(equalTo(By.cssSelector(result[result.length - 1]))));
        }
    }

    @Test
    public void givenAClassLocatorCodeReturnsCorrectType() throws Exception {
        assertThat(loc.autoLocator("class=foo"), is(equalTo(By.className("foo"))));
    }

    @Test
    public void givenAIDLocatorCodeReturnsCorrectType() throws Exception {
        for (String IDLocator : listOfLocators(new String[]{"lst-lib", "foo$bar", "id=lst-lib"})) {
            String[] result = IDLocator.split("=");
            assertThat(loc.autoLocator(IDLocator), is(equalTo(By.id(result[result.length - 1]))));
        }
    }

    @Test
    public void givenALinkTextLocatorCodeReturnsCorrectType() throws Exception {
        assertThat(loc.autoLocator("link=home"), is(equalTo(By.linkText("home"))));
    }

    @Test
    public void givenATagNameLocatorCodeReturnsCorrectType() throws Exception {
        assertThat(loc.autoLocator("tag=div"), is(equalTo(By.tagName("div"))));
    }

    @Test
    public void givenANameLocatorCodeReturnsCorrectType() throws Exception {
        assertThat(loc.autoLocator("name=foo"), is(equalTo(By.name("foo"))));
    }
}
