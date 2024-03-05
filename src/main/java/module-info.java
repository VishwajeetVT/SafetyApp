module com.safetytool.safetytool_fmeda {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.compiler;
    requires commons.csv;
    requires com.jfoenix;
    requires java.logging;
    requires com.opencsv;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;

    opens com.safetytool.safetytool_fmeda to javafx.fxml;
    exports com.safetytool.safetytool_fmeda;

    opens com.safetytool.safetytool_fmeda.controller to javafx.fxml;
    exports com.safetytool.safetytool_fmeda.controller;

    opens com.safetytool.safetytool_fmeda.model to javafx.fxml;
    exports com.safetytool.safetytool_fmeda.model;

}