package com.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;

public class MyGraph {
    private ObservableList<Data<Double, Double>> pointData = FXCollections.observableArrayList();

    public MyGraph(final XYChart<Double, Double> graph) {
        XYChart.Series<Double, Double> series = new XYChart.Series<Double, Double>();
        graph.getData().add(series);
        series.setData(pointData);
    }

    private XYChart.Data<Double, Double> Func(double x){
        return new XYChart.Data<Double, Double>(x, x==0?1:Math.sin(x)/x);
    }

    public void addPoint(double x){
        pointData.add(Func(x));
    }

    public void chengePoint(double j, double x){
        for(int i = 0 ; i< pointData.size(); i++){
            if(((Double)pointData.get(i).getXValue()).equals(j)){
                pointData.set(i, Func(x));
                return;
            }
        }
    }

    public void removePoint(double x){
        for (XYChart.Data<Double, Double> t : pointData) {
            if(t.getXValue().equals(x)){
                pointData.remove(t);
                return;
            }
        }
    }

    public void remove(Point p){
        removePoint(p.getX());
    }

    public void clear() {
        pointData.clear();
    }
}