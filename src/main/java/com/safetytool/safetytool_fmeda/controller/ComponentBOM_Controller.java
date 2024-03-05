package com.safetytool.safetytool_fmeda.controller;

import com.safetytool.safetytool_fmeda.model.Block_SPM_LFM_Model;
import com.safetytool.safetytool_fmeda.model.Component_BOM_Model;
import com.safetytool.safetytool_fmeda.util.ExcelExporter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.util.List;
import java.util.Map;

public class ComponentBOM_Controller {

    @FXML
    private TableView<Component_BOM_Model> tableView;
    @FXML
    private AnchorPane root;
    private ObservableList<Component_BOM_Model> data = FXCollections.observableArrayList();


    public void setComponentBom_Controller(){

    }

    public void initialize(){
        tableView.setItems(data);

        data.add(new Component_BOM_Model("1","1000","This is comp 1","temp1","20","Failed to load","XYZ","9000"));
        data.add(new Component_BOM_Model("2","1001","This is comp 2","temp2","30","Failed to load","PQR","9001"));

        Map<String, String> columnMappings = Map.of(
                "Component Id", "compId",
                "Part Number","partNumber",
                "Description","description",
                "Temp","temp",
                "FIT Rate", "FIT_Rate",
                "Failure Distribution","failureDistribution",
                "Block Id","blockId",
                "Function Id","functionId"
        );

        List<String> columnOrder = List.of("Component Id","Part Number", "Description","Temp","FIT Rate","Failure Distribution","Block Id","Function Id");
        for (String displayHeader : columnOrder) {
            String property = columnMappings.get(displayHeader);
            TableColumn<Component_BOM_Model, String> column = new TableColumn<>(displayHeader);
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
    public void exportToExcel(){
        ExcelExporter.exportToExcel(tableView, "component-bom",root);
    }
}
