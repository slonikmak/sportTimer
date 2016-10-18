package controllers;

import de.jensd.fx.glyphs.control.GlyphCheckBox;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.util.converter.NumberStringConverter;
import model.MyTask;
import repository.Repository;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by anton on 16.10.16.
 */
public class TaskController implements Initializable{
    private final String doneLabel = String.valueOf(FontAwesomeIcon.CHECK);
    private final String playingLabel = String.valueOf(FontAwesomeIcon.PLAY);



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
        timeLable.textProperty().bindBidirectional(task.timeProperty(), new NumberStringConverter());
        pauseLable.textProperty().bindBidirectional(task.pauseProperty(), new NumberStringConverter());
        timesLable.textProperty().bindBidirectional(task.timesProperty(), new NumberStringConverter());
        active.selectedProperty().bindBidirectional(task.activeProperty());
    }
}
