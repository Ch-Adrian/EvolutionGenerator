package agh.ics.oop.proj1.model.simulation;

import agh.ics.oop.proj1.controller.NoElementException;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;

public class AnimalMap {

    private TreeSet<Animal>[][] map;
    private int widthMap;
    private int heightMap;

    private class CompAnimal implements Comparator<Animal> {

        @Override
        public int compare(Animal o1, Animal o2) {
            return o1.getEnergy() - o2.getEnergy();
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
}
