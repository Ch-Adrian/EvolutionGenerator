package agh.ics.oop.proj1.controller;

import agh.ics.oop.proj1.view.simulation.SimulationView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        SimulationController simulationController = new SimulationController();
        Scene scene = new Scene(simulationController.getView());
        primaryStage.setScene(scene);
        primaryStage.show();
        //Menu menu = new Menu();
        //menu.show();
    }
}
