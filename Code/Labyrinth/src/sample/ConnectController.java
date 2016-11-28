package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

/**
 * Created by GREEN on 27.11.2016.
 */
public class ConnectController {
    private ObservableList<pcName> pcNameData = FXCollections.observableArrayList();
    private Connect connect;
    @FXML
    TableView<pcName> table;
    @FXML
    TableColumn<pcName,String> pcNameColumn;
    Button but;
    @FXML
    public void initialize(){
        pcNameColumn.setCellValueFactory(new PropertyValueFactory<pcName, String>("name"));
        table.setItems(pcNameData);
    }
    public void setMap(){
            connect = new Connect(new ServerConnect());
        try {
            connect.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void click(){
        pcNameData.add(new pcName("Ура"));
    }
}
