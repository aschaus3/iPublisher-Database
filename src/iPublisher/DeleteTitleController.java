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

public class DeleteTitleController implements Initializable
{
    @FXML
    Button cancelBtn;
    @FXML
    Button updateBtn;
    @FXML
    Button deleteBtn;
    @FXML
    ComboBox<String> titles;
    @FXML
    TextField updateName;

    final ObservableList<String> data = FXCollections.observableArrayList();
    private TitleAdaptor titleAdaptor;

    public void setModel(TitleAdaptor title) { //Called in main controller
        titleAdaptor = title;
        buildComboBoxData();
    }

    @FXML
    public void cancel() { //Controls the cancel button
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    public void buildComboBoxData() { //adds all the titles to the combo box
        try {
            data.addAll(titleAdaptor.getTitleList());
        } catch (SQLException ex) {

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) { titles.setItems(data); }

    @FXML
    public void update() { //Updates a specifics titles name
        try {
            int index = (titles.getValue()).indexOf("-");
            String sub;
            if(index != -1)
            {
                sub = titles.getValue().substring(0,index);
            }
            else
                sub = "";
            int idNum = Integer.parseInt(sub); //two digit ID
            String name = updateName.getText();
            titleAdaptor.updateTitle(idNum, name);

        } catch (SQLException ex) {

        }

        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    public void delete() //Deletes a specific title
    {
        try {
            int index = (titles.getValue()).indexOf("-");
            String sub;
            if(index != -1)
            {
                sub = titles.getValue().substring(0,index);
            }
            else
                sub = "";
            int idNum = Integer.parseInt(sub); //two digit ID
            titleAdaptor.deleteTitle(idNum);

        } catch (SQLException ex) {
            //displayAlert("ERROR: " + ex.getMessage());
        }

        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

}
