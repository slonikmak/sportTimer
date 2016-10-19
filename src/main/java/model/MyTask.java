package model;

import javafx.beans.property.*;

/**
 * Created by anton on 09.10.16.
 */
public class MyTask {
    StringProperty name = new SimpleStringProperty();
    LongProperty time = new SimpleLongProperty();
    LongProperty pause = new SimpleLongProperty();
    IntegerProperty times = new SimpleIntegerProperty();
    BooleanProperty done = new SimpleBooleanProperty();
    BooleanProperty active = new SimpleBooleanProperty();


    public MyTask() {
        done.setValue(false);
        active.setValue(true);
    }

    public MyTask(StringProperty name, LongProperty time, LongProperty pause, IntegerProperty times) {
        this.name = name;
        this.time = time;
        this.pause = pause;
        this.times = times;
        done.setValue(false);
        active.setValue(true);
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
        this.time.set(time);
    }

    public long getPause() {
        return pause.get();
    }

    public LongProperty pauseProperty() {
        return pause;
    }

    public void setPause(long pause) {
        this.pause.set(pause);
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

    public long getWholeTime(){
        return (getTime()+getPause())*getTimes();
    }

    @Override
    public String toString() {
        return name.getValue();
    }
}
