package sample;

/**
 * Created by GREEN on 13.11.2016.
 */
public interface Facade {
    public void setMap(int[][] labyrinth, Coords[] trapBonus);
    public GameMap getMap();
}
