package eu.kohutek.openpinsfx.backend;
import com.fazecast.jSerialComm.*;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class SerialComm {
    SerialPort comPort;
    public SerialComm() {
        System.out.println(Arrays.stream(SerialPort.getCommPorts()).toList());
//        comPort = SerialPort.getCommPorts()[1];
//        System.out.println(comPort.getDescriptivePortName());
//        comPort.openPort();
//        comPort.addDataListener(new SerialPortDataListener() {
//            @Override
//            public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_AVAILABLE; }
//            @Override
//            public void serialEvent(SerialPortEvent event)
//            {
//                if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
//                    return;
//
//                if (comPort.bytesAvailable() >= 6)
//                {
//                    byte[] newData = new byte[comPort.bytesAvailable()];
//                    int numRead = comPort.readBytes(newData, newData.length);
//                    System.out.println("Read " + numRead + " bytes.");
//                    for (byte b:newData){
//                        System.out.print((char) b);
//                    }
//
//                }
//
//            }
//        });
    }

    public List<SerialPort> getPorts() {
        List<SerialPort> portsList = Arrays.stream(comPort.getCommPorts()).toList();
        return portsList;
    }
}

