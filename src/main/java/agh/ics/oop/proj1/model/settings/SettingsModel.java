package agh.ics.oop.proj1.model.settings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

public class SettingsModel {

    private final ArrayList<String> defaultParam;
    private ArrayList<String> userParamMap1;
    private ArrayList<String> userParamMap2;

    // Map 1
    private int widthMap;
    private int heightMap;
    private double jungleRatio;

    private int amountOfAnimals;
    private boolean magicEvolution;
    private boolean wall;

    private int energyAnimal;
    private int energyPlant;
    private int energyDayLoss;

    // Map 2
    private int widthMap2;
    private int heightMap2;
    private double jungleRatio2;

    private int amountOfAnimals2;
    private boolean magicEvolution2;
    private boolean wall2;

    private int energyAnimal2;
    private int energyPlant2;
    private int energyDayLoss2;

    public SettingsModel() {
        defaultParam = new ArrayList<>();

        defaultParam.add("50");
        defaultParam.add("50");
        defaultParam.add("10");
        defaultParam.add("0.5");
        defaultParam.add("false");
        defaultParam.add("false");
        defaultParam.add("20");
        defaultParam.add("1");
        defaultParam.add("5");
    }

    public ArrayList<String> getDefaultParam() {
        return defaultParam;
    }

    public void setUserParamMap1(ArrayList<String> userParam) {
        this.userParamMap1 = userParam;
        widthMap = Integer.parseInt(userParam.get(0));
        heightMap = Integer.parseInt(userParam.get(1));
        amountOfAnimals = Integer.parseInt(userParam.get(2));
        jungleRatio = Double.parseDouble(userParam.get(3));
        magicEvolution = Boolean.parseBoolean(userParam.get(4));
        wall = Boolean.parseBoolean(userParam.get(5));
        energyAnimal = Integer.parseInt(userParam.get(6));
        energyDayLoss = Integer.parseInt(userParam.get(7));
        energyPlant = Integer.parseInt(userParam.get(8));
    }

    public void setUserParamMap2(ArrayList<String> userParam) {
        this.userParamMap2 = userParam;
        widthMap2 = Integer.parseInt(userParam.get(0));
        heightMap2 = Integer.parseInt(userParam.get(1));
        amountOfAnimals2 = Integer.parseInt(userParam.get(2));
        jungleRatio2 = Double.parseDouble(userParam.get(3));
        magicEvolution2 = Boolean.parseBoolean(userParam.get(4));
        wall2 = Boolean.parseBoolean(userParam.get(5));
        energyAnimal2 = Integer.parseInt(userParam.get(6));
        energyDayLoss2 = Integer.parseInt(userParam.get(7));
        energyPlant2 = Integer.parseInt(userParam.get(8));
    }

    //Getters
    public int getWidthMap() {
        return widthMap;
    }

    public int getHeightMap() {
        return heightMap;
    }

    public double getJungleRatio() {
        return jungleRatio;
    }

    public double getJungleRatio2() {
        return jungleRatio2;
    }

    public int getAmountOfAnimals() {
        return amountOfAnimals;
    }

    public int getAmountOfAnimals2() {
        return amountOfAnimals2;
    }

    public int getEnergyAnimal() {
        return energyAnimal;
    }

    public int getEnergyPlant() {
        return energyPlant;
    }

    public int getEnergyAnimal2() {
        return energyAnimal2;
    }

    public int getEnergyDayLoss() {
        return energyDayLoss;
    }

    public int getEnergyDayLoss2() {
        return energyDayLoss2;
    }

    public int getEnergyPlant2() {
        return energyPlant2;
    }

    public int getWidthMap2() {
        return widthMap2;
    }

    public int getHeightMap2() {
        return heightMap2;
    }

    public boolean getMagic(){ return this.magicEvolution; }

    public boolean getMagic2(){ return this.magicEvolution2; }

    public boolean getWall(){ return this.wall; }

    public boolean getWall2(){ return this.wall2; }
}
