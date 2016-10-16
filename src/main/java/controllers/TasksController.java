package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import model.MyTask;
import repository.Repository;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by anton on 16.10.16.
 */
public class TasksController implements Initializable{

    @FXML
    private ListView<MyTask> tasks;

    Map<MyTask, Node> tasksNodeCach = new HashMap<>();

    private final Repository repository;

    private final URL urlToTaskFxml;

    private final String taskFxmlPath = "/layout/task.fxml";

    public TasksController(Repository repository){
        this.repository = repository;

        urlToTaskFxml = getClass().getResource(taskFxmlPath);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tasks.setItems(repository.tasksProperty());

        tasks.setCellFactory(new Callback<ListView<MyTask>, ListCell<MyTask>>() {
            @Override
            public ListCell<MyTask> call(ListView<MyTask> param) {
                return new ListCell<MyTask>(){
                    @Override
                    protected void updateItem(MyTask task, boolean empty){
                        super.updateItem(task, empty);

                        if(empty){
                            setText(null);
                            setGraphic(null);
                        } else {
                            setText(null);
                            if (!tasksNodeCach.containsKey(task)){
                                final Parent parent = loadTaskFxml(task);
                                tasksNodeCach.put(task, parent);
                            }

                            final Node node = tasksNodeCach.get(task);
                            Node currentNode = getGraphic();

                            if (currentNode == null || !currentNode.equals(node)) {
                                setGraphic(node);
                            }
                        }
                    }
                };
            }
        });

    }

    private Parent loadTaskFxml(MyTask task) {
        FXMLLoader fxmlLoader = new FXMLLoader(urlToTaskFxml);

        try {

            TaskController taskController = new TaskController(repository, task);

            fxmlLoader.setController(taskController);
            fxmlLoader.load();

            return fxmlLoader.getRoot();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
