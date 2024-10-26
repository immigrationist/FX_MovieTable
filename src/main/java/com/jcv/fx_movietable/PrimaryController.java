package com.jcv.fx_movietable;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
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

    @FXML private TableView<HorrorCharacter> tblMovies;
    @FXML private TableColumn<HorrorCharacter, String> clmSubtype;
    @FXML private TableColumn<HorrorCharacter, String> clmName;
    @FXML private TableColumn<HorrorCharacter, Integer> clmAge;
    @FXML private TableColumn<HorrorCharacter, LocalDate> clmRebirth;
    
    //New Movie area
    @FXML private Button btnAddMovie;
    @FXML private Button btnDelete;
    @FXML private Button btnSecondary;
    @FXML private ComboBox<String> cbxSubtype;
    @FXML private TextField txtName;
    @FXML private TextField txtAge;
    @FXML private DatePicker datePicker;

    private static final ObservableList<HorrorCharacter> movieList = FXCollections.observableArrayList();


    @FXML
    public ObservableList<HorrorCharacter> getMovies()
    {
        if(movieList.isEmpty()) {
            movieList.add(new HorrorCharacter("Steve", "Zombie", 30, LocalDate.of(2024, 1, 1)));
            movieList.add(new HorrorCharacter("Wolverine", "Werewolf", 55, LocalDate.of(1974, 1, 1)));
            movieList.add(new HorrorCharacter("Fredy", "Human", 90, LocalDate.of(1985, 1, 1)));
            movieList.add(new HorrorCharacter("Casper", "Ghost", 9999, LocalDate.of(2016, 1, 1)));
            movieList.add(new HorrorCharacter("Jason", "Human", 25, LocalDate.of(2015, 1, 1)));
        }
        return movieList;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        ObservableList<String> options = FXCollections.observableArrayList("Zombie", "Human", "Ghost", "Vampire");
        cbxSubtype.setItems(options);

        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmSubtype.setCellValueFactory(new PropertyValueFactory<>("subtype"));
        clmAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        clmRebirth.setCellValueFactory(new PropertyValueFactory<>("rebirth"));

        tblMovies.setEditable(true);
        clmSubtype.setCellFactory(TextFieldTableCell.forTableColumn());
        clmName.setCellFactory(TextFieldTableCell.forTableColumn());

        tblMovies.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        tblMovies.setItems(getMovies());
    }

    @FXML
    public void titleChanged(CellEditEvent editedTitle)
    {
        HorrorCharacter movieSelected = tblMovies.getSelectionModel().getSelectedItem();
        movieSelected.setName(editedTitle.getNewValue().toString());
    }
    @FXML
    public void subtypeChanged(CellEditEvent editedSubtype)
    {
        HorrorCharacter movieSelected = tblMovies.getSelectionModel().getSelectedItem();
        movieSelected.setSubtype(editedSubtype.getNewValue().toString());
    }
    @FXML
    public void ageChanged(CellEditEvent editedAge)
    {
        HorrorCharacter movieSelected = tblMovies.getSelectionModel().getSelectedItem();
        movieSelected.setAge(Integer.parseInt(editedAge.getNewValue().toString()));
    }

    @FXML

    public void rebirthChanged(CellEditEvent editedRebirth){
        HorrorCharacter movieSelected = tblMovies.getSelectionModel().getSelectedItem();
        movieSelected.setRebirth(LocalDate.parse(editedRebirth.getNewValue().toString()));
    }

    @FXML
    public void sendDataToSecondary(ActionEvent e) throws IOException
    {
        HorrorCharacter m = tblMovies.getSelectionModel().getSelectedItem();
                
        FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));
        Parent root = loader.load();                                //needed for scene switch
        SecondaryController sc = loader.getController();     //new instance, via secondary.fxml
        sc.initData(m);                                      //method from SecondaryController

        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void deleteButtonPressed(ActionEvent e)
    {
        ObservableList<HorrorCharacter> allMovies = tblMovies.getItems();
        ObservableList<HorrorCharacter> selectedRows = tblMovies.getSelectionModel().getSelectedItems();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete this movie?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            for(HorrorCharacter m : selectedRows)
            {
                alert.alertTypeProperty().setValue(Alert.AlertType.INFORMATION);
                allMovies.remove(m);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Movie " + "'" + m.getName() + "'" + " deleted successfully");
                Optional<ButtonType> result1 = alert.showAndWait();
            }
        }
        else{
            alert.setContentText("You have not selected any movies");
        }

    }
    
    @FXML
    public void addButtonPressed()
    {
        String title = txtName.getText();
        String subtype = cbxSubtype.getSelectionModel().getSelectedItem();
        int age = Integer.parseInt(txtAge.getText());
        LocalDate rebirth = datePicker.getValue();
        HorrorCharacter newMovie = new HorrorCharacter(title, subtype, age, rebirth);
        tblMovies.getItems().add(newMovie);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Movie " + title + " successfully");
        Optional<ButtonType> result = alert.showAndWait();
    }
    
    @FXML
    private void switchToSecondary() throws IOException 
    {
        App.setRoot();
    }
}
