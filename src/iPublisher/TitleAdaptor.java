package iPublisher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TitleAdaptor
{
    Connection connection;

    public TitleAdaptor(Connection conn, boolean reset) throws SQLException
    {
        connection = conn;
        if(reset)
        {
            Statement stmt = connection.createStatement();
            try
            {
                stmt.execute("DROP TABLE Title");
            }
            catch(SQLException ex)
            {

            }
            finally
            {
                stmt.execute("CREATE TABLE Title (" //Creates table
                        + "Name CHAR(15) NOT NULL PRIMARY KEY, " //adds Name
                        + "ID INT" + ")"); //adds ID
            }
        }
    }

    public void insertTitle(String titleName, int ID) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO Title (Name, ID) VALUES ('" + titleName + "', " + ID + ")");
    }

    public ObservableList<String> getTitleList() throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();
        ResultSet rs;

        Statement stm = connection.createStatement();
        rs = stm.executeQuery("SELECT * FROM Title");

        while(rs.next())
        {
            list.add(rs.getInt("ID") + "-" + rs.getString("Name"));
        }

        return list;
    }

    public void updateTitle(int id, String name) throws SQLException
    {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("UPDATE Title SET Name ='" + name + "' WHERE ID =" + id);
    } //Updates a title

    public void deleteTitle(int id) throws SQLException
    {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DELETE FROM Title WHERE ID =" + id);
    } //deletes a Title from the table
}
