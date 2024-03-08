package com.safetytool.safetytool_fmeda.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class StageUtil {

    public static void openStage(String fxmlPath, Parent parent, String title) {
        openStage(fxmlPath, parent, title, StageStyle.DECORATED);
    }

    public static void openPopup(String fxmlPath, Parent parent, String title) {
        openStage(fxmlPath, parent, title, StageStyle.UTILITY);
    }

    private static void openStage(String fxmlPath, Parent parent, String title, StageStyle stageStyle) {
        try {
            FXMLLoader loader = new FXMLLoader(StageUtil.class.getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = new Stage();

            // Set the stage style
            stage.initStyle(stageStyle);

            // Set application logo
            Image logo = new Image(StageUtil.class.getResourceAsStream("/images/logo.jpg"));
            stage.getIcons().add(logo);

            stage.setTitle(title);
            stage.initOwner(parent.getScene().getWindow());
            Scene scene = new Scene(root);
            stage.setScene(scene);

            if (stageStyle == StageStyle.UTILITY) {
                stage.initModality(Modality.APPLICATION_MODAL);
            }

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
