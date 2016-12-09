package sample;

import java.util.Random;

/**
 * Created by GREEN on 15.11.2016.
 */
public class menuController {
    boolean course;
    public void startButtonClicked(){
        try {
            new MapBuildWindow();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void infoButtonClicked(){
       Random rand = new Random();
        course = rand.nextBoolean();
        System.out.println(course);
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
