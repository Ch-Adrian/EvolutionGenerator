package agh.ics.oop.proj1.view.menu;

import agh.ics.oop.proj1.controller.MenuController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MenuView{

    private VBox vBox;
    private Label labelTitle;
    private VBox boxButton;
    private Button buttonSimulation;
    private Button buttonTwoSimultaions;
    private Tooltip buttonTooltipOne;
    private Tooltip buttonTooltipTwo;
    private MenuController menuController;

    public MenuView(MenuController menuController){
        this.menuController = menuController;
        vBox = new VBox();
        labelTitle = new Label("Evolution Generator");
        labelTitle.setFont(new Font(30));
        labelTitle.setPadding(new Insets(10, 10, 10, 10 ));
        boxButton = new VBox();

        buttonTooltipOne = new Tooltip();
        buttonTooltipOne.setText("Creates one simulation");
        buttonSimulation = new Button("One Simulation");
        buttonSimulation.setOnAction((event)->{
            menuController.buttonOneSimulation();
        });
        buttonSimulation.setPrefWidth(200);
        buttonSimulation.setTooltip(buttonTooltipOne);

        buttonTooltipTwo = new Tooltip();
        buttonTooltipTwo.setText("Creates two simulations: one with wall and one without wall.");
        buttonTwoSimultaions = new Button("Two Simulations");
        buttonTwoSimultaions.setOnAction((event)->{
            menuController.buttonTwoSimulations();
        });
        buttonTwoSimultaions.setPrefWidth(200);
        buttonTwoSimultaions.setTooltip(buttonTooltipTwo);

        boxButton.getChildren().addAll(buttonSimulation, buttonTwoSimultaions);
        boxButton.setSpacing(10);
        boxButton.setAlignment(Pos.CENTER);
        boxButton.setPadding(new Insets(20,20,20,20));
        vBox.getChildren().addAll(labelTitle, boxButton);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
    }

    public VBox getvBox() {
        return vBox;
    }


}
