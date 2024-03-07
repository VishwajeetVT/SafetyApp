package com.safetytool.safetytool_fmeda.util;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelExporter {

    public static <T> void exportToExcel(TableView<T> tableView, String fileName, AnchorPane root) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Data");

            // Create header row
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < tableView.getColumns().size(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(tableView.getColumns().get(i).getText());
            }

            // Populate data rows
            ObservableList<T> items = tableView.getItems();
            for (int rowIndex = 0; rowIndex < items.size(); rowIndex++) {
                Row dataRow = sheet.createRow(rowIndex + 1);

                for (int colIndex = 0; colIndex < tableView.getColumns().size(); colIndex++) {
                    Cell cell = dataRow.createCell(colIndex);
                    cell.setCellValue(String.valueOf(tableView.getColumns().get(colIndex).getCellData(rowIndex)));
                }
            }

            // Choose file location
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Excel File");
            fileChooser.setInitialFileName(fileName + ".xlsx");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));

            File file = fileChooser.showSaveDialog(root.getScene().getWindow());

            // Save the workbook to a file
            if (file == null) {
                System.out.println("Export to Excel canceled by the user.");
                return;
            }

            // Save the workbook to a file
            try (FileOutputStream fileOut = new FileOutputStream(file)) {
                workbook.write(fileOut);
                System.out.println("Export to Excel successful!");
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error exporting to Excel: " + e.getMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error exporting to Excel: " + e.getMessage());
        }
    }
}
