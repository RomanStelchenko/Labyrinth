package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by GREEN on 15.11.2016.
 */
public class GameWindow {
    public GameWindow() throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("game.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Game");
        primaryStage.setScene(new Scene(root, 1300, 650));
        primaryStage.show();
    }
}
