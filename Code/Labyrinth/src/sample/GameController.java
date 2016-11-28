package sample;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.Random;

public class GameController {

    private Constants mapConstant;
    private ImageView currentImage;
    private ImageView[] trapBonusImages;
    private ImageView[] enemyTrapBonusImages;
    private Coords[] trapBonus;
    private int [][]labyrinth;
    private boolean course;
    private Coords coords;
    private Maps maps;
    private Connect connect;
    private int process;
    String readLine;
    readLineThread rr;
    @FXML
    GridPane mapPane;
    @FXML
    GridPane wallPane;
    @FXML
    GridPane trapBonusPane;
    @FXML
    GridPane enemyMapPane;

    @FXML
    public void initialize(){
        maps = new Maps();
        currentImage = new ImageView(new Image(mapConstant.blue));
        mapPane.add(currentImage,0,0);
        course = false;
        labyrinth = new int[10][10];
        coords = new Coords(0,0);
        trapBonus = new Coords[15];
        trapBonusImages  = new ImageView[15];
        for(int i=0;i<15;i++) {
            trapBonus[i] = new Coords();
            trapBonusImages[i] = new ImageView(new Image(mapConstant.trapBonusImagesUrl[i],40,40,false,false));
            mapPane.add(trapBonusImages[i],0,0);
            trapBonusImages[i].setVisible(false);
        }
    }

    public void setMap(MapFacade enemyMap, int connectSelectionValue){

        maps.setEnemyMap(enemyMap);

        ImageView enemyMapImage = new ImageView();
        int[][] labyrinthValue = new int[10][10];
        labyrinthValue = maps.getEnemyMap().getMap().getLabyrinth();
        Coords[] trapBonusValue = new Coords[15];
        trapBonusValue = maps.getEnemyMap().getMap().getTrapBonus();
        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++) {
                if (labyrinthValue[i][j] != 15) {
                    enemyMapImage = (ImageView) enemyMapPane.getChildren().get( j*10 + i);
                    enemyMapImage.setImage(new Image(mapConstant.wallImagesUrl[labyrinthValue[i][j]]));
                 }
            }

        enemyTrapBonusImages  = new ImageView[15];
        for(int i=0;i<15;i++) {
            if (trapBonusValue[i].getX()!=15 && trapBonusValue[i].getY()!=15){
                enemyTrapBonusImages[i] = new ImageView(new Image(mapConstant.trapBonusImagesUrl[i], 40, 40, false, false));
                enemyMapPane.add(enemyTrapBonusImages[i], trapBonusValue[i].getX(), trapBonusValue[i].getY());
            }
       }
        if(connectSelectionValue == 0){
            connect = new Connect(new ServerConnect());
        }
        else {
            connect = new Connect(new ClientConnect());
        }
        try {
            connect.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        process = mapConstant.ready;
        readLine = connect.getReadLine();
        rr = new readLineThread();
        rr.start();

    }
    public void leftButtonClicked(){
        connect.setWriteLine("Left");
    }
    public void rightButtonClicked(){
        connect.setWriteLine("Right");
    }
    public void upButtonClicked(){
        connect.setWriteLine("Up");
    }
    public void downButtonClicked(){
        connect.setWriteLine("Down");
    }

    public void mapClicked(MouseEvent e) {
        Node source = (Node) e.getSource();
        if (mapPane.getRowIndex(source) != null)
            coords.setY(mapPane.getRowIndex(source));
        else coords.setY(0);
        if (mapPane.getColumnIndex(source) != null)
            coords.setX(mapPane.getColumnIndex(source));
        else coords.setX(0);
        GridPane.setConstraints(currentImage, coords.getX(), coords.getY());
    }
    public void spriteWallClicked(MouseEvent event){
        int row = 0;
        int column = 0;
        Node source = (Node)event.getSource() ;
        if(wallPane.getRowIndex(source)!= null)
            row = wallPane.getRowIndex(source);
        if(wallPane.getColumnIndex(source)!= null)
            column = wallPane.getColumnIndex(source);

        ImageView mapPaneImage  = (ImageView)mapPane.getChildren().get(coords.getY()*10+coords.getX());
        mapPaneImage.setImage(new Image(mapConstant.wallImagesUrl[row*4 + column]));
        labyrinth[coords.getX()][coords.getY()] = row*4 + column;

    }
    public void spriteTrapBonusClicked(MouseEvent event){
        int row = 0;
        int column = 0;
        Node source = (Node)event.getSource() ;
        if(trapBonusPane.getRowIndex(source)!= null)
            row = trapBonusPane.getRowIndex(source);
        if(trapBonusPane.getColumnIndex(source)!= null)
            column = trapBonusPane.getColumnIndex(source);
        GridPane.setConstraints(trapBonusImages[row*4+column],coords.getX(),coords.getY());
        trapBonusImages[row*4+column].setVisible(true);
        trapBonus[row*4+column].setXY(coords.getX(),coords.getY());

    }
    class readLineThread extends Thread {
        public void run() {
            while(true)
            {
                while (connect.getReadReady() == false) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                readLine = connect.readLine;
                System.out.println("Read NEw  "+readLine);
                gameProcess();
                connect.clearReadReady();
            }
        }
    }
    public void gameProcess(){
        int readLineInt = Integer.valueOf(readLine);
        if(readLineInt == mapConstant.readyRequest){
            gameBeginFlag();
            System.out.println(course);
        }/*
        if(readLineInt == mapConstant.exitRequest){

        }
        if(readLineInt == mapConstant.pauseRequest){

        }
        if(readLineInt == mapConstant.timeOutRequest){

        }
        if(readLineInt == mapConstant.leftRequest){

        }
        if(readLineInt == mapConstant.rightRequest){

        }*/
    }
    public void gameBeginFlag(){
        Random rand = new Random();
        course = rand.nextBoolean();
    }
    public void changeCourse(){
        course = !course;
    }
    public void readyButtonClicked(){
        connect.setWriteLine(Integer.toString(mapConstant.readyRequest));
    }
    public void requestToEnemy(){
        
    }
    public void answerToEnemy(){

    }
}
