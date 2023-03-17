package com.example;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.util.converter.DoubleStringConverter;

public class PrimaryController  implements Initializable{

    private ObservableList<Point> pointData = FXCollections.observableArrayList();

    @FXML
    private LineChart<Double, Double> lineGraph;

    @FXML
    private AreaChart<Double, Double> areaGraph;

    @FXML
    private TableView<Point> tablePoint;

    @FXML
    private TableColumn<Point, Double> xColumn;

    @FXML
    private TableColumn<Point, Double> yColumn;

    @FXML
    private TableColumn<Point, Button> bColumn;

    @FXML
    private Button lineGraphButton;

    @FXML
    private Button areaGraphButton;

    @FXML
    private Button xyButton;

    @FXML
    private Button xyButton2;

    @FXML
    private Button squaredButton;

    @FXML
    private Button squaredButton2;

    @FXML
    private Button cubedButton;

    @FXML
    private Button cubedButton2;

    @FXML
    private Button clearButton;

    @FXML
    private TextField textField;

    private MyGraph mathsGraph;
    private MyGraph areaMathsGraph;

    @Override
    public void initialize(final URL url, final ResourceBundle rb) {
        mathsGraph = new MyGraph(lineGraph);
        areaMathsGraph = new MyGraph(areaGraph);

        xColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
        yColumn.setCellValueFactory(new PropertyValueFactory<>("y"));
        bColumn.setCellValueFactory(new PropertyValueFactory<>("button"));

        xColumn.setCellFactory(TextFieldTableCell.<Point, Double>forTableColumn(new DoubleStringConverter()));
        xColumn.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setX(e.getNewValue());
            areaMathsGraph.chengePoint(e.getOldValue(), e.getNewValue());
        });


        tablePoint.setEditable(true);

        tablePoint.setItems(pointData);
    }

    @FXML
    private void handleLineGraphButtonAction(final ActionEvent event) {
        lineGraph.setVisible(true);
        areaGraph.setVisible(false);
    }

    @FXML
    private void handleAreaGraphButtonAction(final ActionEvent event) {
        areaGraph.setVisible(true);
        lineGraph.setVisible(false);
    }

    @FXML
    private void Func(final ActionEvent event) {
        plotLine();
    }

    private void plotLine() {
        for(double i =-20; i<20; i++){
            areaMathsGraph.addPoint(i);
            Button b = new Button("x");
            Point p = new Point(i, b);
            b.setOnAction(e -> {
                pointData.remove(p);
                areaMathsGraph.removePoint(p.getX());
            });
            pointData.add(p);
        }
        // if (lineGraph.isVisible()) {
        //     mathsGraph.plotLine();
        // } else {
        //     areaMathsGraph.plotLine();
        // }
    }

    @FXML
    private void AddPoint(final ActionEvent event) {
        double d = Double.parseDouble(textField.getText());
        areaMathsGraph.addPoint(d);
        Button b = new Button("x");
            Point p = new Point(d, b);
            b.setOnAction(e -> {
                pointData.remove(p);
                areaMathsGraph.removePoint(d);
            });
            pointData.add(p);
    }

    @FXML
    private void handleSquaredButtonAction(final ActionEvent event) {
        areaMathsGraph.chengePoint(5, 3.34);
    }

    @FXML
    private void Clear(final ActionEvent event) {
        clear();
    }

    private void clear() {
        pointData.clear();
        if (lineGraph.isVisible()) {
            mathsGraph.clear();
        } else {
            areaMathsGraph.clear();
        }
    }
}