package main;

import javafx.collections.transformation.SortedList;
import model.MyTask;

import java.util.*;

/**
 * Created by anton on 22.10.16.
 */
public class Exercise {
    private TreeSet<MyTask> tasks;
    private String name;
    private String description;
    private long totalTime;

    public Exercise(){
        tasks = new TreeSet<>((o1, o2) -> o1.getOrderNumber()-o2.getOrderNumber());
    }

    public void addTask(MyTask task){
        if (task.getOrderNumber()==0){
            int maxOrderNumber = tasks.last().getOrderNumber();
            task.setOrderNumber(maxOrderNumber++);
        }
        tasks.add(task);
    }

    public Set<MyTask> getTasks(){
        return tasks;
    }
}
