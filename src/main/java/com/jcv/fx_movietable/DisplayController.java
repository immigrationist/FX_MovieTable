package com.jcv.fx_movietable;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DisplayController implements Initializable {


    @FXML private TextField txtName;
    @FXML private TextField txtAge;
    @FXML private TextField txtSubtype;
    @FXML private TextField txtRebirth;
    @FXML private Label txtInformation;
    @FXML private Button bttnSwitch;
    @FXML private Button bttnUpdate;
    @FXML private Button bttnComplete;

    @FXML
    public void initData(HorrorCharacter m)
    {
        txtName.setText(m.toName());
        txtName.setEditable(false);
        txtAge.setText(m.toAge());
        txtAge.setEditable(false);
        txtSubtype.setText(m.toSubtype());
        txtSubtype.setEditable(false);
        txtRebirth.setText(m.toRebirth());
        txtRebirth.setEditable(false);
    }

    @FXML
    private void switchToPrimary() {
        Stage stage = (Stage) bttnSwitch.getScene().getWindow(); // Get the current stage
        DisplayController.switchScene(stage, "main.fxml"); // Switch to primary scene
    }

    public static void switchScene(Stage stage, String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(DisplayController.class.getResource(fxmlFile)));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setBttnUpdate(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Success!");
        alert.setContentText("You can now edit! " +
                "\nSadly, this won't change the data in the Table... yet.");
        alert.showAndWait();

        txtName.setEditable(true);
        txtAge.setEditable(true);
        txtSubtype.setEditable(true);
        txtRebirth.setEditable(true);
    }

    public void setBttnComplete(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Button Information");
        alert.setContentText("This feature is in maintenance");
        alert.showAndWait();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtInformation.setText("Rebirth is shown as YYYY/MM/DD");
    }
}