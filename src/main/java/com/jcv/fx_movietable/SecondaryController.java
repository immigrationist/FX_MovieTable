package com.jcv.fx_movietable;

import java.io.IOException;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SecondaryController
{
    @FXML private TextField txtName;
    @FXML private TextField txtAge;
    @FXML private TextField txtSubtype;
    @FXML private TextField txtRebirth;
    @FXML private Button bttnSwitch;


    @FXML
    public void initData(HorrorCharacter m)
    {
        txtName.setText(m.toName());
        txtAge.setText(m.toAge());
        txtSubtype.setText(m.toSubtype());
        txtRebirth.setText(m.toRebirth());
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
}
