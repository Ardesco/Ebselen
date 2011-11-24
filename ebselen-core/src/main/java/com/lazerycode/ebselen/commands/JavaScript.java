package com.lazerycode.ebselen.commands;

import org.openqa.selenium.WebElement;

/**
 * Created by IntelliJ IDEA.
 * User: Mark Collin
 * Date: 24/11/11
 * Time: 11:20
 * To change this template use File | Settings | File Templates.
 */
public interface JavaScript {
    /**
     * Helper class to make it easy to fire a JavaScript event.
     *
     * @param event   Type of event you want to fire.
     * @param element The element on the page to fire the event on.
     */
    void triggerJavascriptEvent(jsEvent event, WebElement element);

    public enum jsEvent {
        ONLOAD, ONUNLOAD, ONBLUR, ONCHANGE, ONFOCUS, ONRESET, ONSELECT, ONSUBMIT, ONABORT, ONKEYDOWN, ONKEYPRESS, ONKEYUP, ONCLICK, ONDBLCLICK, ONMOUSEDOWN, ONMOUSEMOVE, ONMOUSEOUT, ONMOUSEOVER, ONMOUSEUP
    }
}
