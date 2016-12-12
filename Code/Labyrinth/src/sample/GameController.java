package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.Calendar;
import java.util.Optional;
import java.util.Random;

public class GameController {

    private Constants mapConstant;
    private ImageView currentImage;
    private ImageView[] trapBonusImages;
    private ImageView[] enemyTrapBonusImages;
    private Coords[] trapBonus;
    private Coords[] enemyTrapBonus;
    private int enemyTreasureValue;
    private int [][]labyrinth,enemyLabyrinth;
    private boolean course;
    private Coords coords;
    private Coords playerCoords;
    private Coords enemyCoords;
    private boolean enemyCrossbowFlag,enemyCrutchFlag,enemyTrapFlag;
    private boolean playerCrossbowFlag,playerCrutchFlag,playerTrapFlag;
    private Maps maps;
    private Connect connect;
    private int bombValue;
    private int process;
    private int selectionValue;
    private int connectSelection;
    private int readyAll;

    private ObservableList<TableText> dataTable = FXCollections.observableArrayList();
    private Calendar c;
    private String time;
    private String processText;
    private String playerName;
    private String enemyName;
    private String sendMessage;
    private ImageView enemyMapImage;
    private boolean grenadeFlag;
    private boolean treasureFlag;
    private boolean pickTreasure;
    private int grenadeValue;
    private boolean flag;
    private int courseNumbers = 0;
    private int courseAll = 0;
    private String readLine;
    private readLineThread readThread;
    @FXML
    GridPane mapPane;
    @FXML
    ImageView leftButtonImage,rightButtonImage,upButtonImage,downButtonImage,bomb1Image,bomb2Image,bomb3Image;
    @FXML
    GridPane wallPane;
    @FXML
    GridPane trapBonusPane;
    @FXML
    GridPane enemyMapPane;
    @FXML
    Label processLabel;
    @FXML
    TableColumn<TableText,String> playerNameColumn;
    @FXML
    TableColumn<TableText,String> timeColumn;
    @FXML
    TableColumn<TableText,String> processColumn;
    @FXML
    TableView<TableText> processTable = new TableView<TableText>();
    private   ImageView enemyImage;
    @FXML
    public void initialize(){
        bombValue = 3;
        selectionValue = 4;
        grenadeValue  = 3;
        enemyTreasureValue = 0;
        courseNumbers =0;
        grenadeFlag = false;
        treasureFlag =false;
        flag =false;
        maps = new Maps();
        mapConstant = new Constants();
        currentImage = new ImageView(new Image(mapConstant.blue));
        mapPane.add(currentImage,0,0);
        course = false;
        labyrinth = new int[10][10];
        enemyLabyrinth = new int[10][10];
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

        enemyMapImage = new ImageView();
        enemyLabyrinth = maps.getEnemyMap().getMap().getLabyrinth();
        enemyTrapBonus = new Coords[15];
        enemyTrapBonus = maps.getEnemyMap().getMap().getTrapBonus();
        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++) {
                if (enemyLabyrinth[i][j] != 15) {
                    enemyMapImage = (ImageView) enemyMapPane.getChildren().get( j*10 + i);
                    enemyMapImage.setImage(new Image(mapConstant.wallImagesUrl[enemyLabyrinth[i][j]]));
                 }
            }

        enemyTrapBonusImages  = new ImageView[15];
        for(int i=0;i<15;i++) {
            if (enemyTrapBonus[i].getRow()!=15 && enemyTrapBonus[i].getColumn()!=15){
                enemyTrapBonusImages[i] = new ImageView(new Image(mapConstant.trapBonusImagesUrl[i], 40, 40, false, false));
                enemyMapPane.add(enemyTrapBonusImages[i], enemyTrapBonus[i].getRow(), enemyTrapBonus[i].getColumn());
            }
       }
       enemyImage = new ImageView(new Image(mapConstant.player,40,40,false,false));
       enemyImage.setVisible(false);
       enemyMapPane.getChildren().add(enemyImage);

