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
    private final int widthGrid = 500;

    public GridPaneComponent(int widthMap, int heightMap, double jungleRatio){
        this.size = widthGrid/widthMap;
        gridPane = new GridPane();

        for(int i = 0; i<widthMap; i++){
            ColumnConstraints cC = new ColumnConstraints(this.size);
            cC.setHalignment(HPos.CENTER);
            RowConstraints rC = new RowConstraints(this.size);
            rC.setValignment(VPos.CENTER);
            gridPane.getColumnConstraints().add(cC);
            gridPane.getRowConstraints().add(rC);
        }

        gridPane.setPadding(new Insets(5,10,5,10));
        gridPane.setGridLinesVisible(true);

        ArrayList<Rectangle> arrayListR = new ArrayList<>();
        for(int i= 0; i<9; i++) {

            Rectangle rectangle = new Rectangle();
            rectangle.setHeight(this.size);
            rectangle.setWidth(this.size);
            rectangle.setFill(Color.GREEN);
            arrayListR.add(rectangle);
        }

        ArrayList<Circle> arrayListC = new ArrayList<>();
        for(int i = 0; i<9; i++) {
            Circle circle = new Circle();
            circle.setRadius(this.size /2);
            circle.setFill(Color.BROWN);
            arrayListC.add(circle);
        }

        int k = 0;
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                gridPane.add(arrayListR.get(k),i,j);
                gridPane.add(arrayListC.get(k), i, j);
                k++;
            }
        }

        Circle c = arrayListC.get(0);
        c.setFill(Color.TRANSPARENT);

        int x = this.size /8;
        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(4.0*x,0.0,
                5.0*x,3.0*x,
                8.0*x,4.0*x,
                5.0*x,5.0*x,
                4.0*x,8.0*x,
                3.0*x,5.0*x,
                0.0,4.0*x,
                3.0*x,3.0*x);
        triangle.setFill(new Color(0,1,0,1));
        gridPane.add(triangle, 0,0);
    }

    public GridPane getGridPane() {
        return gridPane;
    }
}
