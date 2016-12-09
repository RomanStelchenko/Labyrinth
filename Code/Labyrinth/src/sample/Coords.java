package sample;

/**
 * Created by GREEN on 13.11.2016.
 */
public class Coords {
    private int row;
    private int column;
    Coords(){
        row = 0;
        column = 0;
    }
    Coords(int rowValue, int columnValue){
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
    public void setRowColumn(Coords coordsValue){
        row = coordsValue.row;
        column = coordsValue.column;
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
