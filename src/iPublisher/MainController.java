package iPublisher;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Abdelkader
 */
public class MainController implements Initializable {


    private PublisherUnitAdapter publisher;
    private AuthorAdapter author;
    private TitleAdaptor title;
    private Connection conn;

    @FXML
    private MenuBar mainMenu;

    public void showAbout() throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("About.fxml"));
        Parent About = (Parent) fxmlLoader.load();

        Scene scene = new Scene(About);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/iPublisher/WesternLogo.png"));
        stage.setTitle("About Us");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    public void addPublisherUnit() throws Exception { //Loads up the add Publisher Unit page

        publisher = new PublisherUnitAdapter(conn, false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddPublisherUnit.fxml"));
        Parent addPublisherUnit = (Parent) fxmlLoader.load();
        AddPublisherUnitController addPublisherUnitController = (AddPublisherUnitController) fxmlLoader.getController();
        addPublisherUnitController.setModel(publisher);

        Scene scene = new Scene(addPublisherUnit);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/iPublisher/WesternLogo.png"));
        stage.setTitle("Add New Publishing Unit");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }

    @FXML
    public void addAuthor() throws Exception { //Loads up the add author page

        author = new AuthorAdapter(conn, false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddAuthor.fxml"));
        Parent addAuthor = (Parent) fxmlLoader.load();
        AddAuthorController addAuthorController = (AddAuthorController) fxmlLoader.getController();
        addAuthorController.setModel(author);

        Scene scene = new Scene(addAuthor);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/iPublisher/WesternLogo.png"));
        stage.setTitle("Add New Publishing Unit");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }

    @FXML
    public void addTitle() throws Exception {//Loads up the add title page

        title = new TitleAdaptor(conn, false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddTitle.fxml"));
        Parent addTitle = (Parent) fxmlLoader.load();
        AddTitleController addTitleController = (AddTitleController) fxmlLoader.getController();
        addTitleController.setModel(title);

        Scene scene = new Scene(addTitle);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/iPublisher/WesternLogo.png"));
        stage.setTitle("Add New Publishing Unit");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }

    @FXML
    public void deleteAuthor() throws Exception { //Loads up the delete author page

        author = new AuthorAdapter(conn, false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeleteAuthor.fxml"));
        Parent deleteAuthor = (Parent) fxmlLoader.load();
        DeleteAuthorController deleteAuthorController = (DeleteAuthorController) fxmlLoader.getController();
        deleteAuthorController.setModel(author);


        Scene scene = new Scene(deleteAuthor);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/iPublisher/WesternLogo.png"));
        stage.setTitle("Delete author");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }

    @FXML
    public void deletePublisherUnit() throws Exception{ //Loads up the delete Publisher Unit page

        publisher = new PublisherUnitAdapter(conn, false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeletePublisher.fxml"));
        Parent deletePublisher = (Parent) fxmlLoader.load();
        DeletePublisherController deletePublisherController= (DeletePublisherController) fxmlLoader.getController();
        deletePublisherController.setModel(publisher);

        Scene scene = new Scene(deletePublisher);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/iPublisher/WesternLogo.png"));
        stage.setTitle("Delete / Update Publisher");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }

    @FXML
    public void deleteTitle() throws Exception{ //Loads up the delete title page
        title = new TitleAdaptor(conn, false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeleteTitle.fxml"));
        Parent DeleteTitle = (Parent) fxmlLoader.load();
        DeleteTitleController deleteTitleController = (DeleteTitleController) fxmlLoader.getController();
        deleteTitleController.setModel(title);

        Scene scene = new Scene(DeleteTitle);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/iPublisher/WesternLogo.png"));
        stage.setTitle("Delete Title");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();

    }

    public void exit() {

        Stage stage = (Stage) mainMenu.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {

            String DB_URL = "jdbc:derby:TeamDB;create=true";
            conn = DriverManager.getConnection(DB_URL);

        } catch (SQLException ex) {

        }

    }

    @FXML
    public void resetDB() throws SQLException {
        publisher = new PublisherUnitAdapter(conn, true);
        author = new AuthorAdapter(conn, true);
        title = new TitleAdaptor(conn, true);
    } //Resets all the tables, Added this because I was getting an error when I didn't have it
      //Made a reset button in the main menu under file

}

