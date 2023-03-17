package com.example;

import javafx.scene.chart.XYChart;

public class MyGraph {

    private XYChart<Double, Double> graph;
    private XYChart.Series<Double, Double> series;

    public MyGraph(final XYChart<Double, Double> graph) {
        this.graph = graph;
        series = new XYChart.Series<Double, Double>();
        graph.getData().add(series);
    }

    private void plotPoint(final double x, final XYChart.Series<Double, Double> series) {
        series.getData().add(new XYChart.Data<Double, Double>(x, x==0?1:Math.sin(x)/x));
    }

    public void addPoint(double x){
        plotPoint(x, series);
    }

    public void chengePoint(double j, double x){
        for(int i = 0 ; i< series.getData().size(); i++){
            if(series.getData().get(i).getXValue() == i){
            series.getData().get(i).setXValue(x);
            series.getData().get(i).setYValue(x==0?1:Math.sin(x)/x);
        return;
        }
        }
    }

    public void removePoint(double i){
        for (XYChart.Data<Double, Double> t : series.getData()) {
            if(t.getXValue() == i){
                removePoint(t);
                return;
            }
        }
    }

    public void removePoint(XYChart.Data<Double, Double> i){
        series.getData().remove(i);
    }

    public void clear() {
        series.getData().clear();
    }
}