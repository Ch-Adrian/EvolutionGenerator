package agh.ics.oop.proj1.controller;

import agh.ics.oop.proj1.model.simulation.Animal;
import agh.ics.oop.proj1.model.simulation.Plant;
import agh.ics.oop.proj1.model.simulation.WorldModel;
import agh.ics.oop.proj1.view.simulation.SimulationComponent;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;

public class World {

    private WorldModel worldModel;
    private SimulationComponent simulationComponent;
    private Changer changer;
    private DominantGenotype dominantGenotype;
    private Statistic statistic;

    private final int widthMap;
    private final int heightMap;
    private final double jungleRatio;
    private final int amountOfAnimals;
    private final boolean magic;
    private final boolean wall;
    private final int energyAnimal;
    private final int energyPlant;
    private final int energyDayLoss;

    private int magicCounter = 0;

    private Thread simulationThread;
    private int timeDelay;
    private boolean simulationRuns;

    public World(int widthMap, int heightMap, double jungleRatio, Color[] colors,
                int amountOfAnimals, boolean magic, boolean wall,
                 int energyAnimal, int energyPlant, int energyDayLoss){

        this.widthMap = widthMap;
        this.heightMap = heightMap;
        this.jungleRatio = jungleRatio;
        this.amountOfAnimals = amountOfAnimals;
        this.magic = magic;
        this.wall = wall;
        this.energyAnimal = energyAnimal;
        this.energyPlant = energyPlant;
        this.energyDayLoss = energyDayLoss;
        this.timeDelay=100;
        this.simulationRuns = false;
        this.worldModel = new WorldModel(widthMap, heightMap, jungleRatio, energyAnimal, wall);

        this.simulationComponent = new SimulationComponent(widthMap, heightMap,
                this.worldModel.getJungleParam(), colors);

        this.changer = new Changer(this.worldModel,
                this.simulationComponent.getGridPaneComponent());

        this.dominantGenotype = new DominantGenotype();
        this.statistic = new Statistic(this.amountOfAnimals, 2, this.energyAnimal);
        this.setWorld();

    }

    private void setWorld(){
        Random random = new Random();
        for(int i = 0; i<this.amountOfAnimals; i++){
            int x = Math.abs(random.nextInt())%this.widthMap;
            int y = Math.abs(random.nextInt())%this.heightMap;
            this.createAnimal(x,y,this.energyAnimal , this.createGenotype());
        }
        createPlantInJungle();
        createPlantInSteppe();

            this.changer.applyView();
            applyViewEpoch();
            applyViewGenotype();
            applyViewChart();
    }

    private void generateDay(){

        removeDeadAnimals();
        moveAnimals();
        eatPlants();
        multiplyAnimals();
        createPlantInJungle();
        createPlantInSteppe();
        this.worldModel.incrementEpoch();

        if(magic && magicCounter<3 && this.worldModel.getAnimals().size() == 5){
            this.applyMagic();
            this.magicCounter ++;
        }

        Integer avgEnergy = 0;
        Integer avgChildren = 0;
        for(Animal a: this.worldModel.getAnimals()){
            a.incremetDays();
            a.setEnergy(a.getEnergy()-energyDayLoss);
            avgEnergy += a.getEnergy();
            avgChildren += a.getAmtOfChildren();
        }
//        System.out.println("AvgChildren: "+avgChildren);
        int energ = 0;
        double children = 0;
        if(this.worldModel.getAnimals().size() != 0){
            energ = avgEnergy / this.worldModel.getAnimals().size();
            children = avgChildren / (this.worldModel.getAnimals().size());
        }
//        System.out.println("Children: "+children);
        this.statistic.addStatistic(this.worldModel.getEpochInt(), new Integer[]{
                this.worldModel.getAnimals().size(),
                this.worldModel.getPlants().size(),
                energ,
                this.statistic.avgOfDead(),
                (int)children
        });

        Platform.runLater(()->{
            this.changer.applyView();
            applyViewEpoch();
            applyViewGenotype();
            applyViewChart();
        });

    }

    private void removeDeadAnimals(){

        for(Iterator<Animal> iterator = this.worldModel.getAnimals().iterator(); iterator.hasNext();)
        {
            Animal a = iterator.next();
            if(a.getEnergy() <= 0){
                this.changer.addChange(a.getX(), a.getY());
                this.dominantGenotype.removeGenotype(a.getGenotype());
                this.statistic.addDead(a.getDays());
                this.worldModel.removeAnimal(a.copy(), iterator);
            }
        }

    }

