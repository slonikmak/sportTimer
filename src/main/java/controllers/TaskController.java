package controllers;

import de.jensd.fx.glyphs.control.GlyphCheckBox;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;
import model.MyTask;
import repository.Repository;
import utils.Utils;
import utils.Utils.CustomStringConverter;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by anton on 16.10.16.
 */
public class TaskController implements Initializable{
    private final String doneLabel = String.valueOf(FontAwesomeIcon.CHECK);
    private final String playingLabel = String.valueOf(FontAwesomeIcon.PLAY);
    private final String circleLable = String.valueOf(FontAwesomeIcon.DOT_CIRCLE_ALT);


    @FXML
    VBox rootNode;

    @FXML
    FontAwesomeIconView playLabel;

    @FXML
    private Label title;

    @FXML
    private Label timeLable;

    @FXML
    private Label pauseLable;

    @FXML
    private Label timesLable;

    @FXML
    private Label totalTimeLable;

    @FXML
    private GlyphCheckBox active;

    @FXML
    void deleteTask(ActionEvent event) {
        repository.deleteItem(task);

    }


    Repository repository;
    MyTask task;

    public TaskController(Repository repository, MyTask task){
        this.repository = repository;
        this.task = task;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //completed.selectedProperty().bindBidirectional(todoItem.doneProperty());
        playLabel.setGlyphName(String.valueOf(FontAwesomeIcon.DOT_CIRCLE_ALT));


        title.textProperty().bindBidirectional(task.nameProperty());
        timeLable.textProperty().bindBidirectional(task.timeProperty(), new CustomStringConverter());
        pauseLable.textProperty().bindBidirectional(task.pauseProperty(), new CustomStringConverter());
        totalTimeLable.textProperty().bindBidirectional(new SimpleLongProperty(task.totalTimeProperty().longValue()), new CustomStringConverter());
        timesLable.textProperty().bindBidirectional(task.timesProperty(), new NumberStringConverter());
        active.selectedProperty().bindBidirectional(task.activeProperty());
        active.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue){
                rootNode.getStyleClass().removeAll("in_active");
                rootNode.getStyleClass().add("active");
            } else {
                rootNode.getStyleClass().removeAll("active");
                rootNode.getStyleClass().add("in_active");
            }

        });
        task.workingProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) playLabel.setGlyphName(playingLabel);
            else playLabel.setGlyphName(circleLable);
        });
        task.doneProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) playLabel.setGlyphName(doneLabel);
        });
    }
}
