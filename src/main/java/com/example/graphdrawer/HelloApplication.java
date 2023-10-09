package com.example.graphdrawer;

import com.example.graphdrawer.Menu.Data;
import com.example.graphdrawer.Menu.MenuBarController;
import com.example.interpreter.Expression;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Callable;

public class HelloApplication extends Application  {
    private final Data expression = new Data();
    private GraphicsContext g;
    private final int v = 1920;
    private final int v1 = 1080;
    private int x = (v / 2);
    private int y = (v1 / 2);
    private double scaleX = 1;
    private double scaleY = 1;
    private double dragDeltaStartX = 0;
    private double dragDeltaStartY = 0;

    @Override
    public void start(Stage stage) throws IOException, NoSuchMethodException {
        Canvas canvas = new Canvas(v, v1);
        g = canvas.getGraphicsContext2D();

        expression.setG(g);
        expression.setX(x);
        expression.setY(y);
        expression.setScaleX(scaleX);
        expression.setScaleY(scaleY);
        expression.setFunc(this.getClass().getMethod("drawGraphic", Data.class));

        MenuBarController mBC = new MenuBarController(expression);
        VBox vBox = new VBox(mBC);
        Group root = new Group();

        addMouseScrolling(canvas);
        addMouseDragListener(canvas);


        root.getChildren().add(canvas);
        root.getChildren().add(vBox);

        Scene scene = new Scene(root, v, v1);

        stage.setTitle("GraphDrawer");
        stage.setScene(scene);
        stage.show();
    }

    public static void drawGraphic(Data data) {
        GraphicsContext gc = data.getG();
        data.getG().clearRect(0, 0, data.getG().getCanvas().getWidth(), data.getG().getCanvas().getHeight());
        PixelWriter pW = data.getG().getPixelWriter();
        for(int i = 0; i < data.getG().getCanvas().getHeight(); i++) { // ?
            pW.setColor(data.getX(), i, Color.BLACK);
        }
        for(int i = 0; i < data.getG().getCanvas().getWidth(); i++) { // ?
            pW.setColor(i, data.getY(), Color.BLACK);
        }

        double scaleX = data.getScaleX();
        double scaleY = data.getScaleY();

        int[] xP = new int[1920];
        int[] yP = new int[1920];

        for(int x = 0; x < data.getG().getCanvas().getWidth(); x++) {
            xP[x] = x;
            yP[x] = data.getY() - (int)(data.getData().getRoot().calc((x - data.getX()) * scaleX) * scaleY);
        }

        for (int x = 0; x < data.getG().getCanvas().getWidth() - 1; x++) {
            LineDrawer.WuLine(xP[x], yP[x], xP[x+1], yP[x+1], gc.getPixelWriter());
        }
        /*
        gc.beginPath();
        for (int x = 0; x < data.getG().getCanvas().getWidth(); x++) {
            double scaledX = (x - data.getX()) * scaleX;
            double scaledY = data.getData().getRoot().calc(scaledX) * scaleY;
            double y = data.getY() - scaledY;

            gc.lineTo(x, y);
        }
        gc.stroke();
        */
    }
    private void addMouseScrolling(Node node) {
        node.setOnScroll((ScrollEvent event) -> {
            expression.setScaleX(expression.getScaleX() + event.getDeltaX() / 100);
            expression.setScaleY(expression.getScaleY() + event.getDeltaY() / 100);

            drawGraphic(expression);
        });
    }
    private void addMouseDragListener(Node node) {
        node.setOnMousePressed(mouseEvent -> {
            this.dragDeltaStartX = mouseEvent.getX();
            this.dragDeltaStartY = mouseEvent.getY();
        });
        node.setOnMouseDragged(mouseEvent -> {
            expression.setX(expression.getX() - ((int)this.dragDeltaStartX - (int)mouseEvent.getX()) / 10);
            expression.setY(expression.getY() - ((int)this.dragDeltaStartY - (int)mouseEvent.getY()) / 10);

            drawGraphic(expression);
        });
    }
    public static void main(String[] args) {
        launch();
    }
}