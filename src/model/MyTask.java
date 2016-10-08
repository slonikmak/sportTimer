package model;

/**
 * Created by anton on 09.10.16.
 */
public class MyTask {
    public enum taskType{
        RELAX, WORK
    }

    String name;
    Long time;
    taskType type;

    public MyTask() {
    }

    public MyTask(String name, Long time, taskType type) {
        this.name = name;
        this.time = time;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public taskType getType() {
        return type;
    }

    public void setType(taskType type) {
        this.type = type;
    }
}
