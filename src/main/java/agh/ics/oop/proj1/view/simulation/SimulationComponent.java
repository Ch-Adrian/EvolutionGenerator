package agh.ics.oop.proj1.view.simulation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class SimulationComponent {

    private VBox vBox;
    private Label epoch;
    private Label dominantGenotype;
    private GridPaneComponent gridPaneComponent;
    private ChartComponent chartComponent;
    private BorderPane borderBox;
    private Label magicEvolution;

    public SimulationComponent(int widthMap, int heightMap, int[] jungleParam, Color[] colors){
        vBox = new VBox();
        borderBox = new BorderPane();
        epoch = new Label("Epoch: 0");
        epoch.setPadding(new Insets(20,0,0,20));
        epoch.setFont(new Font(30));

        dominantGenotype = new Label("Dominant genotype: ");
        dominantGenotype.setPadding(new Insets(3,0,0,20));
        dominantGenotype.setFont(new Font(20));

        magicEvolution = new Label("");
        magicEvolution.setPadding(new Insets(28,0,0,20));
        magicEvolution.setFont(new Font(17));

        gridPaneComponent = new GridPaneComponent(widthMap,heightMap, jungleParam, colors);
        chartComponent = new ChartComponent();
        HBox title =  new HBox();
        title.getChildren().add(epoch);
        title.getChildren().add(magicEvolution);

        vBox.getChildren().addAll(title, dominantGenotype, gridPaneComponent.getGridPane());
        vBox.alignmentProperty().setValue(Pos.TOP_LEFT);
        borderBox.setTop(vBox);
        borderBox.setBottom(chartComponent.getLineChart());
        //vBox.setPrefWidth(1000);
    }

    public void setTextDominantGenotype(String genotype) {
        this.dominantGenotype.setText(genotype);
    }

    public void setTextEpoch(String epoch) {
        this.epoch.setText(epoch);
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

    public void setMagicEvolution(String txt){
        this.magicEvolution.setText(txt);
    }
}
