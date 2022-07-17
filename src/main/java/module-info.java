module eu.kohutek.openpinsfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.desktop;
    requires com.fazecast.jSerialComm;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires lombok;

    opens eu.kohutek.openpinsfx to javafx.fxml;
    exports eu.kohutek.openpinsfx;
    exports eu.kohutek.openpinsfx.gui;
    opens eu.kohutek.openpinsfx.gui to javafx.fxml;
}