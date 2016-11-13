package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class MapBuildController {

    Constant constants;
    ImageView[][]Images;
    ImageView currentImage;
    Coords coords;
    boolean spriteFlag;
    @FXML
    Spinner spinnerX,spinnerY;
    @FXML
    GridPane mapPane;
    @FXML
    ImageView spriteWallDown, spriteWallDownLeft, spriteWallDownLeftRight, spriteWallDownRight, spriteWallDownUpLeft, spriteWallDownUpRight, spriteWallLeft,
              spriteWallLeftRight, spriteWallNone, spriteWallRight, spriteWallUp, spriteWallUpDown, spriteWallUpLeft, spriteWallUpLeftRight, spriteUpRight;
    GridPane spritePane;


    @FXML
    public void initialize(){
        SpinnerValueFactory<Integer> valueFactoryX = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        SpinnerValueFactory<Integer> valueFactoryY = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        spinnerX.setValueFactory(valueFactoryX);
        spinnerY.setValueFactory(valueFactoryY);
        spriteFlag = false;
        Images = new ImageView[10][10];
        currentImage = new ImageView(new Image("file:Images/red.png"));
        currentImage.setFitHeight(10);
        currentImage.setFitWidth(10);
        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++){
                Images[i][j] = new ImageView();
               // Images[i][j].setImage(new Image(constants.blue));
                Images[i][j].setVisible(true);
                Images[i][j].setFitHeight(30);
                Images[i][j].setFitWidth(30);
                mapPane.add(Images[i][j],i,j);
            }
        mapPane.add(currentImage,0,0);

        Images[0][0].setVisible(true);
        coords = new Coords(0,0);
        spinnerX.valueProperty().addListener(new ChangeListener<Integer>() {
            public void changed(ObservableValue<? extends Integer> observable,//
                                Integer oldValue, Integer newValue) {
                mapPane.clearConstraints(currentImage);
                coords.setY(Integer.valueOf(spinnerX.getValue().toString())-1);;
                mapPane.add(currentImage,coords.getX(),coords.getY());

            }
        });
        spinnerY.valueProperty().addListener(new ChangeListener<Integer>() {
            public void changed(ObservableValue<? extends Integer> observable,//
                                Integer oldValue, Integer newValue) {
                mapPane.clearConstraints(currentImage);
                coords.setX(Integer.valueOf(spinnerY.getValue().toString())-1);
                mapPane.add(currentImage,coords.getX(),coords.getY());
            }
        });
    }

    public void spriteWallDownClicked(){
        spriteFlag = true;
        Images[coords.getX()][coords.getY()].setImage(new Image(constants.wallDown));
    }
    public void spriteWallDownLeftClicked(){
        spriteFlag = true;
        Images[coords.getX()][coords.getY()].setImage(new Image(constants.wallDownLeft));
    }
    public void spriteWallDownLeftRightClicked(){
        spriteFlag = true;
        Images[coords.getX()][coords.getY()].setImage(new Image(constants.wallDownLeftRight));
    }
    public void spriteWallDownRightClicked(){
        spriteFlag = true;
        Images[coords.getX()][coords.getY()].setImage(new Image(constants.wallDownRight));
    }
    public void spriteWallDownUpLeftClicked(){
        spriteFlag = true;
        Images[coords.getX()][coords.getY()].setImage(new Image(constants.wallDownUpLeft));
    }
    public void spriteWallDownUpRightClicked(){
        spriteFlag = true;
        Images[coords.getX()][coords.getY()].setImage(new Image(constants.wallDownUpRight));
    }
    public void spriteWallLeftClicked(){
        spriteFlag = true;
        Images[coords.getX()][coords.getY()].setImage(new Image(constants.wallLeft));
    }
    public void spriteWallLeftRightClicked(){
        spriteFlag = true;
        Images[coords.getX()][coords.getY()].setImage(new Image(constants.wallLeftRight));
    }
    public void spriteWallNoneClicked(){
        spriteFlag = true;
        Images[coords.getX()][coords.getY()].setImage(new Image(constants.wallNone));
    }
    public void spriteWallRightClicked(){
        spriteFlag = true;
        Images[coords.getX()][coords.getY()].setImage(new Image(constants.wallRight));
    }
    public void spriteWallUpClicked(){
        spriteFlag = true;
        Images[coords.getX()][coords.getY()].setImage(new Image(constants.wallUp));
    }
    public void spriteWallUpDownClicked(){
        spriteFlag = true;
        Images[coords.getX()][coords.getY()].setImage(new Image(constants.wallUpDown));
    }
    public void spriteWallUpLeftClicked(){
        spriteFlag = true;
        Images[coords.getX()][coords.getY()].setImage(new Image(constants.wallUpLeft));
    }
    public void spriteWallUpLeftRightClicked(){
        spriteFlag = true;
        Images[coords.getX()][coords.getY()].setImage(new Image(constants.wallUpLeftRight));
    }
    public void spriteUpRightClicked(){
        spriteFlag = true;
        Images[coords.getX()][coords.getY()].setImage(new Image(constants.wallUpRight));
    }
}
