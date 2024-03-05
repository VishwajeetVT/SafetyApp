package com.safetytool.safetytool_fmeda.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.HashMap;
import java.util.Map;

public class Block_SPM_LFM_Model {

    private final Map<String, StringProperty> properties = new HashMap<>();

    public Block_SPM_LFM_Model(String compId, String name, String functionality, String failureMode, String impactOfBlock, String impactOnSG, String FIT, String failureDistribution){
        setProperty("compId", compId);
        setProperty("name",name);
        setProperty("functionality", functionality);
        setProperty("failureMode", failureMode);
        setProperty("impactOfBlock", impactOfBlock);
        setProperty("impactOnSG", impactOnSG);
        setProperty("FIT", FIT);
        setProperty("failureDistribution",failureDistribution);
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

    public StringProperty getName(){
        return properties.get("name");
    }

    public StringProperty getFunctionality() {
        return properties.get("functionality");
    }

    public StringProperty getFailureMode() {
        return properties.get("failureMode");
    }

    public StringProperty getImpactOfBlock() {
        return properties.get("impactOfBlock");  // Use consistent key here
    }

    public StringProperty getImpactOnSG() {
        return properties.get("impactOnSG");
    }

    public StringProperty getFIT() {
        return properties.get("FIT");
    }

    public StringProperty getFailureDistribution() {
        return properties.get("failureDistribution");
    }
}
