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
         if(genotypes.size()!=0) {
             return this.genotypes.get(0).getValue();
         }else{
             return "";
         }
    }

    public void removeGenotype(String genotype){
        for(Pair<Integer, String> p: this.genotypes){
            if(p.getValue().equals(genotype)){
                int temp = p.getKey();
                this.genotypes.remove(p);
                if(temp>1) {
                    this.genotypes.add(new Pair<Integer, String>(temp - 1, genotype));
                }
                return;
            }
        }
    }

}