        processLabel.setText("Ожидание готовности игроков");
        if(connectSelectionValue == 0){
            processText = "подключился как сервер";
            connect = new Connect(new ServerConnect());
        }
        else {
            connect = new Connect(new ClientConnect());
            processText = "подключился как клиент";
        }
        try {
            connect.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        connectSelection = connectSelectionValue;
        process = mapConstant.ready;
        readThread = new readLineThread();
        readThread.start();


        playerNameColumn.setCellValueFactory(new PropertyValueFactory<TableText, String>("playerName"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<TableText, String>("time"));
        processColumn.setCellValueFactory(new PropertyValueFactory<TableText, String>("process"));
        processTable.setItems(dataTable);
        c = Calendar.getInstance();
        time = new String(c.get(c.HOUR) +":"+c.get(c.MINUTE) +":"+c.get(c.SECOND));
        dataTable.add(new TableText(playerName,processText,time));

        c = Calendar.getInstance();
        time = new String(c.get(c.HOUR) +":"+c.get(c.MINUTE) +":"+c.get(c.SECOND));
        processText = "ожидание готовности игроков";
        dataTable.add(new TableText("Game",processText,time));


    }
    public void leftButtonClicked(){

        if(selectionValue!= mapConstant.left){
            leftButtonImage.setImage(new Image(mapConstant.leftButtonClicked));
            changeSelectionImage();
            selectionValue = mapConstant.left;
        }
        if(grenadeFlag)
            sendMessage = mapConstant.grenadeLeftRequest;
        else sendMessage = mapConstant.leftRequest;
    }
    public void rightButtonClicked(){
        if(selectionValue!= mapConstant.right){
            rightButtonImage.setImage(new Image(mapConstant.rightButtonClicked));
            changeSelectionImage();
            selectionValue = mapConstant.right;
        }
        if(grenadeFlag)
            sendMessage = mapConstant.grenadeRightRequest;
        else sendMessage = mapConstant.rightRequest;
    }
    public void upButtonClicked(){
        if(selectionValue!= mapConstant.up){
            upButtonImage.setImage(new Image(mapConstant.upButtonClicked));
            changeSelectionImage();
            selectionValue = mapConstant.up;
        }
        if(grenadeFlag)
            sendMessage = mapConstant.grenadeUpRequest;
        else sendMessage = mapConstant.upRequest;
    }
    public void downButtonClicked(){
        if(selectionValue!= mapConstant.down){
            downButtonImage.setImage(new Image(mapConstant.downButtonClicked));
            changeSelectionImage();
            selectionValue = mapConstant.down;
        }
        if(grenadeFlag)
            sendMessage = mapConstant.grenadeDownRequest;
        else sendMessage = mapConstant.downRequest;
    }
    public void changeGrenadeImages(boolean isClicked) {
        String imageUrl = new String();
        if (isClicked)
            imageUrl = mapConstant.bombClicked;
        else imageUrl = mapConstant.bomb;
        switch (bombValue) {
            case 0:
                bomb1Image.setImage(new Image(imageUrl));
                break;
            case 1:
                bomb2Image.setImage(new Image(imageUrl));
                break;
            case 2:
                bomb3Image.setImage(new Image(imageUrl));
                break;
            default:
                break;
        }
    }
    public void changeGrenadeFlag(int number){
        if(grenadeFlag == false) {
            grenadeFlag = true;
            bombValue = number;
            changeGrenadeImages(true);
        }
        else {
            changeGrenadeImages(false);
            if(bombValue == number )
                grenadeFlag = false;
            else {
                bombValue = number;
                changeGrenadeImages(true);
            }
        }
    }
    public void disableGrenadeImages() {
        switch (bombValue) {
            case 0:
                bomb1Image.setVisible(false);
                break;
            case 1:
                bomb2Image.setVisible(false);
                break;
            case 2:
                bomb3Image.setVisible(false);
                break;
            default:
                break;
        }
    }

    public void mapClicked(MouseEvent e) {
        Node source = (Node) e.getSource();
        if (mapPane.getRowIndex(source) != null)
            coords.setColumn(mapPane.getRowIndex(source));
        else coords.setColumn(0);
        if (mapPane.getColumnIndex(source) != null)
            coords.setRow(mapPane.getColumnIndex(source));
        else coords.setRow(0);
        GridPane.setConstraints(currentImage, coords.getRow(), coords.getColumn());
    }
    public void spriteWallClicked(MouseEvent event){
        int row = 0;
        int column = 0;
        Node source = (Node)event.getSource() ;
        if(wallPane.getRowIndex(source)!= null)
            row = wallPane.getRowIndex(source);
        if(wallPane.getColumnIndex(source)!= null)
            column = wallPane.getColumnIndex(source);

        ImageView mapPaneImage  = (ImageView)mapPane.getChildren().get(coords.getColumn()*10+coords.getRow());
        mapPaneImage.setImage(new Image(mapConstant.wallImagesUrl[row*4 + column]));
        labyrinth[coords.getRow()][coords.getColumn()] = row*4 + column;

    }
    public void spriteTrapBonusClicked(MouseEvent event){
        int row = 0;
        int column = 0;
        Node source = (Node)event.getSource() ;
        if(trapBonusPane.getRowIndex(source)!= null)
            row = trapBonusPane.getRowIndex(source);
        if(trapBonusPane.getColumnIndex(source)!= null)
            column = trapBonusPane.getColumnIndex(source);
        GridPane.setConstraints(trapBonusImages[row*4+column],coords.getRow(),coords.getColumn());
        trapBonusImages[row*4+column].setVisible(true);
        trapBonus[row*4+column].setRowColumn(coords.getRow(),coords.getColumn());

    }
    public void bomb1Clicked(){
        changeGrenadeFlag(0);
    }
    public void bomb2Clicked(){
        changeGrenadeFlag(1);
    }
    public void bomb3Clicked(){
        changeGrenadeFlag(2);
    }
    public void changeSelectionImage(){
        if(selectionValue == mapConstant.left){
            leftButtonImage.setImage(new Image(mapConstant.leftButton));
        }
        if(selectionValue == mapConstant.right){
            rightButtonImage.setImage(new Image(mapConstant.rightButton));
        }
        if(selectionValue == mapConstant.up){
            upButtonImage.setImage(new Image(mapConstant.upButton));
        }
        if(selectionValue == mapConstant.down){
            downButtonImage.setImage(new Image(mapConstant.downButton));
        }
    }
    public void setFlag(boolean flagL){
    flag = flagL;
}

    class readLineThread extends Thread {
        public void run() {
            while(true)
            {
                while (flag == false) {
                    Platform.runLater(()->setFlag(connect.getReadReady()));
                    threadSleep(500);
                }
                Platform.runLater(()->setFlag(false));
                Platform.runLater(()->connect.clearReadReady());
                Platform.runLater(()->setReadLime(connect.getReadLine()));
                Platform.runLater(()->gameProcess());
                threadSleep(100);
            }
        }
    }

    public void setReadLime(String line){
        readLine = new String(line);
    }
    public void gameProcess(){
       if(process == mapConstant.ready){
           enemyName = new String(readLine);
           c = Calendar.getInstance();
           time = new String(c.get(c.HOUR) +":"+c.get(c.MINUTE) +":"+c.get(c.SECOND));
           processText = "готов к игре";
           dataTable.add(new TableText(enemyName,processText,time));
           readyAll++;
           if (readyAll == 2) {
               processLabel.setText("Выбор стартовых клеток игроками");
               process = mapConstant.beginCoords;
           }
           return;
       }

       if(process == mapConstant.beginCoords){
           enemyCoords = new Coords(Integer.valueOf(Character.toString(readLine.charAt(0))),Integer.valueOf(Character.toString(readLine.charAt(1))));
           enemyImage.setVisible(true);
           enemyMapPane.setConstraints(enemyImage, enemyCoords.getRow(), enemyCoords.getColumn());

           c = Calendar.getInstance();
           time = new String(c.get(c.HOUR) +":"+c.get(c.MINUTE) +":"+c.get(c.SECOND));
           processText = ("выбрал стартовую клетку : "+enemyCoords.getRow()+"-"+enemyCoords.getColumn());
           dataTable.add(new TableText(enemyName,processText,time));
           readyAll++;
           if (readyAll == 4) {
               process = mapConstant.course;
               if(connectSelection == 0 )
                   gameBeginFlag();
           }
           return;
       }

       if(process == mapConstant.course){
           if(readLine.equals("true"))
               course = false;
           else course = true;
           c = Calendar.getInstance();
           time = new String(c.get(c.HOUR) +":"+c.get(c.MINUTE) +":"+c.get(c.SECOND));
           processText = "начинает первым ";
           if(course == true) {
               processLabel.setText("Ваш ход");
               dataTable.add(new TableText(playerName, processText, time));
           }
           if(course == false) {
               processLabel.setText("Ход соперника");
               dataTable.add(new TableText(enemyName, processText, time));
           }
           process = mapConstant.game;
           return;
       }
       if(process == mapConstant.game){
           String selection = new String(readLine);
           if(course == true) {

               if (selection.equals(mapConstant.endCourseRequest)) {
                   {
                       changeCourse();
                   }
               }else{
                   if (selection.equals(mapConstant.onTreasure)) {
                       treasureFlag = true;
                   }
                   if (selection.equals(mapConstant.onCrossbow)) {
                      playerCrossbowFlag = true;
                       courseAll++;
                   }
                   if (selection.equals(mapConstant.onCrutch)) {
                       playerCrutchFlag = true;
                     }
                   if (selection.equals(mapConstant.onTrap)) {
                       playerTrapFlag = true;
                       if(enemyCrossbowFlag)
                           courseNumbers = 7;
                       else courseNumbers = 3;
                   }

                   c = Calendar.getInstance();
                   time = new String(c.get(c.HOUR) + ":" + c.get(c.MINUTE) + ":" + c.get(c.SECOND));
                   dataTable.add(new TableText(playerName, selection, time));
               }
           }
           else{

               if(selection.equals(mapConstant.pickTreasure)){
                   enemyTreasureValue = 0;
                    return;
               }


               int[] wall = new int[4];
               wall = toBinary(enemyLabyrinth[enemyCoords.getRow()][enemyCoords.getColumn()]);

               if(selection.equals(mapConstant.leftRequest)) {
                    if(wall[0]==0) {
                        enemyCoords.decRow();
                        if(enemyCoords.getRow()<0){
                            if(enemyTreasureValue == 1)
                                connect.setWriteLine("Клад настоящий");
                            else if(enemyTreasureValue == 2)
                                    connect.setWriteLine("Клад ложный");
                                else connect.setWriteLine("У вас нету клада");
                            threadSleep(500);
                            connect.setWriteLine(mapConstant.endCourseRequest);
                            return;
                        }

                        enemyMapPane.setConstraints(enemyImage, enemyCoords.getRow(), enemyCoords.getColumn());
                        trapBonusExample();
                        connect.setWriteLine("Да влево можно");
                    }else {
                        connect.setWriteLine("нельзя влево,брат");
                    }
               }
               if(selection.equals(mapConstant.rightRequest)) {
                   if(wall[1]==0) {
                       enemyCoords.incRow();
                       if(enemyCoords.getRow()>9){
                           if(enemyTreasureValue == 1)
                               connect.setWriteLine("Клад настоящий");
                           else if(enemyTreasureValue == 2)
                               connect.setWriteLine("Клад ложный");
                           else connect.setWriteLine("У вас нету клада");
                           threadSleep(500);
                           connect.setWriteLine(mapConstant.endCourseRequest);
                           return;
                       }

                       enemyMapPane.setConstraints(enemyImage, enemyCoords.getRow(), enemyCoords.getColumn());
                       trapBonusExample();
                       connect.setWriteLine("Да вправо можно");
                   }else {
                       connect.setWriteLine("нельзя вправо");
                   }
               }
               if(selection.equals(mapConstant.upRequest)) {
                   if(wall[2]==0) {
                       enemyCoords.decColumn();
                       if(enemyCoords.getColumn()<0){
                           if(enemyTreasureValue == 1)
                               connect.setWriteLine("Клад настоящий");
                           else if(enemyTreasureValue == 2)
                               connect.setWriteLine("Клад ложный");
                           else connect.setWriteLine("У вас нету клада");
                           threadSleep(500);
                           connect.setWriteLine(mapConstant.endCourseRequest);
                           return;
                       }
                       enemyMapPane.setConstraints(enemyImage, enemyCoords.getRow(), enemyCoords.getColumn());
                       trapBonusExample();
                       connect.setWriteLine("Да вверх можно");
                   }else {
                       connect.setWriteLine("нельзя вверх");
                   }
               }
               if(selection.equals(mapConstant.downRequest)) {
                   if(wall[3]==0) {
                       enemyCoords.incColumn();
                       if(enemyCoords.getColumn()>9){
                           if(enemyTreasureValue == 1)
                               connect.setWriteLine("Клад настоящий");
                           else if(enemyTreasureValue == 2)
                               connect.setWriteLine("Клад ложный");
                           else connect.setWriteLine("У вас нету клада");
                           threadSleep(500);
                           connect.setWriteLine(mapConstant.endCourseRequest);
                           return;
                       }

                       trapBonusExample();
                       connect.setWriteLine("Вниз можно");
                       enemyMapPane.setConstraints(enemyImage, enemyCoords.getRow(), enemyCoords.getColumn());
                   }else {
                       connect.setWriteLine("нельзя вниз");
                   }
               }
               if(selection.equals(mapConstant.pauseRequest)) {
               }
               if(selection.equals(mapConstant.grenadeLeftRequest)) {
                    wall[0]=0;
                   enemyMapImage = (ImageView) enemyMapPane.getChildren().get( enemyCoords.getColumn()*10 + enemyCoords.getRow());
                   enemyLabyrinth[enemyCoords.getRow()][enemyCoords.getColumn()] = toDec(wall);
                   enemyMapImage.setImage(new Image(mapConstant.wallImagesUrl[toDec(wall)]));
                   if(enemyCoords.getRow()-1 >=0){
                       wall = toBinary(enemyLabyrinth[enemyCoords.getRow()-1][enemyCoords.getColumn()]);
                       wall[1] = 0;
                       enemyMapImage = (ImageView) enemyMapPane.getChildren().get( enemyCoords.getColumn()*10 + enemyCoords.getRow()-1);
                       enemyLabyrinth[enemyCoords.getRow()-1][enemyCoords.getColumn()] = toDec(wall);
                       enemyMapImage.setImage(new Image(mapConstant.wallImagesUrl[toDec(wall)]));
                   }
                   connect.setWriteLine("Граната брошена");

               }
               if(selection.equals(mapConstant.grenadeRightRequest)) {
                   wall[1]=0;
                   enemyMapImage = (ImageView) enemyMapPane.getChildren().get( enemyCoords.getColumn()*10 + enemyCoords.getRow());
                   enemyLabyrinth[enemyCoords.getRow()][enemyCoords.getColumn()] = toDec(wall);
                   enemyMapImage.setImage(new Image(mapConstant.wallImagesUrl[toDec(wall)]));
                   if(enemyCoords.getRow()+1 <=9){
                       wall = toBinary(enemyLabyrinth[enemyCoords.getRow()+1][enemyCoords.getColumn()]);
                       wall[0] = 0;
                       enemyMapImage = (ImageView) enemyMapPane.getChildren().get( enemyCoords.getColumn()*10 + enemyCoords.getRow()+1);
                       enemyLabyrinth[enemyCoords.getRow()+1][enemyCoords.getColumn()] = toDec(wall);
                       enemyMapImage.setImage(new Image(mapConstant.wallImagesUrl[toDec(wall)]));
                   }
                   connect.setWriteLine("Граната брошена");
               }
               if(selection.equals(mapConstant.grenadeUpRequest)) {
                   wall[2]=0;
                   enemyMapImage = (ImageView) enemyMapPane.getChildren().get( enemyCoords.getColumn()*10 + enemyCoords.getRow());
                   enemyLabyrinth[enemyCoords.getRow()][enemyCoords.getColumn()] = toDec(wall);
                   enemyMapImage.setImage(new Image(mapConstant.wallImagesUrl[toDec(wall)]));
                   if(enemyCoords.getColumn()-1 >=0){
                       wall = toBinary(enemyLabyrinth[enemyCoords.getRow()][enemyCoords.getColumn()-1]);
                       wall[3] = 0;
                       enemyMapImage = (ImageView) enemyMapPane.getChildren().get( (enemyCoords.getColumn()-1)*10 + enemyCoords.getRow());
                       enemyLabyrinth[enemyCoords.getRow()][enemyCoords.getColumn()-1] = toDec(wall);
                       enemyMapImage.setImage(new Image(mapConstant.wallImagesUrl[toDec(wall)]));
                   }
                   connect.setWriteLine("Граната брошена");
               }
               if(selection.equals(mapConstant.grenadeDownRequest)) {
                   wall[3]=0;
                   enemyMapImage = (ImageView) enemyMapPane.getChildren().get( enemyCoords.getColumn()*10 + enemyCoords.getRow());
                   enemyLabyrinth[enemyCoords.getRow()][enemyCoords.getColumn()] = toDec(wall);
                   enemyMapImage.setImage(new Image(mapConstant.wallImagesUrl[toDec(wall)]));
                   if(enemyCoords.getColumn()+1 <=9){
                       wall = toBinary(enemyLabyrinth[enemyCoords.getRow()][enemyCoords.getColumn()+1]);
                       wall[2] = 0;
                       enemyMapImage = (ImageView) enemyMapPane.getChildren().get( (enemyCoords.getColumn()+1)*10 + enemyCoords.getRow());
                       enemyLabyrinth[enemyCoords.getRow()][enemyCoords.getColumn()+1] = toDec(wall);
                       enemyMapImage.setImage(new Image(mapConstant.wallImagesUrl[toDec(wall)]));
                   }
                   connect.setWriteLine("Граната брошена");

               }
               if(selection.equals(mapConstant.exitRequest)){
                   changeCourse();
                   c = Calendar.getInstance();
                   time = new String(c.get(c.HOUR) + ":" + c.get(c.MINUTE) + ":" + c.get(c.SECOND));
                   processText = ("завершил ход  ");
                   dataTable.add(new TableText(enemyName, processText, time));
                   connect.setWriteLine("Вниз можно");
               }
               System.out.println("cc " + courseNumbers +" "+ courseAll);
               if(courseNumbers == 0 || enemyTrapFlag) {
                   threadSleep(500);
                   connect.setWriteLine(mapConstant.endCourseRequest);
                   changeCourse();
                   if(enemyTrapFlag)
                       enemyTrapFlag = false;
               }else courseNumbers--;

               if(treasureFlag){
                   treasureDialog();
               }
           }
           return;
       }
    }
    public void trapBonusExample(){
        int i;
        for(i=0;i<15;i++){
            if(enemyTrapBonus[i].getRow() ==  enemyCoords.getRow() &&
                    enemyTrapBonus[i].getColumn() ==  enemyCoords.getColumn()) {
                break;
            }
        }
        if(i == mapConstant.hole1I){
            enemyCoords.setRowColumn(enemyTrapBonus[mapConstant.door1I]);
            enemyMapPane.setConstraints(enemyImage, enemyCoords.getRow(), enemyCoords.getColumn());
            connect.setWriteLine("Попал в яму номер 1");
        }
        if(i == mapConstant.hole2I){
            enemyCoords.setRowColumn(enemyTrapBonus[mapConstant.door2I]);
            enemyMapPane.setConstraints(enemyImage, enemyCoords.getRow(), enemyCoords.getColumn());
            connect.setWriteLine("Попал в яму номер 2");
        }
        if(i == mapConstant.hole3I){
            enemyCoords.setRowColumn(enemyTrapBonus[mapConstant.door3I]);
            enemyMapPane.setConstraints(enemyImage, enemyCoords.getRow(), enemyCoords.getColumn());
            connect.setWriteLine("Попал в яму номер 3");
        }
        if(i == mapConstant.hole4I){
            enemyCoords.setRowColumn(enemyTrapBonus[mapConstant.door4I]);
            enemyMapPane.setConstraints(enemyImage, enemyCoords.getRow(), enemyCoords.getColumn());
            connect.setWriteLine("Попал в яму номер 4");
        }
        if(i == mapConstant.door1I){
            connect.setWriteLine("Выход из ямы номер 1");
        }
        if(i == mapConstant.door2I){
            connect.setWriteLine("Выход из ямы номер 2");
        }
        if(i == mapConstant.door3I){
            connect.setWriteLine("Выход из ямы номер 3");
        }
        if(i == mapConstant.door4I){
            connect.setWriteLine("Выход из ямы номер 4");
        }
        if(i == mapConstant.trapI){
            connect.setWriteLine(mapConstant.onTrap);
        }
        if(i == mapConstant.crossbowI){
           connect.setWriteLine(mapConstant.onCrossbow);
            enemyTrapFlag = true;
        }
        if(i == mapConstant.crutchI){
            connect.setWriteLine("Нашел костыль. Количество ходов уменьшается");
            enemyCrossbowFlag = true;
            courseAll++;
        }
        if(i == mapConstant.realTreasureI || i == mapConstant.falseTreasure1I || i == mapConstant.falseTreasure2I
                                        || i == mapConstant.falseTreasure3I){
            if(i == mapConstant.realTreasureI)
                enemyTreasureValue = 1;
            else enemyTreasureValue = 2;
            connect.setWriteLine(mapConstant.onTreasure);
        }
        threadSleep(500);


    }

    public void gameBeginFlag(){
        Random rand = new Random();
        course = rand.nextBoolean();
        c = Calendar.getInstance();
        time = new String(c.get(c.HOUR) +":"+c.get(c.MINUTE) +":"+c.get(c.SECOND));
        processText = "начинает первый ";
        if(course ==  true){
            processLabel.setText("Ваш ход");
            dataTable.add(new TableText(playerName,processText,time));
        }
        if(course == false){
            processLabel.setText("Ход соперника");
            dataTable.add(new TableText(enemyName,processText,time));
        }
        process = mapConstant.game;

        connect.setWriteLine(Boolean.toString(course));
        threadSleep(200);

    }

    public int[] toBinary(int number){
        String binaryString = Integer.toBinaryString(number);
        int[] binaryNumber = new int[4];
        int i=0;
        for(i=0;i<4-binaryString.length();i++){
            binaryNumber[i]=0;
        }
        for(int j = 0; j<binaryString.length(); i++,j++){
            if(binaryString.charAt(j)=='0')
                binaryNumber[i] = 0;
            else
                binaryNumber[i] = 1;
        }
        return binaryNumber;
    }
    public int toDec(int[] binary){
        int retValue=0;
        for(int i=0;i<4;i++){
            retValue+=binary[i]*(int)Math.pow(2,3-i);
        }
        return retValue;
    }

    public void changeCourse(){
        if(course == false) {
            processLabel.setText("Ваш ход");
            course = true;
        }
        else {
            if(!playerTrapFlag)
                courseNumbers = courseAll;
            else playerTrapFlag = false;
            processLabel.setText("Ход соперника");
            course  = false;
        }
    }
    public void readyButtonClicked() {
       if(process == mapConstant.ready) {
           c = Calendar.getInstance();
           time = new String(c.get(c.HOUR) + ":" + c.get(c.MINUTE) + ":" + c.get(c.SECOND));
           processText = "готов к игре";
           dataTable.add(new TableText(playerName, processText, time));
           readyAll++;
           if (readyAll == 2) {
               processLabel.setText("Выбор стартовых клеток игроками");
               process = mapConstant.beginCoords;
           }
           connect.setWriteLine(playerName);
           threadSleep(200);
           return;
       }
       if(process == mapConstant.beginCoords){
           c = Calendar.getInstance();
           time = new String(c.get(c.HOUR) + ":" + c.get(c.MINUTE) + ":" + c.get(c.SECOND));
           processText = ("выбрал стартовую клетку : "+ coords.getRow()+"-"+ coords.getColumn());
           dataTable.add(new TableText(playerName, processText, time));
           readyAll++;
           connect.setWriteLine((Integer.toString(coords.getRow())+Integer.toString(coords.getColumn())));
           threadSleep(200);
           if (readyAll == 4) {
               process = mapConstant.course;
               if(connectSelection == 0 )
                   gameBeginFlag();
           }
           return;
       }
       if(process == mapConstant.game){
           if(course == true) {
               connect.setWriteLine(sendMessage);
               if(grenadeFlag) {
                   grenadeFlag = false;
                   disableGrenadeImages();
               }
           }
           return;
       }

    }
    public void treasureDialog(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Labyrinth");
        alert.setHeaderText("Вы нашли один из кладов");
        alert.setContentText("Забрать клад?");
        ButtonType buttonTypeYes = new ButtonType("ДА", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("НЕТ", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeNo){
            connect.setWriteLine(mapConstant.pickTreasure);
        } else if (result.get() == buttonTypeYes) {
        }
        treasureFlag = false;
    }

    public void gameEndDialog(int result){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Labyrinth");
        if(result == 0) {
            alert.setHeaderText("Поздравляем!");
            alert.setContentText("Вы выиграли.");
        }
        else{
            alert.setHeaderText("Жаль конечно:(");
            alert.setContentText("Но вы проиграли.");
        }
    }

    public void threadSleep(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void setPlayerName(String name){
        playerName = new String(name);
    }
    public void keyPressed(KeyEvent e) {
        if( e.getCode().toString() == "UP" && coords.getColumn()>0 )
            coords.setColumn(coords.getColumn()-1);
        if( e.getCode().toString() == "DOWN" && coords.getColumn()<9 )
            coords.setColumn(coords.getColumn()+1);
        if( e.getCode().toString() == "LEFT" && coords.getRow()>0 )
            coords.setRow(coords.getRow()-1);
        if( e.getCode().toString() == "RIGHT" && coords.getRow()<9 )
            coords.setRow(coords.getRow()+1);
        GridPane.setConstraints(currentImage, coords.getRow(), coords.getColumn());
    }

}
