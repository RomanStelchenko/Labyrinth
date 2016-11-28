package sample;

import java.util.ArrayList;

/**
 * Created by GREEN on 13.11.2016.
 */
public class MapSubjectList {
    private ArrayList<MapSubject> list;
    public  void addAll(MapSubject[] subjects){
        for(int i=0;i<subjects.length;i++){
            list.add(subjects[i]);
        }
    }
    public void addMapSubject(MapSubject mapSubjectValue){
        list.add(mapSubjectValue);
    }
    public void addMapSubjectInd(int index, MapSubject mapSubjectValue){
        list.add(index,mapSubjectValue);
    }
    public MapSubject getMapSubject(int index){
        return list.get(index);
    }

    public MapSubject[] getAll(){
        MapSubject[] subjects = new MapSubject[list.size()];
        for(int i = 0; i < list.size(); i++){
            subjects[i] = list.get(i);
        }
        return subjects;
    }
}
