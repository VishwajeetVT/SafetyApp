package com.safetytool.safetytool_fmeda.util;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.DirectoryChooser;
import java.io.File;

public class FolderExplorer {

    private TreeView<String> folderTreeView;
    private File selectedDirectory;

    public FolderExplorer(TreeView<String> folderTreeView) {
        this.folderTreeView = folderTreeView;
    }

    public void selectAndDisplayFolder() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        selectedDirectory = directoryChooser.showDialog(null);
        if (selectedDirectory != null) {
            displayFolderContent(selectedDirectory);
        }
    }

    public void displayFolderContent(File directory) {
        folderTreeView.setRoot(createTree(directory));
    }

    private TreeItem<String> createTree(File file) {
        TreeItem<String> root = new TreeItem<>(file.getName());
        File[] files = file.listFiles();
        if (files != null) {
            for (File childFile : files) {
                if (childFile.isDirectory()) {
                    root.getChildren().add(createTree(childFile));
                } else {
                    if (isCsvOrExcelFileOrFxmlFile(childFile.getName())) {
                        root.getChildren().add(new TreeItem<>(childFile.getName()));
                    }
                }
            }
        }
        return root;
    }

    private boolean isCsvOrExcelFileOrFxmlFile(String fileName) {
        return fileName.endsWith(".csv") || fileName.endsWith(".xls") || fileName.endsWith(".xlsx") || fileName.endsWith(".fxml");
    }

    public File getSelectedDirectory(){
        return selectedDirectory;
    }
}