package agh.ics.oop.proj1.controller;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Comparator;
public class DominantGenotype {

    ArrayList<Pair<Integer, String>> genotypes;

    public DominantGenotype(){
        this.genotypes = new ArrayList<>();
    }

    public void addGenotype(String genotype){
        for(int i = 0; i<this.genotypes.size(); i++){
            if(this.genotypes.get(i).getValue().equals(genotype)){
                int a = this.genotypes.get(i).getKey();
                this.genotypes.remove(a);
                this.genotypes.add(new Pair<Integer,String>(a+1, genotype));
                return;
            }
        }
        this.genotypes.add(new Pair<>(1, genotype));
    }

    public String getFirst(){
         this.genotypes.sort(new Comparator<Pair<Integer, String>>() {
             @Override
             public int compare(Pair<Integer, String> o1, Pair<Integer, String> o2) {
                 return o2.getKey() - o1.getKey();
             }
         });
         return this.genotypes.get(0).getValue();
    }

}
