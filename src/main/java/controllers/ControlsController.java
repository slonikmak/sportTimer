package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import repository.Repository;

import java.io.IOException;
import java.net.URL;

/**
 * Created by anton on 16.10.16.
 */
public class ControlsController {
    Repository repository;
    final String pathToMain = "/layout/add.fxml";
    final URL mainFxmlUrl;

    @FXML
    void addTask(ActionEvent event) {
        Parent root;
        FXMLLoader loader = new FXMLLoader(mainFxmlUrl);

        try {
            Stage stage = new Stage();
            loader.setController(new AddTaskController(repository, stage));
            root = loader.load();

            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    void pause(ActionEvent event) {

    }

    @FXML
    void start(ActionEvent event) {

    }

    @FXML
    void stop(ActionEvent event) {

    }

    public ControlsController(Repository repository){
        this.repository = repository;
        mainFxmlUrl = this.getClass().getResource(pathToMain);
    }


}
