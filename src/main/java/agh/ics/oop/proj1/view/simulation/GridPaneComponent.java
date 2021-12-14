package agh.ics.oop.proj1.view.simulation;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class GridPaneComponent extends GridPane {

    private GridPane gridPane;
    private final int size;
    private final int maxWidthGrid = 800;
    private final int maxHeightGrid = 500;
    private Rectangle[][] fields;
    private Circle[][] animals;
    private Polygon[][] plants;

    private int polygonVar;
    private Color[] colors;// 0 - animal, 1 - plant, 2 - jungle, 3 - steppe

    public GridPaneComponent(int widthMap, int heightMap, int[] jungleParam, Color[] colors){
        this.colors = colors;
        int a = maxWidthGrid/widthMap;
        int b = maxHeightGrid/heightMap;
        if(a<b) this.size = a;
        else this.size = b;

        gridPane = new GridPane();

        for(int i = 0; i<widthMap; i++){
            ColumnConstraints cC = new ColumnConstraints(this.size);
            cC.setHalignment(HPos.CENTER);
            gridPane.getColumnConstraints().add(cC);
        }
        for(int i = 0; i<heightMap; i++){
            RowConstraints rC = new RowConstraints(this.size);
            rC.setValignment(VPos.CENTER);
            gridPane.getRowConstraints().add(rC);
        }

        gridPane.setPadding(new Insets(5,10,5,10));
        gridPane.setGridLinesVisible(true);

        fields = new Rectangle[widthMap][];
        for(int i = 0; i<widthMap; i++){
            fields[i] = new Rectangle[heightMap];
        }
        for(int i= 0; i<widthMap; i++) {
            for(int j = 0; j<heightMap; j++) {
                fields[i][j] = new Rectangle();
                fields[i][j].setHeight(this.size);
                fields[i][j].setWidth(this.size);
                fields[i][j].setFill(colors[3]);
            }
        }
        for(int i= jungleParam[0]; i<jungleParam[0]+jungleParam[2]; i++) {
            for(int j = jungleParam[1]; j<jungleParam[1] + jungleParam[3]; j++) {
                fields[i][j].setFill(colors[2]);
            }
        }



        animals = new Circle[widthMap][];
        for(int i = 0; i<widthMap; i++){
            animals[i] = new Circle[heightMap];
        }
        for(int i= 0; i<widthMap; i++) {
            for(int j = 0; j<heightMap; j++) {
                animals[i][j] = new Circle();
                animals[i][j].setRadius((int)(this.size/2));
                //animals[i][j].setFill(colors[0]);
                animals[i][j].setFill(Color.TRANSPARENT);
            }
        }

        this.polygonVar = this.size/8;
        plants = new Polygon[widthMap][];
        for(int i = 0; i<widthMap; i++){
            plants[i] = new Polygon[heightMap];
        }
        for(int i= 0; i<widthMap; i++) {
            for(int j = 0; j<heightMap; j++) {
                plants[i][j] = new Polygon();
                plants[i][j].getPoints().addAll(4.0*this.polygonVar,0.0,
                        5.0*this.polygonVar,3.0*this.polygonVar,
                        8.0*this.polygonVar,4.0*this.polygonVar,
                        5.0*this.polygonVar,5.0*this.polygonVar,
                        4.0*this.polygonVar,8.0*this.polygonVar,
                        3.0*this.polygonVar,5.0*this.polygonVar,
                        0.0,4.0*this.polygonVar,
                        3.0*this.polygonVar,3.0*this.polygonVar);
                plants[i][j].setFill(Color.TRANSPARENT);
                //plants[i][j].setFill(colors[1]);
            }
        }

        for(int i = 0; i<widthMap; i++){
            for(int j = 0; j<heightMap; j++){
                gridPane.add(fields[i][j],i,j);
                gridPane.add(animals[i][j], i, j);
                gridPane.add(plants[i][j],i,j);
            }
        }

    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public void updateGrid(int x, int y, Color c){
        animals[x][y].setFill(c);
        plants[x][y].setFill(Color.TRANSPARENT);
    }

    public void updateGrid(int x, int y){
        animals[x][y].setFill(Color.TRANSPARENT);
        plants[x][y].setFill(colors[1]);
    }

    public void clearField(int x, int y){
        animals[x][y].setFill(Color.TRANSPARENT);
        plants[x][y].setFill(Color.TRANSPARENT);
    }

}
