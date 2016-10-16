package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.MyTask;
import repository.Repository;

/**
 * Created by anton on 16.10.16.
 */
public class AddTaskController {
    Repository repository;
    Stage stage;

    @FXML
    private TextField name;

    @FXML
    private TextField time;

    @FXML
    private TextField pause;

    @FXML
    private TextField times;

    @FXML
    void addTasks(ActionEvent event) {
        MyTask task = new MyTask();
        task.setName(name.getText());
        task.setTime(Long.parseLong(time.getText()));
        task.setPause(Long.parseLong(pause.getText()));
        task.setTimes(Integer.parseInt(times.getText()));

        repository.addItem(task);
        stage.close();
    }

    @FXML
    void doCancel(ActionEvent event) {
        stage.close();
    }

    public AddTaskController(Repository repository, Stage stage){
        this.repository = repository;
        this.stage = stage;
    }

}
