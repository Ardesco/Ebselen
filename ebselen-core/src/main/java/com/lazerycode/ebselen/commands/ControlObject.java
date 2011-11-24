package com.lazerycode.ebselen.commands;

import org.openqa.selenium.Keyboard;
import org.openqa.selenium.Mouse;

/**
 * Created by IntelliJ IDEA.
 * User: Mark Collin
 * Date: 24/11/11
 * Time: 11:24
 * To change this template use File | Settings | File Templates.
 */
public interface ControlObject {
    Mouse getMouseObject();

    Keyboard getKeyboardObject();
}
