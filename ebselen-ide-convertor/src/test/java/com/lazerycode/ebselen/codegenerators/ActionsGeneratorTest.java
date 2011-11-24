package com.lazerycode.ebselen.codegenerators;

import org.junit.Test;
import org.openqa.selenium.Keys;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class ActionsGeneratorTest {

    private ActionsGenerator action = new ActionsGenerator("mouse", "keyboard", "builder");
    private String webElement = "driver.findElement(By.xpath(\"//div[@id='bar']\"))";
    private String altWebElement = "driver.findElement(By.xpath(\"//div[@id='foo']\"))";

    @Test
    public void generateCodeForKeyboardPressKeys() throws Exception {
        assertThat(this.action.keyboard().pressKey(Keys.ADD), is(equalTo("keyboard.pressKey(Keys.ADD)")));
    }

    @Test
    public void generateCodeForKeyboardReleaseKeys() throws Exception {
        assertThat(this.action.keyboard().releaseKey(Keys.ADD), is(equalTo("keyboard.releaseKey(Keys.ADD)")));
    }

    @Test
    public void generateCodeForKeyboardSendKeys() throws Exception {
        assertThat(this.action.keyboard().sendKeys("foobar"), is(equalTo("keyboard.sendKeys(\"foobar\")")));
    }

    @Test
    public void generateCodeForMouseClick() throws Exception {
        assertThat(this.action.mouse().click("10, 100"), is(equalTo("mouse.click(10, 100)")));
    }

    @Test
    public void generateCodeForMouseContextClick() throws Exception {
        assertThat(this.action.mouse().contextClick("10, 100"), is(equalTo("mouse.contextClick(10, 100)")));
    }

    @Test
    public void generateCodeForMouseDoubleClick() throws Exception {
        assertThat(this.action.mouse().doubleClick("10, 100"), is(equalTo("mouse.doubleClick(10, 100)")));
    }

    @Test
    public void generateCodeForMouseDown() throws Exception {
        assertThat(this.action.mouse().mouseDown("10, 100"), is(equalTo("mouse.mouseDown(10, 100)")));
    }

    @Test
    public void generateCodeForMouseUp() throws Exception {
        assertThat(this.action.mouse().mouseUp("10, 100"), is(equalTo("mouse.mouseUp(10, 100)")));
    }

    @Test
    public void generateCodeForMouseMouse() throws Exception {
        assertThat(this.action.mouse().mouseMove("10, 100"), is(equalTo("mouse.mouseMove(10, 100)")));
    }

    @Test
    public void generateCodeForMouseMovePlusExtraCoordinates() throws Exception {
        assertThat(this.action.mouse().mouseMove("10, 100", 50, 25), is(equalTo("mouse.mouseMove(10, 100, 50, 25)")));
    }

    @Test
    public void generateCodeActionClick() throws Exception {
        assertThat(this.action.builder().click(), is(equalTo("builder.click()")));
    }

    @Test
    public void generateCodeActionClickWebElement() throws Exception {
        assertThat(this.action.builder().click(altWebElement), is(equalTo("builder.click(driver.findElement(By.xpath(\"//div[@id='foo']\")))")));
    }

    @Test
    public void generateCodeActionClickAndHold() throws Exception {
        assertThat(this.action.builder().clickAndHold(), is(equalTo("builder.clickAndHold()")));
    }

    @Test
    public void generateCodeActionClickWebElementAndHold() throws Exception {
        assertThat(this.action.builder().clickAndHold(altWebElement), is(equalTo("builder.clickAndHold(driver.findElement(By.xpath(\"//div[@id='foo']\")))")));
    }

    @Test
    public void generateCodeActionDoubleClick() throws Exception {
        assertThat(this.action.builder().doubleClick(), is(equalTo("builder.doubleClick()")));
    }

    @Test
    public void generateCodeActionDoubleClickWebElement() throws Exception {
        assertThat(this.action.builder().doubleClick(altWebElement), is(equalTo("builder.doubleClick(driver.findElement(By.xpath(\"//div[@id='foo']\")))")));
    }

    @Test
    public void generateCodeActionContextClick() throws Exception {
        assertThat(this.action.builder().contextClick(altWebElement), is(equalTo("builder.contextClick(driver.findElement(By.xpath(\"//div[@id='foo']\")))")));
    }

    @Test
    public void generateCodeDragAndDropToElement() throws Exception {
        assertThat(this.action.builder().dragAndDrop(altWebElement, webElement), is(equalTo("builder.dragAndDrop(driver.findElement(By.xpath(\"//div[@id='foo']\")), driver.findElement(By.xpath(\"//div[@id='bar']\")))")));
    }

    @Test
    public void generateCodeDragAndDropToLocation() throws Exception {
        assertThat(this.action.builder().dragAndDrop(altWebElement, "1", "200"), is(equalTo("builder.dragAndDrop(driver.findElement(By.xpath(\"//div[@id='foo']\")), 1, 200)")));
    }

    @Test
    //TODO should really only be used when an element has been clicked
    public void generateCodeActionMoveByOffset() throws Exception {
        assertThat(this.action.builder().moveByOffset("200", "30"), is(equalTo("builder.moveByOffset(200, 30)")));
    }

    @Test
    //TODO should really only be used when an element has been clicked
    public void generateCodeActionMoveToElement() throws Exception {
        assertThat(this.action.builder().moveToElement(webElement), is(equalTo("builder.moveToElement(driver.findElement(By.xpath(\"//div[@id='bar']\")))")));
    }

    @Test
    //TODO should really only be used when an element has been clicked
    public void generateCodeActionMoveToElementWithOffest() throws Exception {
        assertThat(this.action.builder().moveToElement(webElement, "25", "48"), is(equalTo("builder.moveToElement(driver.findElement(By.xpath(\"//div[@id='bar']\")), 25, 48)")));
    }

    @Test
    public void generateCodeActionRelease() throws Exception {
        assertThat(this.action.builder().release(), is(equalTo("builder.release()")));
    }

    @Test
    public void generateCodeActionReleaseAtWebElement() throws Exception {
        assertThat(this.action.builder().release(webElement), is(equalTo("builder.release(driver.findElement(By.xpath(\"//div[@id='bar']\")))")));
    }

    @Test
    public void generateCodeActionSendKeys() throws Exception {
        assertThat(this.action.builder().sendKeys("foobar"), is(equalTo("builder.sendKeys(\"foobar\")")));
    }

    @Test
    public void generateCodeActionSendKeysAtElement() throws Exception {
        assertThat(this.action.builder().sendKeys(webElement, "foobar"), is(equalTo("builder.sendKeys(driver.findElement(By.xpath(\"//div[@id='bar']\")), \"foobar\")")));
    }

    @Test
    public void generateCodeActionPerform() throws Exception {
        assertThat(this.action.builder().perform(), is(equalTo("builder.perform()")));
    }

    @Test
    public void generateCodeActionKeyDown() throws Exception {
        assertThat(this.action.builder().keyDown(Keys.ENTER), is(equalTo("builder.keyDown(Keys.ENTER)")));
    }

    @Test
    public void generateCodeActionKeyDownAtElement() throws Exception {
        assertThat(this.action.builder().keyDown(webElement, Keys.ENTER), is(equalTo("builder.keyDown(driver.findElement(By.xpath(\"//div[@id='bar']\")), Keys.ENTER)")));
    }

    @Test
    public void generateCodeActionKeyUp() throws Exception {
        assertThat(this.action.builder().keyUp(Keys.ENTER), is(equalTo("builder.keyUp(Keys.ENTER)")));
    }

    @Test
    public void generateCodeActionKeyUpAtElement() throws Exception {
        assertThat(this.action.builder().keyUp(webElement, Keys.ENTER), is(equalTo("builder.keyUp(driver.findElement(By.xpath(\"//div[@id='bar']\")), Keys.ENTER)")));
    }
}
