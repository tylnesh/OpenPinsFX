module eu.kohutek.openpinsfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens eu.kohutek.openpinsfx to javafx.fxml;
    exports eu.kohutek.openpinsfx;
}