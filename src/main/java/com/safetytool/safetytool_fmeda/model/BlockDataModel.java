package com.safetytool.safetytool_fmeda.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.HashMap;
import java.util.Map;

public class BlockDataModel {

    private final Map<String, StringProperty> properties = new HashMap<>();

    public BlockDataModel(String blockId, String blockName, String blockFunctionality, String sgNumber, String sgAsil) {
        setProperty("blockId", blockId);
        setProperty("blockName", blockName);
        setProperty("blockFunctionality", blockFunctionality);
        setProperty("sgNumber", sgNumber);
        setProperty("sgAsil", sgAsil);
    }

    // Getter for generic property access
    public StringProperty getProperty(String propertyName) {
        return properties.get(propertyName);
    }

    // Setter for generic property access
    public void setProperty(String propertyName, String value) {
        properties.put(propertyName, new SimpleStringProperty(value));
    }

    // Specific property getters
    public StringProperty blockIdProperty() {
        return properties.get("blockId");
    }

    public StringProperty blockNameProperty() {
        return properties.get("blockName");
    }

    public StringProperty blockFunctionalityProperty() {
        return properties.get("blockFunctionality");
    }

    public StringProperty sgNumberProperty() {
        return properties.get("sgNumber");
    }

    public StringProperty sgAsilProperty() {
        return properties.get("sgAsil");
    }
}
