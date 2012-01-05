package com.lazerycode.ebselen.customhandlers;

import org.junit.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class TinyMCETest {

    private static JettyServer localWebServer = new JettyServer();
    private static int webServerPort = 8081;
    //    private WebDriver driver = new HtmlUnitDriver(true);
    private WebDriver driver = new FirefoxDriver();

    @BeforeClass
    public static void start() throws Exception {
        localWebServer.startJettyServer(webServerPort);
    }

    @AfterClass
    public static void stop() throws Exception {
        localWebServer.stopJettyServer();
    }

    public void waitForAlertToBeAccepted(final int timeout) {
        new WebDriverWait(driver, timeout) {
        }.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                Boolean alertAccepted = false;
                try {
                    driver.switchTo().alert().accept();
                    alertAccepted = true;
                } catch (Exception Ex) {
                    // Couldn't switch!
                }
                return alertAccepted;
            }
        });
    }

    @After
    public void closeWebDriver() {
        driver.close();
        waitForAlertToBeAccepted(2000);
    }

    @Ignore
    @Test
    public void clearTinyMCEField() throws Exception {
        driver.get("http://localhost:8081/tinymce/examples/full.html");
        TinyMCEHandler tiny = new TinyMCEHandler("elm1", driver);
        tiny.clear();
        assertThat(tiny.getText(), is(equalTo("")));
    }

    @Ignore
    @Test
    public void replaceTextInTinyMCEField() throws Exception {
        driver.get("http://localhost:8081/tinymce/examples/full.html");
        TinyMCEHandler tiny = new TinyMCEHandler("elm1", driver);
        tiny.clear();
        tiny.type("foo");
        assertThat(tiny.getText(), is(equalTo("foo")));
    }

    @Ignore
    @Test
    public void replaceHTMLSourceTinyMCEField() throws Exception {
        driver.get("http://localhost:8081/tinymce/examples/full.html");
        TinyMCEHandler tiny = new TinyMCEHandler("elm1", driver);
        tiny.replaceHTMLSource("<h1>Foo!</h1><p>bar</p>");
        assertThat(tiny.getText(), is(equalTo("Foo!\nbar")));
    }
}
