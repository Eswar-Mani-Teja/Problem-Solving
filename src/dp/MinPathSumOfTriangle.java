package dp;

public class MinPathSumOfTriangle {

    public static void main(String[] args) {
        //int[][] triangle = new int[][]{{1}, {2, 3}, {4, 5, 6}, {7, 8, 9, 10}};
        int[][] triangle = new int[][]{{5}, {-1, 3}, {22, 1, -9}};
        String outputFormat = "Minimum path sum is: %d [%s]%n";
        int minPathSum = getMinimumPathSum(0, 0, triangle, triangle.length);
        System.out.printf(outputFormat, minPathSum, "BF");

        minPathSum = getMinimumPathSum(triangle, triangle.length);
        System.out.printf(outputFormat, minPathSum, "TB");

        minPathSum = getMinimumPathSumTBSO(triangle, triangle.length);
        System.out.printf(outputFormat, minPathSum, "TBSO");
    }


    //Brute-force: Recursion
    private static int getMinimumPathSum(int i, int j, int[][] triangle, int n) {
        if (i == n) return 0;
        int down = triangle[i][j] + getMinimumPathSum(i + 1, j, triangle, n);
        int diagonal = triangle[i][j] + getMinimumPathSum(i + 1, j + 1, triangle, n);
        return Math.min(diagonal, down);
    }


    // Tabulation
    private static int getMinimumPathSum(int[][] triangle, int n) {
        int[][] memo = new int[n][n];
        //Base Case
        System.arraycopy(triangle[n - 1], 0, memo[n - 1], 0, n);
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                memo[i][j] = Math.min(memo[i + 1][j], memo[i + 1][j + 1]) + triangle[i][j];
            }
        }
        return memo[0][0];
    }

    //Tabulation with Space optimization
    private static int getMinimumPathSumTBSO(int[][] triangle, int n) {
        int[] currentRow = new int[n];
        int[] nextRow = new int[n];
        //Base Case
        System.arraycopy(triangle[n - 1], 0, nextRow, 0, n);
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                currentRow[j] = Math.min(nextRow[j], nextRow[j + 1]) + triangle[i][j];
            }
            nextRow = currentRow;
        }
        return nextRow[0];
    }
}
