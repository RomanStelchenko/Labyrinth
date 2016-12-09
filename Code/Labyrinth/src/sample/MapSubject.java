package sample;

/**
 * Created by GREEN on 13.11.2016.
 */
public class MapSubject {
    private Coords position;
    private int status;
    MapSubject(){
        status = 0;
        position = new Coords();
    }
    public void setCoords(Coords positionValue){
        position.setRowColumn(positionValue.getRow(),positionValue.getColumn());
    }
    public void setStatus(int statusValue){
        status = statusValue;
    }
    public Coords getCoords(){
        return position;
    }
    public int getStatus(){
        return status;
    }
}
