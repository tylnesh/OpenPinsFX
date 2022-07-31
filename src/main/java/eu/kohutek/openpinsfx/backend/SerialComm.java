package eu.kohutek.openpinsfx.backend;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ContainerNode;
import com.fazecast.jSerialComm.*;
import eu.kohutek.openpinsfx.OpenPinsFX;
import eu.kohutek.openpinsfx.gui.GameWindow;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.io.Serial;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;



public class SerialComm {
    private static SerialPort comPort;
    private static ObjectMapper mapper = new ObjectMapper();

    private GameWindow activeWindow;



    public SerialComm() {
        System.out.println(Arrays.stream(SerialPort.getCommPorts()).toList());
    }

    public List<SerialPort> getPorts() {
        List<SerialPort> portsList = Arrays.stream(comPort.getCommPorts()).toList();
        return portsList;
    }

    public void establishConnection (SerialPort serialPort) {
        comPort = serialPort;
        comPort.openPort();
        MessageListener listener = new MessageListener();
        comPort.addDataListener(listener);
    }

    public void closeConnection() {

        comPort.removeDataListener();
        comPort.closePort();

    }


    private final class MessageListener implements SerialPortMessageListener
    {
        @Override
        public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_RECEIVED; }

        @Override
        public byte[] getMessageDelimiter() { return new byte[] { (byte)0x0B, (byte)0x65 }; }

        @Override
        public boolean delimiterIndicatesEndOfMessage() { return true; }

        @Override
        public void serialEvent(SerialPortEvent event)
        {
            byte[] data = event.getReceivedData();
            char[] msg = new char[data.length-2];
            for (int i = 0; i<data.length-2;i++) msg[i] = (char) data[i];

            String jsonStr = String.valueOf(msg);
            System.out.println("Received the following delimited message: " + jsonStr);

            if(!isValidJson(jsonStr)) {
                System.out.println("Received invalid json");
                return;
            }
            else {
                try {
                    GameStatus receivedStatus = mapper.readValue(jsonStr, GameStatus.class);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            OpenPinsFX.getStatusPropInstance().updateGameStatusProperty(receivedStatus);
                        }
                    });
                } catch (JsonProcessingException e) {
                    System.out.println(e);
                }
            }
        }

        public boolean isValidJson(final String json) {
            try {
                final ObjectMapper objectMapper = new ObjectMapper();
                final JsonNode jsonNode = objectMapper.readTree(json);
                return jsonNode instanceof ContainerNode;
            } catch (JsonProcessingException jpe) {
                return false;
            }
        }
    }

        public void setActiveWindow(GameWindow window) {
            System.out.println("Active window is: " + window.getClass().toString());
            this.activeWindow = window;
        }

}

