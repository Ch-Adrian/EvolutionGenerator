package agh.ics.oop.proj1.view.simulation;

import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.util.ArrayList;
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
        /*ArrayList<Pair<Integer,Integer>> a = new ArrayList<>();
        a.add(new Pair<>(1,1));
        a.add(new Pair<>(5,17));
        a.add(new Pair<>(3,12));
        a.add(new Pair<>(9,25));
        a.add(new Pair<>(2,7));
        a.add(new Pair<>(3,8));
        addSeriesData(a);*/

    }

    public LineChart<Number, Number> getLineChart() {
        return lineChart;
    }

    public void addSeriesData(Pair<Integer,Integer[]> stat){

        if(stat.getValue().length == 6){
            for(int i =0 ; i<6; i++){
                series[i].getData().add(new XYChart.Data(stat.getKey(),
                        stat.getValue()[i]));
            }
        }
        else{
            System.out.println("Lack of data.");
        }
    }
}
