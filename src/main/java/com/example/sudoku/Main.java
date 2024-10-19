package com.example.sudoku;

import com.example.sudoku.model.GameAdministrator;
import com.example.sudoku.view.GameView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        GameView.getInstance();
    }

    public static void main(String[] args) {
        int[][] a = {{1,2},{3,4}};
        int[][] b = {{5,6},{7,8}};
        int[][] c = {{1,2},{3,4}};

        GameAdministrator admin = new GameAdministrator();


        launch();
    }
}

/*
Grid grid = new Grid();
        GameAdministrator admin = new GameAdministrator();
        int[][] solvedBoard = new int[6][6];
        int[][] unsolvedBoard = new int[6][6];

        System.out.println("+++++++++SOLVED GRID+++++++++");
        grid.generateSudokuBoard();
        solvedBoard = grid.finalGrid;
        admin.setSolvedBoard(solvedBoard);
        grid.printBoard(admin.getSolvedBoard());

        System.out.println("++++++++UNSOLVED GRID++++++++");
        unsolvedBoard = grid.initialGrid;
        admin.setUnsolvedBoard(unsolvedBoard);
        grid.printBoard(admin.getUnsolvedBoard());

        System.out.println("++++++++HELP APPLIED++++++");
        admin.gameHelp();
        grid.printBoard(admin.getUnsolvedBoard());

        System.out.println("++++++++HELP APPLIED++++++");
        admin.gameHelp();
        grid.printBoard(admin.getUnsolvedBoard());

        System.out.println("+++++++IS A VALID NUMBER+++++++");
        System.out.println(admin.verifyNum(0, 0, 3));
 */
