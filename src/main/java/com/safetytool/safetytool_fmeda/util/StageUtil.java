package com.safetytool.safetytool_fmeda.util;

import com.safetytool.safetytool_fmeda.controller.NewProjectController;
import com.safetytool.safetytool_fmeda.controller.RootLayoutController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.jar.JarEntry;

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

    public static void openPopupScreen(String fxmlPath, RootLayoutController rootLayoutController) {
        try {
            FXMLLoader loader = new FXMLLoader(StageUtil.class.getResource(fxmlPath));
            Parent parent = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(parent));

            if (fxmlPath.equals("/screen/new-project-view.fxml")) {
                NewProjectController controller = loader.getController();
                // Set the RootLayoutController in NewProjectController
                controller.setRootLayoutController(rootLayoutController);
                // Set the folderTreeView in NewProjectController
                controller.setFolderTreeView(rootLayoutController.folderTreeView);
            }

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
