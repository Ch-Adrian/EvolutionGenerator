package agh.ics.oop.proj1.view.simulation;

import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.util.concurrent.atomic.AtomicInteger;

public class ChartComponent {

    NumberAxis xAxis;
    NumberAxis yAxis;
    LineChart<Number,Number> lineChart;

    /*
    - liczby wszystkich zwierząt,
    - liczby wszystkich roślin,
    - dominujących genotypów (dominanta z genotypów, a nie genów),
    - średniego poziomu energii dla żyjących zwierząt,
    - średniej długości życia zwierząt dla martwych zwierząt (wartość uwzględnia wszystkie nieżyjące zwierzęta - od początku symulacji),
    - średniej liczby dzieci dla żyjących zwierząt (wartość uwzględnia wszystkie powstałe zwierzęta, a nie tylko zwierzęta powstałe w danej epoce).
     */
    XYChart.Series[] series;

    public ChartComponent() {
        xAxis = new NumberAxis();
        yAxis = new NumberAxis();
        lineChart = new LineChart<>(xAxis,yAxis);
        lineChart.setCreateSymbols(false);

        series = new XYChart.Series[6];
        for(int i=0; i<6; i++){
            series[i] = new XYChart.Series();
        }
        series[0].setName("Animals");
        series[1].setName("Plants");
        series[2].setName("Genotype");
        series[3].setName("Average energy");
        series[4].setName("Life time");
        series[5].setName("Offspring amount");

        lineChart.getData().addAll(series);
    }

    public LineChart<Number, Number> getLineChart() {
        return lineChart;
    }

    public void addSeriesData(/**/){

    }
}
