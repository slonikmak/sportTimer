package model;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.*;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anton on 09.10.16.
 */
public class MyTask {
    private ConcurrentLinkedQueue<TaskQueueItem> queue = new ConcurrentLinkedQueue<>();
    private long currTime;
    private TaskQueueItem currQueueItem;
    private int orderNumber;


    StringProperty name = new SimpleStringProperty();
    StringProperty description = new SimpleStringProperty();
    LongProperty time = new SimpleLongProperty(0);
    LongProperty pause = new SimpleLongProperty(0);
    IntegerProperty times = new SimpleIntegerProperty(0);
    BooleanProperty done = new SimpleBooleanProperty();
    BooleanProperty active = new SimpleBooleanProperty();
    BooleanProperty working = new SimpleBooleanProperty();
    NumberBinding totalTime;


    public MyTask() {
        done.setValue(false);
        active.setValue(true);
        working.setValue(false);
        totalTime = (time.multiply(times)).add(pause.multiply(times));
        orderNumber = 0;
    }



    public MyTask(StringProperty name, LongProperty time, LongProperty pause, IntegerProperty times) {
        this.name = name;
        this.time = time;
        this.pause = pause;
        this.times = times;

        done.setValue(false);
        active.setValue(true);
        working.setValue(false);
        totalTime = (time.multiply(times)).add(pause.multiply(times));
        orderNumber = 0;

    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public long getTime() {
        return time.get();
    }

    public LongProperty timeProperty() {
        return time;
    }

    public void setTime(long time) {
        this.time.set(time*1000);
    }

    public long getPause() {
        return pause.get();
    }

    public LongProperty pauseProperty() {
        return pause;
    }

    public void setPause(long pause) {
        this.pause.set(pause*1000);
    }

    public int getTimes() {
        return times.get();
    }

    public IntegerProperty timesProperty() {
        return times;
    }

    public void setTimes(int times) {
        this.times.set(times);
    }

    public boolean isDone() {
        return done.get();
    }

    public BooleanProperty doneProperty() {
        return done;
    }

    public void setDone(boolean done) {
        this.done.set(done);
    }

    public boolean isActive() {
        return active.get();
    }

    public BooleanProperty activeProperty() {
        return active;
    }

    public void setActive(boolean active) {
        this.active.set(active);
    }

    public boolean isWorking() {
        return working.get();
    }

    public BooleanProperty workingProperty() {
        return working;
    }

    public void setWorking(boolean working) {
        this.working.set(working);
    }

    public long getWholeTime(){
        return (getTime()+getPause())*getTimes();
    }

    public Number getTotalTime() {
        return totalTime.getValue();
    }

    public NumberBinding totalTimeProperty() {
        return totalTime;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public void setQueue(){

        for (int i = 0; i < getTimes(); i++) {
            queue.add(new TaskQueueItem(getTime(), TaskQueueItem.Type.WORK));
            queue.add(new TaskQueueItem(getPause(), TaskQueueItem.Type.PAUSE));
        }
        currTime = (long)getTotalTime();
        currQueueItem = queue.poll();
        System.out.println(currQueueItem.getType());
        System.out.println(currQueueItem.getTime());
    }

    public void sub(int duration){
        currTime-=duration;
        if (currQueueItem.getTime()==0){
            currQueueItem = queue.poll();
            System.out.println("type "+currQueueItem.getType());
            System.out.println("time "+currQueueItem.getTime());
        }
        currQueueItem.sub(duration);

    }

    public long getCurrentTime(){
        return currTime;
    }

    @Override
    public String toString() {
        return name.getValue();
    }

    public static class TaskQueueItem{
        enum Type {
            WORK, PAUSE
        }
        long time;
        Type type;

        public TaskQueueItem(){

        }

        public TaskQueueItem(long time, Type type) {
            this.time = time*1000;
            this.type = type;
        }

        public void sub(int duration){
            time = time-duration;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time*1000;
        }

        public Type getType() {
            return type;
        }

        public void setType(Type type) {
            this.type = type;
        }
    }
}
