package sample;

/**
 * Created by GREEN on 13.11.2016.
 */
public class Coordinates {
    private int row;
    private int column;
    Coordinates(){
        row = 0;
        column = 0;
    }
    Coordinates(int rowValue, int columnValue){
        row = rowValue;
        column = columnValue;
    }
    public void setRow(int rowValue){
        row = rowValue;
    }
    public void setColumn(int columnValue){
        column = columnValue;
    }
    public void setRowColumn(int rowValue, int columnValue){
        row = rowValue;
        column = columnValue;
    }
    public void setRowColumn(Coordinates CoordinatesValue){
        row = CoordinatesValue.row;
        column = CoordinatesValue.column;
    }
    public int getColumn(){
        return column;
    }
    public int getRow(){
        return row;
    }
    public void incRow(){ row++;}
    public void incColumn(){ column++;}
    public void decRow(){ row--;}
    public void decColumn(){ column--;}
}
