import java.util.Scanner;

public class SudokuSolver {
    static final int N = 9;

    // Function to print the Sudoku grid
    static void printGrid(int[][] grid) {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++)
                System.out.print(grid[row][col] + " ");
            System.out.println();
        }
    }

    // Function to check if a number can be placed in a given cell
    static boolean isSafe(int[][] grid, int row, int col, int num) {
        // Check if the number is not present in the current row and column
        for (int x = 0; x < N; x++)
            if (grid[row][x] == num || grid[x][col] == num)
                return false;

        // Check if the number is not present in the current 3x3 subgrid
        int startRow = row - row % 3, startCol = col - col % 3;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (grid[i + startRow][j + startCol] == num)
                    return false;

        return true;
    }

    // Function to find an empty cell in the grid
    static boolean findEmptyCell(int[][] grid, int[] position) {
        for (position[0] = 0; position[0] < N; position[0]++) {
            for (position[1] = 0; position[1] < N; position[1]++) {
                if (grid[position[0]][position[1]] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    // Function to solve the Sudoku puzzle using backtracking
    static boolean solveSudoku(int[][] grid) {
        int[] position = new int[2];

        // If no empty cell is found, the Sudoku is solved
        if (!findEmptyCell(grid, position))
            return true;

        int row = position[0];
        int col = position[1];

        // Try filling the empty cell with numbers 1 to 9
        for (int num = 1; num <= 9; num++) {
            if (isSafe(grid, row, col, num)) {
                // Place the number in the current cell
                grid[row][col] = num;

                // Recursively attempt to solve the rest of the puzzle
                if (solveSudoku(grid))
                    return true;

                // If placing the number leads to an invalid solution, backtrack
                grid[row][col] = 0;
            }
        }

        // No valid number can be placed, backtrack to the previous cell
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] grid = new int[N][N];

        System.out.println("Enter the Sudoku grid (9x9) with 0 for empty cells:");
        for (int row = 0; row < N; row++)
            for (int col = 0; col < N; col++)
                grid[row][col] = scanner.nextInt();

        System.out.println("Sudoku Puzzle:");
        printGrid(grid);

        if (solveSudoku(grid)) {
            System.out.println("\nSolved Sudoku:");
            printGrid(grid);
        } else {
            System.out.println("\nNo solution exists for the given Sudoku.");
        }
    }
}
