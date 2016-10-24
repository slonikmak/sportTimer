package t;

/**
 * Created by Oceanos on 24.10.2016.
 */
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class Test2 extends Application {

    @Override
    public void start(Stage primaryStage) {
        AnchorPane root = new AnchorPane();
        root.setStyle("-fx-border-color: black; -fx-border-width: 1px; ");
        enableDragging(root);


        StackPane mainContainer = new StackPane();
        AnchorPane.setTopAnchor(mainContainer, 5.0);
        AnchorPane.setLeftAnchor(mainContainer, 5.0);
        AnchorPane.setRightAnchor(mainContainer, 5.0);
        AnchorPane.setBottomAnchor(mainContainer, 5.0);
        mainContainer.setStyle("-fx-background-color: aliceblue;");

        root.getChildren().add(mainContainer);



        primaryStage.initStyle(StageStyle.TRANSPARENT);
        final ChoiceBox<String> choiceBox = new ChoiceBox<>(FXCollections.observableArrayList("Item 1", "Item 2", "Item 3"));
        final Button closeButton = new Button("Close");
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(choiceBox, closeButton);
        mainContainer.getChildren().add(vbox);
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });
        primaryStage.setScene(new Scene(root,  300, 200, Color.TRANSPARENT));
        primaryStage.show();
    }

    private void enableDragging(final Node n) {
        final ObjectProperty<Point2D> mouseAnchor = new SimpleObjectProperty<>(null);
        n.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseAnchor.set(new Point2D(event.getX(), event.getY()));
            }
        });
        n.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseAnchor.set(null);
            }
        });
        n.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Point2D anchor = mouseAnchor.get();
                Scene scene = n.getScene();
                Window window = null ;
                if (scene != null) {
                    window = scene.getWindow();
                }
                if (anchor != null && window != null) {
                    double deltaX = event.getX()-anchor.getX();
                    double deltaY = event.getY()-anchor.getY();

                    window.setX(window.getX()+deltaX);
                    window.setY(window.getY()+deltaY);
                }
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}