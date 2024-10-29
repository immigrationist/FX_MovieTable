package com.jcv.fx_movietable;

import java.io.IOException;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SecondaryController  {
    @FXML private TextField txtName;
    @FXML private TextField txtAge;
    @FXML private TextField txtSubtype;
    @FXML private TextField txtRebirth;
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
        SecondaryController.switchScene(stage, "main.fxml"); // Switch to primary scene
    }

    public static void switchScene(Stage stage, String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(SecondaryController.class.getResource(fxmlFile)));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setBttnUpdate(ActionEvent e){
        txtName.setEditable(true);
        txtAge.setEditable(true);
        txtSubtype.setEditable(true);
        txtRebirth.setEditable(true);
    }

    public void setBttnComplete(ActionEvent e){

    }


}
