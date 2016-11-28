package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Arrays;

public class MapBuildController {

    private Constants mapConstant;
    private int connectSelection;
    private GameController children;
    private ImageView[] trapBonusImages;
    private Coords[] trapBonus;
    private int [][]labyrinth;
    private ImageView currentImage;
    private Coords coords;
    @FXML
    Button serverButton, clientButton;
    @FXML
    GridPane mapPane;
    @FXML
    GridPane wallPane;
    @FXML
    GridPane trapBonusPane;

    @FXML
    public void initialize(){
        currentImage = new ImageView(new Image(mapConstant.blue));
        mapPane.add(currentImage,0,0);
        coords = new Coords(0,0);

        labyrinth = new int[10][10];
        for(int i=0;i<10;i++)
            Arrays.fill(labyrinth[i],15);

        trapBonus = new Coords[15];
        trapBonusImages  = new ImageView[15];
        for(int i=0;i<15;i++) {
            trapBonus[i] = new Coords(15,15);
            trapBonusImages[i] = new ImageView(new Image(mapConstant.trapBonusImagesUrl[i],40,40,false,false));
            mapPane.getChildren().add(trapBonusImages[i]);
            trapBonusImages[i].setVisible(false);
        }


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
    public void keyPressed(KeyEvent e) {
        if( e.getCode().toString() == "UP" && coords.getY()>0 )
            coords.setY(coords.getY()-1);
        if( e.getCode().toString() == "DOWN" && coords.getY()<9 )
            coords.setY(coords.getY()+1);
        if( e.getCode().toString() == "LEFT" && coords.getX()>0 )
            coords.setX(coords.getX()-1);
        if( e.getCode().toString() == "RIGHT" && coords.getX()<9 )
            coords.setX(coords.getX()+1);
        GridPane.setConstraints(currentImage, coords.getX(), coords.getY());
    }

    public void startButtonClicked(){
      /*
        String example;
        example = mapExample();
        if(example.equals("ok") == false){
            System.out.println(example);
            return;
        }
        */
        try {
            children = new GameController();
            //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("connect.fxml"));
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("game.fxml"));
            Parent root =(Parent) fxmlLoader.load();
            children = fxmlLoader.getController();
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Game");
            //primaryStage.setScene(new Scene(root, 250, 380));
            primaryStage.setScene(new Scene(root, 1300, 650));
            primaryStage.show();
            mapExample();
            children.setMap(new MapFacade(labyrinth,trapBonus),connectSelection);
            //  System.out.println(children);
            //  children.test();
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
            if(trapBonus[i].getX()==15 || trapBonus[i].getY()==15)
                return "TrapBonus no all";
            for(int j = 0; j <15;j++){
                if(j!=i && trapBonus[i].getX() == trapBonus[j].getX() && trapBonus[i].getY() == trapBonus[j].getY())
                    return ("TrapBonus in equals position "+trapBonus[i].getX()+":"+trapBonus[j].getY());
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

}
