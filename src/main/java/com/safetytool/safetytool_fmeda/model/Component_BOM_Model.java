package com.safetytool.safetytool_fmeda.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.HashMap;
import java.util.Map;

public class Component_BOM_Model {

    private final Map<String, StringProperty> properties = new HashMap<>();

    public Component_BOM_Model(String compId, String partNumber, String description, String temp, String FIT_Rate, String failureDistribution, String blockId, String functionId){
        setProperty("compId", compId);
        setProperty("partNumber", partNumber);
        setProperty("description", description);
        setProperty("temp",temp);
        setProperty("FIT_Rate", FIT_Rate);
        setProperty("failureDistribution", failureDistribution);
        setProperty("blockId", blockId);
        setProperty("functionId", functionId);
    }

    public void setProperty(String propertyName, String value) {
        properties.put(propertyName, new SimpleStringProperty(value));
    }
    public StringProperty getProperty(String propertyName) {
        return properties.get(propertyName);
    }

    public StringProperty getCompId(){
        return properties.get("compId");
    }

    public StringProperty getPartNumber(){
        return properties.get("partNumber");
    }

    public StringProperty getDescription(){
        return properties.get("description");
    }

    public StringProperty getTemp(){
        return properties.get("temp");
    }

    public StringProperty getFITRate(){
        return properties.get("FIT_Rate");
    }

    public StringProperty getFailureDescription(){
        return properties.get("failureDescription");
    }

    public StringProperty getBlockId(){
        return properties.get("blockId");
    }

    public StringProperty getFunctionId(){
        return properties.get("functionId");
    }
}
