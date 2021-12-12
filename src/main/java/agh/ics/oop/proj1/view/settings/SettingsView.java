package agh.ics.oop.proj1.view.settings;

import agh.ics.oop.proj1.controller.SettingsController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class SettingsView {

    private VBox vBox;
    private HBox hBox;
    private Separator separator;
    private SettingsController settingsController;
    private SettingsComponent componentLeft;
    private SettingsComponent componentRight;
    private Button buttonCreate;

    public SettingsView(SettingsController settingsController, ArrayList<String> realValues){
        this.settingsController = settingsController;
        vBox = new VBox();
        hBox = new HBox();
        separator = new Separator();
        componentLeft = new SettingsComponent(realValues);
        hBox.getChildren().add(componentLeft.getvBox());
        buttonCreate = new Button("Create");
        buttonCreate.setPrefWidth(100);
        buttonCreate.setOnAction((event)->{
            this.settingsController.buttonCreate(componentLeft.getSettings());
        });
        vBox.getChildren().addAll(hBox, buttonCreate);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(0,0,20,0));
    }

    public SettingsView(SettingsController settingsController, ArrayList<String> realValues, ArrayList<String> realValues2){
        this.settingsController = settingsController;
        hBox = new HBox();
        vBox = new VBox();
        separator = new Separator();
        separator.prefWidth(20);

        componentLeft = new SettingsComponent(realValues);
        componentLeft.setCheckBox2(false);
        componentLeft.disableCheckBox();
        componentLeft.setTitle("Map 1");

        componentRight = new SettingsComponent(realValues2);
        componentRight.setCheckBox2(true);
        componentRight.disableCheckBox();
        componentRight.setTitle("Map 2");

        hBox.getChildren().addAll(componentLeft.getvBox(), separator, componentRight.getvBox());
        buttonCreate = new Button("Create");
        buttonCreate.setPrefWidth(100);
        buttonCreate.setOnAction((event)->{
            this.settingsController.buttonCreate(componentLeft.getSettings(), componentRight.getSettings());
        });
        vBox.getChildren().addAll(hBox, buttonCreate);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(0,0,20,0));
    }


    public VBox getvBox() {
        return vBox;
    }


}
