package agh.ics.oop.proj1.controller;

import agh.ics.oop.proj1.view.settings.SettingsComponent;
import agh.ics.oop.proj1.view.simulation.SimulationView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    private static MenuController menuController;
    private static Stage stage;
    private static SettingsController settingsController;
    private static SimulationController simulationController;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        setMenu();
    }

    public static void setMenu(){
        menuController = MenuController.createController();
        Scene scene = new Scene(menuController.getMenuView());
        stage.setScene(scene);
        stage.show();
    }

    public static void setSettings(boolean isTwoMap){
        stage.hide();
        settingsController = SettingsController.createController(isTwoMap);
        Scene scene = new Scene(settingsController.getSettingsView());
        stage.setScene(scene);
        stage.show();
    }

    public static void setSimulation(int widthMap1, int heightMap1, double jungleRatio1){
        simulationController = SimulationController.createController(widthMap1, heightMap1, jungleRatio1);
        Scene scene = new Scene(simulationController.getSimulationView());
        stage.setScene(scene);
        stage.show();
    }

    public static void setSimulation(int widthMap1, int heightMap1, double jungleRatio1,int widthMap2, int heightMap2, double jungleRatio2){
        stage.hide();
        simulationController = SimulationController.createController(widthMap1, heightMap1, jungleRatio1, widthMap2, heightMap2, jungleRatio2);
        Scene scene = new Scene(simulationController.getSimulationView());
        stage.setScene(scene);
        stage.show();
    }

}
