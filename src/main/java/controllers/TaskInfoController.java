package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import model.MyTask;

/**
 * Created by anton on 23.10.16.
 */
public class TaskInfoController {
    MyTask task;

    @FXML
    Label nameLabel;

    @FXML
    Text description;

    public void setTask(MyTask task){
        this.task = task;
        nameLabel.setText(task.getName());
        description.setText(task.getDescription());
    }


}
