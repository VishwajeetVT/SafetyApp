package com.safetytool.safetytool_fmeda.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class StageUtil {

    public static void openStage(String fxmlPath, Parent parent, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(StageUtil.class.getResource(fxmlPath));
            Parent root = loader.load();

            // Set preferred width and height
//            root.setPrefWidth(700);
//            root.setPrefHeight(500);

            Stage stage = new Stage();
            stage.setTitle(title);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(parent.getScene().getWindow());
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

