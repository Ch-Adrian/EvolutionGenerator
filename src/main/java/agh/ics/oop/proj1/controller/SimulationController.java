package agh.ics.oop.proj1.controller;

import agh.ics.oop.proj1.model.simulation.SimulationComponentModel;
import agh.ics.oop.proj1.model.simulation.SimulationModel;
import agh.ics.oop.proj1.view.simulation.SimulationComponent;
import agh.ics.oop.proj1.view.simulation.SimulationView;
import javafx.scene.layout.HBox;

public class SimulationController {

    private SimulationModel simulationModel;
    private SimulationView simulationView;

    public static SimulationController createController(int widthMap1, int heightMap1, double jungleRatio1){
        return new SimulationController(widthMap1, heightMap1, jungleRatio1 );
    }

    public static SimulationController createController(int widthMap1, int heightMap1, double jungleRatio1,int widthMap2, int heightMap2, double jungleRatio2){
        return new SimulationController(widthMap1, heightMap1, jungleRatio1, widthMap2, heightMap2, jungleRatio2);
    }

    public SimulationController(int widthMap1, int heightMap1, double jungleRatio1 ){
        this.simulationModel = new SimulationModel(this);
        this.simulationView = new SimulationView(this, this.simulationModel.getColors(), this.simulationModel.getPointsPolygon(),
                new SimulationComponent(widthMap1, heightMap1, jungleRatio1, simulationModel.getColors()));
    }

    public SimulationController(int widthMap1, int heightMap1, double jungleRatio1,int widthMap2, int heightMap2, double jungleRatio2){
        this.simulationModel = new SimulationModel(this);
        this.simulationView = new SimulationView(this, this.simulationModel.getColors(), this.simulationModel.getPointsPolygon(),
                new SimulationComponent(widthMap1, heightMap1, jungleRatio1, simulationModel.getColors()),
                new SimulationComponent(widthMap2, heightMap2, jungleRatio2, simulationModel.getColors()));
    }

    public HBox getSimulationView(){
        return simulationView.gethBox();
    }

}
