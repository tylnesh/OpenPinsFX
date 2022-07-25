module eu.kohutek.openpinsfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.desktop;
    requires com.fazecast.jSerialComm;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires lombok;

    opens eu.kohutek.openpinsfx to javafx.fxml;
    exports eu.kohutek.openpinsfx;
    exports eu.kohutek.openpinsfx.gui;
    opens eu.kohutek.openpinsfx.gui to javafx.fxml;

    opens eu.kohutek.openpinsfx.backend to com.fasterxml.jackson.databind;
}