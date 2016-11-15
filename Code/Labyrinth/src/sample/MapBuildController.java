package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class MapBuildController {

    private Constants mapConstant;
    ImageView[][]Images;
    ImageView[][]currentImages;
    ImageView[] trapBonusImages;
    Coords[] trapBonus;
    int [][]labyrinth;
    ImageView timeImageView;
    ImageView currentImage;
    Coords coords;
    boolean spriteFlag;
    @FXML
    Spinner spinnerX,spinnerY;
    @FXML
    ComboBox connectComboBox;
    @FXML
    GridPane mapPane;
    @FXML
    ImageView spriteWallDown, spriteWallDownLeft, spriteWallDownLeftRight, spriteWallDownRight, spriteWallDownUpLeft, spriteWallDownUpRight, spriteWallLeft,
              spriteWallLeftRight, spriteWallNone, spriteWallRight, spriteWallUp, spriteWallUpDown, spriteWallUpLeft, spriteWallUpLeftRight, spriteUpRight;
    GridPane spritePane;
    @FXML
    ImageView spriteHole1;
    @FXML
    public void initialize(){
        SpinnerValueFactory<Integer> valueFactoryX = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        SpinnerValueFactory<Integer> valueFactoryY = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        spinnerX.setValueFactory(valueFactoryX);
        spinnerY.setValueFactory(valueFactoryY);
        Images = new ImageView[10][10];
        currentImages = new ImageView[10][10];
        labyrinth = new int[10][10];
        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++){
                Images[i][j] = new ImageView(
                );
                currentImages[i][j] = new ImageView();
                currentImages[i][j].setImage(new Image(mapConstant.blue));
                currentImages[i][j].setVisible(false);
                Images[i][j].setVisible(true);
                Images[i][j].setFitHeight(40);
                Images[i][j].setFitWidth(40);
                mapPane.add(Images[i][j],i,j);
                mapPane.add(currentImages[i][j],i,j);
            }

        currentImages[0][0].setVisible(true);
        coords = new Coords(0,0);
        spinnerX.valueProperty().addListener(new ChangeListener<Integer>() {
            public void changed(ObservableValue<? extends Integer> observable,//
                                Integer oldValue, Integer newValue) {
                currentImages[coords.getX()][coords.getY()].setVisible(false);
                coords.setY(Integer.valueOf(spinnerX.getValue().toString())-1);;
                currentImages[coords.getX()][coords.getY()].setVisible(true);
            }
        });
        spinnerY.valueProperty().addListener(new ChangeListener<Integer>() {
            public void changed(ObservableValue<? extends Integer> observable,//
                                Integer oldValue, Integer newValue) {
                currentImages[coords.getX()][coords.getY()].setVisible(false);
                coords.setX(Integer.valueOf(spinnerY.getValue().toString())-1);
                currentImages[coords.getX()][coords.getY()].setVisible(true);
            }
        });
        trapBonus = new Coords[15];
        trapBonusImages  = new ImageView[15];
        for(int i=0;i<15;i++) {
            trapBonus[i] = new Coords();
            trapBonusImages[i] = new ImageView(new Image(mapConstant.trapBonusImagesUrl[i],40,40,false,false));
            mapPane.getChildren().add(trapBonusImages[i]);
            trapBonusImages[i].setVisible(false);
        }
    }
    public void startButtonClicked(){
        try {
            new GameWindow();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void spriteHole1Clicked(){
        GridPane.setConstraints(trapBonusImages[mapConstant.hole1I-1],coords.getX(),coords.getY());
        trapBonusImages[mapConstant.hole1I-1].setVisible(true);
        trapBonus[mapConstant.hole1I-1].setXY(coords.getX(),coords.getY());
    }
    public void spriteHole2Clicked(){
        GridPane.setConstraints(trapBonusImages[mapConstant.hole2I-1],coords.getX(),coords.getY());
        trapBonusImages[mapConstant.hole2I-1].setVisible(true);
        trapBonus[mapConstant.hole2I-1].setXY(coords.getX(),coords.getY());
    }
    public void spriteHole3Clicked(){
        GridPane.setConstraints(trapBonusImages[mapConstant.hole3I-1],coords.getX(),coords.getY());
        trapBonusImages[mapConstant.hole3I-1].setVisible(true);
        trapBonus[mapConstant.hole3I-1].setXY(coords.getX(),coords.getY());
    }
    public void spriteHole4Clicked(){
        GridPane.setConstraints(trapBonusImages[mapConstant.hole4I-1],coords.getX(),coords.getY());
        trapBonusImages[mapConstant.hole4I-1].setVisible(true);
        trapBonus[mapConstant.hole4I-1].setXY(coords.getX(),coords.getY());
    }
    public void spriteDoor1Clicked(){
        GridPane.setConstraints(trapBonusImages[mapConstant.door1I-1],coords.getX(),coords.getY());
        trapBonusImages[mapConstant.door1I-1].setVisible(true);
        trapBonus[mapConstant.door1I-1].setXY(coords.getX(),coords.getY());
    }
    public void spriteDoor2Clicked(){
        GridPane.setConstraints(trapBonusImages[mapConstant.door2I-1],coords.getX(),coords.getY());
        trapBonusImages[mapConstant.door2I-1].setVisible(true);
        trapBonus[mapConstant.door2I-1].setXY(coords.getX(),coords.getY());
    }
    public void spriteDoor3Clicked(){
        GridPane.setConstraints(trapBonusImages[mapConstant.door3I-1],coords.getX(),coords.getY());
        trapBonusImages[mapConstant.door3I-1].setVisible(true);
        trapBonus[mapConstant.door3I-1].setXY(coords.getX(),coords.getY());
    }
    public void spriteDoor4Clicked(){
        GridPane.setConstraints(trapBonusImages[mapConstant.door4I-1],coords.getX(),coords.getY());
        trapBonusImages[mapConstant.door4I-1].setVisible(true);
        trapBonus[mapConstant.door4I-1].setXY(coords.getX(),coords.getY());
    }
    public void spriteTrapClicked(){
        GridPane.setConstraints(trapBonusImages[mapConstant.trapI-1],coords.getX(),coords.getY());
        trapBonusImages[mapConstant.trapI-1].setVisible(true);
        trapBonus[mapConstant.trapI].setXY(coords.getX(),coords.getY());
    }
    public void spriteCrossbowClicked(){
        GridPane.setConstraints(trapBonusImages[mapConstant.crossbowI-1],coords.getX(),coords.getY());
        trapBonusImages[mapConstant.crossbowI-1].setVisible(true);
        trapBonus[mapConstant.crossbowI-1].setXY(coords.getX(),coords.getY());
    }
    public void spriteCrutchClicked(){
        GridPane.setConstraints(trapBonusImages[mapConstant.crutchI-1],coords.getX(),coords.getY());
        trapBonusImages[mapConstant.crutchI-1].setVisible(true);
        trapBonus[mapConstant.crutchI-1].setXY(coords.getX(),coords.getY());
    }
    public void spriteRealTreasureClicked(){
        GridPane.setConstraints(trapBonusImages[mapConstant.realTreasureI-1],coords.getX(),coords.getY());
        trapBonusImages[mapConstant.realTreasureI-1].setVisible(true);
        trapBonus[mapConstant.realTreasureI-1].setXY(coords.getX(),coords.getY());
    }
    public void spriteFalseTreasure1Clicked(){
        GridPane.setConstraints(trapBonusImages[mapConstant.falseTreasure1I-1],coords.getX(),coords.getY());
        trapBonusImages[mapConstant.falseTreasure1I-1].setVisible(true);
        trapBonus[mapConstant.falseTreasure1I-1].setXY(coords.getX(),coords.getY());
    }
    public void spriteFalseTreasure2Clicked(){
        GridPane.setConstraints(trapBonusImages[mapConstant.falseTreasure2I-1],coords.getX(),coords.getY());
        trapBonusImages[mapConstant.falseTreasure2I-1].setVisible(true);
        trapBonus[mapConstant.falseTreasure2I-1].setXY(coords.getX(),coords.getY());
    }
    public void spriteFalseTreasure3Clicked(){
        GridPane.setConstraints(trapBonusImages[mapConstant.falseTreasure3I-1],coords.getX(),coords.getY());
        trapBonusImages[mapConstant.falseTreasure3I-1].setVisible(true);
        trapBonus[mapConstant.falseTreasure3I-1].setXY(coords.getX(),coords.getY());
    }

    public void spriteWallDownClicked(){
        Images[coords.getX()][coords.getY()].setImage(new Image(mapConstant.wallDown));
        labyrinth[coords.getX()][coords.getY()] = mapConstant.wallDownI;
    }
    public void spriteWallDownLeftClicked(){
        Images[coords.getX()][coords.getY()].setImage(new Image(mapConstant.wallDownLeft));
        labyrinth[coords.getX()][coords.getY()] = mapConstant.wallDownLeftI;
    }
    public void spriteWallDownLeftRightClicked(){
        Images[coords.getX()][coords.getY()].setImage(new Image(mapConstant.wallDownLeftRight));
        labyrinth[coords.getX()][coords.getY()] = mapConstant.wallDownLeftRightI;
    }
    public void spriteWallDownRightClicked(){
        Images[coords.getX()][coords.getY()].setImage(new Image(mapConstant.wallDownRight));
        labyrinth[coords.getX()][coords.getY()] = mapConstant.wallDownRightI;
    }
    public void spriteWallDownUpLeftClicked(){
        Images[coords.getX()][coords.getY()].setImage(new Image(mapConstant.wallDownUpLeft));
        labyrinth[coords.getX()][coords.getY()] = mapConstant.wallDownUpLeftI;
    }
    public void spriteWallDownUpRightClicked(){
        Images[coords.getX()][coords.getY()].setImage(new Image(mapConstant.wallDownUpRight));
        labyrinth[coords.getX()][coords.getY()] = mapConstant.wallDownUpRightI;
    }
    public void spriteWallLeftClicked(){
        Images[coords.getX()][coords.getY()].setImage(new Image(mapConstant.wallLeft));
        labyrinth[coords.getX()][coords.getY()] = mapConstant.wallLeftI;
    }
    public void spriteWallLeftRightClicked(){
        Images[coords.getX()][coords.getY()].setImage(new Image(mapConstant.wallLeftRight));
        labyrinth[coords.getX()][coords.getY()] = mapConstant.wallLeftRightI;
    }
    public void spriteWallNoneClicked(){
        Images[coords.getX()][coords.getY()].setImage(new Image(mapConstant.wallNone));
        labyrinth[coords.getX()][coords.getY()] = mapConstant.wallNoneI;
    }
    public void spriteWallRightClicked(){
        Images[coords.getX()][coords.getY()].setImage(new Image(mapConstant.wallRight));
        labyrinth[coords.getX()][coords.getY()] = mapConstant.wallRightI;
    }
    public void spriteWallUpClicked(){
        Images[coords.getX()][coords.getY()].setImage(new Image(mapConstant.wallUp));
        labyrinth[coords.getX()][coords.getY()] = mapConstant.wallUpI;
    }
    public void spriteWallUpDownClicked(){
        Images[coords.getX()][coords.getY()].setImage(new Image(mapConstant.wallUpDown));
        labyrinth[coords.getX()][coords.getY()] = mapConstant.wallUpDownI;
    }
    public void spriteWallUpLeftClicked(){
        Images[coords.getX()][coords.getY()].setImage(new Image(mapConstant.wallUpLeft));
        labyrinth[coords.getX()][coords.getY()] = mapConstant.wallUpLeftI;
    }
    public void spriteWallUpLeftRightClicked(){
        Images[coords.getX()][coords.getY()].setImage(new Image(mapConstant.wallUpLeftRight));
        labyrinth[coords.getX()][coords.getY()] = mapConstant.wallUpLeftRightI;
    }
    public void spriteUpRightClicked(){
        Images[coords.getX()][coords.getY()].setImage(new Image(mapConstant.wallUpRight));
        labyrinth[coords.getX()][coords.getY()] = mapConstant.wallUpRightI;
    }
}
