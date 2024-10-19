package com.example.sudoku.controller;

import com.example.sudoku.model.GameAdministrator;
import com.example.sudoku.model.Grid;
import com.example.sudoku.model.alert.AlertBox;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import org.w3c.dom.Text;

public class GameController {

    Grid gameBoard = new Grid();
    GameAdministrator admin = new GameAdministrator();
    boolean isWinner = false;
    int helpsAmount = 4;

    @FXML
    private Button playButton;
    @FXML
    private Button helpButton;
    @FXML
    private GridPane grid;

    @FXML
    private void playGame(ActionEvent event) {
        playButton.setDisable(true);
        playButton.setVisible(false);
        helpsAmount = 4;

        gameBoard.generateSudokuBoard();

        System.out.println("++++++++++SOLVED+++++++++");
        admin.setSolvedBoard(gameBoard.getFinalGrid());
        gameBoard.printBoard(admin.getSolvedBoard());

        System.out.println("+++++++++UNSOLVED+++++++++");
        admin.setUnsolvedBoard(gameBoard.getInitialGrid());
        gameBoard.printBoard(admin.getUnsolvedBoard());

        System.out.println("++++++++++PLAYER++++++++++");
        admin.setPlayerBoard(gameBoard.getPlayerGrid());
        gameBoard.printBoard(admin.getPlayerBoard());

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                TextField textField = (TextField) getNodeByRowColumnIndex(row, col, grid);
                textField.setEditable(true);

                if (textField != null) {
                    verifyTextField(textField);
                    if (admin.getUnsolvedBoard()[row][col] == 0) {
                        textField.setText(String.valueOf(""));
                        String backGroundColor = "-fx-background-color: #ffffff;";
                        textField.setStyle(textField.getStyle() + backGroundColor);
                    } else {
                        textField.setText(String.valueOf(admin.getUnsolvedBoard()[row][col]));
                        String backGroundColor = "-fx-background-color: #ffc6c6;";
                        textField.setStyle(textField.getStyle() + backGroundColor);
                        textField.setEditable(false);
                        textField.setDisable(true);
                    }
                }
            }
        }
    }

    @FXML
    private void useHelp(ActionEvent event) {
        if (helpsAmount == 0){
            helpButton.setDisable(true);
            helpButton.setText("Helps: 0");
        }
        else if (helpsAmount > 0){
            helpsAmount--;
            helpButton.setText("Helps: " + (helpsAmount));
            admin.gameHelp();
            for (int row = 0; row < 6; row++) {
                for (int col = 0; col < 6; col++) {
                    TextField textField = (TextField) getNodeByRowColumnIndex(row, col, grid);
                    textField.setEditable(true);

                    if (textField != null) {
                        verifyTextField(textField);
                        if (admin.getUnsolvedBoard()[row][col] == 0) {

                        }
                        else {
                            textField.setText(String.valueOf(admin.getUnsolvedBoard()[row][col]));
                            String backGroundColor = "-fx-background-color: #ffc6c6;";
                            textField.setStyle(textField.getStyle() + backGroundColor);
                            textField.setEditable(false);
                        }
                    }
                }
            }
        }

        isWinner = admin.verifyWinner(admin.getPlayerBoard(), admin.getSolvedBoard());
        if (isWinner){
            GameOver();
        }
    }

    @FXML
    private void verifyNumber(KeyEvent event) {
        getTextFieldValue(grid);

        System.out.println("+++++++++++++++");
        gameBoard.printBoard(admin.getUnsolvedBoard());
        System.out.println("+++++++++++++++");
        gameBoard.printBoard(admin.getPlayerBoard());

        isWinner = admin.verifyWinner(admin.getPlayerBoard(), admin.getSolvedBoard());
        if (isWinner){
            GameOver();
        }
    }

    public TextField getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        for (javafx.scene.Node node : gridPane.getChildren()) {
            Integer rowIndex = GridPane.getRowIndex(node);
            Integer colIndex = GridPane.getColumnIndex(node);

            if (rowIndex == null) rowIndex = 0;
            if (colIndex == null) colIndex = 0;

            if (rowIndex == row && colIndex == column) {
                return (TextField) node;
            }
        }
        return null;
    }

    public void verifyTextField(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            }

            if (newValue.length() > 1) {
                textField.setText(newValue.substring(newValue.length() - 1));
            }
        });
    }

    public void getTextFieldValue(GridPane gridPane) {
        for (var node : gridPane.getChildren()) {
            if (node instanceof TextField) {
                TextField textField = (TextField) node;

                textField.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
                    String submittedVal = textField.getText();
                    int val;
                    if (submittedVal == "")
                        val = 0;
                    else
                        val = Integer.parseInt(submittedVal);

                    Integer row = GridPane.getRowIndex(textField);
                    Integer column = GridPane.getColumnIndex(textField);

                    if (row == null) row = 0;
                    if (column == null) column = 0;

                    admin.modifyPlayerBoard(row, column, val);

                    if (admin.isValidMove(admin.getUnsolvedBoard(), row, column, val)) {
                        String backGroundColor = "-fx-background-color: lightgreen;";
                        textField.setStyle(textField.getStyle() + backGroundColor);
                    } else {
                        String backGroundColor = "-fx-background-color: #fc5b5b;";
                        textField.setStyle(textField.getStyle() + backGroundColor);
                    }
                });
            }
        }
    }

    public void GameOver(){
        helpButton.setDisable(true);
        playButton.setDisable(false);
        playButton.setVisible(true);
        playButton.setText("Play Again");

        new AlertBox().showAlert("Sudoku",
                null,
                "¡You Win!");
    }
}


/*
    public void monitorTextFields(GridPane gridPane) {

        for (var node : gridPane.getChildren()) {
            if (node instanceof TextField) {
                TextField textField = (TextField) node;

                textField.textProperty().addListener((observable, oldValue, newValue) -> {
                    String valorIngresado = newValue;
                    System.out.println("Valor ingresado: " + valorIngresado);
                });
            }
        }
    }
 */

