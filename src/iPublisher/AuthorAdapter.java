package iPublisher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AuthorAdapter
{
    Connection connection;

    public AuthorAdapter(Connection conn, Boolean reset) throws SQLException {
        connection = conn;
        if(reset)
        {
            Statement stm = connection.createStatement();
            try
            {
                stm.execute("DROP TABLE Author");
            }
            catch(SQLException ex)
            {

            }
            finally
            {
                stm.execute("CREATE TABLE Author (" //Creates table
                        + "AuthorName CHAR(15) NOT NULL PRIMARY KEY, " //Adds author name to table
                        + "IDNum INT" + ")"); //Adds author ID to table
            }
        }
    }  //Creates the author Table

    public void insertAuthor(String authorName, int ID) throws SQLException { //Inserts a new Author into the table
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO Author (AuthorName, IDNum) VALUES ('" + authorName + "', " + ID + ")");
    }

    public ObservableList<String> getAuthorList() throws SQLException { //Gets a List of Strings containing all authors
        ObservableList<String> list = FXCollections.observableArrayList();
        ResultSet rs;

        // Create a Statement object
        Statement stmt = connection.createStatement();

        String sqlStatement = "SELECT * FROM Author";
        rs = stmt.executeQuery(sqlStatement);

        while (rs.next()) {
            list.add(rs.getInt("IDNum") + "-" + rs.getString("AuthorName"));
        }
        return list;
    }

    public void updateAuthor(int id, String name) throws SQLException //Updates a specific authors name
    {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("UPDATE Author SET AuthorName ='" + name + "' WHERE IDNum =" + id);
    }

    public void deleteAuthor(int id) throws SQLException //deletes a specific author
    {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DELETE FROM Author WHERE IDNum =" + id);
    }

}
