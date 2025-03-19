public class Main {
    private static final int size = 9;
    private static final int subgridSize = 3;
    
    // Checks if placing a number in the given row and column is safe.
    public static boolean isSafe(int[][] board, int row, int col, int num) {
        return isRowSafe(board, row, num) && isColSafe(board, col, num) && isBoxSafe(board, row, col, num);
    }


     //Checks if the number is not already present in the given row.
    private static boolean isRowSafe(int[][] board, int row, int num) {
        for (int col = 0; col < size; col++) {
            if (board[row][col] == num) {
                return false;
            }
        }
        return true;
    }

    
    //Checks if the number is not already present in the given column.
    private static boolean isColSafe(int[][] board, int col, int num) {
        for (int row = 0; row < size; row++) {
            if (board[row][col] == num) {
                return false;
            }
        }
        return true;
    }
    
     // Checks if the number is not already present in the 3x3 subgrid.
    private static boolean isBoxSafe(int[][] board, int row, int col, int num) {
        int startRow = (row / subgridSize) * subgridSize;
        int startCol = (col / subgridSize) * subgridSize;

        for (int i = 0; i < subgridSize; i++) {
            for (int j = 0; j < subgridSize; j++) {
                if (board[startRow + i][startCol + j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Recursively solves the Sudoku board using backtracking.
     */
    public static boolean solveSudoku(int[][] board, int row, int col) {
        if (row == size) {
            return true;
        }

        int nextRow = (col == size - 1) ? row + 1 : row;
        int nextCol = (col + 1) % size;

        if (board[row][col] != 0) {
            return solveSudoku(board, nextRow, nextCol);
        }

        for (int num = 1; num <= size; num++) {
            if (isSafe(board, row, col, num)) {
                board[row][col] = num;
                if (solveSudoku(board, nextRow, nextCol)) {
                    return true;
                }
                board[row][col] = 0; // Backtrack
            }
        }
        return false;
    }

    public static void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        int[][] board = {
                {0, 0, 8, 0, 0, 0, 0, 0, 0},
                {4, 9, 0, 1, 5, 7, 0, 0, 2},
                {0, 0, 3, 0, 0, 4, 1, 9, 0},
                {1, 8, 5, 0, 6, 0, 0, 2, 0},
                {0, 0, 0, 0, 2, 0, 0, 6, 0},
                {9, 6, 0, 4, 0, 5, 3, 0, 0},
                {0, 3, 0, 0, 7, 2, 0, 0, 4},
                {0, 4, 9, 0, 3, 0, 0, 5, 7},
                {8, 2, 7, 0, 0, 9, 0, 1, 3}
        };

        if (solveSudoku(board, 0, 0)) {
            System.out.println("Solution exists:");
            printBoard(board);
        } else {
            System.out.println("No solution exists.");
        }
    }
}
