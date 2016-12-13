package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.Arrays;
import java.util.Optional;

public class MapBuildController {

    private Constants mapConstant;
    private int connectSelection;
    private GameController children;
    private ImageView[] trapBonusImages;
    private Coordinates[] trapBonus;
    private int [][]labyrinth;
    private ImageView currentImage;
    private Coordinates Coordinates;
    @FXML
    Button serverButton, clientButton;
    @FXML
    GridPane mapPane;
    @FXML
    GridPane wallPane;
    @FXML
    GridPane trapBonusPane;
    @FXML
    TextField nameTextField;
    @FXML
    public void initialize(){
        currentImage = new ImageView(new Image(mapConstant.blue));
        mapPane.add(currentImage,0,0);
        Coordinates = new Coordinates(0,0);

        labyrinth = new int[10][10];
        for(int i=0;i<10;i++)
            Arrays.fill(labyrinth[i],15);


        BufferedReader myfile = null;
        try {
            myfile = new BufferedReader (new FileReader("map.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            for(int j=0;j<10;j++)
            for(int i=0;i<10;i++){
                    labyrinth[i][j]=Integer.valueOf(myfile.readLine());
                ImageView mapPaneImage  = (ImageView)mapPane.getChildren().get(j*10+i);
                mapPaneImage.setImage(new Image(mapConstant.wallImagesUrl[labyrinth[i][j]]));
            }

            trapBonus = new Coordinates[15];
            trapBonusImages  = new ImageView[15];
            for(int i=0;i<15;i++){
                trapBonus[i] = new Coordinates(Integer.valueOf(myfile.readLine()),Integer.valueOf(myfile.readLine()));
                trapBonusImages[i] = new ImageView(new Image(mapConstant.trapBonusImagesUrl[i],40,40,false,false));
                trapBonusImages[i].setVisible(true);
                mapPane.add(trapBonusImages[i],trapBonus[i].getRow(),trapBonus[i].getColumn());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

/*
        trapBonus = new Coordinates[15];
        trapBonusImages  = new ImageView[15];
        for(int i=0;i<15;i++) {
            trapBonus[i] = new Coordinates(15,15);
            trapBonusImages[i] = new ImageView(new Image(mapConstant.trapBonusImagesUrl[i],40,40,false,false));
            trapBonusImages[i].setVisible(false);
        }
*/



    }

    public void mapClicked(MouseEvent e) {
        Node source = (Node) e.getSource();
        if (mapPane.getRowIndex(source) != null)
            Coordinates.setColumn(mapPane.getRowIndex(source));
        else Coordinates.setColumn(0);
        if (mapPane.getColumnIndex(source) != null)
            Coordinates.setRow(mapPane.getColumnIndex(source));
        else Coordinates.setRow(0);
        GridPane.setConstraints(currentImage, Coordinates.getRow(), Coordinates.getColumn());
    }
    public void spriteWallClicked(MouseEvent event){
        int row = 0;
        int column = 0;
        Node source = (Node)event.getSource() ;
        if(wallPane.getRowIndex(source)!= null)
            row = wallPane.getRowIndex(source);
        if(wallPane.getColumnIndex(source)!= null)
            column = wallPane.getColumnIndex(source);

        ImageView mapPaneImage  = (ImageView)mapPane.getChildren().get(Coordinates.getColumn()*10+Coordinates.getRow());
        mapPaneImage.setImage(new Image(mapConstant.wallImagesUrl[row*4 + column]));
        labyrinth[Coordinates.getRow()][Coordinates.getColumn()] = row*4 + column;

    }
    public void spriteTrapBonusClicked(MouseEvent event){
        int row = 0;
        int column = 0;
        Node source = (Node)event.getSource() ;
        if(trapBonusPane.getRowIndex(source)!= null)
            row = trapBonusPane.getRowIndex(source);
        if(trapBonusPane.getColumnIndex(source)!= null)
            column = trapBonusPane.getColumnIndex(source);
        GridPane.setConstraints(trapBonusImages[row*4+column],Coordinates.getRow(),Coordinates.getColumn());
        trapBonusImages[row*4+column].setVisible(true);
        trapBonus[row*4+column].setRowColumn(Coordinates.getRow(),Coordinates.getColumn());

    }
    public void keyPressed(KeyEvent e) {
        if( e.getCode().toString() == "UP" && Coordinates.getColumn()>0 )
            Coordinates.setColumn(Coordinates.getColumn()-1);
        if( e.getCode().toString() == "DOWN" && Coordinates.getColumn()<9 )
            Coordinates.setColumn(Coordinates.getColumn()+1);
        if( e.getCode().toString() == "LEFT" && Coordinates.getRow()>0 )
            Coordinates.setRow(Coordinates.getRow()-1);
        if( e.getCode().toString() == "RIGHT" && Coordinates.getRow()<9 )
            Coordinates.setRow(Coordinates.getRow()+1);
        GridPane.setConstraints(currentImage, Coordinates.getRow(), Coordinates.getColumn());
    }

    public void startButtonClicked(){

        String example;
        example = mapExample();
        if(example.equals("ok") == false){
            exampleDialog(example);
            return;
        }
        try {
            children = new GameController();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("game.fxml"));
            Parent root =(Parent) fxmlLoader.load();
            children = fxmlLoader.getController();
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Game");
            primaryStage.setScene(new Scene(root, 1300, 650));
            primaryStage.show();
            mapExample();
            if(nameTextField.getLength() == 0)
                children.setPlayerName("Unnamed");
            else children.setPlayerName(nameTextField.getText());
            children.setMap(new MapFacade(labyrinth,trapBonus),connectSelection);
         }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void serverButtonClicked(){
        serverButton.setDisable(true);
        clientButton.setDisable(false);
        connectSelection = 0;
    }
    public void clientButtonClicked(){
        clientButton.setDisable(true);
        serverButton.setDisable(false);
        connectSelection = 1;
    }

    public String mapExample(){
        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++) {
                if (labyrinth[i][j] == 15) {
                    return "No all labyrinth fill";
                }
            }

        for(int i = 0;i<15;i++){
            if(trapBonus[i].getRow()==15 || trapBonus[i].getColumn()==15)
                return "TrapBonus no all";
            for(int j = 0; j <15;j++){
                if(j!=i && trapBonus[i].getRow() == trapBonus[j].getRow() && trapBonus[i].getColumn() == trapBonus[j].getColumn())
                    return ("TrapBonus in equals position "+trapBonus[i].getRow()+":"+trapBonus[j].getColumn());
            }

        }
        int[] exitNumber = {0,0,0,0};
        int[] currentValue = new int[4];
        int[] nextValue = new int[4];

        for(int j=0;j<10;j++)
            for(int i=0;i<10;i++)
            {
                currentValue = toBinary(labyrinth[i][j]);
                if (i == 0 && currentValue[mapConstant.left] == 0)
                    exitNumber[mapConstant.left]++;
                if (i == 9 && currentValue[mapConstant.right] == 0)
                    exitNumber[mapConstant.right]++;
                if (j == 0 && currentValue[mapConstant.up] == 0)
                    exitNumber[mapConstant.up]++;
                if (j == 9 && currentValue[mapConstant.down] == 0)
                    exitNumber[mapConstant.down]++;
                if(j != 9 ) {
                    nextValue = toBinary(labyrinth[i][j + 1]);
                    if (nextValue[mapConstant.up] != currentValue[mapConstant.down])
                        return (i +":"+j + " downWall not good ");
                }
                if(i != 9 ){
                    nextValue = toBinary(labyrinth[i+1][j]);
                if(nextValue[mapConstant.left]!= currentValue[mapConstant.right])
                     return (i +":"+j + " rightWall not good  ");
                }
            }
        if(exitNumber[mapConstant.left]!=1)
           return "leftWall exit problem";
        if(exitNumber[mapConstant.right]!=1)
            return "rightWall exit problem";
        if(exitNumber[mapConstant.up]!=1)
            return "upWall exit problem";
        if(exitNumber[mapConstant.down]!=1)
            return "downWall exit problem\n";
        return "ok";
    }
    public void testButtonClicked(){
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
    public void exampleDialog(String error){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Ошибка создания карты");
        alert.setHeaderText("Карта заполнена некорректно");
        alert.setContentText(error);
        ButtonType buttonTypeCancel = new ButtonType("OK", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeCancel){
        } }

}
