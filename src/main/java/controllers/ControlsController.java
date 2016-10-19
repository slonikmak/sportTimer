package controllers;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.MyTask;
import repository.Repository;
import utils.Utils;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by anton on 16.10.16.
 */
public class ControlsController implements Initializable{
    Repository repository;
    final String pathToMain = "/layout/add.fxml";
    final URL mainFxmlUrl;

    @FXML
    Label leftTasks;

    @FXML
    Label leftTimeLabel;

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
        //repository.init();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ListProperty<MyTask> activTasksProperty = new SimpleListProperty<>(repository.activeTasksProperty());

        leftTasks.textProperty().bind(Bindings.createStringBinding(()->{
            final int size = activTasksProperty.getSize();

            final String taskText = size == 1 ? "task" : "tasks";

            return size + " " + taskText + " left";
        },  activTasksProperty.sizeProperty()));

        leftTimeLabel.textProperty().bind(Bindings.createStringBinding(()->{
            final long timeMillis = repository.getWholeTime().getValue();
            return Utils.prepareTime(timeMillis)+" min";
        }, repository.getWholeTime()));

    }
}
