package agh.ics.oop.proj1.model.simulation;

import agh.ics.oop.proj1.controller.NoElementException;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.ArrayList;

import static javafx.scene.paint.Color.rgb;

public class WorldModel {

    private final int widthMap;
    private final int heightMap;
    private final int animalEnergy;

    private final Double jungleRatio;
    private final int junglePosX;
    private final int junglePosY;
    private final int jungleWidth;
    private final int jungleHeight;

    private AnimalMap animalMap;

    private ArrayList<Pair<Integer, Integer[]>> statistics;
    private ArrayList<Animal> animals;
    private ArrayList<Plant> plants;
    private Integer epoch;

    public WorldModel(int widthMap, int heightMap, double jungleRatio, int animalEnergy) {

        this.animals = new ArrayList<>();
        this.plants = new ArrayList<>();
        this.epoch = 0;

        animalMap = new AnimalMap(widthMap, heightMap);
        this.animalEnergy = animalEnergy;

        this.jungleRatio = jungleRatio;
        this.widthMap = widthMap;
        this.heightMap = heightMap;
        double sqrtJungleRatio = Math.sqrt(this.jungleRatio);

        this.jungleWidth = (int) Math.floor(this.widthMap * sqrtJungleRatio);
        this.jungleHeight = (int) Math.floor(this.heightMap * sqrtJungleRatio);
        this.junglePosX = (this.widthMap - this.jungleWidth) / 2;
        this.junglePosY = (this.heightMap - this.jungleHeight) / 2;

        statistics = new ArrayList<>();
    }

    public int[] getJungleParam() {
        return new int[]{this.junglePosX, this.junglePosY,
                this.jungleWidth, this.jungleHeight};
    }

    public void addStatistic(int day, Integer[] values) {
        statistics.add(new Pair<Integer, Integer[]>(day, values));
    }

    public void addAnimal(Animal animal){
        this.animals.add(animal);
        this.animalMap.placeAnimal(animal.getX(), animal.getY(),animal);
    }

    public Color getFieldColor(int x, int y){
        double energy = 0;
        try {
            energy = this.animalMap.getHighestEnergy(x, y);
        } catch (NoElementException e){
            return Color.TRANSPARENT;
        }
        double prc = energy/this.animalEnergy;
        if(prc > 1){
            return rgb(51,0,102);
        }else if(prc > 0.8){
            return rgb(102,0,204);
        }
        else if(prc > 0.5){
            return rgb(153,51,255);
        }
        else if(prc > 0.2){
            return rgb(178,102,255);
        }
        else{
            return rgb(229,204,255);
        }
    }

    public boolean isPlant(int x, int y){
        for(Plant p: this.plants){
            if(p.getX() == x && p.getY() == y){
                return true;
            }
        }
        return false;
    }

    public void addPlant(Plant p){
        plants.add(p);
    }

    public void incrementEpoch(){ this.epoch++;}

    public String getEpoch(){
        return this.epoch.toString();
    }
}
