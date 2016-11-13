package sample;

/**
 * Created by GREEN on 13.11.2016.
 */
public class Coords {
    private int x;
    private int y;
    Coords(){
        x = 0;
        y = 0;
    }
    Coords(int xValue, int yValue){
        x = xValue;
        y = yValue;
    }
    public void setX(int xValue){
        x = xValue;
    }
    public void setY(int yValue){
        y = yValue;
    }
    public void setXY(int xValue, int yValue){
        x = xValue;
        y = yValue;
    }
    public int getY(){
        return y;
    }
    public int getX(){
        return x;
    }
}
