package agh.ics.oop.proj1.model.simulation;

import agh.ics.oop.proj1.controller.NoElementException;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantLock;

import static javafx.scene.paint.Color.rgb;

public class WorldModel {

    private final int widthMap;
    private final int heightMap;
    private final int animalEnergy;
    private final boolean wall;

    private final Double jungleRatio;
    private final int junglePosX;
    private final int junglePosY;
    private final int jungleWidth;
    private final int jungleHeight;

    private AnimalMap animalMap;

    private ArrayList<Animal> animals;
    private ArrayList<Plant> plants;
    private Integer epoch;


    ReentrantLock reentrantLock = new ReentrantLock();

    public WorldModel(int widthMap, int heightMap, double jungleRatio, int animalEnergy, boolean wall) {

        this.animals = new ArrayList<>();
        this.plants = new ArrayList<>();
        this.epoch = 0;
        this.wall = wall;
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

    }

    public int[] getJungleParam() {
        return new int[]{this.junglePosX, this.junglePosY,
                this.jungleWidth, this.jungleHeight};
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

    public Integer getEpochInt() { return this.epoch; }

    //During iteration in arrayList animals:
    public void removeAnimal(Animal a, Iterator<Animal> iterator){
        this.animalMap.removeAnimal(a);
        iterator.remove();
    }

    public void removeAnimal(Animal a){
        this.animals.remove(a);
        this.animalMap.removeAnimal(a);
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public ArrayList<Plant> getPlants() {
        return plants;
    }

    public void addAnimalToMap(Animal a){
        this.animalMap.placeAnimal(a.getX(),a.getY(), a);
    }

    public void removeAnimalFromMap(Animal a){
        this.animalMap.removeAnimal(a);
    }

    public int getWidthMap() {
        return widthMap;
    }

    public int getHeightMap() {
        return heightMap;
    }

    public boolean isWall() {
        return wall;
    }

    public boolean eatPlantOnPosition(int x, int y, int energy){
        return this.animalMap.eatPlantOnPosition(x,y,energy);
    }

    public Pair<Animal, Animal> getTwoAnimalsFromPosition(int x, int y){
        return this.animalMap.getTwoAnimalsFromPosition(x,y);
    }

}
