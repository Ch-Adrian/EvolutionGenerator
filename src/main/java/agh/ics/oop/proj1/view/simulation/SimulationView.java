package agh.ics.oop.proj1.view.simulation;

import agh.ics.oop.proj1.controller.SimulationController;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
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

    private BorderPane settings;
    private VBox legendBox;
    private VBox buttonsBox;
    private Button start;
    private Button start2;
    private Button exportData;
    private Button exportData2;
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
        settings = new BorderPane();
        legendBox = new VBox();

        Button showDominant = new Button("Dominant map 1");
        showDominant.setPadding(new Insets(5,5,5,5));
        showDominant.setPrefWidth(150);
        showDominant.setOnAction((event)->{
            this.simulationController.showDominant();
        });

        Button showDominant2 = new Button("Dominant map 2");
        showDominant2.setPadding(new Insets(5,5,5,5));
        showDominant2.setPrefWidth(150);
        showDominant2.setOnAction((event)->{
            this.simulationController.showDominant2();
        });

        start = new Button("Start Map 1");
        start.setPadding(new Insets(5,5,5,5));
        start.setPrefWidth(150);

        start.setOnAction((event -> {
            if(simulationController.startButton()){
                start.setText("Stop Map 1");
            }else{
                start.setText("Start Map 1");
            }
        }));

        start2 = new Button("Start Map 2");
        start2.setPadding(new Insets(5,5,5,5));
        start2.setPrefWidth(150);

        start2.setOnAction((event -> {
            if(simulationController.start2Button()){
                start2.setText("Stop Map 2");
            }else{
                start2.setText("Start Map 2");
            }
        }));

        exportData = new Button("Export data map1");
        exportData.setPadding(new Insets(5,5,5,5));
        exportData.setPrefWidth(150);
        exportData.setOnAction((event)->{
            this.simulationController.saveToFile();
        });

        exportData2 = new Button("Export data map 2");
        exportData2.setPadding(new Insets(5,5,5,5));
        exportData2.setPrefWidth(150);
        exportData2.setOnAction((event)->{
            this.simulationController.saveToFile2();
        });

        dominantGenotype = new Button("Dominant genotype");
        dominantGenotype.setPadding(new Insets(5,5,5,5));
        dominantGenotype.setPrefWidth(150);

        buttonsBox = new VBox();
        buttonsBox.getChildren().addAll(showDominant, showDominant2, exportData, exportData2, start, start2);
        buttonsBox.setSpacing(10);
        int iconSize = 15;

        labelLegend = new Label("Legend:");
        labelLegend.setFont(new Font(iconSize*3));
        labelMap = new Label("Map:");
        labelMap.setFont(new Font(iconSize*2));

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

        legendBox.getChildren().addAll(labelLegend,labelMap, hBoxAnimal, hBoxPlant, hBoxJungle, hBoxSteppe);
        legendBox.setPadding(new Insets(20, 5, 10, 5));
        legendBox.setAlignment(Pos.TOP_LEFT);

        settings.setTop(legendBox);
        settings.setBottom(buttonsBox);
        settings.setPadding(new Insets(5,5,10,5));

    }

    public HBox gethBox() {
        return hBox;
    }
}
