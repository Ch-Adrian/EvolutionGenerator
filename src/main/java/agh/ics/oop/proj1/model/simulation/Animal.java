package agh.ics.oop.proj1.model.simulation;

public class Animal {

    private int x;
    private int y;
    private int energy;
    private String genotype;

    public Animal(int x, int y, int energy, String g){
        this.x = x;
        this.y = y;
        this.energy = energy;
        this.genotype = g;
    }

    public int getEnergy() {
        return energy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getGenotype() {
        return genotype;
    }
}
