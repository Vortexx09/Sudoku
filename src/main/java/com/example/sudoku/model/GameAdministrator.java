package com.example.sudoku.model;

public class GameAdministrator implements GameAdministratorInterface {
    private int[][] solvedBoard = new int[6][6];
    private int[][] unsolvedBoard = new int[6][6];
    private int[][] playerBoard = new int[6][6];
    private boolean winner = false;
    private int helpAmount;

    public GameAdministrator() {
    }

    public void gameHelp() {
        int randomRow = (int)(Math.random()*5) + 1;
        int randomCol = (int)(Math.random()*5) + 1;

        if (unsolvedBoard[randomRow][randomCol] == 0){
            unsolvedBoard[randomRow][randomCol] = solvedBoard[randomRow][randomCol];
            playerBoard[randomRow][randomCol] = solvedBoard[randomRow][randomCol];
        }
        else
            gameHelp();

        Grid grid = new Grid();

        System.out.println("-------------------");
        grid.printBoard(unsolvedBoard);
        System.out.println("-------------------");
        grid.printBoard(playerBoard);

    }

    public boolean isValidMove(int[][] board, int row, int col, int num) {
        return isValidInRow(board, row, num) &&
                isValidInColumn(board, col, num) &&
                isValidInBlock(board, row, col, num);
    }

    public boolean isValidInRow(int[][] board, int row, int num) {
        for (int col = 0; col < 6; col++) {
            if (board[row][col] == num) {
                return false;
            }
        }
        return true;
    }

    public boolean isValidInColumn(int[][] board, int col, int num) {
        for (int row = 0; row < 6; row++) {
            if (board[row][col] == num) {
                return false;
            }
        }
        return true;
    }

    public boolean isValidInBlock(int[][] board, int row, int col, int num) {
        int startRow = (row / 2) * 2;
        int startCol = (col / 3) * 3;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[startRow + i][startCol + j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    public int[][] getUnsolvedBoard(){
        return unsolvedBoard;
    }

    public void setUnsolvedBoard(int[][] unsolvedBoard){
        this.unsolvedBoard = unsolvedBoard;
    }

    public int[][] getSolvedBoard() {
        return solvedBoard;
    }

    public void setSolvedBoard(int[][] solvedBoard) {
        this.solvedBoard = solvedBoard;
    }

    public int[][] getPlayerBoard() {
        return playerBoard;
    }

    public void setPlayerBoard(int[][] playerBoard) {
        this.playerBoard = playerBoard;
    }

    public void modifyPlayerBoard(int row, int col, int value) {
        this.playerBoard[row][col] = value;
    }


    public boolean verifyWinner(int[][] matrix1, int[][] matrix2) {
        // Check if both matrices are null
        if (matrix1 == null || matrix2 == null) return false;

        // Check if they have the same dimensions
        if (matrix1.length != matrix2.length) return false;

        for (int i = 0; i < matrix1.length; i++) {
            if (matrix1[i].length != matrix2[i].length) return false;

            for (int j = 0; j < matrix1[i].length; j++) {
                if (matrix1[i][j] != matrix2[i][j]) return false;
            }
        }

        return true; // Matrices are equal
    }

    public boolean getWinner() {
        return winner;
    }
}