package controllers;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.Main;
import repository.Repository;
import utils.Utils;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by anton on 16.10.16.
 */
public class MainController implements Initializable{
    Repository repository;

    @FXML
    Label timer;

    public MainController(Repository repository){
        this.repository = repository;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*timer.textProperty().bind(Bindings.createStringBinding(()->{
            final long timeMillis = repository.getWholeTime().getValue();
            return Utils.prepareTime(timeMillis)+" min";
        }, repository.getWholeTime()));*/
    }
}
