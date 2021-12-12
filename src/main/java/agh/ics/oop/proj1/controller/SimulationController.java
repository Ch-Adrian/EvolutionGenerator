package agh.ics.oop.proj1.controller;

import agh.ics.oop.proj1.model.simulation.SimulationComponentModel;
import agh.ics.oop.proj1.model.simulation.SimulationModel;
import agh.ics.oop.proj1.view.simulation.SimulationComponent;
import agh.ics.oop.proj1.view.simulation.SimulationView;

public class SimulationController {

    private SimulationModel simulationModel;
    private SimulationView simulationView;

    public static SimulationController createController(){
        return new SimulationController();
    }

    public SimulationController(){
        SimulationComponentModel simulationComponentModel = new SimulationComponentModel();
        this.simulationModel = new SimulationModel(this);
        this.simulationView = new SimulationView(this, this.simulationModel.getColors(), this.simulationModel.getPointsPolygon(),
                new SimulationComponent(simulationComponentModel.getWidthMap(), simulationComponentModel.getHeightMap(), simulationComponentModel.getJungleRatio(), simulationModel.getColors()));
    }

    public SimulationController(int a){
        SimulationComponentModel simulationComponentModelL = new SimulationComponentModel();
        SimulationComponentModel simulationComponentModelR = new SimulationComponentModel();
        this.simulationModel = new SimulationModel(this);
        this.simulationView = new SimulationView(this, this.simulationModel.getColors(), this.simulationModel.getPointsPolygon(),
                new SimulationComponent(simulationComponentModelL.getWidthMap(), simulationComponentModelL.getHeightMap(), simulationComponentModelL.getJungleRatio(), simulationModel.getColors()),
                new SimulationComponent(simulationComponentModelR.getWidthMap(), simulationComponentModelR.getHeightMap(), simulationComponentModelR.getJungleRatio(), simulationModel.getColors()));
    }

    public javafx.scene.layout.HBox getView(){
        return simulationView.gethBox();
    }

}
