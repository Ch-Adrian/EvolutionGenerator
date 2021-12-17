package agh.ics.oop.proj1.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DominantGenotypeTest {

    @Test
    public void getFirstTest(){
        DominantGenotype dG = new DominantGenotype();
        dG.addGenotype("ala");
        dG.addGenotype("first");
        dG.addGenotype("ala");
        dG.addGenotype("makaron");

        Assertions.assertTrue("ala".equals(dG.getFirst()));

    }
}
