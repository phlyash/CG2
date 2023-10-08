package com.example.graphdrawer.Menu;

import javafx.scene.control.MenuBar;

import java.lang.reflect.InvocationTargetException;

public class MenuBarController extends MenuBar {
    public MenuBarController(Data expression) {
        this.getMenus().add(new MenuController(expression));
    }
}
