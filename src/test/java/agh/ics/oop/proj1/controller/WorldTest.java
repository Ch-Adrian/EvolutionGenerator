package agh.ics.oop.proj1.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javafx.scene.paint.Color;

public class WorldTest {

    @Test
    public void createGenotypeTest(){
        World world = new World(12,12,0.5,new Color[]{Color.BLACK, Color.RED, Color.FIREBRICK, Color.RED},
                10,true, true, 1, 1, 1);
        String g = world.createGenotype();
        String g2 = world.createGenotype();
        Assertions.assertTrue(g.length() == 32);
        Assertions.assertTrue(g2.length() == 32);
        String g3 = world.crossGenotype(g, g2, 30, 30);
        Assertions.assertTrue(g3.length() == 32);
    }

}
