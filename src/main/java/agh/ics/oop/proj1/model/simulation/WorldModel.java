package agh.ics.oop.proj1.model.simulation;

public class WorldModel {

    private final int widthMap;
    private final int heightMap;

    private final Double jungleRatio;
    private final int junglePosX;
    private final int junglePosY;
    private final int jungleWidth;
    private final int jungleHeight;

    public WorldModel(int widthMap, int heightMap, double jungleRatio){

        this.jungleRatio = jungleRatio;
        this.widthMap = widthMap;
        this.heightMap = heightMap;
        double sqrtJungleRatio = Math.sqrt(this.jungleRatio);

        this.jungleWidth = (int)Math.ceil(this.widthMap * sqrtJungleRatio);
        this.jungleHeight = (int)Math.ceil(this.heightMap * sqrtJungleRatio);
        this.junglePosX = (this.widthMap - this.jungleWidth)/2;
        this.junglePosY = (this.heightMap - this.jungleHeight)/2;


    }

    public int[] getJungleParam(){
        return new int[]{this.junglePosX, this.junglePosY,
                this.jungleWidth, this.jungleHeight};
    }

}
