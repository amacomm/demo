package com.example;

import javafx.beans.property.*;
import javafx.scene.control.Button;

public class Point {

    private SimpleDoubleProperty x;
    private SimpleDoubleProperty y;
    private Button button;

    public Point(double x, Button button) {
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(x==0?1:Math.sin(x)/x);
        this.button = button;
    }

    public Point() {
    }


    public double getX() {
        return x.get();
    }

    public void setX(double x) {
        this.x.set(x);
        this.y.set(x==0?1:Math.sin(x)/x);
    }
    
    public double getY() {
        return y.get();
    }

    public void setY(double y) {
        this.y.set(y);
    }
    
    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}