package agh.ics.oop.proj1.model.simulation;

import agh.ics.oop.proj1.controller.NoElementException;
import javafx.util.Pair;

import java.util.*;

public class AnimalMap {

    private TreeSet<Animal>[][] map;
    private int widthMap;
    private int heightMap;

    private class CompAnimal implements Comparator<Animal> {

        @Override
        public int compare(Animal o1, Animal o2) {
            return o2.getEnergy() - o1.getEnergy();
        }
    }

    public AnimalMap(int widthMap, int heightMap){
        this.widthMap = widthMap;
        this.heightMap = heightMap;

        this.map = new TreeSet[this.widthMap][];
        for(int i = 0; i<this.widthMap; i++){
            this.map[i] = new TreeSet[this.heightMap];
            for(int j = 0; j<heightMap; j++){
                this.map[i][j] = new TreeSet<>(new CompAnimal());
            }
        }
    }

    public void placeAnimal(int x, int y, Animal animal){
        map[x][y].add(animal);
    }

    public int getHighestEnergy(int x, int y) throws NoElementException {
        if(this.map[x][y].isEmpty()){
            throw new NoElementException();
        }
        return this.map[x][y].last().getEnergy();
    }

    public void removeAnimal(Animal a){
        this.map[a.getX()][a.getY()].remove(a);
    }

    public Pair<Animal, Animal> getTwoAnimalsFromPosition(int x, int y){
        if(this.map[x][y].size() < 2) return new Pair<>(null,null);
        Iterator<Animal> iterator = this.map[x][y].iterator();
        return new Pair<>(iterator.next(), iterator.next());
    }

    public boolean eatPlantOnPosition(int x, int y, int energy){
//        this.map[x][y].first().eatPlant(energy);
        if(this.map[x][y].size() == 0) return false;
        int maxEnergy = 0;
        int cnt = 1;
        for(Animal a: this.map[x][y]){
            if(a.getEnergy() > maxEnergy){
                maxEnergy = a.getEnergy();
                cnt = 1;
            }
            else if(a.getEnergy() == maxEnergy){
                cnt += 1;
            }
        }

        for(Animal a: this.map[x][y]){
            if(a.getEnergy() == maxEnergy){
                a.eatPlant((int) Math.floor(energy/cnt));
            }
        }

        return true;

    }

}
