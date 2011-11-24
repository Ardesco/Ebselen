package com.lazerycode.ebselen.codegenerators;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class WebElementListGeneratorTest {

    private WebDriverGenerator driver = new WebDriverGenerator("driver");
    private String locator = "By.id(\"foo\")";

    @Test
    public void checkWebElementListSizeReturnsCorrectCode() {
        assertThat(driver.findElements(locator).size(), is(equalTo("driver.findElements(" + locator + ").size()")));
    }

    @Test
    public void checkWebElementListReturnsCorrectCode() {
        assertThat(driver.findElements(locator).toString(), is(equalTo("driver.findElements(" + locator + ")")));
    }
}
