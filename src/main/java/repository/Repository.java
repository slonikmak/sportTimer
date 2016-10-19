package repository;

import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import model.MyTask;

import javax.inject.Singleton;

/**
 * Created by anton on 16.10.16.
 */
@Singleton
public class Repository {
    private LongProperty wholeTime = new SimpleLongProperty(0);

    private ObservableList<MyTask> allTasks = FXCollections.observableArrayList(item->{
        return new Observable[]{item.doneProperty(), item.activeProperty()};
    });

    private FilteredList<MyTask> completedTasks = new FilteredList<MyTask>(allTasks, MyTask::isDone);
    private FilteredList<MyTask> activeTasks = new FilteredList<MyTask>(allTasks, myTask -> !myTask.isDone()&&myTask.isActive());


    private ListProperty<MyTask> tasksProperty = new SimpleListProperty<>(allTasks);

    private ObjectProperty<MyTask> playingTask = new SimpleObjectProperty<>();

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

    public void setPlayingTask(MyTask task){
        playingTask.setValue(task);
    }

    public ObjectProperty<MyTask> playingTask(){
        return playingTask;
    }

    public LongProperty getWholeTime(){
        return wholeTime;
    }

    public Repository(){

        activeTasks.addListener(new ListChangeListener<MyTask>() {
            @Override
            public void onChanged(Change<? extends MyTask> c) {

                final long[] val = {0};
                activeTasks.forEach(task -> {
                    val[0] += task.getWholeTime();
                });
                wholeTime.setValue(val[0]);
            }
        });

        init();

    }

    public void init(){
        MyTask task = new MyTask();
        task.setTimes(2);
        task.setTime(10);
        task.setPause(4);
        task.setName("Dance dance dance");
        addItem(task);
    }
}
