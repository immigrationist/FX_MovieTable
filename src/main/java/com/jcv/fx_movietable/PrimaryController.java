package com.jcv.fx_movietable;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

public class PrimaryController implements Initializable{

    @FXML private TableView<Movie> tblMovies;    
    @FXML private TableColumn<Movie, String> clmGenre;
    @FXML private TableColumn<Movie, String> clmTitle;
    @FXML private TableColumn<Movie, Integer> clmYear;
    @FXML private TableColumn<Movie, LocalDate> clmRebirth;
    
    //New Movie area
    @FXML private Button btnAddMovie;
    @FXML private Button btnDelete;
    @FXML private Button btnSecondary;
    @FXML private TextField txtGenre;
    @FXML private TextField txtTitle;
    @FXML private TextField txtYear;
    @FXML private DatePicker datePicker;

    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    public void sendDataToSecondary(ActionEvent e) throws IOException
    {
        Movie m = tblMovies.getSelectionModel().getSelectedItem();
                
        FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));
        root = loader.load();                                //needed for scene switch
        SecondaryController sc = loader.getController();     //new instance, via secondary.fxml
        sc.initData(m);                                      //method from SecondaryController
        
        stage = (Stage)((Node)e.getSource()).getScene().getWindow(); 
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void deleteButtonPressed(ActionEvent e)
    {
        ObservableList<Movie> allMovies = tblMovies.getItems();
        ObservableList<Movie> selectedRows = tblMovies.getSelectionModel().getSelectedItems();
    
        for(Movie m : selectedRows)
        {
            allMovies.remove(m);
        }
    }
    
    @FXML
    public void addButtonPressed()
    {
        String title = txtTitle.getText();
        String genre = txtGenre.getText();
        int year = Integer.parseInt(txtYear.getText());
        LocalDate rebirth = datePicker.getValue();
        Movie newMovie = new Movie(title, genre, year, rebirth);
        
        tblMovies.getItems().add(newMovie);     
    }
    
    //Methods to get values from column cells
    @FXML
    public void titleChanged(CellEditEvent editedTitle)
    {
        Movie movieSelected = tblMovies.getSelectionModel().getSelectedItem();
        movieSelected.setTitle(editedTitle.getNewValue().toString());
    }
    @FXML
    public void genreChanged(CellEditEvent editedGenre)
    {
        Movie movieSelected = tblMovies.getSelectionModel().getSelectedItem();
        movieSelected.setGenre(editedGenre.getNewValue().toString());
    }
    @FXML
    public void yearChanged(CellEditEvent editedYear)
    {
        Movie movieSelected = tblMovies.getSelectionModel().getSelectedItem();
        movieSelected.setYear(Integer.parseInt(editedYear.getNewValue().toString()));
    }

    @FXML

    public void rebirthChanged(CellEditEvent editedRebirth){
        Movie movieSelected = tblMovies.getSelectionModel().getSelectedItem();
        movieSelected.setRebirth(LocalDate.parse(editedRebirth.getNewValue().toString()));
    }
    
    @FXML
    public ObservableList<Movie> getMovies()
    {
        ObservableList<Movie> movieList = FXCollections.observableArrayList();
        movieList.add(new Movie("Terrifier 3", "Horror", 2024, LocalDate.of(2024, 1, 1)));
        movieList.add(new Movie("The Texas Chainsaw Massacre", "Horror", 1974, LocalDate.of(1974, 1, 1)));
        movieList.add(new Movie("Re-Animator", "Horror,Comedy", 1985, LocalDate.of(1985, 1, 1)));
        movieList.add(new Movie("The Nice Guys", "Action,Comedy", 2016, LocalDate.of(2016, 1, 1)));
        movieList.add(new Movie("Room", "Drama", 2015, LocalDate.of(2015, 1, 1)));
        return movieList;   
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {   //set up columns, using reflection to build the getTitle method,
        //then call it.  SetCellValueFactory maps to the columns
        clmTitle.setCellValueFactory(new PropertyValueFactory<Movie, String>("title"));
        clmGenre.setCellValueFactory(new PropertyValueFactory<Movie, String>("genre"));
        clmYear.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("year"));
        clmRebirth.setCellValueFactory(new PropertyValueFactory<>("rebirth"));
        
        //Set up the columns to be editable
        tblMovies.setEditable(true);
        clmGenre.setCellFactory(TextFieldTableCell.forTableColumn());
        clmTitle.setCellFactory(TextFieldTableCell.forTableColumn());
        //clmRebirth.setCellFactory(TextFieldTableCell.forTableColumn());
        //clmYear.setCellFactory(TextFieldTableCell.forTableColumn());
        
        tblMovies.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //Get data from the 'backend'
        tblMovies.setItems(getMovies());
    }
    
    @FXML
    private void switchToSecondary() throws IOException 
    {
        App.setRoot("secondary");
    }
}
