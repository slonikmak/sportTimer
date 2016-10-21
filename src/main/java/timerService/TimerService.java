package timerService;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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

    public TimerService(Repository repository) {
        this.repository = repository;
    }

    public void startTimer() {
        ObservableList<MyTask> tasks = repository.activeTasksProperty();
        tasks.forEach(MyTask::setQueue);
        final MyTask[] currentTask = {tasks.get(0)};

        System.out.println("whole time task "+currentTask[0].getCurrentTime());

        final long[] wholeTime = {repository.getWholeTime().getValue() * 1000};
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(200),
                ae -> {
                    wholeTime[0] = wholeTime[0] - 200;
                    currentTask[0].sub(200);
                    System.out.println("time " + currentTask[0].getCurrentTime());
                    if (currentTask[0].getCurrentTime() == (long) 0) {
                        System.out.println("end");
                        currentTask[0].setActive(false);
                        currentTask[0] = tasks.get(0);
                    }


                }));
        timeline.setOnFinished(event -> {
        });
        timeline.setCycleCount((int) wholeTime[0] / 200);

        timeline.play();


    }

    private void playTimer(Queue<MyTask.TaskQueueItem> queue) {
        if (queue.size() == 0) return;

        MyTask.TaskQueueItem queueItem = queue.poll();

        System.out.println(queueItem.getType());
        System.out.println(queueItem.getTime());

        final long[] time = {queueItem.getTime() * 1000};
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(200),
                ae -> {
                    time[0] = time[0] - 200;
                }));
        timeline.setOnFinished(event -> {
        });
        timeline.setCycleCount((int) time[0] / 200);
        timeline.setOnFinished(e -> {
            System.out.println("next");
            playTimer(queue);
        });
        timeline.play();
    }

}
