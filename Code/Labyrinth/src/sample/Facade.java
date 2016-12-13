package sample;

/**
 * Created by GREEN on 13.11.2016.
 */
public interface Facade {
    public void setMap(int[][] labyrinth, Coordinates[] trapBonus);
    public GameMap getMap();
}
