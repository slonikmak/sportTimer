package controllers;

import javafx.beans.binding.Bindings;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import model.MyTask;
import repository.Repository;
import utils.Utils;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by anton on 23.10.16.
 */
public class TimerWidgetController implements Initializable{
    Repository repository;
    double step;
    long totalTime;

    @FXML
    Arc timerArc;
    @FXML
    Pane arcContainer;
    @FXML
    AnchorPane rootPane;
    @FXML
    Label timeLabel;


    public TimerWidgetController(Repository repository){
        this.repository = repository;

    }

    private void setTimerSectors(){
        arcContainer.getChildren().clear();

        Color workColor = Color.web("#ddd059");
        Color pauseColor = Color.web("#128318");
        final double[] startAngle = {90};
        ObservableList<MyTask> list = repository.activeTasksProperty();
        step = 360D/repository.getWholeTime().get();
        totalTime = repository.getWholeTime().get();

        timerArc.lengthProperty().bind((repository.getWholeTime().subtract(totalTime)).multiply(step));
        timeLabel.textProperty().bind(Bindings.createStringBinding(()->{
            final long timeMillis = repository.getWholeTime().getValue();
            return Utils.prepareTime(timeMillis);
        }, repository.getWholeTime()));


        System.out.println("step "+step);
        list.forEach(task -> {
            int times = task.getTimes();
            for (int i = 0; i < times; i++) {
                double length = -1*task.getTime()*step;
                insertSecotr(startAngle[0], length, workColor);
                startAngle[0] = startAngle[0] +length;
                length = -1*task.getPause()*step;
                insertSecotr(startAngle[0], length, pauseColor);
                startAngle[0] = startAngle[0] +length;
            }
        });
    }

    public void insertSecotr(double startAngle, double length, Color color){
        System.out.println(startAngle+" "+length);
        Arc arc = new Arc(150, 150, 140, 140, startAngle, length);
        arc.setFill(color);
        arc.setType(ArcType.ROUND);
        arc.setOpacity(0.2);
        arcContainer.getChildren().add(arc);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        setTimerSectors();
        repository.allTasksProperty().addListener(new ListChangeListener<MyTask>() {
            @Override
            public void onChanged(Change<? extends MyTask> c) {
                c.next();
                if (c.wasUpdated()) return;
                setTimerSectors();
            }
        });

    }
}
