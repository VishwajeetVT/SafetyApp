package com.safetytool.safetytool_fmeda.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.IOException;

public class StageUtil {

    private static TabPane fileContainer;

    public static void setFileContainer(TabPane container) {
        fileContainer = container;
    }

    public static void openScreen(String fxmlPath, String title) {
        // Check if a tab with the same content already exists
        for (Tab tab : fileContainer.getTabs()) {
            FXMLLoader loader = (FXMLLoader) tab.getContent().getUserData();
            if (loader != null && loader.getLocation().toString().equals(StageUtil.class.getResource(fxmlPath).toString())) {
                fileContainer.getSelectionModel().select(tab); // Select the existing tab
                return;
            }
        }

        // If no existing tab found, create a new one
        try {
            FXMLLoader loader = new FXMLLoader(StageUtil.class.getResource(fxmlPath));
            Tab tab = new Tab(title);
            tab.setContent(loader.load());
            // Set user data to hold the FXMLLoader instance for comparison
            tab.getContent().setUserData(loader);
            fileContainer.getTabs().add(tab);
            fileContainer.getSelectionModel().select(tab); // Select the newly opened tab
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
