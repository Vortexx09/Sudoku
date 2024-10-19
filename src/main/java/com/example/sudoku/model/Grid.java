package com.example.sudoku.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Grid implements GridInterface {
    private int[][] initialGrid = new int[6][6];
    private int[][] finalGrid = new int[6][6];
    private int[][] playerGrid = new int[6][6];
    private Random random = new Random();

    public Grid() {
    }

    public void generateSudokuBoard() {
        int[][] board = new int[6][6];

        solveSudoku(board, 0, 0);
        finalGrid = copyBoard(board);
        removeNumbers(board);
        initialGrid = copyBoard(board);
        playerGrid = copyBoard(board);
    }

    public int[][] copyBoard(int[][] board) {
        int[][] copy = new int[6][6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                copy[i][j] = board[i][j];
            }
        }
        return copy;
    }

    private void removeNumbers(int[][] board) {
        for (int blockRow = 0; blockRow < 6; blockRow += 2) {
            for (int blockCol = 0; blockCol < 6; blockCol += 3) {
                int count = 0;
                while (count < 4) {
                    int row = random.nextInt(2) + blockRow;
                    int col = random.nextInt(3) + blockCol;
                    if (board[row][col] != 0) {
                        board[row][col] = 0;
                        count++;
                    }
                }
            }
        }
    }

    public boolean checkValue(int[][] board, int row, int col, int num) {
        for (int colX = 0; colX < 6; colX++) {
            if (board[row][colX] == num) {
                return false;
            }
            if (board[colX][col] == num) {
                return false;
            }
        }

        int rowStart = row - (row % 2);
        int colStart = col - (col % 3);

        for (int rowY = 0; rowY < 2; rowY++) {
            for (int colY = 0; colY < 3; colY++) {
                if (board[rowY + rowStart][colY + colStart] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean solveSudoku(int[][] board, int row, int col) {
        if (row == 6 && col == 5) {
            return true;
        }

        if (row == 6) {
            col++;
            row = 0;
        }

        if (board[row][col] != 0) {
            return solveSudoku(board, row + 1, col);
        }

        List<Integer> numbers = getRandomNumbers();

        for (int num : numbers) {
            if (checkValue(board, row, col, num)) {
                board[row][col] = num;
                if (solveSudoku(board, row + 1, col)) {
                    return true;
                }
            }
            board[row][col] = 0;
        }
        return false;
    }

    private List<Integer> getRandomNumbers() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return numbers;
    }

    public void printBoard(int[][] grid) {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                System.out.print(grid[row][col] + " ");
            }
            System.out.println();
        }
    }

    public int[][] getInitialGrid() {
        return initialGrid;
    }

    public void setInitialGrid(int[][] initialGrid) {
        this.initialGrid = initialGrid;
    }

    public int[][] getFinalGrid() {
        return finalGrid;
    }

    public void setFinalGrid(int[][] finalGrid) {
        this.finalGrid = finalGrid;
    }

    public int[][] getPlayerGrid() {
        return playerGrid;
    }

    public void setPlayerGrid(int[][] playerGrid) {
        this.playerGrid = playerGrid;
    }
}

/*
private void removeNumbers(int[][] board) {
    // Elimina números aleatorios del tablero sin garantizar que haya 2 por bloque
    int numbersToRemove = 20; // Puedes ajustar este número según cuántos números quieras eliminar
    Random random = new Random();

    while (numbersToRemove > 0) {
        int row = random.nextInt(6); // Fila aleatoria
        int col = random.nextInt(6); // Columna aleatoria

        // Si la celda no está vacía, la vaciamos
        if (board[row][col] != 0) {
            board[row][col] = 0;
            numbersToRemove--;
        }
    }
}
 */