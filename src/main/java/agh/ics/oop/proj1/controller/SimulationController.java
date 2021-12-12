package agh.ics.oop.proj1.controller;

import agh.ics.oop.proj1.model.simulation.SimulationModel;
import agh.ics.oop.proj1.view.simulation.SimulationComponent;
import agh.ics.oop.proj1.view.simulation.SimulationView;

public class SimulationController {

    private SimulationModel simulationModel;
    private SimulationView simulationView;

    public SimulationController(){
        this.simulationModel = new SimulationModel(this);
        this.simulationView = new SimulationView(this, this.simulationModel.getColors(), this.simulationModel.getPointsPolygon(),
                new SimulationComponent(), new SimulationComponent());
    }

    public javafx.scene.layout.HBox getView(){
        return simulationView.gethBox();
    }

}
