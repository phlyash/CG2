package com.example.graphdrawer.Menu;

import com.example.graphdrawer.Menu.ExpressionWindow.ExpressionWindowController;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class ExpressionMenuItemController extends MenuItem {
    public ExpressionMenuItemController(Data expression) {
        super("Expression...");
        this.setOnAction(e -> {
            Stage stage = new Stage();
            stage.setTitle("Expression field");
            stage.setScene(new Scene(new ExpressionWindowController(expression),350, 100));
            stage.show();
        });
    }
}
