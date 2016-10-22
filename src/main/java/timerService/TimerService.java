package timerService;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.util.Duration;
import model.MyTask;
import repository.Repository;

import java.util.Date;
import java.util.Queue;
import java.util.function.Consumer;

/**
 * Created by anton on 20.10.16.
 */
public class TimerService {
    private Repository repository;

    private final int DURATION_MILLIS = 100;

    private Timeline timeline = null;

    public TimerService(Repository repository) {
        this.repository = repository;
    }

    private void startTimer() {
        System.out.println("Start");
        ObservableList<MyTask> tasks = repository.activeTasksProperty();
        tasks.forEach(MyTask::setQueue);
        final ObjectProperty<MyTask> currTask = new SimpleObjectProperty<>(tasks.get(0));
        currTask.get().setWorking(true);

        //System.out.println("whole time task "+currentTask[0].getCurrentTime());

        timeline = new Timeline(new KeyFrame(
                Duration.millis(DURATION_MILLIS),
                ae -> {
                    repository.getWholeTime().setValue(repository.getWholeTime().get()-DURATION_MILLIS);
                    currTask.get().sub(DURATION_MILLIS);
                    //System.out.println("time " + tasks.get(0).getCurrentTime());
                    if (currTask.get().getCurrentTime() == (long) 0) {
                        System.out.println("end");
                        currTask.get().setWorking(false);
                        currTask.get().setActive(false);
                        currTask.get().setDone(true);
                        if (tasks.size()==0){
                            timeline.stop();
                            return;
                        }
                        currTask.setValue(tasks.get(0));
                        tasks.get(0).setWorking(true);
                    }


                }));
        timeline.setOnFinished(event -> {
            tasks.forEach(task -> task.setDone(false));
        });
        timeline.setCycleCount((int) repository.getWholeTime().get() / DURATION_MILLIS);

        timeline.play();


    }

    public void play(){
        if (timeline==null){
            startTimer();
        } else timeline.play();
    }
    public void stop(){
        timeline.stop();
        timeline=null;
        repository.init();
    }
    public void pause(){
        timeline.stop();
    }



}
