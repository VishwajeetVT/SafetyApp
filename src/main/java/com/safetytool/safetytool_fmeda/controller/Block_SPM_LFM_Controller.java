package com.safetytool.safetytool_fmeda.controller;

import com.safetytool.safetytool_fmeda.SafetyApp;
import com.safetytool.safetytool_fmeda.model.BlockDataModel;
import com.safetytool.safetytool_fmeda.model.Block_SPM_LFM_Model;
import com.safetytool.safetytool_fmeda.util.ExcelExporter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Block_SPM_LFM_Controller {

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<Block_SPM_LFM_Model> tableView;
    private final ObservableList<Block_SPM_LFM_Model> data = FXCollections.observableArrayList();

    public void setBlockSPM_LFM_Controller(){

    }

    public void initialize(){
        tableView.setItems(data);

        data.add(new Block_SPM_LFM_Model("1","Component 1","Functionality A","F","XYZ","SG 1","X","FD"));
        data.add(new Block_SPM_LFM_Model("2","Component 2","Functionality B","N","ABC","SG 2","Y","FD2"));
        data.add(new Block_SPM_LFM_Model("1","Component 1","Functionality A","F","XYZ","SG 1","X","FD"));
        data.add(new Block_SPM_LFM_Model("2","Component 2","Functionality B","N","ABC","SG 2","Y","FD2"));
        data.add(new Block_SPM_LFM_Model("1","Component 1","Functionality A","F","XYZ","SG 1","X","FD"));
        data.add(new Block_SPM_LFM_Model("2","Component 2","Functionality B","N","ABC","SG 2","Y","FD2"));

        Map<String, String> columnMappings = Map.of(
                "Component Id", "compId",
                "Name","name",
                "Functionality","functionality",
                "Failure Mode","failureMode",
                "Impact Of Block", "impactOfBlock",
                "Impact On SG","impactOnSG",
                "FIT","FIT",
                "Failure Distribution","failureDistribution"
        );

        List<String> columnOrder = List.of("Component Id","Name","Functionality",
                "Failure Mode","Impact Of Block","Impact On SG","FIT","Failure Distribution");
        for (String displayHeader : columnOrder) {
            String property = columnMappings.get(displayHeader);
            TableColumn<Block_SPM_LFM_Model, String> column = new TableColumn<>(displayHeader);
            column.setCellValueFactory(cellData -> cellData.getValue().getProperty(property));

            column.prefWidthProperty().bind(tableView.widthProperty().divide(columnOrder.size()));

            tableView.getColumns().add(column);
        }

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setFixedCellSize(30); // Set a fixed row height
        tableView.prefHeightProperty().bind(tableView.fixedCellSizeProperty().multiply(data.size()).add(31));

        // Optional: If you want to disable the vertical scrollbar
        tableView.setMinHeight(TableView.USE_PREF_SIZE);
        tableView.setMaxHeight(TableView.USE_PREF_SIZE);
    }

    @FXML
    private void exportToExcel() {
        ExcelExporter.exportToExcel(tableView, "block-spm-lfm",root);
    }

    @FXML
    private void selectBlock() {
        System.out.println("Select block Button clicked...!!!");
    }


    @FXML
    private Button btnNavigate;
    @FXML
    public void navigateToAnotherScreen(ActionEvent event) {
        try {
            // Load the new screen FXML file
            // Load the new screen FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/block-view.fxml"));
            Parent newScreen = loader.load();

            // Replace the content of the existing window with the new screen
            root.getChildren().setAll(newScreen);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately (e.g., show an error dialog)
        }
    }
}
