package com.example.graphdrawer.Menu.ExpressionWindow;

import com.example.graphdrawer.Menu.Data;
import com.example.interpreter.Expression;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;

public class ExpressionWindowController extends GridPane {
    public ExpressionWindowController(Data expression) {
        super();

        this.setPadding(new Insets(10, 10, 10, 10));
        this.setVgap(5);
        this.setHgap(5);

        final TextField expressionField = new TextField();
        expressionField.setPromptText("Enter expression");
        expressionField.setPrefColumnCount(20);
        expressionField.getText();
        GridPane.setConstraints(expressionField, 0, 0);
        this.getChildren().add(expressionField);

        final Label labelError = new Label();
        GridPane.setConstraints(labelError, 0, 3);
        GridPane.setColumnSpan(labelError, 2);
        this.getChildren().add(labelError);

        Button draw = new Button("Draw");
        draw.setOnAction(e -> {
            if (expressionField.getText() != null && !expressionField.getText().isEmpty()) {
                expression.setData(new Expression(expressionField.getText()));
                Stage stage = (Stage) this.getScene().getWindow();
                stage.setOnHidden(event -> {
                    Object[] parameters = new Object[1];
                    parameters[0] = "123";
                    try {
                        expression.getFunc().invoke(parameters, expression);
                    } catch (IllegalAccessException ex) {
                        throw new RuntimeException(ex);
                    } catch (InvocationTargetException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                stage.close();
            }
            else {
                labelError.setText("Enter the expression");
            }
        });
        GridPane.setConstraints(draw, 1, 0);
        this.getChildren().add(draw);
    }
}
