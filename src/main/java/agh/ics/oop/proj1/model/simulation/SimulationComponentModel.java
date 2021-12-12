package agh.ics.oop.proj1.model.simulation;

public class SimulationComponentModel {

    private int widthMap;
    private int heightMap;
    private double jungleRatio;

    private int amountOfAnimals;
    private boolean magicEvolution;
    private boolean foldedMap;

    private int energyAnimal;
    private int energyPlant;
    private int energyDayLoss;

    public SimulationComponentModel(){
        widthMap = 3;
        heightMap = 3;
        jungleRatio = 0.5;
    }

    public double getJungleRatio() {
        return jungleRatio;
    }

    public int getHeightMap() {
        return heightMap;
    }

    public int getWidthMap() {
        return widthMap;
    }
}
