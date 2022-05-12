package dp.array_and_grid;

import utils.Log;

public class TotalUniquePathsInAGrid {

    public static void main(String[] args) {
        //start_row = 0; start_column = 0
        int m = 3; // target_row
        int n = 3; // target_column
        int totalUniquePaths = getTotalUniquePaths(m, n);
        System.out.printf("Total unique paths from (%d, %d) " +
                        "to (%d, %d) is: %d - [BF]%n",
                        0, 0, m, n, totalUniquePaths);
        totalUniquePaths = getTotalUniquePaths(m, n, new int[m][n]);
        System.out.printf("Total unique paths from (%d, %d) " +
                        "to (%d, %d) is: %d - [MEMO-f]%n",
                        0, 0, m, n, totalUniquePaths);
        totalUniquePaths = getTotalUniquePathsTB(m, n);
        System.out.printf("Total unique paths from (%d, %d) " +
                        "to (%d, %d) is: %d - [TB-f]%n",
                        0, 0, m, n, totalUniquePaths);
        Log.callSummaries();
    }


    //Brute Force
    private static int getTotalUniquePaths(int m, int n) {
        Log.callCounter("BF");
        if (m == 0 && n == 0) return 1;
        if (m < 0 || n < 0) return 0;
        int waysToReachAboveCell = getTotalUniquePaths(m - 1, n);
        int waysToReachLeftCell = getTotalUniquePaths(m, n - 1);
        return waysToReachAboveCell + waysToReachLeftCell;
    }


    public static int getTotalUniquePaths(int m, int n, int[][] memo) {
        Log.callCounter("memo-full");
        if (m == 0 && n == 0) return 1;
        if (m < 0 || n < 0) return 0;
        if (m < memo.length && n < memo[0].length && memo[m][n] != 0) return memo[m][n];
        int waysToReachAboveCell = getTotalUniquePaths(m - 1, n, memo);
        int waysToReachLeftCell = getTotalUniquePaths(m, n - 1, memo);
        int uniquePaths = waysToReachAboveCell + waysToReachLeftCell;
        if (memo.length == m || memo[0].length == n) return uniquePaths;
        memo[m][n] = uniquePaths;
        return memo[m][n];
    }


    //Tabulation
    public static int getTotalUniquePathsTB(int m, int n) {
        int[][] memo = new int[m + 1][n + 1];
        memo[0][0] = 1;
        int startRow = 0;
        int startColumn = 0;
        for (int row = startRow; row <= m; row++) {
            for (int column = startColumn; column <= n; column++) {
                if (row == startRow && column == startColumn) continue;
                int left = 0;
                int up = 0;
                if (row != 0) up = memo[row - 1][column];
                if (column != 0) left = memo[row][column - 1];
                memo[row][column] = up + left;
            }
        }
        return memo[m][n];
    }
}
