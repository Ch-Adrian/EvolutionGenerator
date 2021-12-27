package agh.ics.oop.proj1.model.simulation;

import agh.ics.oop.proj1.controller.SimulationController;
import javafx.scene.paint.Color;

import static javafx.scene.paint.Color.rgb;

public class SimulationModel {

    private final Color colorAnimal;
    private final Color colorPlant;
    private final Color colorJungle;
    private final Color colorSteppe;
    private final Double[] pointsPolygon;
    private final SimulationController simulationController;

    public SimulationModel(SimulationController simulationController){
        this.simulationController = simulationController;
        colorAnimal = rgb(139,0,139);
        colorPlant = Color.RED;//rgb(204,204,0);
        colorSteppe = rgb(154,205,50);
        colorJungle = rgb(34,139,34);
        pointsPolygon = new Double[]{
                4.0,0.0,
                5.0,3.0,
                8.0,4.0,
                5.0,5.0,
                4.0,8.0,
                3.0,5.0,
                0.0,4.0,
                3.0,3.0};

    }

    public Color getColorAnimal() {
        return colorAnimal;
    }

    public Color getColorJungle() {
        return colorJungle;
    }

    public Color getColorPlant() {
        return colorPlant;
    }

    public Color getColorSteppe() {
        return colorSteppe;
    }

    public Double[] getPointsPolygon() {
        return pointsPolygon;
    }

    public Color[] getColors(){
        return new Color[]{
                colorAnimal,
                colorPlant,
                colorJungle,
                colorSteppe
        };
    }
}
