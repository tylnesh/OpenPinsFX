package eu.kohutek.openpinsfx.backend;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fazecast.jSerialComm.*;

import java.io.IOException;
import java.io.Serial;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;



public class SerialComm {
    private static SerialPort comPort;
    private static ObjectMapper mapper = new ObjectMapper();

    public SerialComm() {
        System.out.println(Arrays.stream(SerialPort.getCommPorts()).toList());
    }

    public List<SerialPort> getPorts() {
        List<SerialPort> portsList = Arrays.stream(comPort.getCommPorts()).toList();
        return portsList;
    }

    public void establishConnection (SerialPort serialPort) {
        SerialPort comPort = serialPort;
        comPort.openPort();
        MessageListener listener = new MessageListener();
        comPort.addDataListener(listener);
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

            try {
                    GameStatus status = mapper.readValue(jsonStr, GameStatus.class);
                    System.out.println(status.toString());

                } catch(IOException ex) {
                    System.out.println(ex);
                }
        }
    }
}

