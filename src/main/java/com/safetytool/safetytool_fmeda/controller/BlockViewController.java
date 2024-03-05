package com.safetytool.safetytool_fmeda.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import com.safetytool.safetytool_fmeda.model.BlockDataModel;

import java.util.List;
import java.util.Map;

public class BlockViewController {

    @FXML
    private TableView<BlockDataModel> tableView;

    private final ObservableList<BlockDataModel> data = FXCollections.observableArrayList();

    public void setMainApp() {
    }

    public void initialize() {
        // Set the items for the table
        tableView.setItems(data);

        // Add sample data (you can replace this with your backend logic)
        data.add(new BlockDataModel("1", "Block A", "Function A", "123", "ASIL A"));
        data.add(new BlockDataModel("2", "Block B", "Function B", "456", "ASIL B"));
        data.add(new BlockDataModel("3", "Block C", "Function C", "789", "ASIL C"));
        data.add(new BlockDataModel("4", "Block D", "Function D", "100", "ASIL D"));
        data.add(new BlockDataModel("1", "Block A", "Function A", "123", "ASIL A"));
        data.add(new BlockDataModel("2", "Block B", "Function B", "456", "ASIL B"));

        // Create a mapping between display headers and property names
        Map<String, String> columnMappings = Map.of(
                "Block Id", "blockId",
                "Block Name", "blockName",
                "Block Functionality", "blockFunctionality",
                "SG Number", "sgNumber",
                "SG ASIL", "sgAsil"
        );

        // Dynamically add columns based on display headers in a specific order
        List<String> columnOrder = List.of(
                "Block Id", "Block Name", "Block Functionality", "SG Number", "SG ASIL"
        );

        for (String displayHeader : columnOrder) {
            String property = columnMappings.get(displayHeader);
            TableColumn<BlockDataModel, String> column = new TableColumn<>(displayHeader);
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

    // Add a method to dynamically add data to the table
    public void addDataToTable(BlockDataModel dataModel) {
        data.add(dataModel);
    }

    // Helper method to capitalize the first letter of a string
    private String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    @FXML
    private void exportToExcel(){
        System.out.println("Exporting to Excel");
    }
}
