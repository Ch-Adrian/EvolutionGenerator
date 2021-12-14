package agh.ics.oop.proj1.controller;

import agh.ics.oop.proj1.model.simulation.SimulationModel;
import agh.ics.oop.proj1.view.simulation.SimulationComponent;
import agh.ics.oop.proj1.view.simulation.SimulationView;
import javafx.scene.layout.HBox;

public class SimulationController {

    private SimulationModel simulationModel;
    private SimulationView simulationView;

    private World worldL;
    private World worldR;

    public static SimulationController createController(int widthMap1, int heightMap1, double jungleRatio1){
        return new SimulationController(widthMap1, heightMap1, jungleRatio1 );
    }

    public static SimulationController createController(int widthMap1, int heightMap1, double jungleRatio1,int widthMap2, int heightMap2, double jungleRatio2){
        return new SimulationController(widthMap1, heightMap1, jungleRatio1, widthMap2, heightMap2, jungleRatio2);
    }

    private SimulationController(int widthMap1, int heightMap1, double jungleRatio1 ){
        this.simulationModel = new SimulationModel(this);
        this.worldL = new World(widthMap1, heightMap1, jungleRatio1, simulationModel.getColors());
        this.simulationView = new SimulationView(this, this.simulationModel.getColors(), this.simulationModel.getPointsPolygon(),
                this.worldL.getSimulationComponent());


    }

    private SimulationController(int widthMap1, int heightMap1, double jungleRatio1,int widthMap2, int heightMap2, double jungleRatio2){
        this.simulationModel = new SimulationModel(this);
        this.worldL = new World(widthMap1, heightMap1, jungleRatio1, simulationModel.getColors());
        this.worldR = new World(widthMap2, heightMap2, jungleRatio2, simulationModel.getColors());
        this.simulationView = new SimulationView(this, this.simulationModel.getColors(), this.simulationModel.getPointsPolygon(),
                this.worldL.getSimulationComponent(), this.worldR.getSimulationComponent());


    }

    public HBox getSimulationView(){
        return simulationView.gethBox();
    }

}
