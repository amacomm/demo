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
    private MyGraph MathGraph;

    @Override
    public void initialize(final URL url, final ResourceBundle rb) {
        MathGraph = new MyGraph(areaGraph);

        xColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
        yColumn.setCellValueFactory(new PropertyValueFactory<>("y"));
        bColumn.setCellValueFactory(new PropertyValueFactory<>("button"));

        xColumn.setCellFactory(TextFieldTableCell.<Point, Double>forTableColumn(new DoubleStringConverter()));
        xColumn.setOnEditCommit(e -> {
            MathGraph.chengePoint(e.getOldValue(), e.getNewValue());
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setX(e.getNewValue());
        });


        tablePoint.setEditable(true);

        tablePoint.setItems(pointData);
    }

    @FXML
    private void Func(final ActionEvent event) {
        plotLine();
    }

    private void AddValue(double x){
        MathGraph.addPoint(x);
        Button b = new Button("x");
        Point p = new Point(x, b);
        b.setOnAction(e -> {
            pointData.remove(p);
            MathGraph.remove(p);
        });
        pointData.add(p);
    }

    private void plotLine() {
        for(double i =-20; i<20; i++){
            AddValue(i);
        }
    }

    @FXML
    private void AddPoint(final ActionEvent event) {
        double d = Double.parseDouble(textField.getText());
        AddValue(d);
    }

    @FXML
    private void Clear(final ActionEvent event) {
        clear();
    }

    private void clear() {
        pointData.clear();
        MathGraph.clear();
    }
}