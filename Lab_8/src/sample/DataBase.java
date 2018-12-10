package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DataBase {
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    DataBase()
    {
    }

    public ObservableList<Book> getData(String query){

        connect();
        statementCreation();

        ObservableList<Book> listaKsiazek = FXCollections.observableArrayList();
        try {
            resultSet = statement.executeQuery(query);
            while(resultSet.next())
            {
                listaKsiazek.add(new Book(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4)));
            }
        }
        catch (SQLException e) {}
        finally
        {
            closeAll();
        }
        return listaKsiazek;
    }

    public void addBook(Book book)
    {
        connect();
        statementCreation();

        String query ="INSERT INTO books VALUES('"+book.getIsbn()+"','" +book.getTitle()+"','" +book.getAuthor()+"','"+ book.getYear()+"')";

        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {}

        closeAll();
    }

    public void deleteBook(String query)
    {
        connect();
        statementCreation();

        try {
            statement.executeUpdate(query);
        } catch (SQLException e){}

        closeAll();
    }

    public boolean isConnected()
    {
        return connection != null;
    }

    private void connect()
    {
        byte tries = 0;
        while(tries++<3) {
            try
            {
                connection = DriverManager.getConnection("jdbc:mysql://mysql.agh.edu.pl/nogaigor", "nogaigor", "rncTHsxhfspmKetH");
                tries=3;
            }
            catch (SQLException ex)
            {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
            finally
            {
                if(connection==null)
                {
                    ++tries;
                }
                if(connection==null && tries==3)
                {
                    System.out.println("Brak połączenia z bazą spróbuj jeszcze raz");
                }
            }
        }
    }

    private void statementCreation()
    {
        if(isConnected())
        {
            try {
                statement = connection.createStatement();
            } catch (SQLException e) {}
        }
    }

    private void closeAll()
    {
        if(resultSet!=null)
        {
            try
            {
                resultSet.close();
            }
            catch (SQLException e) {}
        }
        resultSet = null;

        if(statement!=null)
        {
            try
            {
                statement.close();
            }
            catch (SQLException e) {}
        }
        statement = null;

        if(connection != null)
        {
            try
            {
                connection.close();
            }
            catch (SQLException e) {}
        }
        connection = null;
    }



}
