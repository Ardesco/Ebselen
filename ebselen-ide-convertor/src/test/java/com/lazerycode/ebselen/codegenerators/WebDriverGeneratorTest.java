package com.lazerycode.ebselen.codegenerators;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class WebDriverGeneratorTest {

    private WebDriverGenerator driver = new WebDriverGenerator("driver");

    @Test
    public void checkGetReturnsCorrectCode() {
        assertThat(driver.get("http://www.lazeryattack.com"), is(equalTo("driver.get(\"http://www.lazeryattack.com\")")));
    }

    @Test
    public void checkCloseReturnsCorrectCode() {
        assertThat(driver.close(), is(equalTo("driver.close()")));
    }

    @Test
    public void checkGetCurrentURLReturnsCorrectCode() {
        assertThat(driver.getCurrentUrl(), is(equalTo("driver.getCurrentUrl()")));
    }

    @Test
    public void checkGetTitleReturnsCorrectCode() {
        assertThat(driver.getTitle(), is(equalTo("driver.getTitle()")));
    }

    @Test
    public void checkGetPageSourceReturnsCorrectCode() {
        assertThat(driver.getPageSource(), is(equalTo("driver.getPageSource()")));
    }

    @Test
    public void checkWindowHandlesReturnsCorrectCode() {
        assertThat(driver.getWindowHandles(), is(equalTo("driver.getWindowHandles()")));
    }

    @Test
    public void checkWindowHandleReturnsCorrectCode() {
        assertThat(driver.getWindowHandle(), is(equalTo("driver.getWindowHandle()")));
    }

    @Test
    public void checkManageAddCookieReturnsCorrectCode() {
        assertThat(driver.manage().addCookie("YUM COOKIE!!"), is(equalTo("driver.manage().addCookie(\"YUM COOKIE!!\")")));
    }

    @Test
    public void checkManageDeleteCookieNamedReturnsCorrectCode() {
        assertThat(driver.manage().deleteCookieNamed("YUM COOKIE!!"), is(equalTo("driver.manage().deleteCookieNamed(\"YUM COOKIE!!\")")));
    }

    @Test
    public void checkManageDeleteCookieReturnsCorrectCode() {
        assertThat(driver.manage().deleteCookie("YUM COOKIE!!"), is(equalTo("driver.manage().deleteCookie(\"YUM COOKIE!!\")")));
    }

    @Test
    public void checkManageDeleteAllCookiesReturnsCorrectCode() {
        assertThat(driver.manage().deleteAllCookies(), is(equalTo("driver.manage().deleteAllCookies()")));
    }

    @Test
    public void checkManageGetCookiesReturnsCorrectCode() {
        assertThat(driver.manage().getCookies(), is(equalTo("driver.manage().getCookies()")));
    }

    @Test
    public void checkManageGetCookieNamedReturnsCorrectCode() {
        assertThat(driver.manage().getCookieNamed("YUM COOKIE!!"), is(equalTo("driver.manage().getCookieNamed(\"YUM COOKIE!!\")")));
    }

    @Test
    public void checkManageTimeoutsImplicitlyWaitReturnsCorrectCode() {
        assertThat(driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS), is(equalTo("driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS)")));
    }

    @Test
    public void checkManageTimeoutsSetScriptTimeoutReturnsCorrectCode() {
        assertThat(driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS), is(equalTo("driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS)")));
    }

    @Test
    public void checkSwitchToFrameReturnsCorrectCode() {
        assertThat(driver.switchTo().frame("foobar"), is(equalTo("driver.switchTo().frame(\"foobar\")")));
    }

    @Test
    public void checkSwitchToWindowReturnsCorrectCode() {
        assertThat(driver.switchTo().window("barfoo"), is(equalTo("driver.switchTo().window(\"barfoo\")")));
    }

    @Test
    public void checkSwitchToDefaultContentReturnsCorrectCode() {
        assertThat(driver.switchTo().defaultContent(), is(equalTo("driver.switchTo().defaultContent()")));
    }

    @Test
    public void checkSwitchToActiveElementReturnsCorrectCode() {
        assertThat(driver.switchTo().activeElement(), is(equalTo("driver.switchTo().activeElement()")));
    }

    @Test
    public void checkSwitchToAlertAcceptReturnsCorrectCode() {
        assertThat(driver.switchTo().alert().accept(), is(equalTo("driver.switchTo().alert().accept()")));
    }

    @Test
    public void checkSwitchToAlertDismissReturnsCorrectCode() {
        assertThat(driver.switchTo().alert().dismiss(), is(equalTo("driver.switchTo().alert().dismiss()")));
    }

    @Test
    public void checkSwitchToAlertGetTextReturnsCorrectCode() {
        assertThat(driver.switchTo().alert().getText(), is(equalTo("driver.switchTo().alert().getText()")));
    }

    @Test
    public void checkSwitchToAlertSendKeysReturnsCorrectCode() {
        assertThat(driver.switchTo().alert().sendKeys("bar"), is(equalTo("driver.switchTo().alert().sendKeys(\"bar\")")));
    }

    @Test
    public void checkNavigateBackReturnsCorrectCode() {
        assertThat(driver.navigate().back(), is(equalTo("driver.navigate().back()")));
    }

    @Test
    public void checkNavigateForwardReturnsCorrectCode() {
        assertThat(driver.navigate().forward(), is(equalTo("driver.navigate().forward()")));
    }

    @Test
    public void checkNavigateToReturnsCorrectCode() {
        assertThat(driver.navigate().to("http://www.lazeryattack.com"), is(equalTo("driver.navigate().to(\"http://www.lazeryattack.com\")")));
    }

    @Test
    public void checkNavigateRefreshReturnsCorrectCode() {
        assertThat(driver.navigate().refresh(), is(equalTo("driver.navigate().refresh()")));
    }
}
