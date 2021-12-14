package agh.ics.oop.proj1.view.simulation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class SimulationComponent {

    private VBox vBox;
    private Label epoch;
    private GridPaneComponent gridPaneComponent;
    private ChartComponent chartComponent;
    private BorderPane borderBox;

    public SimulationComponent(int widthMap, int heightMap, int[] jungleParam, Color[] colors){
        vBox = new VBox();
        borderBox = new BorderPane();
        epoch = new Label("Epoch: 0");
        epoch.setPadding(new Insets(20,0,0,20));
        epoch.setFont(new Font(30));

        gridPaneComponent = new GridPaneComponent(widthMap,heightMap, jungleParam, colors);
        chartComponent = new ChartComponent();

        vBox.getChildren().addAll(epoch, gridPaneComponent.getGridPane());
        vBox.alignmentProperty().setValue(Pos.TOP_LEFT);
        borderBox.setTop(vBox);
        borderBox.setBottom(chartComponent.getLineChart());
        //vBox.setPrefWidth(1000);
    }

    public BorderPane getvBox() {
        return borderBox;
    }

    public ChartComponent getChartComponent() {
        return chartComponent;
    }

    public GridPaneComponent getGridPaneComponent() {
        return gridPaneComponent;
    }
}
