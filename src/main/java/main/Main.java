package main;

import eu.lestard.easydi.EasyDI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URI;
import java.net.URL;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        EasyDI context = new EasyDI();

        final String pathToMain = "/layout/main.fxml";
        final URL mainFxmlUrl = this.getClass().getResource(pathToMain);

        if(mainFxmlUrl == null) {
            throw new IllegalStateException("Can't find Main.fxml file with path: " + pathToMain);
        }

        FXMLLoader loader = new FXMLLoader(mainFxmlUrl);

        loader.setControllerFactory((requestedType) -> context.getInstance(requestedType));
        loader.load();

        Parent root = loader.getRoot();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
