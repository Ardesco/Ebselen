package com.lazerycode.ebselen.customhandlers;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class TinyMCETest {

    private static JettyServer localWebServer = new JettyServer();
    private static int webServerPort = 8081;
    private WebDriver driver = new HtmlUnitDriver();

    @BeforeClass
    public static void start() throws Exception {
        localWebServer.startJettyServer(webServerPort);
    }

    @AfterClass
    public static void stop() throws Exception {
        localWebServer.stopJettyServer();
    }

    @After
    public void closeWebDriver() {
        driver.close();
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
    public void addTextToTinyMCEFIeld() throws Exception {
        driver.get("http://localhost:8081/tinymce/examples/full.html");
        TinyMCEHandler tiny = new TinyMCEHandler("elm1", driver);
        tiny.type("foo");
        assertThat(tiny.getText(), is(equalTo("foo")));
    }
}
