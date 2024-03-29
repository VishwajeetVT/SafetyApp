package com.safetytool.safetytool_fmeda;

import com.safetytool.safetytool_fmeda.controller.BlockViewController;
import com.safetytool.safetytool_fmeda.controller.Block_SPM_LFM_Controller;
import com.safetytool.safetytool_fmeda.controller.ComponentBOM_Controller;
import com.safetytool.safetytool_fmeda.controller.RootLayoutController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class SafetyApp extends Application {

    public static final String TITLE = "Safety Tool - FMEDA";
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/screen/Root-layout.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
       // BlockViewController controller = fxmlLoader.getController();
       // controller.setMainApp();
//        Block_SPM_LFM_Controller controller = fxmlLoader.getController();
//        controller.setBlockSPM_LFM_Controller();
//        ComponentBOM_Controller controller =fxmlLoader.getController();
//        controller.setComponentBom_Controller();

        RootLayoutController rootLayoutController = fxmlLoader.getController();
        rootLayoutController.setRootLayoutController();


        Image logo = new Image(getClass().getResourceAsStream("/images/logo.jpg"));
        stage.getIcons().add(logo);



        Rectangle2D screenBound = Screen.getPrimary().getVisualBounds();
        stage.setX(screenBound.getMinX());
        stage.setY(screenBound.getMinY());
        stage.setWidth(screenBound.getWidth());
        stage.setHeight(screenBound.getHeight());

        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}