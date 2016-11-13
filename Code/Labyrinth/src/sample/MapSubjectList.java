package sample;

import java.util.ArrayList;

/**
 * Created by GREEN on 13.11.2016.
 */
public class MapSubjectList {
    private ArrayList<MapSubject> list;
    private int lenght;
    public void addMapSubject(MapSubject mapSubjectValue){
        list.add(mapSubjectValue);
    }
    public void addMapSubjectInd(int index, MapSubject mapSubjectValue){
        list.add(index,mapSubjectValue);
    }
    public MapSubject getMapSubject(int index){
        return list.get(index);
    }
    public void setLenght(int lenghtValue){
        lenght = lenghtValue;
    }
    public int getLenght(){
        return lenght;
    }

}
