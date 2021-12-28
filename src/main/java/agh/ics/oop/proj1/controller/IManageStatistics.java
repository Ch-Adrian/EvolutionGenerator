package agh.ics.oop.proj1.controller;

import javafx.util.Pair;

public interface IManageStatistics {

    void addStatistic(int day, Integer[] values);
    Pair<Integer, Integer[]> getLastStatistic();

}
