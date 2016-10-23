package repository;

import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import main.Exercise;
import model.MyTask;

import javax.inject.Singleton;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anton on 16.10.16.
 */
@Singleton
public class Repository {
    private Exercise exercise;

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
        exercise = new Exercise();


        allTasks.clear();
        MyTask task = new MyTask();
        task.setTimes(2);
        task.setTime(4);
        task.setPause(2);
        task.setName("Dance dance dance");
        task.setDescription("The examples will always reflect the latest version of FontAwesomeFX " +
                "(currently 8.12 which can be loaded from Bintray) " +
                "and I am going to extend it soon to show all stuff which provided by the lib.");
        task.setOrderNumber(2);

        MyTask task1 = new MyTask();
        task1.setTimes(3);
        task1.setTime(3);
        task1.setPause(2);
        task1.setName("Do some thing");
        task1.setDescription("Ни для кого не секрет что система в процессе своей работы засоряется. " +
                "Установка и удаление программ, выполнение скриптов, обновления, неверная установка программ " +
                "из исходников, ошибки в программах, все это оставляет в системе лишние, ненужные пакеты. " +
                "Со временем этих файлов может накапливаться достаточно большое количество.");
        task1.setOrderNumber(1);
        exercise.addTask(task);
        exercise.addTask(task1);

        allTasks.addAll(exercise.getTasks());

        //addItem(task);
        //addItem(task1);
    }

}
