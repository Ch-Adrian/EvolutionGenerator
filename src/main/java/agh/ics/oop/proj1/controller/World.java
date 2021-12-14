package agh.ics.oop.proj1.controller;

import agh.ics.oop.proj1.model.simulation.WorldModel;
import agh.ics.oop.proj1.view.simulation.SimulationComponent;
import javafx.scene.paint.Color;

public class World {

    private WorldModel worldModel;
    private SimulationComponent simulationComponent;
    private Changer changer;

    public World(int widthMap, int heightMap, double jungleRatio, Color[] colors){
        this.worldModel = new WorldModel(widthMap, heightMap, jungleRatio);
        this.simulationComponent = new SimulationComponent(widthMap, heightMap,
                this.worldModel.getJungleParam(), colors);
    }

    public SimulationComponent getSimulationComponent(){
        return simulationComponent;
    }

}
