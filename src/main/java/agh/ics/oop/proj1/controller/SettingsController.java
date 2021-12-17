package agh.ics.oop.proj1.controller;

import agh.ics.oop.proj1.model.settings.SettingsModel;
import agh.ics.oop.proj1.view.settings.SettingsView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class SettingsController {

    private SettingsView settingsView;
    private SettingsModel settingsModel;

    public static SettingsController createController(boolean isTwoMap){
        return new SettingsController(isTwoMap);
    }

    private SettingsController(boolean isTwoMap){
        if(isTwoMap) {
            settingsModel = new SettingsModel();
            settingsView = new SettingsView(this, settingsModel.getDefaultParam(), settingsModel.getDefaultParam());
        } else {
            settingsModel = new SettingsModel();
            settingsView = new SettingsView(this, settingsModel.getDefaultParam());
        }
    }

    public void buttonCreate(ArrayList<String> realValues){
        settingsModel.setUserParamMap1(realValues);
        App.setSimulation(settingsModel, false);
    }

    public void buttonCreate(ArrayList<String> realValues, ArrayList<String> realValues2){
        settingsModel.setUserParamMap1(realValues);
        settingsModel.setUserParamMap2(realValues2);
        App.setSimulation(settingsModel, true);
    }

    public VBox getSettingsView(){
        return settingsView.getvBox();
    }

}
