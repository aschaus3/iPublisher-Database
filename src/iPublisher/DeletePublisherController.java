package iPublisher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DeletePublisherController implements Initializable
{
    @FXML
    Button cancelBtn;
    @FXML
    Button updateBtn;
    @FXML
    Button deleteBtn;
    @FXML
    ComboBox<String> publisherName;
    @FXML
    TextField updateName;
    @FXML
    TextField updateAddress;

    final ObservableList<String> data = FXCollections.observableArrayList();

    private PublisherUnitAdapter publisherUnitAdapter;

    public void setModel(PublisherUnitAdapter publisher) { //Called in main controller
        publisherUnitAdapter = publisher;
        buildComboBoxData();
    }

    @FXML
    public void cancel() { //Controls the cancel button
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void update() { //Updates a specific PublisherUnits name, address
        try {
            int index = (publisherName.getValue()).indexOf("-");
            String sub;
            if(index != -1)
            {
                sub = publisherName.getValue().substring(0,index);
            }
            else
                sub = "";
            int idNum = Integer.parseInt(sub);
            String name = updateName.getText();
            String address = updateAddress.getText();
            publisherUnitAdapter.updatePublisherUnit(idNum, name, address);

        } catch (SQLException ex) {
            //displayAlert("ERROR: " + ex.getMessage());
        }

        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    public void delete() //Deletes a specific Publisher Unit
    {
        try {
            int index = (publisherName.getValue()).indexOf("-");
            String sub;
            if(index != -1)
            {
                sub = publisherName.getValue().substring(0,index);
            }
            else
                sub = "";
            int idNum = Integer.parseInt(sub); //two digit ID
            publisherUnitAdapter.deletePublisherUnit(idNum);

        } catch (SQLException ex) {
            //displayAlert("ERROR: " + ex.getMessage());
        }

        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        publisherName.setItems(data);
    }

    public void buildComboBoxData() { //Adds all Publisher Units to the combo box
        try {
            data.addAll(publisherUnitAdapter.getPublisherUnitList());
        } catch (SQLException ex) {

        }
    }

}
