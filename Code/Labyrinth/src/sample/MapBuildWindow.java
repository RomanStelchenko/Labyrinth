package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by GREEN on 15.11.2016.
 */
public class MapBuildWindow {
    MapBuildController children;
    public MapBuildWindow() throws Exception
    {
        children = new MapBuildController();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root =(Parent) fxmlLoader.load();
        children = fxmlLoader.getController();
        Stage primaryStage = new Stage();
        primaryStage.setTitle("MapBuild");
        primaryStage.setScene(new Scene(root, 800, 650));
        primaryStage.show();
    }
}
