package sample;

/**
 * Created by GREEN on 13.11.2016.
 */
public class MapSubject {
    private Coordinates position;
    private int status;
    MapSubject(){
        status = 0;
        position = new Coordinates();
    }
    public void setCoordinates(Coordinates positionValue){
        position.setRowColumn(positionValue.getRow(),positionValue.getColumn());
    }
    public void setStatus(int statusValue){
        status = statusValue;
    }
    public Coordinates getCoordinates(){
        return position;
    }
    public int getStatus(){
        return status;
    }
}
