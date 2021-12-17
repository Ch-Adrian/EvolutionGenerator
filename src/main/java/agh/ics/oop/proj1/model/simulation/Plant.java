package agh.ics.oop.proj1.model.simulation;

public class Plant {

    private int x;
    private int y;
    private int energy;

    public Plant(int x, int y, int energy){
        this.x = x;
        this.y = y;
        this.energy = energy;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getEnergy() {
        return energy;
    }
}
