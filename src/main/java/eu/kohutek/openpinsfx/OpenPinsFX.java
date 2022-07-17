package eu.kohutek.openpinsfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class OpenPinsFX extends Application {
    static FXMLLoader fxmlLoader;
    static Scene scene;
    @Override
    public void start(Stage stage) throws IOException {
        fxmlLoader = new FXMLLoader(OpenPinsFX.class.getResource("main-view.fxml"));
        scene = new Scene(fxmlLoader.load(), 1280, 800);
        stage.setTitle("OpenPinsFX - Free and Open Source Ninepins Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void setScene(String fxml) {
        try {
            Parent pane = fxmlLoader.load(OpenPinsFX.class.getResource(fxml));
            scene.setRoot(pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        launch();
    }
}