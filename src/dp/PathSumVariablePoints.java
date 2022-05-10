package dp;

public class PathSumVariablePoints {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 2, 10, 9},
                {100, 3, 2, 1},
                {1, 1, 20, 2},
                {1, 2, 2, 1},
        };
        int maxPathSum = getMaxPathSum(matrix);
        String outputFormat = "Max. Path Sum = %d [%s]%n";
        System.out.printf(outputFormat, maxPathSum, "BF");

        maxPathSum = getMaxPathSumTB(matrix, matrix.length, matrix[0].length);
        System.out.printf(outputFormat, maxPathSum, "TB");

        maxPathSum = getMaxPathSumTBSO(matrix, matrix.length, matrix[0].length);
        System.out.printf(outputFormat, maxPathSum, "TB-SO");
    }

    private static int getMaxPathSum(int[][] matrix) {
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < matrix[0].length; i++) {
            int pathSum = getMaxPathSum(matrix, matrix.length - 1, i);
            if (maxSum < pathSum) {
                maxSum = pathSum;
            }
        }
        return maxSum;
    }


    // Brute Force - Recursion
    private static int getMaxPathSum(int[][] matrix, int row, int column) {
        if (row == 0 && column >= 0 && column < matrix.length) return matrix[row][column];
        if (row < 0 || column < 0 || column == matrix.length) return Integer.MIN_VALUE;

        int leftUp = getMaxPathSum(matrix, row - 1, column - 1);
        int up = getMaxPathSum(matrix, row - 1, column);
        int rightUp = getMaxPathSum(matrix, row - 1, column + 1);
        return Math.max(Math.max(leftUp, up), rightUp) + matrix[row][column];
    }


    // Tabulation
    private static int getMaxPathSumTB(int[][] matrix, int rows, int columns) {
        int[][] memo = new int[rows][columns];
        System.arraycopy(matrix[0], 0, memo[0], 0, columns);
        int maxPathSum = Integer.MIN_VALUE;
        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int leftUp = j > 0 ? memo[i - 1][j - 1] : Integer.MIN_VALUE;
                int up = memo[i - 1][j];
                int rightUp = j < columns - 1 ? memo[i - 1][j + 1] : Integer.MIN_VALUE;
                memo[i][j] = Math.max(Math.max(leftUp, up), rightUp) + matrix[i][j];
                if (i == rows - 1 && maxPathSum < memo[i][j]) {
                    maxPathSum = memo[i][j];
                }
            }
        }
        return maxPathSum;
    }


    // Tabulation with space optimization
    private static int getMaxPathSumTBSO(int[][] matrix, int rows, int columns) {
        int[] prev = new int[columns];
        int[] current = new int[columns];
        System.arraycopy(matrix[0], 0, prev, 0, columns);
        int maxPathSum = Integer.MIN_VALUE;
        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int leftUp = j > 0 ? prev[j - 1] : Integer.MIN_VALUE;
                int up = prev[j];
                int rightUp = j < columns - 1 ? prev[j + 1] : Integer.MIN_VALUE;
                current[j] = Math.max(Math.max(leftUp, up), rightUp) + matrix[i][j];
                if (i == rows - 1 && maxPathSum < current[j]) {
                    maxPathSum = current[j];
                }
            }
            int[] temp = current;
            current = prev;
            prev = temp;
        }
        return maxPathSum;
    }
}
