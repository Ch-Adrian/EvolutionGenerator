package agh.ics.oop.proj1.model.simulation;

import java.text.BreakIterator;
import java.util.Objects;

public class Animal extends MapElement implements ICanMove{

    private String genotype;
    private int amtOfChildren;
    private int days;
    private int orientation = 0;
    private WorldModel worldModel;

    public Animal(int x, int y, int energy, String g, WorldModel wM){
        super(x, y, energy);
        this.genotype = g;
        this.amtOfChildren = 0;
        this.days=0;
        this.worldModel = wM;
    }
    private Animal(int x, int y, int energy, String g, int amtOfChildren, int days, WorldModel wM){
        super(x, y, energy);
        this.genotype = g;
        this.amtOfChildren = amtOfChildren;
        this.days=days;
        this.worldModel = wM;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return x == animal.x && y == animal.y && energy == animal.energy && amtOfChildren == animal.amtOfChildren && genotype.equals(animal.genotype);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x,y,genotype);
    }

    public void eatPlant(int energy){
        this.energy += energy;
    }

    public void incrementAmtOfChildren(){
        this.amtOfChildren++;
    }

    public int getAmtOfChildren() {
        return amtOfChildren;
    }

    public String getGenotype() {
        return genotype;
    }

    public int getDays() {
        return days;
    }

    public void incremetDays(){
        this.days++;
    }

    public Animal copy(){
        Animal other = new Animal(this.x, this.y, this.energy, this.genotype,
                this.amtOfChildren, this.days, this.worldModel);
        return other;
    }

    public void changeOrientation(int o){
        this.orientation = (this.orientation+o)%8;
    }

    public void move(){
        int tempX = this.x;
        int tempY = this.y;

        switch(this.orientation){
            case 0:
                this.y--;
                break;
            case 1:
                this.x++;
                this.y--;
                break;
            case 2:
                this.x++;
                break;
            case 3:
                this.x++;
                this.y++;
                break;
            case 4:
                this.y++;
                break;
            case 5:
                this.x--;
                this.y++;
                break;
            case 6:
                this.x--;
                break;
            case 7:
                this.x--;
                this.y--;
                break;
        }

        if(this.x < 0){
            if(this.worldModel.isWall()){
                this.x = tempX;
                this.y = tempY;
            }
            else{
                this.x = this.worldModel.getWidthMap()-1;
            }
        }
        if(this.x >= this.worldModel.getWidthMap()){
            if(this.worldModel.isWall()){
                this.x = tempX;
                this.y = tempY;
            }
            else{
                this.x = 0;
            }
        }
        if(this.y < 0){
            if(this.worldModel.isWall()){
                this.x = tempX;
                this.y = tempY;
            }
            else{
                this.y = this.worldModel.getHeightMap()-1;
            }
        }
        if(this.y >= this.worldModel.getHeightMap()){
            if(this.worldModel.isWall()){
                this.x = tempX;
                this.y = tempY;
            }
            else{
                this.y = 0;
            }
        }


    }

}
