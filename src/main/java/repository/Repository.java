package repository;

import javafx.beans.Observable;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import model.MyTask;

import javax.inject.Singleton;

/**
 * Created by anton on 16.10.16.
 */
@Singleton
public class Repository {
    private ObservableList<MyTask> allTasks = FXCollections.observableArrayList(item->{
        return new Observable[]{item.doneProperty(), item.activeProperty()};
    });

    private FilteredList<MyTask> completedTasks = new FilteredList<MyTask>(allTasks, MyTask::isDone);
    private FilteredList<MyTask> activeTasks = new FilteredList<MyTask>(allTasks, myTask -> !myTask.isDone()&&myTask.isActive());

    private ListProperty<MyTask> tasksProperty = new SimpleListProperty<>(allTasks);

    public ObservableList<MyTask> tasksProperty(){return tasksProperty;}

    public ObservableList<MyTask> allTasksProperty(){return allTasks;}

    public ObservableList<MyTask> completedTasksProperty(){return completedTasks;}

    public ObservableList<MyTask> activeTasksProperty(){return activeTasks;}

    public void showAllTasks() {
        tasksProperty.set(allTasks);
    }

    public void showCompletedTasks() {
        tasksProperty.set(completedTasks);
    }

    public void showActiveItems() {
        tasksProperty.set(activeTasks);
    }

    public void addItem(MyTask task) {
        allTasks.add(task);
    }

    public void deleteItem(MyTask task) {
        allTasks.remove(task);
    }
}
