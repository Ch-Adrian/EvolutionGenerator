package agh.ics.oop.proj1.controller;

import agh.ics.oop.proj1.model.simulation.Animal;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Iterator;

public class Statistic {

    private ArrayList<Pair<Integer, Integer[]>> statistics;
    private int deadAnimals = 0;
    private double sumDaysAi = 0;

    public Statistic(int amtOfAnimals, int amtOfPlants, int avgEnergy){
        statistics = new ArrayList<>();
        statistics.add(new Pair<>(0, new Integer[]{amtOfAnimals, amtOfPlants,
        avgEnergy, 0, 0}));
    }

    //values: living animals, living plants, avgEnergy for living animals,
    // avg of life for dead animals, avg value of chldren of living animals
    public void addStatistic(int day, Integer[] values) {
        statistics.add(new Pair<Integer, Integer[]>(day, values));
    }

    public Pair<Integer, Integer[]> getLastStatistic(){
        return this.statistics.get(this.statistics.size()-1);
    }

    public void addDead(int days){
        this.sumDaysAi+=days;
        this.deadAnimals++;
    }

    public int avgOfDead(){
        return  (int)(this.sumDaysAi / this.deadAnimals);
    }

    public Iterator<Pair<Integer, Integer[]>> getIterator(){
        return this.statistics.iterator();
    }

}
