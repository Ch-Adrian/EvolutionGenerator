package agh.ics.oop.proj1.model.simulation;

import agh.ics.oop.proj1.controller.NoElementException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnimalMapTest {

    @Test
    public void getHighestEnergyTest(){
        Animal ani1 = new Animal(1,2,10,"ani1");
        Animal ani2 = new Animal(1,2,20,"ani2");
        AnimalMap animalMap = new AnimalMap(10,10);
        animalMap.placeAnimal(1,2, ani1);
        animalMap.placeAnimal(1,2,ani2);
        try {
            Assertions.assertTrue(animalMap.getHighestEnergy(1, 2) == 20);
        } catch(NoElementException e){
            Assertions.fail();
        }


    }

}
