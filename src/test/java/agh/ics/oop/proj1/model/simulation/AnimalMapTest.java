package agh.ics.oop.proj1.model.simulation;

import agh.ics.oop.proj1.controller.NoElementException;
import agh.ics.oop.proj1.controller.World;
import javafx.util.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnimalMapTest {

    @Test
    public void getHighestEnergyTest(){
        Animal ani1 = new Animal(1,2,10,"ani1", new WorldModel(1,1,1,1, true));
        Animal ani2 = new Animal(1,2,20,"ani2", new WorldModel(1,1,1,1, true));
        AnimalMap animalMap = new AnimalMap(10,10);
        animalMap.placeAnimal(1,2, ani1);
        animalMap.placeAnimal(1,2,ani2);
        try {
            Assertions.assertTrue(animalMap.getHighestEnergy(1, 2) == 20);
        } catch(NoElementException e){
            Assertions.fail();
        }


    }

    @Test
    public void getTwoAnimalsTest(){
        Animal ani1 = new Animal(1,2,10,"ani1", new WorldModel(1,1,1,1, true));
        Animal ani2 = new Animal(1,2,20,"ani2", new WorldModel(1,1,1,1, true));
        Animal ani3 = new Animal(1,2,10,"ani2", new WorldModel(1,1,1,1, true));
        Animal ani4 = new Animal(1,2,2,"ani2", new WorldModel(1,1,1,1, true));
        AnimalMap animalMap = new AnimalMap(10,10);
        animalMap.placeAnimal(1,2,ani2);
        animalMap.placeAnimal(1,2,ani4);
        animalMap.placeAnimal(1,2, ani1);
        animalMap.placeAnimal(1,2,ani3);
        Assertions.assertTrue(new Pair<>(ani2, ani1)
                .equals(animalMap.getTwoAnimalsFromPosition(1,2)));

    }

}
