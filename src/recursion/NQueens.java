package recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NQueens {

    public static void main(String[] args) {
        int n = 8;
        int[][] board = new int[n][n];
        var result = new ArrayList<ArrayList<Integer>>();
        placeNQueens(board, 0, new Stack<>(), result);
        for (var positionList : result) {
            printMatrix(board, positionList);
            System.out.println("----------");
        }
    }

    private static void printMatrix(int[][] matrix, List<Integer> rowPositions) {
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[0].length; column++) {
                boolean isQueenPosition = rowPositions.get(column) == row;
                String mark = isQueenPosition ? "X" : String.valueOf(0);
                System.out.print(mark + " ");
            }
            System.out.println();
        }
    }

    // We check only to the left, as we are filling up from left to right
    private static boolean isSafePosition(int[][] board, int row, int column) {
        if (row < 0 || column < 0) return true;

        // check to the left of the current row
        boolean leftSafe;
        int currColumn = column;
        while (currColumn >= 0) {
            leftSafe = board[row][currColumn--] == 0;
            if (!leftSafe) return false;
        }
        // check to the left up diagonal
        boolean leftUpSafe;
        int currRow = row;
        currColumn = column;
        while (currRow >= 0 && currColumn >= 0) {
            leftUpSafe = board[currRow--][currColumn--] == 0;
            if (!leftUpSafe) return false;
        }
        // check to the left down diagonal
        boolean leftDownSafe;
        currRow = row;
        currColumn = column;
        while (currRow < board.length && currColumn >= 0) {
            leftDownSafe = board[currRow++][currColumn--] == 0;
            if (!leftDownSafe) return false;
        }
        return true;
    }

    private static void placeNQueens(int[][] board, int column,
                                     Stack<Integer> rowPositionForColumns,
                                     ArrayList<ArrayList<Integer>> result) {
        if (column == board[0].length) {
            result.add(new ArrayList<>(rowPositionForColumns));
            return;
        }
        for (int currentRow = 0; currentRow < board.length; currentRow++) {
            if (isSafePosition(board, currentRow, column)) {
                rowPositionForColumns.push(currentRow);
                board[currentRow][column] = 1;
                placeNQueens(board, column + 1, rowPositionForColumns, result);
                board[currentRow][column] = 0;
                rowPositionForColumns.pop();
            }
        }
    }
}
