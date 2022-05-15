package arrays;

import static utils.Utils.matrixToString;

public class setMatrixZeros {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        System.out.printf("Input matrix  : %s%n", matrixToString(matrix));
        String outputFormat = "Updated matrix: %s%n";
        deleteRowAndColumnForZero(matrix);
        System.out.printf(outputFormat, matrixToString(matrix));
    }

    private static void deleteRowAndColumnForZero(int[][] matrix) {
        boolean[] rows = new boolean[matrix.length];
        boolean[] columns = new boolean[matrix[0].length];
        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < columns.length; j++) {
                if (matrix[i][j] == 0) {
                    rows[i] = true;
                    columns[j] = true;
                }
            }
        }
        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < columns.length; j++) {
                if (rows[i] || columns[j]) matrix[i][j] = 0;
            }
        }
    }
}
