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

public class DeleteAuthorController implements Initializable
{
    @FXML
    Button cancelBtn;
    @FXML
    Button updateBtn;
    @FXML
    Button deleteBtn;
    @FXML
    ComboBox<String> authors;
    @FXML
    TextField updateName;

    final ObservableList<String> data = FXCollections.observableArrayList();
    private AuthorAdapter authorAdapter;

    public void setModel(AuthorAdapter author) { //Called in main controller
        authorAdapter = author;
        buildComboBoxData();
    }

    @FXML
    public void cancel() { //Controls cancel button
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    public void buildComboBoxData() { //Responsible for adding Author data to the comboBox
        try {
            data.addAll(authorAdapter.getAuthorList());
        } catch (SQLException ex) {

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        authors.setItems(data);
    }

    public void delete() //Deletes a specific author from the data table
    {
        try {
            int index = (authors.getValue()).indexOf("-");
            String sub;
            if(index != -1)
            {
                sub = authors.getValue().substring(0,index);
            }
            else
                sub = "";
            int idNum = Integer.parseInt(sub); //two digit ID
            authorAdapter.deleteAuthor(idNum);

        } catch (SQLException ex) {
            //displayAlert("ERROR: " + ex.getMessage());
        }

        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void update() { //Updates a specific authors name
        try {
            int index = (authors.getValue()).indexOf("-");
            String sub;
            if(index != -1)
            {
                sub = authors.getValue().substring(0,index);
            }
            else
                sub = "";
            int idNum = Integer.parseInt(sub);
            String name = updateName.getText();
            authorAdapter.updateAuthor(idNum, name);

        } catch (SQLException ex) {

        }

        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
}
