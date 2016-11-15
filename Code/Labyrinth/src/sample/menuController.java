package sample;

/**
 * Created by GREEN on 15.11.2016.
 */
public class menuController {
    public void startButtonClicked(){
        try {
            new MapBuildWindow();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void settingsButtonClicked(){

    }
    public void statisticsButtonClicked(){

    }
    public void helpButtonClicked(){

    }
    public void exitButtonClicked(){
        System.exit(1);
    }

}
