package agh.ics.oop.proj1.view.simulation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class SimulationComponent {

    private VBox vBox;
    private Label epoch;
    private GridPaneComponent gridPaneComponent;
    private ChartComponent chartComponent;

    public SimulationComponent(){
        vBox = new VBox();
        epoch = new Label("Epoch: 0");
        epoch.setPadding(new Insets(20,0,0,20));
        epoch.setFont(new Font(30));
        gridPaneComponent = new GridPaneComponent(3,3,0.5);
        chartComponent = new ChartComponent();
        vBox.getChildren().addAll(epoch, gridPaneComponent.getGridPane(), chartComponent.getLineChart());
        vBox.alignmentProperty().setValue(Pos.TOP_LEFT);
        //vBox.setPrefWidth(1000);
    }

    public VBox getvBox() {
        return vBox;
    }
}
