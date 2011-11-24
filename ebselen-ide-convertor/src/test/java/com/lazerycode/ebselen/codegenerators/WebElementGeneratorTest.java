package com.lazerycode.ebselen.codegenerators;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class WebElementGeneratorTest {

    private WebDriverGenerator driver = new WebDriverGenerator("driver");
    private String locator = "By.id(\"foo\")";

    @Test
    public void checkWebElementGetAttributeReturnsCorrectCode() {
        assertThat(driver.findElement(locator).getAttribute("href"), is(equalTo("driver.findElement(" + locator + ").getAttribute(\"href\")")));
    }

    @Test
    public void checkWebElementIsSelectedReturnsCorrectCode() {
        assertThat(driver.findElement(locator).isSelected(), is(equalTo("driver.findElement(" + locator + ").isSelected()")));
    }

    @Test
    public void checkWebElementIsEnableReturnsCorrectCode() {
        assertThat(driver.findElement(locator).isEnabled(), is(equalTo("driver.findElement(" + locator + ").isEnabled()")));
    }

    @Test
    public void checkWebElementGetTextReturnsCorrectCode() {
        assertThat(driver.findElement(locator).getText(), is(equalTo("driver.findElement(" + locator + ").getText()")));
    }

    @Test
    public void checkWebElementIsDisplayedReturnsCorrectCode() {
        assertThat(driver.findElement(locator).isDisplayed(), is(equalTo("driver.findElement(" + locator + ").isDisplayed()")));
    }

    @Test
    public void checkWebElementGetLocationReturnsCorrectCode() {
        assertThat(driver.findElement(locator).getLocation().toString(), is(equalTo("driver.findElement(" + locator + ").getLocation()")));
    }

    @Test
    public void checkWebElementGetLocationGetXReturnsCorrectCode() {
        assertThat(driver.findElement(locator).getLocation().getX(), is(equalTo("driver.findElement(" + locator + ").getLocation().getX()")));
    }

    @Test
    public void checkWebElementGetLocationGetYReturnsCorrectCode() {
        assertThat(driver.findElement(locator).getLocation().getY(), is(equalTo("driver.findElement(" + locator + ").getLocation().getY()")));
    }

    @Test
    public void checkWebElementGetSizeReturnsCorrectCode() {
        assertThat(driver.findElement(locator).getSize().toString(), is(equalTo("driver.findElement(" + locator + ").getSize()")));
    }

    @Test
    public void checkWebElementGetSizeGetWidthReturnsCorrectCode() {
        assertThat(driver.findElement(locator).getSize().getWidth(), is(equalTo("driver.findElement(" + locator + ").getSize().getWidth()")));
    }

    @Test
    public void checkWebElementGetSizeGetHeightReturnsCorrectCode() {
        assertThat(driver.findElement(locator).getSize().getHeight(), is(equalTo("driver.findElement(" + locator + ").getSize().getHeight()")));
    }

    @Test
    public void checkWebElementClickReturnsCorrectCode() {
        assertThat(driver.findElement(locator).click(), is(equalTo("driver.findElement(" + locator + ").click()")));
    }

    @Test
    public void checkWebElementGetCSSValueReturnsCorrectCode() {
        assertThat(driver.findElement(locator).getCssValue("background-color"), is(equalTo("driver.findElement(" + locator + ").getCssValue(\"background-color\")")));
    }

    @Test
    public void checkWebElementSendKeysReturnsCorrectCode() {
        assertThat(driver.findElement(locator).sendKeys("These keys are being sent!"), is(equalTo("driver.findElement(" + locator + ").sendKeys(\"These keys are being sent!\")")));
    }

    @Test
    public void checkWebElementSubmitReturnsCorrectCode() {
        assertThat(driver.findElement(locator).submit(), is(equalTo("driver.findElement(" + locator + ").submit()")));
    }

    @Test
    public void checkWebElementToStringReturnsCorrectCode() {
        assertThat(driver.findElement(locator).toString(), is(equalTo("driver.findElement(" + locator + ")")));
    }
}
