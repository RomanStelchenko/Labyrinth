package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by GREEN on 15.11.2016.
 */
public class MapBuildWindow {
    public MapBuildWindow() throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("MapBuild");
        primaryStage.setScene(new Scene(root, 800, 650));
        primaryStage.show();
    }
}