    void moveAnimals(){
        Random rand = new Random();
        for(Iterator<Animal> iterator = this.worldModel.getAnimals().iterator(); iterator.hasNext();)
        {
            int direct = Math.abs(rand.nextInt())%32;
            Animal ani = iterator.next();
            char c = ani.getGenotype().toCharArray()[direct];
            ani.changeOrientation(Character.getNumericValue(c));
            if(direct == 0 || direct == 4){
                this.changer.addChange(ani.getX(), ani.getY());
                this.worldModel.removeAnimalFromMap(ani);
                ani.move();
                this.worldModel.addAnimalToMap(ani);
                this.changer.addChange(ani.getX(), ani.getY());
            }
        }
    }

    void eatPlants(){
        for(Iterator<Plant> iterator = this.worldModel.getPlants().iterator(); iterator.hasNext();){
            Plant p = iterator.next();
            if(this.worldModel.eatPlantOnPosition(p.getX(), p.getY(), p.getEnergy())){
                iterator.remove();
            }
        }
    }

    void multiplyAnimals(){
        boolean[][] isVisited = new boolean[this.widthMap][];
        for(int i = 0; i<this.widthMap; i++){
            isVisited[i] = new boolean[this.heightMap];
            for(int j = 0; j< this.heightMap; j++){
                isVisited[i][j] = false;
            }
        }

        ArrayList<Animal> animalArrayList = new ArrayList<>();

        for(Iterator<Animal> iterator = this.worldModel.getAnimals().iterator(); iterator.hasNext();) {
            Animal a = iterator.next();
            if(isVisited[a.getX()][a.getY()]) continue;

            Pair<Animal,Animal> p = this.worldModel.getTwoAnimalsFromPosition(a.getX(),a.getY());
            if(p.getKey() != null && p.getValue() != null){
                if(p.getValue().getEnergy()>energyAnimal/2 && p.getKey().getEnergy()>energyAnimal/2){

                    String geno = crossGenotype(p.getKey().getGenotype(),p.getValue().getGenotype(),
                                p.getKey().getEnergy(), p.getValue().getEnergy());

                    Animal tempA = new Animal(a.getX(),
                            a.getY(),
                            p.getKey().getEnergy()/4 + p.getValue().getEnergy()/4,
                            geno, this.worldModel);

                    changer.addChange(a.getX(),a.getY());
                    dominantGenotype.addGenotype(geno);
                    animalArrayList.add(tempA);
//                    this.createAnimal(
//                            a.getX(),
//                            a.getY(),
//                            p.getKey().getEnergy()/4 + p.getValue().getEnergy()/4,
//                            crossGenotype(p.getKey().getGenotype(),p.getValue().getGenotype(),
//                                    p.getKey().getEnergy(), p.getValue().getEnergy()));


                    p.getValue().setEnergy(p.getValue().getEnergy()*3/4);
                    p.getKey().setEnergy(p.getKey().getEnergy()*3/4);

                    p.getKey().incrementAmtOfChildren();
                    p.getValue().incrementAmtOfChildren();
                    this.changer.addChange(a.getX(), a.getY());

                }
                isVisited[a.getX()][a.getY()] = true;
            }

        }

        for(Animal a: animalArrayList){
            worldModel.addAnimal(a);
        }
    }

    public SimulationComponent getSimulationComponent(){
        return simulationComponent;
    }

    public void createAnimal(int x, int y, int energy, String genotype){
        Animal ani = new Animal(x,y,energy, genotype, this.worldModel);

        changer.addChange(x,y);
        worldModel.addAnimal(ani);
        dominantGenotype.addGenotype(genotype);
    }

    public String createGenotype(){
        Random random = new Random();
        ArrayList<Integer> g = new ArrayList<>();
        for(int i = 0; i<32; i++){
            g.add(Math.abs(random.nextInt())%8);
        }
        g.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        StringBuilder stringBuilder = new StringBuilder();
        g.forEach((i)->{stringBuilder.append(i.toString());});

        return stringBuilder.toString();
    }

    public String crossGenotype(String genotype1, String genotype2, int energy1, int energy2){

        double sum_energy = energy1 + energy2;
        Random random = new Random();
        boolean left = random.nextBoolean();
        StringBuilder stringBuilder = new StringBuilder();

        if(energy1 > energy2){
            int size =(int)( (energy1 / sum_energy)*32.0);
//            System.out.println(size);
            if(left){
                stringBuilder.append(genotype1.substring(0,size));
                stringBuilder.append(genotype2.substring(size, 32));
            } else{
                stringBuilder.append(genotype2.substring(0,32-size));
                stringBuilder.append(genotype1.substring(32-size, 32));
            }
        } else{
            int size =(int)( (energy2 / sum_energy)*32.0);
            if(left){
                stringBuilder.append(genotype2.substring(0,size));
                stringBuilder.append(genotype1.substring(size, 32));
            } else{
                stringBuilder.append(genotype1.substring(0,32-size));
                stringBuilder.append(genotype2.substring(32-size, 32));
            }
        }

        return stringBuilder.toString();
    }

