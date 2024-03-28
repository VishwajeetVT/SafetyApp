package com.safetytool.safetytool_fmeda.controller;

import com.safetytool.safetytool_fmeda.util.FolderExplorer;
import com.safetytool.safetytool_fmeda.util.StageUtil;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;

public class RootLayoutController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private MenuBar menuBar;
    @FXML
    private SplitPane splitPane;
    @FXML
    private TreeView<String> folderTreeView;
    @FXML
    private TableView<?> mainTableView;

    @FXML
    private TextArea textArea;
    @FXML
    private Pane drawingPane;
    @FXML
    private TabPane fileContainer;
    private FolderExplorer folderExplorer;
    private File selectedFile;
    private double initialX;
    private double initialY;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<SplitPane.Divider> dividers = splitPane.getDividers();
        this.mainTableView = new TableView<>();
        this.textArea = new TextArea();
        StageUtil.setFileContainer(fileContainer);
        this.folderExplorer = new FolderExplorer(folderTreeView);



//        drawingPane.setOnMousePressed(this::handleMousePressed);
//        drawingPane.setOnMouseDragged(this::handleMouseDragged);
        if (!dividers.isEmpty()) {
            dividers.get(0).positionProperty().bindBidirectional(folderTreeView.prefWidthProperty());
        }
    }

    private void handleMousePressed(MouseEvent event){
        initialX = event.getX();
        initialY = event.getY();
    }
    private void handleMouseDragged(MouseEvent event) {
        double currentX = event.getX();
        double currentY = event.getY();

        double width = Math.abs(currentX - initialX);
        double height = Math.abs(currentY - initialY);

        if (event.isControlDown()) {
            // Draw a circle
            Circle circle = new Circle(initialX, initialY, Math.min(width, height));
            circle.setFill(Color.BLUE);
            drawingPane.getChildren().add(circle);
        } else {
            // Draw a rectangle
            Rectangle rectangle = new Rectangle(initialX, initialY, width, height);
            rectangle.setFill(Color.RED);
            drawingPane.getChildren().add(rectangle);
        }
    }

    public void setRootLayoutController(){

    }

    @FXML
    private void openFolderStructure(){
        folderExplorer.selectAndDisplayFolder();
    }

    @FXML
    private void onTreeViewMouseClicked() {
        TreeItem<String> selectedItem = folderTreeView.getSelectionModel().getSelectedItem();
        if (selectedItem != null && selectedItem.isLeaf()) {
            String fileName = selectedItem.getValue();
            File rootDirectory = folderExplorer.getSelectedDirectory();
            if (rootDirectory != null) {
                String fullPath = rootDirectory.getAbsolutePath() + File.separator + constructPath(selectedItem);
                if (fileName.endsWith(".fxml")) {
                    StageUtil.openScreen(constructPath(selectedItem), selectedItem.getValue());
                } else {
                    // Show a message if the file format is not supported
                    showAlert("Unsupported File Format", "Selected file format is not supported.");
                }
                // Update the selectedFile field
                selectedFile = new File(fullPath);
            } else {
                showAlert("Error","Root directory is not selected");
            }
        }
    }
    private String constructPath(TreeItem<String> item) {
        StringBuilder path = new StringBuilder(item.getValue());
        TreeItem<String> parent = item.getParent();
        while (parent != null && !parent.getValue().equals("screen")) {
            path.insert(0, "/");
            path.insert(0, parent.getValue());
            parent = parent.getParent();
        }
        if (parent != null && parent.getValue().equals("screen")) {
            path.insert(0, "/screen/");
        }
        return path.toString();
    }




    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    public void getSystemSGs(){
        System.out.println("In the SGs Menu Item from System");
        StageUtil.openScreen("/screen/block-view.fxml","Block View");
    }

    @FXML
    public void getSystemSMs(){
        System.out.println("In the SMs Menu Item from System");
        StageUtil.openScreen("/screen/block-spm-lfm-view.fxml","Block SPM LFM");
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
        //StageUtil.openStage("/screen/components-bom-view.fxml", menuBar, "Components BOM");
        StageUtil.openScreen("/screen/components-bom-view.fxml","Components BOM");
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
        StageUtil.openScreen("/screen/help-view.fxml","About");
    }

}
