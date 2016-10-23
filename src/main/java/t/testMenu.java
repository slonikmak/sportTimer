package t;

import javafx.application.Application;
import javafx.beans.property.Property;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import model.MyTask;
import repository.Repository;

public class testMenu extends Application {

    Repository repository;
    Pane root;

    @Override
    public void start(Stage primaryStage) throws Exception{
        repository = new Repository();

        double centerX = 150;
        double centerY = 150;

        root = new Pane();
        root.prefWidth(400);
        root.prefHeight(400);
        root.maxWidth(400);
        root.maxHeight(400);
        Slider slider = new Slider();
        slider.setMax(360);
        slider.setMin(0);
        root.getChildren().add(slider);
        //background
        Arc bkgArc = new Arc();
        bkgArc.setCenterY(centerY);
        bkgArc.setCenterX(centerX);
        bkgArc.setRadiusX(100);
        bkgArc.setRadiusY(100);
        bkgArc.setType(ArcType.ROUND);
        bkgArc.setFill(Color.web("#99ff99"));
        bkgArc.setLength(360);



        ///timer
        Arc arc = new Arc();
        arc.setCenterX(centerX);
        arc.setCenterY(centerY);
        arc.setRadiusX(100);
        arc.setRadiusY(100);
        arc.setStartAngle(90);
        arc.setType(ArcType.ROUND);
        arc.setLength(-10.0f);
        arc.lengthProperty().bind((slider.valueProperty().multiply(-1)));
        arc.setFill(new Color(1, 0.5, 0.5, 0.8));

        Arc outArc = new Arc();
        outArc.setCenterX(centerX);
        outArc.setCenterY(centerY);
        outArc.setRadiusX(90);
        outArc.setRadiusY(90);
        outArc.setStartAngle(0);
        outArc.setType(ArcType.ROUND);
        outArc.setLength(360);
        outArc.setFill(Color.web("#006633"));
        outArc.setOpacity(0.8);


        root.getChildren().add(bkgArc);
        setTimerScale();
        root.getChildren().add(arc);
        root.getChildren().add(outArc);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }

    public void setTimerScale(){
        Color workColor = Color.web("#cccc00");
        Color pauseColor = Color.web("#99ff99");
        final double[] sartAngle = {90};
        ObservableList<MyTask> list = repository.activeTasksProperty();
        double step = 360D/repository.getWholeTime().get();
        System.out.println("step "+step);
        list.forEach(task -> {
            int times = task.getTimes();
            for (int i = 0; i < times; i++) {
                double length = -1*task.getTime()*step;
                insertSecotr(sartAngle[0], length, workColor);
                sartAngle[0] = sartAngle[0] +length;
                length = -1*task.getPause()*step;
                insertSecotr(sartAngle[0], length, pauseColor);
                sartAngle[0] = sartAngle[0] +length;
            }
        });
    }

    public void insertSecotr(double startAngle, double length, Color color){
        System.out.println(startAngle+" "+length);
        Arc arc = new Arc(150, 150, 100, 100, startAngle, length);
        arc.setFill(color);
        arc.setType(ArcType.ROUND);
        root.getChildren().add(arc);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
