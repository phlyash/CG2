package com.example.graphdrawer.Menu;

import com.example.interpreter.Expression;
import javafx.scene.canvas.GraphicsContext;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class Data {
    private int x;
    private int y;
    private double scaleX;
    private double scaleY;

    public double getScaleX() {
        return scaleX;
    }

    public void setScaleX(double scaleX) {
        if (scaleX > 0.001) this.scaleX = scaleX;
    }

    public double getScaleY() {
        return scaleY;
    }

    public void setScaleY(double scaleY) {
        if(scaleY > 0.001) this.scaleY = scaleY;
    }

    private Expression data;
    private Method func;
    private GraphicsContext g;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public GraphicsContext getG() {
        return g;
    }

    public void setG(GraphicsContext g) {
        this.g = g;
    }

    public Method getFunc() {
        return func;
    }

    public void setFunc(Method func) {
        this.func = func;
    }

    public Expression getData() {
        return data;
    }

    public void setData(Expression data) {
        this.data = data;
    }
}
