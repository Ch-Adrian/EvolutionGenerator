package agh.ics.oop.proj1.controller;

import agh.ics.oop.proj1.model.simulation.Animal;
import agh.ics.oop.proj1.model.simulation.WorldModel;
import agh.ics.oop.proj1.view.simulation.GridPaneComponent;
import agh.ics.oop.proj1.view.simulation.IAmAMap;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.Comparator;
import java.util.Objects;
import java.util.TreeSet;

public class Changer {

    private TreeSet<Pair<Integer, Integer>> positionChanges;
    private IAmAMap gridPaneComponent;
    private WorldModel worldModel;

    public Changer(WorldModel worldModel, GridPaneComponent gridPaneComponent){
        this.gridPaneComponent = gridPaneComponent;
        this.worldModel = worldModel;

        positionChanges = new TreeSet<>(new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                if(o1.getKey()< o2.getKey()){
                    return -1;
                }
                else if(Objects.equals(o1.getKey(), o2.getKey())){
                    return o1.getValue().compareTo(o2.getValue());
                }
                else{
                    return 1;
                }
            }
        });
    }

    public void addChange(int x, int y){
        positionChanges.add(new Pair<>(x,y));
    }

    public TreeSet<Pair<Integer, Integer>> getPositionChanges() {
        return positionChanges;
    }

    public void showGenotype(String genotype){
        for(Animal a: this.worldModel.getAnimals()){
            if(a.getGenotype().equals(genotype)){
                this.gridPaneComponent.clearField(a.getX(), a.getY());
                Color c = worldModel.getFieldColor(a.getX(), a.getY());
                if(c != Color.TRANSPARENT){
                    this.gridPaneComponent.updateGridWithBorder(a.getX(), a.getY(), c);
                }
            }
        }
    }

    public void hideGenotype(){
        for(Animal a: this.worldModel.getAnimals()){
                this.gridPaneComponent.clearField(a.getX(), a.getY());
                Color c = worldModel.getFieldColor(a.getX(), a.getY());
                if(c != Color.TRANSPARENT){
                    this.gridPaneComponent.updateGrid(a.getX(), a.getY(), c);
                }
        }
    }

    public void applyView(){

        for(Pair<Integer, Integer> p: positionChanges){
            this.gridPaneComponent.clearField(p.getKey(), p.getValue());
            Color c = worldModel.getFieldColor(p.getKey(), p.getValue());
            if(c == Color.TRANSPARENT){
                if(this.worldModel.isPlant(p.getKey(), p.getValue())){
                    this.gridPaneComponent.updateGrid(p.getKey(), p.getValue());
                }
            } else{
                this.gridPaneComponent.updateGrid(p.getKey(), p.getValue(), c);
            }
        }

    }
}
