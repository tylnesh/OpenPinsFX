package eu.kohutek.openpinsfx.gui;

import eu.kohutek.openpinsfx.OpenPinsFX;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class MainWindow {
    @FXML
    HBox mainBox;
@FXML Button firstLaneButton;
@FXML Button secondLaneButton;
@FXML Button customGameButton;
@FXML
ImageView imageView;



public void initialize(){

    mainBox.getStylesheets().add(OpenPinsFX.class.getResource("style.css").toString());
    mainBox.getStyleClass().add("background");
    firstLaneButton.getStyleClass().add("game-chooser-button");
    firstLaneButton.setOnAction( e -> {
        System.out.println("FullGame");
        OpenPinsFX.setScene("game-view.fxml");

    });

    secondLaneButton.getStyleClass().add("game-chooser-button");
    secondLaneButton.setOnAction( e -> {
       System.out.println("PartialGame");
    });

    customGameButton.getStyleClass().add("game-chooser-button");
    customGameButton.setOnAction( e -> {
        System.out.println("CustomPracticeGame");
    });

    Image img = new Image(OpenPinsFX.class.getResource("logo.png").toString());
    imageView.setImage(img);


}
}