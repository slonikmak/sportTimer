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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;
import model.MyTask;
import repository.Repository;
import timerService.TimerService;
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
    final TimerService timerService;

    @FXML
    ToggleButton startBtn;

    @FXML
    ToggleButton pauseBtn;

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
        timerService.pause();
        startBtn.setSelected(false);
    }

    @FXML
    void start(ActionEvent event) {
        timerService.play();
        pauseBtn.setSelected(false);
        try {
            showTimerWidget();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void stop(ActionEvent event) {
        timerService.stop();
        pauseBtn.setSelected(false);
        startBtn.setSelected(false);
    }

    public ControlsController(Repository repository){
        this.repository = repository;
        mainFxmlUrl = this.getClass().getResource(pathToMain);
        timerService = new TimerService(repository);
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

    public void showTimerWidget() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/layout/timerWidget.fxml"));
        TimerWidgetController controller = new TimerWidgetController(repository);
        loader.setController(controller);
        Stage stage = new Stage();
        loader.load();
        Parent root = loader.getRoot();
        stage.setScene(new Scene(root, 400, 400));
        stage.show();
    }
}
