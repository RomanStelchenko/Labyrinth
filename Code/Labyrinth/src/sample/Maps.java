package sample;

/**
 * Created by GREEN on 13.11.2016.
 */
public class Maps {
    MapFacade playerMap;
    MapFacade enemyMap;
    Maps(){
        playerMap = new MapFacade();
        enemyMap = new MapFacade();
    }
    public void setEnemyMap(MapFacade enemyMapValue){
        enemyMap = enemyMapValue;
    }
    public void setPlayerMap(MapFacade playerMapValue){
        playerMap = playerMapValue;
    }

    public MapFacade getEnemyMap() {
        return enemyMap;
    }

    public MapFacade getPlayerMap() {
        return playerMap;
    }

    public void updatePlayerMap(){

    }
    public void updateEnemyMap(){

    }
}
