package sample;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.MyTask;

import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Queue;

public class Controller {

    private Queue<MyTask> tasks = new PriorityQueue<>();


    @FXML
    void addTask(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        Parent root = loader.load(getClass().getResource("add.fxml"));
        System.out.println("add task");
        Stage stage = new Stage();
        stage.setTitle("Add task");
        stage.setScene(new Scene(root, 300, 275));
        stage.showAndWait();

    }

    public void addTask(MyTask task){
        tasks.add(task);
    }

    public void removeTask(MyTask task){
        tasks.remove(task);
    }
}
