package eu.kohutek.openpinsfx;

import eu.kohutek.openpinsfx.backend.GameStatus;
import eu.kohutek.openpinsfx.backend.GameStatusProperty;
import eu.kohutek.openpinsfx.backend.SerialComm;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class OpenPinsFX extends Application {
    static FXMLLoader fxmlLoader;
    static Scene scene;
    static SerialComm comm;
    static GameStatus status;
    static GameStatusProperty statusProperty;
    @Override
    public void start(Stage stage) throws IOException {
        Platform.setImplicitExit(true);
        comm = new SerialComm();
        status = new GameStatus();
        statusProperty = new GameStatusProperty();
        fxmlLoader = new FXMLLoader(OpenPinsFX.class.getResource("main-view.fxml"));
        scene = new Scene(fxmlLoader.load(), 1280, 800);
        stage.setOnCloseRequest(e -> {
            comm.closeConnection();
        });
        stage.setTitle("OpenPinsFX - Free and Open Source Ninepins Game");
        stage.setScene(scene);
        stage.show();


    }


    public static void main(String[] args) {
        launch();
    }

    public static SerialComm getSerialInstance() {
        return comm;
    }

    public static GameStatusProperty getStatusPropInstance() { return statusProperty; }

}