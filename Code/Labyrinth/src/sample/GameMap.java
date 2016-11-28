package sample;

/**
 * Created by GREEN on 13.11.2016.
 */
public class GameMap {

    int [][] labyrinth;
    Coords[] trapBonus;
    GameMap(){
        labyrinth = new int[10][10];
        trapBonus = new Coords[15];
    }
    public void setLabyrinth(int[][] labyrinthValue){
        labyrinth = labyrinthValue;
    }
    public void setTrapBonus(Coords[] trapBonusValue){
        trapBonus = trapBonusValue;
    }
    public int[][] getLabyrinth(){
        return labyrinth;
    }
    public Coords[] getTrapBonus(){
        return trapBonus;
    }
    public int getLabyrinthInd(int x, int y){
        return labyrinth[x][y];
    }
    public Coords getTrapBonusInd(int index){
        return trapBonus[index];
    }
}
