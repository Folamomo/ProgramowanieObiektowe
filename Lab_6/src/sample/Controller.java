package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class Controller {

    @FXML private TextField textfieldFindByAuthor;
    @FXML private TextField textfieldFindByISBN;

    @FXML private TextField textfieldAddISBN;
    @FXML private TextField textfieldAddTitle;
    @FXML private TextField textfieldAddAuthor;
    @FXML private TextField textfieldAddYear;

    @FXML private TextField textfieldDeleteByISBN;


    @FXML private TableView<Book> tableView;
    @FXML private TableColumn<Book, String> col1;
    @FXML private TableColumn<Book, String> col2;
    @FXML private TableColumn<Book, String> col3;
    @FXML private TableColumn<Book, String> col4;

    private DataBase dataBase = new DataBase();

    @FXML
    public void bClear(){
        tableView.getItems().clear();
    }

    @FXML
    public void addBookWhenButtonIsClick()
    {
        dataBase.addBook(new Book(textfieldAddISBN.getText(),textfieldAddTitle.getText(),textfieldAddAuthor.getText(),Integer.parseInt(textfieldAddYear.getText())));
    }

    @FXML
    public void deleteBookWithSpecISBN()
    {
        String query = "DELETE FROM books WHERE isbn ='"+textfieldDeleteByISBN.getText()+"'";
        dataBase.deleteBook(query);
    }

    @FXML
    public void getBooks()
    {
        bClear();
        String query = "SELECT * FROM books";
        ObservableList<Book> list = dataBase.getData(query);
        col1.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        col2.setCellValueFactory(new PropertyValueFactory<>("title"));
        col3.setCellValueFactory(new PropertyValueFactory<>("author"));
        col4.setCellValueFactory(new PropertyValueFactory<>("year"));
        tableView.setItems(list);
    }

    @FXML
    public void getBooksWithAuthor()
    {
        bClear();
        String author = textfieldFindByAuthor.getText();
        String query;
        if(author.contains(" "))
            query = "SELECT * FROM books WHERE author LIKE '"+author+"'";
        else
            query = "SELECT * FROM books WHERE author LIKE '%"+author+"'";
        ObservableList<Book> list = dataBase.getData(query);
        col1.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        col2.setCellValueFactory(new PropertyValueFactory<>("title"));
        col3.setCellValueFactory(new PropertyValueFactory<>("author"));
        col4.setCellValueFactory(new PropertyValueFactory<>("year"));
        tableView.setItems(list);
    }

    @FXML
    public void getBooksWithISBN()
    {
        bClear();
        textfieldFindByISBN.getText();
        String query = "SELECT * FROM books WHERE isbn LIKE '"+textfieldFindByISBN.getText()+"'";
        ObservableList<Book> list = dataBase.getData(query);
        col1.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        col2.setCellValueFactory(new PropertyValueFactory<>("title"));
        col3.setCellValueFactory(new PropertyValueFactory<>("author"));
        col4.setCellValueFactory(new PropertyValueFactory<>("year"));
        tableView.setItems(list);
    }
}
