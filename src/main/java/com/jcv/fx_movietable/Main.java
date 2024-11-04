package com.jcv.fx_movietable;

import java.awt.event.MouseEvent;
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
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;

public class Main implements Initializable{


    @FXML private TableView<HorrorCharacter> tblCharacters;
    @FXML private TableColumn<HorrorCharacter, String> clmSubtype;
    @FXML private TableColumn<HorrorCharacter, String> clmName;
    @FXML private TableColumn<HorrorCharacter, Integer> clmAge;
    @FXML private TableColumn<HorrorCharacter, LocalDate> clmRebirth;
    @FXML private Button bttnUpdate;
    
    //New Character area
    @FXML private Button bttnAddCharacter;
    @FXML private Button btnDelete;
    @FXML private Button btnSecondary;

    @FXML private TextField txtName;
    @FXML private TextField txtAge;
    @FXML private ComboBox<String> cbxSubtype;
    @FXML private DatePicker datePicker;

    public static ObservableList<HorrorCharacter> characterList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        // combobox data
        ObservableList<String> options = FXCollections.observableArrayList("Zombie", "Human", "Ghost", "Vampire");
        cbxSubtype.setItems(options);

        // retrieves the specific data into the TableColumn
        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmSubtype.setCellValueFactory(new PropertyValueFactory<>("subtype"));
        clmAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        clmRebirth.setCellValueFactory(new PropertyValueFactory<>("rebirth"));

        tblCharacters.setEditable(true);
        // TextFieldTableCell allows the column to be editable
        clmSubtype.setCellFactory(TextFieldTableCell.forTableColumn());
        clmName.setCellFactory(TextFieldTableCell.forTableColumn());
        clmAge.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        clmRebirth.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));

        tblCharacters.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        tblCharacters.setItems(getCharacters());
    }

    // fake backend for the TableView
    @FXML
    public ObservableList<HorrorCharacter> getCharacters()
    {
        if(characterList.isEmpty()) {
            characterList = FXCollections.observableArrayList(
                    new HorrorCharacter("Dracula", 300, "Vampire", LocalDate.of(1700, 1, 1)),
                    new HorrorCharacter("Lycan", 120, "Werewolf", LocalDate.of(1800, 6, 15)),
                    new HorrorCharacter("Ghoul", 50, "Zombie", LocalDate.of(2000, 10, 31)),
                    new HorrorCharacter("Phantom", 200, "Ghost", LocalDate.of(1750, 5, 1))
            );
        }
        return characterList;
    }

    @FXML
    public void titleChanged(CellEditEvent editedTitle)
    {
        HorrorCharacter characterSelected = tblCharacters.getSelectionModel().getSelectedItem();
        characterSelected.setName(editedTitle.getNewValue().toString());
    }
    @FXML
    public void subtypeChanged(CellEditEvent editedSubtype)
    {
        HorrorCharacter characterSelected = tblCharacters.getSelectionModel().getSelectedItem();
        characterSelected.setSubtype(editedSubtype.getNewValue().toString());
    }
    @FXML
    public void ageChanged(CellEditEvent editedAge)
    {
        HorrorCharacter characterSelected = tblCharacters.getSelectionModel().getSelectedItem();
        characterSelected.setAge(Integer.parseInt(editedAge.getNewValue().toString()));
    }
    @FXML
    public void rebirthChanged(CellEditEvent editedRebirth){
        HorrorCharacter characterSelected = tblCharacters.getSelectionModel().getSelectedItem();
        characterSelected.setRebirth(LocalDate.parse(editedRebirth.getNewValue().toString()));
    }

    @FXML
    public void addButtonPressed()
    {
        if(txtName.getText() != null && txtAge.getText() != null && datePicker.getValue()
                != null && cbxSubtype.getSelectionModel().getSelectedItem() != null) {
            String name = txtName.getText();
            String subtype = cbxSubtype.getSelectionModel().getSelectedItem();
            int age = Integer.parseInt(txtAge.getText());
            LocalDate rebirth = datePicker.getValue();
            HorrorCharacter newCharacter = new HorrorCharacter(name, age, subtype, rebirth);

            tblCharacters.getItems().add(newCharacter);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Character " + name + " added successfully");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("You have not entered enough information!");
            alert.showAndWait();
        }
    }

    // deletes a row from the TableView
    @FXML
    public void deleteButtonPressed()
    {
        ObservableList<HorrorCharacter> selectedRow = tblCharacters.getSelectionModel().getSelectedItems();

        if(!selectedRow.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete this character?");
            alert.showAndWait();

            if (alert.getResult() == ButtonType.OK) {
                for (HorrorCharacter m : selectedRow) {
                    // Removes the selected row
                    characterList.remove(m);

                    alert.alertTypeProperty().setValue(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText(null);
                    alert.setContentText("Character " + "'" + m.getName() + "'" + " deleted successfully");
                    alert.showAndWait();
                }
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("You have not selected any character");
            alert.showAndWait();
        }
    }

    /**
     * Used to send the data inside the selected row to another scene.
     * @param e Gives the ability to a button to switch the scene and send the data in the Table Columns
     * @throws IOException
     */

    @FXML
    public void sendDataToSecondary(ActionEvent e) throws IOException
    {
        if(tblCharacters.getSelectionModel().getSelectedItem() != null) {
            HorrorCharacter m = tblCharacters.getSelectionModel().getSelectedItem();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));
            Parent root = loader.load();                                //needed for scene switch
            DisplayController sc = loader.getController();     //new instance, via secondary.fxml
            sc.initData(m);                                      //method from SecondaryController

            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("You have not selected any character");
            alert.showAndWait();
        }
    }
}
