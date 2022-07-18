package eu.kohutek.openpinsfx.backend;
import com.fazecast.jSerialComm.*;

import java.io.Serial;
import java.util.Arrays;

public class SerialComm {
    SerialPort comPort;
    public SerialComm() {
        System.out.println(Arrays.stream(SerialPort.getCommPorts()).toList());

        comPort = SerialPort.getCommPorts()[1];
        System.out.println(comPort.getDescriptivePortName());
        comPort.openPort();
        comPort.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_AVAILABLE; }
            @Override
            public void serialEvent(SerialPortEvent event)
            {
                if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
                    return;

                if (comPort.bytesAvailable() >= 6)
                {
                    byte[] newData = new byte[comPort.bytesAvailable()];
                    int numRead = comPort.readBytes(newData, newData.length);
                    System.out.println("Read " + numRead + " bytes.");
                    for (byte b:newData){
                        System.out.print((char) b);
                    }

                }

            }
        });
    }
}

