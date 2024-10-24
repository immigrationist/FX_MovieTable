package com.jcv.fx_movietable;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SecondaryController 
{
    private Movie movie;
    @FXML private TextField txtResults;

    @FXML
    public void initData(Movie m)
    {
        movie = m;
        txtResults.setText(m.toString());
    }
    
    /*
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    */
}