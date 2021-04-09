package iPublisher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddPublisherUnitController implements Initializable
{
    @FXML
    Button cancelBtn;
    @FXML
    Button saveBtn;
    @FXML
    TextField publisherName;
    @FXML
    TextField addressName;
    @FXML
    TextField ID;


    private PublisherUnitAdapter publisherUnitAdapter;

    public void setModel(PublisherUnitAdapter unit) {
        publisherUnitAdapter = unit;
    } //Called in main controller

    @FXML
    public void cancel() { //Controls the cancel button
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void save() throws SQLException { //Controls the save button

        String name = publisherName.getText(); //Gets the name from text box
        String address = addressName.getText(); //Gets the address from text box
        int id = Integer.parseInt(ID.getText()); //Gets the int ID from text box
        publisherUnitAdapter.insertPublisherUnit(name, address, id);


        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO
    }

}
