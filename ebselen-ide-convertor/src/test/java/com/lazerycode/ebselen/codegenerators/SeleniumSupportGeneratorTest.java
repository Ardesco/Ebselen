package com.lazerycode.ebselen.codegenerators;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class SeleniumSupportGeneratorTest {
    private SeleniumSupportGenerator support = new SeleniumSupportGenerator();
    //    private String webElement = "driver.findElement(By.xpath(\"//div[@id='foo']\"))";
    private String webElement = "bar";

    @Test
    public void selectObjectIsMultiple() throws Exception {
        this.support.getOptions(webElement);
        assertThat(this.support.isMultiple(webElement), is(equalTo("select1.isMultiple()")));
    }

    @Test
    public void selectObjectGetOptions() throws Exception {
        this.support.getOptions(webElement);
        assertThat(this.support.getOptions(webElement), is(equalTo("select1.getOptions()")));
    }

    @Test
    public void selectObjectGetAllSelectedOptions() throws Exception {
        this.support.getOptions(webElement);
        assertThat(this.support.getAllSelectedOptions(webElement), is(equalTo("select1.getAllSelectedOptions()")));
    }

    @Test
    public void selectObjectGetFirstSelectedOption() throws Exception {
        this.support.getOptions(webElement);
        assertThat(this.support.getFirstSelectedOption(webElement), is(equalTo("select1.getFirstSelectedOption()")));
    }

    @Test
    public void selectObjectSelectByVisibleText() throws Exception {
        this.support.getOptions(webElement);
        assertThat(this.support.selectByVisibleText(webElement, "foobar"), is(equalTo("select1.selectByVisibleText(\"foobar\")")));
    }

    @Test
    public void selectObjectSelectByIndex() throws Exception {
        this.support.getOptions(webElement);
        assertThat(this.support.selectByIndex(webElement, "1"), is(equalTo("select1.selectByIndex(\"1\")")));
    }

    @Test
    public void selectObjectSelectByValue() throws Exception {
        this.support.getOptions(webElement);
        assertThat(this.support.selectByValue(webElement, "Cookies!"), is(equalTo("select1.selectByValue(\"Cookies!\")")));
    }

    @Test
    public void selectObjectDeselectAll() throws Exception {
        this.support.getOptions(webElement);
        assertThat(this.support.deselectAll(webElement), is(equalTo("select1.deselectAll()")));
    }


    @Test
    public void selectObjectDeselectByValue() throws Exception {
        this.support.getOptions(webElement);
        assertThat(this.support.deselectByValue(webElement, "Cookies!"), is(equalTo("select1.deselectByValue(\"Cookies!\")")));
    }

    @Test
    public void selectObjectDeselectByIndex() throws Exception {
        this.support.getOptions(webElement);
        assertThat(this.support.deselectByIndex(webElement, "1"), is(equalTo("select1.deselectByIndex(\"1\")")));
    }

    @Test
    public void selectObjectDeselectByVisibleText() throws Exception {
        this.support.getOptions(webElement);
        assertThat(this.support.deselectByVisibleText(webElement, "foobar"), is(equalTo("select1.deselectByVisibleText(\"foobar\")")));
    }

    @Test
    public void checkThatSelectObjectIsGeneratedByFirstCall() throws Exception {
        assertThat(this.support.getOptions(webElement), is(equalTo("Select select1 = new Select(driver.findElement(By.id(\"" + webElement + "\")));" + System.getProperty("line.separator") + "select1.getOptions()")));
    }

    @Test
    public void checkThatSubsequentCallWithDifferentLocatorCreatesNewSelectObject() throws Exception {
        this.support.getOptions(webElement);
        assertThat(this.support.getOptions("foo"), is(equalTo("Select select2 = new Select(driver.findElement(By.id(\"foo\")));" + System.getProperty("line.separator") + "select2.getOptions()")));
    }

    @Test
    public void checkThatCorrectSlectObjectIsUsedForAllSubsequentCalls() throws Exception {
        this.support.getOptions(webElement);
        this.support.getOptions("foo");
        assertThat(this.support.deselectAll("foo"), is(equalTo("select2.deselectAll()")));
        assertThat(this.support.isMultiple(webElement), is(equalTo("select1.isMultiple()")));
    }
}