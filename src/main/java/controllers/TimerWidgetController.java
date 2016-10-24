package controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import model.MyTask;
import repository.Repository;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by anton on 23.10.16.
 */
public class TimerWidgetController implements Initializable{
    Repository repository;

    @FXML
    Arc timerArc;
    @FXML
    Pane arcContainer;
    @FXML
    AnchorPane rootPane;


    public TimerWidgetController(Repository repository){
        this.repository = repository;

    }

    private void setTimerSectors(){
        Color workColor = Color.web("#db521c");
        Color pauseColor = Color.web("#1fff2d");
        final double[] startAngle = {90};
        ObservableList<MyTask> list = repository.activeTasksProperty();
        double step = 360D/repository.getWholeTime().get();
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
        Arc arc = new Arc(200, 200, 190, 190, startAngle, length);
        arc.setFill(color);
        arc.setType(ArcType.ROUND);
        arc.setOpacity(0.2);
        arcContainer.getChildren().add(arc);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        arcContainer.getChildren().clear();
        setTimerSectors();

    }
}
