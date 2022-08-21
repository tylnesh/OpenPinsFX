package eu.kohutek.openpinsfx.gui;

import eu.kohutek.openpinsfx.OpenPinsFX;
import eu.kohutek.openpinsfx.backend.GameStatus;
import eu.kohutek.openpinsfx.backend.GameStatusProperty;
import eu.kohutek.openpinsfx.backend.GameType;
import eu.kohutek.openpinsfx.backend.SerialComm;
import javafx.application.Platform;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;

import javax.swing.event.DocumentEvent;
import java.beans.EventHandler;
import java.util.ArrayList;

public class GameWindow {

    private GameType gameType;


    private SerialComm comm;

    @FXML
    GridPane pinGrid;
    @FXML
    BorderPane mainPane;
    @FXML
    Button pinSetupButton;

    @FXML Label throwsPanel;
    @FXML Label scorePanel;
    @FXML Label pinsPanel;

    static Image pinStanding = new Image(OpenPinsFX.class.getResource("images/pinStanding.png").toString());
    static Image pinHit = new Image(OpenPinsFX.class.getResource("images/pinHit.png").toString());

    private static GameStatusProperty statusProperty = OpenPinsFX.getStatusPropInstance();
    static ArrayList<ImageView> pinsViews;

    public void initData(GameType type) {
        gameType = type;
        System.out.println(gameType);
    }
    public void initialize(){
        //SerialComm comm = new SerialComm();
        comm = OpenPinsFX.getSerialInstance();
        //comm.sendMessage(new Message());
        mainPane.getStylesheets().add(OpenPinsFX.class.getResource("style.css").toString());
        pinsViews = generatePinsViews();
        pinGrid.setMaxWidth(640);
        pinGrid.setMaxHeight(480);
        populatePinGrid(pinsViews);

        throwsPanel.textProperty().bind(statusProperty.getBallsThrown().asString());
        scorePanel.textProperty().bind(statusProperty.getScore().asString());
        statusProperty.getBallsThrown().addListener(GameWindow::changed);

        //pinsPanel.textProperty().bind()

        pinSetupButton.setOnAction(e -> {
            comm.sendNumber(1);
        });


    }

    private void populatePinGrid(ArrayList<ImageView> pinsViews) {
        pinGrid.addRow(0,new Label(""),new Label(""), pinsViews.get(0),new Label(""),new Label(""));
        pinGrid.addRow(1,new Label(""), pinsViews.get(1),new Label(""),pinsViews.get(2),new Label(""));
        pinGrid.addRow(2, pinsViews.get(3),new Label(""),pinsViews.get(4),new Label(""), pinsViews.get(5));
        pinGrid.addRow(3,new Label(""), pinsViews.get(6),new Label(""),pinsViews.get(7),new Label(""));
        pinGrid.addRow(4,new Label(""),new Label(""), pinsViews.get(8),new Label(""),new Label(""));
    }



    private ArrayList<ImageView> generatePinsViews() {
        ArrayList<ImageView> pinsView = new ArrayList<>();
        for (int i = 0; i< 9; i++) {
            ImageView imgView = new ImageView(pinStanding);
//            imgView.setOnMouseClicked( e -> {
//                if (imgView.getImage().equals(pinStanding)) {
//                    imgView.setImage(pinHit);
//                } else {
//                    imgView.setImage(pinStanding);
//                }
//            });
            pinsView.add(imgView);
        }
        return pinsView;
    }

    private static void updatePinsViews() {
        for (int i = 0; i<9; i++) {
            System.out.println(statusProperty.getPinState()[i]);
            if (statusProperty.getPinState()[i]) {
                pinsViews.get(i).setImage(pinHit);
            } else {
                pinsViews.get(i).setImage(pinStanding);
            }
        }
    }

    public static void changed(ObservableValue<? extends Number> prop,
                               Number oldValue,
                               Number newValue) {
        System.out.println("updating pins");
        updatePinsViews();
    }


}
