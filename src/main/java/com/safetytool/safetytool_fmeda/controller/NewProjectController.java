package com.safetytool.safetytool_fmeda.controller;

import com.safetytool.safetytool_fmeda.util.FolderExplorer;
import com.safetytool.safetytool_fmeda.util.StageUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;

public class NewProjectController {

    @FXML
    private AnchorPane root;
    @FXML
    private TextField projectNameField;
    @FXML
    private TextField locationField;
    @FXML
    private FolderExplorer folderExplorer;
    @FXML
    private Button selectLocationButton;
    @FXML
    private Button createButton;
    @FXML
    private Button cancelButton;

    private TreeView<String> folderTreeView;
    private RootLayoutController rootLayoutController;

    public void setRootLayoutController(RootLayoutController rootLayoutController){
        this.rootLayoutController = rootLayoutController;
    }

    public void setFolderTreeView(TreeView<String> folderTreeView) {
        this.folderTreeView = folderTreeView;
    }


    @FXML
    private void handleSelectLocation() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Location");

        File selectedDirectory = directoryChooser.showDialog(root.getScene().getWindow());

        if (selectedDirectory != null){
            locationField.setText(selectedDirectory.getAbsolutePath());
        }
    }

    @FXML
    private void handleCreateProject(){
        String projectName = projectNameField.getText().trim();
        String location = locationField.getText().trim();

        if (projectName.isEmpty() || location.isEmpty()){
            showAlert("Error","Please enter a project name and select a location", Alert.AlertType.ERROR);
            return;
        }

        File projectDirectory = new File(location, projectName);
        if (projectDirectory.exists()){
            showAlert("Error","A project with the same name already exists at the selected location", Alert.AlertType.ERROR);
        }

        boolean created = projectDirectory.mkdir();
        if (created) {
            showAlert("Success","Project created successfully.", Alert.AlertType.INFORMATION);

            //StageUtil.openScreen("/screen/root-layout.fxml", "Root Layout");
            rootLayoutController.updateTreeViewWithNewProject(projectDirectory);
            // Close the current stage
            Stage stage = (Stage) root.getScene().getWindow();
            stage.close();
        } else {
            showAlert("Error","Failed to create project.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleCancel(){
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
