package eu.kohutek.openpinsfx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.MalformedURLException;
import java.net.URL;

public class MainWindow {

@FXML Button firstLaneButton;
@FXML Button secondLaneButton;
@FXML
ImageView imageView;



public void initialize(){
    firstLaneButton.setOnAction( e -> {
        System.out.println("FullGame");
    });

    secondLaneButton.setOnAction( e -> {
       System.out.println("PartialGame");
    });


}
}