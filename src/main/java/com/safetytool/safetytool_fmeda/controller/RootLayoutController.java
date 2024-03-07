package com.safetytool.safetytool_fmeda.controller;

import com.safetytool.safetytool_fmeda.util.StageUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class RootLayoutController {

    @FXML
    private AnchorPane root;

    @FXML
    private MenuBar menuBar;

    public void setRootLayoutController(){

    }


    @FXML
    public void getSystemSGs(){
        System.out.println("In the SGs Menu Item from System");
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/block-view.fxml"));
//            Parent blockViewScreen = loader.load();
//
//            blockViewScreen.prefHeight(800);
//            blockViewScreen.prefWidth(600);
//
//            Stage stage = new Stage();
//            stage.setTitle("Block View");
//            stage.initModality(Modality.APPLICATION_MODAL);
//            stage.initOwner(menuBar.getScene().getWindow());
//            Scene scene = new Scene(blockViewScreen);
//            stage.setScene(scene);
//            stage.showAndWait();
//        } catch (IOException e){
//            e.printStackTrace();
//        }

        StageUtil.openStage("/screen/block-view.fxml", root,"Block View");
    }

    @FXML
    public void getSystemSMs(){
        System.out.println("In the SMs Menu Item from System");
        StageUtil.openStage("/screen/block-spm-lfm-view.fxml", menuBar,"Block SPM/LFM");

    }
    @FXML
    public void getSystemRBD(){
        System.out.println("In the RBD Menu Item from System");
    }
    @FXML
    public void getSystemReports(){
        System.out.println("In the Report Menu Item from System");
    }

    @FXML
    public void getBlockSMs(){
        System.out.println("In the SMs Menu Item from Block");
    }

    @FXML
    public void getBlockComponents(){
        System.out.println("In the Components Menu Item from Block");
    }
    @FXML
    public void getBlockFailureModes(){
        System.out.println("In the Failure Modes Menu Item from Block");
    }

    @FXML
    public void getBlockFIT_Rates(){
        System.out.println("In the FIT Rates Menu Item from Block");
    }

    @FXML
    public void getComponentsFailureModes(){
        System.out.println("In the Failure Modes Item from Components");
    }

    @FXML
    public void getComponentsBOM_Import(){
        System.out.println("In the BOM Import Item from Components");
    }

    @FXML
    public void getComponentsFIT_Rates(){
        System.out.println("In the FIT Rates Item from Components");
    }

    @FXML
    public void getComponentsTempProfile(){
        System.out.println("In the Temp Profile Item from Components");
    }

    @FXML
    public void getAboutHelp(){
        System.out.println("In the Help Item from About");
        StageUtil.openStage("/screen/help-view.fxml",menuBar,"Help");
    }




}
