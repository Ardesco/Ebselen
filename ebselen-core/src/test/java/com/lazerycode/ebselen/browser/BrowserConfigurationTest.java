package com.lazerycode.ebselen.browser;

import com.lazerycode.ebselen.BrowserConfiguration;
import com.lazerycode.ebselen.BrowserConfiguration.htmlUnitEmulation;
import com.lazerycode.ebselen.BrowserConfiguration.selectedBrowser;
import com.lazerycode.ebselen.exceptions.UnknownBrowserException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class BrowserConfigurationTest {

    private String browser;
    private selectedBrowser browserType;
    private String htmlUnit;
    private htmlUnitEmulation htmlUnitType;

    public BrowserConfigurationTest(String browser, selectedBrowser browserType, String htmlUnit, htmlUnitEmulation htmlUnitType) {
        this.browser = browser;
        this.browserType = browserType;
        this.htmlUnit = htmlUnit;
        this.htmlUnitType = htmlUnitType;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"firefox", selectedBrowser.FIREFOX, "none", htmlUnitEmulation.NONE},
                {"ie6", selectedBrowser.IE6, "firefox", htmlUnitEmulation.FIREFOX},
                {"ie7", selectedBrowser.IE7, "ie6", htmlUnitEmulation.IE6},
                {"ie8", selectedBrowser.IE8, "ie7", htmlUnitEmulation.IE7},
                {"ie9", selectedBrowser.IE9, "ie8", htmlUnitEmulation.IE8},
                {"safari", selectedBrowser.SAFARI, "FireFox", htmlUnitEmulation.FIREFOX},
                {"opera", selectedBrowser.OPERA, "Ie6", htmlUnitEmulation.IE6},
                {"googlechrome", selectedBrowser.GOOGLECHROME, "iE7", htmlUnitEmulation.IE7},
                {"android", selectedBrowser.ANDROID, "IE8", htmlUnitEmulation.IE8},
                {"iphone", selectedBrowser.IPHONE, "FIREfoX", htmlUnitEmulation.FIREFOX},
                {"htmlunit", selectedBrowser.HTMLUNIT, "iE6", htmlUnitEmulation.IE6},
                {"FireFox", selectedBrowser.FIREFOX, "Ie7", htmlUnitEmulation.IE7},
        });
    }

    @Test
    public void checkBrowserConfigurationDefaultsToHTMLUnit() throws Exception {
        BrowserConfiguration browserConfigObject = new BrowserConfiguration();
        assertThat(browserConfigObject.getBrowser(), is(equalTo(selectedBrowser.HTMLUNIT)));
        assertThat(browserConfigObject.getHTMLUnitEmulation(), is(equalTo(htmlUnitEmulation.NONE)));
    }

    @Test
    public void checkBrowserConfigurationSetsCorrectBrowserTypeUsingBrowserTypeObject() throws Exception {
        BrowserConfiguration browserConfigObject = new BrowserConfiguration();
        assertThat(browserConfigObject.getBrowser(), is(equalTo(selectedBrowser.HTMLUNIT)));
        assertThat(browserConfigObject.getHTMLUnitEmulation(), is(equalTo(htmlUnitEmulation.NONE)));
    }

    @Test
    public void checkBrowserConfigurationSetsBrowserCorrectly() throws Exception {
        BrowserConfiguration browserConfigObject = new BrowserConfiguration(this.browser);
        assertThat(browserConfigObject.getBrowser(), is(equalTo(this.browserType)));
        assertThat(browserConfigObject.getHTMLUnitEmulation(), is(equalTo(htmlUnitEmulation.NONE)));
    }

    @Test
    public void checkBrowserConfigurationSetsHTMLUnitEmulationCorrectly() throws Exception {
        BrowserConfiguration browserConfigObject = new BrowserConfiguration("htmlunit", this.htmlUnit);
        assertThat(browserConfigObject.getBrowser(), is(equalTo(selectedBrowser.HTMLUNIT)));
        assertThat(browserConfigObject.getHTMLUnitEmulation(), is(equalTo(this.htmlUnitType)));
    }

    @Test
    public void htmlUnitEmulationDefaultsToNoneIfUnknownTypeSet() throws Exception {
        BrowserConfiguration browserConfigObject = new BrowserConfiguration("htmlunit", "Firefox");
        browserConfigObject.setHTMLUnitEmulation("foobar");
        assertThat(browserConfigObject.getHTMLUnitEmulation(), is(equalTo(htmlUnitEmulation.NONE)));
    }

    @Test(expected = UnknownBrowserException.class)
    public void exceptionThrownIfUnknownBrowserTypeSet() throws Exception {
        BrowserConfiguration browserConfigObject = new BrowserConfiguration("foobar");
    }
}

