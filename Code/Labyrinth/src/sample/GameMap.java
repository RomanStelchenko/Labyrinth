package sample;

/**
 * Created by GREEN on 13.11.2016.
 */
public class GameMap {

    int [][] labyrinth;
    Coordinates[] trapBonus;
    GameMap(){
        labyrinth = new int[10][10];
        trapBonus = new Coordinates[15];
    }
    public void setLabyrinth(int[][] labyrinthValue){
        labyrinth = labyrinthValue;
    }
    public void setTrapBonus(Coordinates[] trapBonusValue){
        trapBonus = trapBonusValue;
    }
    public int[][] getLabyrinth(){
        return labyrinth;
    }
    public Coordinates[] getTrapBonus(){
        return trapBonus;
    }
    public int getLabyrinthInd(int x, int y){
        return labyrinth[x][y];
    }
    public Coordinates getTrapBonusInd(int index){
        return trapBonus[index];
    }
}
