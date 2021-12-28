package agh.ics.oop.proj1.controller;

import agh.ics.oop.proj1.model.settings.SettingsModel;
import agh.ics.oop.proj1.model.simulation.SimulationModel;
import agh.ics.oop.proj1.view.simulation.SimulationComponent;
import agh.ics.oop.proj1.view.simulation.SimulationView;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SimulationController {

    private SettingsModel settingsModel;

    private SimulationModel simulationModel;
    private SimulationView simulationView;

    private World worldL;
    private World worldR;
    private boolean isTwoMap;
    private boolean simulationRuns;

    public static SimulationController createController(SettingsModel settingsModel, boolean isTwoMap){
        return new SimulationController(settingsModel, isTwoMap);
    }

    private SimulationController(SettingsModel settingsModel, boolean isTwoMap){
        this.simulationRuns=false;
        this.isTwoMap = isTwoMap;
        this.settingsModel = settingsModel;
        this.simulationModel = new SimulationModel(this);
        this.worldL = new World(settingsModel.getWidthMap(), settingsModel.getHeightMap(), settingsModel.getJungleRatio()
                , simulationModel.getColors(), settingsModel.getAmountOfAnimals(), settingsModel.getMagic(), settingsModel.getWall(),
                settingsModel.getEnergyAnimal(), settingsModel.getEnergyPlant(), settingsModel.getEnergyDayLoss());

        if(isTwoMap){
            this.worldR = new World(settingsModel.getWidthMap2(), settingsModel.getHeightMap2(), settingsModel.getJungleRatio2()
                    , simulationModel.getColors(), settingsModel.getAmountOfAnimals2(), settingsModel.getMagic2(), settingsModel.getWall2(),
                    settingsModel.getEnergyAnimal2(), settingsModel.getEnergyPlant2(), settingsModel.getEnergyDayLoss2());
            this.simulationView = new SimulationView(this, this.simulationModel.getColors(), this.simulationModel.getPointsPolygon(),
                    this.worldL.getSimulationComponent(), this.worldR.getSimulationComponent());
        }else{
            this.simulationView = new SimulationView(this, this.simulationModel.getColors(), this.simulationModel.getPointsPolygon(),
                    this.worldL.getSimulationComponent());
        }

    }

    public boolean startButton(){
        if(this.simulationRuns){
            this.simulationRuns=false;
            this.worldL.stopSimulation();
            if (this.isTwoMap) {
                this.worldR.stopSimulation();
            }
        }else {
            this.simulationRuns=true;
            this.worldL.startButton();
            if (this.isTwoMap) {
                this.worldR.startButton();
            }
        }
        return this.simulationRuns;
    }

    public void saveToFile(){
        File csvFile = new File("lastSimulationWorld1.csv");
        if(csvFile.isFile()){
            csvFile.delete();
        }

        try {
            csvFile.createNewFile();

            PrintWriter printWriter = new PrintWriter("lastSimulationWorld1.csv");
            this.worldL.saveToFile(printWriter);
            printWriter.close();

        } catch(IOException ioE){
            return;
        }
        if(isTwoMap){
            File csvFile2 = new File("lastSimulationWorld2.csv");
            if(csvFile2.isFile()){
                csvFile2.delete();
            }
            try {
                csvFile2.createNewFile();

                PrintWriter printWriter = new PrintWriter("lastSimulationWorld2.csv");
                this.worldR.saveToFile(printWriter);
                printWriter.close();

            } catch(IOException ioE){
                return;
            }
        }
    }

    public HBox getSimulationView(){
        return simulationView.gethBox();
    }

}
