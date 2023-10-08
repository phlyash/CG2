package com.example.graphdrawer.Menu;

import javafx.scene.control.Menu;

import java.lang.reflect.InvocationTargetException;

public class MenuController extends Menu {
    public MenuController(Data expression) {
        super("Draw");
        this.getItems().add(new ExpressionMenuItemController(expression));
    }
}
