package eu.kohutek.openpinsfx.gui;

import com.fazecast.jSerialComm.SerialPort;
import eu.kohutek.openpinsfx.OpenPinsFX;
import eu.kohutek.openpinsfx.backend.GameType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class MainWindow {
    @FXML
    HBox mainBox;
@FXML Button firstLaneButton;
@FXML Button secondLaneButton;
@FXML Button customGameButton;
@FXML
ImageView imageView;

@FXML
    ChoiceBox serialPortChoiceBox;



public void initialize(){

    mainBox.getStylesheets().add(OpenPinsFX.class.getResource("style.css").toString());
    mainBox.getStyleClass().add("background");
    firstLaneButton.getStyleClass().add("game-chooser-button");
    firstLaneButton.setOnAction( e -> {
        System.out.println("FullGame");
        FXMLLoader loader = new FXMLLoader(OpenPinsFX.class.getResource("game-view.fxml"));
        try {
            Stage newStage = new Stage();
            Scene scene = new Scene(loader.load());
            //OpenPinsFX.setScene(loader.load());
            GameWindow controller = loader.getController();
            controller.initData(GameType.FULLGAME);
            newStage.setTitle("Plna hra");
            newStage.setScene(scene);
            newStage.show();

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


        //GameWindow controller
        //OpenPinsFX.setScene("game-view.fxml");


    });

    secondLaneButton.getStyleClass().add("game-chooser-button");
    secondLaneButton.setOnAction( e -> {
       System.out.println("PartialGame");
    });

    customGameButton.getStyleClass().add("game-chooser-button");
    customGameButton.setOnAction( e -> {
        System.out.println("CustomPracticeGame");
    });

    Image img = new Image(OpenPinsFX.class.getResource("images/logo.png").toString());
    imageView.setImage(img);

    List<SerialPort> serialPorts = OpenPinsFX.getSerialInstance().getPorts();
    serialPortChoiceBox.getItems().addAll(serialPorts);
    serialPortChoiceBox.setOnAction(e -> {
        OpenPinsFX.getSerialInstance().establishConnection(serialPorts.get(serialPortChoiceBox.getSelectionModel().getSelectedIndex()));
        System.out.println(serialPortChoiceBox.getValue());

    });






}
}