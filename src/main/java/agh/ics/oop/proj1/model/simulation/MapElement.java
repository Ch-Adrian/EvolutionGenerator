package agh.ics.oop.proj1.model.simulation;

public abstract class MapElement {

    protected int x;
    protected int y;
    protected int energy;

    public MapElement(int x, int y, int e){
        this.x = x;
        this.y = y;
        this. energy = e;
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

    public void setEnergy(int e){
        this.energy = e;
    }
}
