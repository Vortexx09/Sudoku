package com.example.sudoku.model.alert;

import javafx.scene.control.Alert;

public class AlertBox implements AlertBoxInterface {
    @Override
    public void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