    public void createPlant(int x, int y, int energy){
        Plant plant = new Plant(x, y, energy);

        changer.addChange(x,y);
        worldModel.addPlant(plant);
    }

    private void createPlantInJungle(){
        int[] jungleP = this.worldModel.getJungleParam();
        Random random = new Random();
        int cnt = 0;
        int x,y;
        do {
            x = Math.abs(random.nextInt()) % jungleP[2] + jungleP[0];
            y = Math.abs(random.nextInt()) % jungleP[3] + jungleP[1];
            cnt++;
            if(cnt >= jungleP[2]*jungleP[3])
            {
                return;
            }
        } while(this.worldModel.isPlant(x, y));
        createPlant(x,y, this.energyPlant);
    }

    private void createPlantInSteppe(){
        int[] jungleP = this.worldModel.getJungleParam();
        Random random = new Random();
        int cnt = 0;
        int x,y;
        do {
            switch(Math.abs(random.nextInt())%4){
                case 0:
                    x = Math.abs(random.nextInt()) % this.widthMap;
                    y = Math.abs(random.nextInt()) % jungleP[1];
                    break;
                case 1:
                    x = Math.abs(random.nextInt()) % (this.widthMap - jungleP[2] - jungleP[0]) + jungleP[2] + jungleP[0];
                    y = Math.abs(random.nextInt()) % this.heightMap;
                    break;
                case 2:
                    x = Math.abs(random.nextInt()) % this.widthMap;
                    y = Math.abs(random.nextInt()) % (this.heightMap - jungleP[3] - jungleP[1]) + jungleP[3] + jungleP[1];
                    break;
                case 3:
                    x = Math.abs(random.nextInt()) % jungleP[0];
                    y = Math.abs(random.nextInt()) % this.heightMap;
                    break;
                default:
                    x = 0;
                    y = 0;
            }
            cnt++;
            if(cnt >= this.heightMap*this.widthMap*(1-this.jungleRatio))
            {
                return;
            }
        } while(this.worldModel.isPlant(x, y));
        createPlant(x,y, this.energyPlant);
    }

    private void applyViewGenotype(){
        this.simulationComponent.setTextDominantGenotype("Dominant: "+dominantGenotype.getFirst());
    }

    private void applyViewEpoch(){
        this.simulationComponent.setTextEpoch("Epoch: "+this.worldModel.getEpoch());
    }

    private void applyViewChart(){
        this.simulationComponent.getChartComponent().addSeriesData(this.statistic.getLastStatistic());
    }

    public void applyMagic(){
        Random random = new Random();
        ArrayList<Animal> animalArrayList= new ArrayList<>();
        for(Animal a: this.worldModel.getAnimals()){
                int x = Math.abs(random.nextInt())%this.widthMap;
                int y = Math.abs(random.nextInt())%this.heightMap;
//                this.createAnimal(x,y,this.energyAnimal , a.getGenotype());
                Animal ani = new Animal(x,y,this.energyAnimal, a.getGenotype(), this.worldModel);
            changer.addChange(x,y);
//            worldModel.addAnimal(ani);
            dominantGenotype.addGenotype(a.getGenotype());
            animalArrayList.add(ani);
        }

        for(Animal ani: animalArrayList){
            worldModel.addAnimal(ani);
        }


        this.changer.applyView();
//        applyViewEpoch();
        applyViewGenotype();
        applyViewChart();

        new Thread(new Task<>(){

            @Override
            protected Object call() throws Exception {
                Platform.runLater(()->{simulationComponent.setMagicEvolution("Magic!");});
                try {
                    Thread.sleep(7000);
                } catch (Exception e){
                    System.out.println(e);
                }
                Platform.runLater(()->{simulationComponent.setMagicEvolution("");});
                return null;
            }
        }).start();

    }

    public void startButton(){
        this.simulationRuns = true;
        this.simulationThread = new Thread(new Task<>() {
            @Override
            protected Void call() throws Exception {
                while(simulationRuns) {

                    generateDay();

                    try{
                        Thread.sleep(timeDelay);
                    }catch (Exception e){
                        System.out.println("I was interrupted");
                        System.out.println(e.toString());
                    }

                }
                return null;
            }
        });
        this.simulationThread.setDaemon(true);
        this.simulationThread.start();
    }

    public void stopSimulation(){
        this.simulationRuns = false;
    }

}
