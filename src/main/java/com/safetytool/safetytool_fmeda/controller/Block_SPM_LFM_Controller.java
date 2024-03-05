package com.safetytool.safetytool_fmeda.controller;

import com.safetytool.safetytool_fmeda.SafetyApp;
import com.safetytool.safetytool_fmeda.model.BlockDataModel;
import com.safetytool.safetytool_fmeda.model.Block_SPM_LFM_Model;
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
        System.out.println("Export to Excel event");
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("BlockData");

            // Create header row
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < tableView.getColumns().size(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(tableView.getColumns().get(i).getText());
            }

            // Populate data rows
            for (int rowIndex = 0; rowIndex < tableView.getItems().size(); rowIndex++) {
                Row dataRow = sheet.createRow(rowIndex + 1);

                for (int colIndex = 0; colIndex < tableView.getColumns().size(); colIndex++) {
                    Cell cell = dataRow.createCell(colIndex);
                    cell.setCellValue(String.valueOf(tableView.getColumns().get(colIndex).getCellData(rowIndex)));
                }
            }
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Excel File");
            fileChooser.setInitialFileName("block_data.xlsx");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));

            File file = fileChooser.showSaveDialog(root.getScene().getWindow());

            // Save the workbook to a file
            try (FileOutputStream fileOut = new FileOutputStream(file)) {
                workbook.write(fileOut);
            }

            System.out.println("Export to Excel successful!");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error exporting to Excel: " + e.getMessage());
        }
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
