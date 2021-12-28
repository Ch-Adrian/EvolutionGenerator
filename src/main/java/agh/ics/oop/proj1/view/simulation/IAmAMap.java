package agh.ics.oop.proj1.view.simulation;

import javafx.scene.paint.Color;

public interface IAmAMap {

    void updateGridWithBorder(int x, int y, Color c);
    void updateGrid(int x, int y, Color c);
    void updateGrid(int x, int y);
    void clearField(int x, int y);

}
