package agh.ics.oop.proj1.view.simulation;

import agh.ics.oop.proj1.controller.SimulationController;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.text.BreakIterator;

public class SimulationView {

    private HBox hBox;
    private Separator left;
    private Separator right;
    private SimulationComponent simulationComponentLeft;
    private SimulationComponent simulationComponentRight;
    private SimulationController simulationController;

    private VBox settings;
    private VBox legendBox;
    private VBox buttonsBox;
    private Button start;
    private Button exportData;
    private Button dominantGenotype;
    private Label labelLegend;
    private Label labelMap;
    private HBox hBoxAnimal;
    private Label labelAnimal;
    private Circle circleAnimal;
    private HBox hBoxPlant;
    private Label labelPlant;
    private Polygon polygonPlant;
    private HBox hBoxJungle;
    private Label labelJungle;
    private Rectangle rectangleJungle;
    private HBox hBoxSteppe;
    private Label labelSteppe;
    private Rectangle rectangleSteppe;
    private Label labelChart;
    private Color[] colors;
    private Double[] points;



    public SimulationView(SimulationController simulationController, Color[] colors, Double[] points,
                          SimulationComponent simulationComponent){
        setView(simulationController, colors, points);
        this.simulationComponentLeft = simulationComponent;
        setMiddle();
        hBox.getChildren().addAll(this.simulationComponentLeft.getvBox(), left, settings);

    }

    public SimulationView(SimulationController simulationController, Color[] colors, Double[] points,
                          SimulationComponent simulationComponentL, SimulationComponent simulationComponentR){
        setView(simulationController, colors, points);
        this.simulationComponentLeft = simulationComponentL;
        this.simulationComponentRight = simulationComponentR;
        setMiddle();
        hBox.getChildren().addAll(this.simulationComponentLeft.getvBox(), left, settings, right, this.simulationComponentRight.getvBox());
    }

    public void setView(SimulationController simulationController, Color[] colors, Double[] points){
        hBox = new HBox();
        left = new Separator();
        left.setOrientation(Orientation.VERTICAL);
        left.prefWidth(20);
        right = new Separator();
        right.setOrientation(Orientation.VERTICAL);
        right.prefWidth(20);
        this.simulationController = simulationController;
        this.colors = colors;
        this.points = points;
    }

    private void setMiddle(){
        settings = new VBox();
        legendBox = new VBox();
        start = new Button("Start");
        start.setPadding(new Insets(5,5,5,5));
        start.setPrefWidth(150);
        exportData = new Button("Export data");
        exportData.setPadding(new Insets(5,5,5,5));
        exportData.setPrefWidth(150);
        dominantGenotype = new Button("Dominant genotype");
        dominantGenotype.setPadding(new Insets(5,5,5,5));
        dominantGenotype.setPrefWidth(150);

        buttonsBox = new VBox();
        buttonsBox.getChildren().addAll(exportData, dominantGenotype, start);
        buttonsBox.setSpacing(10);
        int iconSize = 15;

        labelLegend = new Label("Legend:");
        labelLegend.setFont(new Font(iconSize*3));
        labelMap = new Label("Map:");
        labelMap.setFont(new Font(iconSize*2));
        labelChart = new Label("Chart:");
        labelChart.setFont(new Font(iconSize*2));

        hBoxAnimal = new HBox();
        labelAnimal = new Label(" animal shape");
        labelAnimal.setFont(new Font(iconSize));
        circleAnimal = new Circle();
        circleAnimal.setRadius(iconSize/2);
        circleAnimal.setFill(colors[0]);
        hBoxAnimal.getChildren().addAll(circleAnimal, labelAnimal);

        hBoxPlant = new HBox();
        labelPlant = new Label(" plant shape");
        labelPlant.setFont(new Font(iconSize));
        polygonPlant = new Polygon();
        for(int i =0; i<points.length; i++){
            points[i] *= iconSize/4;
        }
        polygonPlant.getPoints().addAll(points);
        polygonPlant.setFill(colors[1]);
        hBoxPlant.getChildren().addAll(polygonPlant, labelPlant);

        hBoxJungle = new HBox();
        labelJungle = new Label(" jungle");
        labelJungle.setFont(new Font(iconSize));
        rectangleJungle = new Rectangle();
        rectangleJungle.setWidth(iconSize);
        rectangleJungle.setHeight(iconSize);
        rectangleJungle.setFill(colors[2]);
        hBoxJungle.getChildren().addAll(rectangleJungle, labelJungle);

        hBoxSteppe = new HBox();
        labelSteppe = new Label(" steppe");
        labelSteppe.setFont(new Font(iconSize));
        rectangleSteppe = new Rectangle();
        rectangleSteppe.setHeight(iconSize);
        rectangleSteppe.setWidth(iconSize);
        rectangleSteppe.setFill(colors[3]);
        hBoxSteppe.getChildren().addAll(rectangleSteppe, labelSteppe);

        legendBox.getChildren().addAll(labelLegend,labelMap, hBoxAnimal, hBoxPlant, hBoxJungle, hBoxSteppe,
                labelChart);
        legendBox.setPadding(new Insets(20, 5, 10, 5));
        legendBox.setAlignment(Pos.TOP_LEFT);

        settings.getChildren().addAll(legendBox, buttonsBox);
        settings.setSpacing(550);

    }

    public HBox gethBox() {
        return hBox;
    }
}
