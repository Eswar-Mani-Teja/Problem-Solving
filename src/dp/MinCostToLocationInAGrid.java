package dp;

import utils.Log;

import static java.lang.Math.min;

public class MinCostToLocationInAGrid {

    public static void main(String[] args) {
        //int[][] cost = new int[][]{{3, 4, 1, 2}, {2, 1, 8, 9}, {4, 7, 8, 1}};
        int[][] cost = new int[][]{{5, 9, 6}, {11, 5, 2}};
        int rows = cost.length;
        int columns = cost[0].length;
        int minCost;
        int x = rows - 1;
        int y = columns - 1;
        minCost = minCostPath(cost, x, y);
        String outputFormat = "Minimum cost from (%d, %d): %d to (%d, %d): %d is: %d [%s]%n";
        System.out.printf(outputFormat, 0, 0, cost[0][0], x, y, cost[x][y], minCost, "BF");

        minCost = minCostPath(cost, x, y, new int[rows][columns]);
        System.out.printf(outputFormat, 0, 0, cost[0][0], x, y, cost[x][y], minCost, "MEMO-F");

        minCost = minCostPathTB(cost, x, y);
        System.out.printf(outputFormat, 0, 0, cost[0][0], x, y, cost[x][y], minCost, "TB");

        minCost = minCostPathTBSO(cost, x, y);
        System.out.printf(outputFormat, 0, 0, cost[0][0], x, y, cost[x][y], minCost, "TBSO");

        Log.callSummaries();
    }

    //BruteForce
    private static int minCostPath(int[][] cost, int x, int y) {
        Log.callCounter("BF");
        if (x < 0 || y < 0) return Integer.MAX_VALUE;
        if (x == 0 && y == 0) return cost[x][y];
        int tillUp = minCostPath(cost, x - 1, y);
        int tillLeft = minCostPath(cost, x, y - 1);
        return min(tillLeft, tillUp) + cost[x][y];
    }

    //Memoization
    private static int minCostPath(int[][] cost, int x, int y, int[][] memo) {
        Log.callCounter("MEMO-F");
        if (x == 0 && y == 0) return cost[x][y];
        if (x < 0 || y < 0) return Integer.MAX_VALUE;
        if (memo[x][y] != 0) return memo[x][y];
        int tillUp = minCostPath(cost, x - 1, y, memo);
        int tillLeft = minCostPath(cost, x, y - 1, memo);
        return memo[x][y] = min(tillLeft, tillUp) + cost[x][y];
    }

    //Tabulation
    private static int minCostPathTB(int[][] cost, int x, int y) {
        int[][] memo = new int[x + 1][y + 1];
        for (int i = 0; i <= x; i++) {
            for (int j = 0; j <= y; j++) {
                if (i == 0 && j == 0) {
                    memo[i][j] = cost[i][j];
                } else {
                    int tillUp = i > 0 ? memo[i - 1][j] : Integer.MAX_VALUE;
                    int tillLeft = j > 0 ? memo[i][j - 1] : Integer.MAX_VALUE;
                    memo[i][j] = min(tillUp, tillLeft) + cost[i][j];
                }
            }
        }
        return memo[x][y];
    }

    //Tabulation - Space optimized
    private static int minCostPathTBSO(int[][] cost, int x, int y) {
        int[] prevRow = new int[y + 1];
        int[] currentRow = new int[y + 1];
        for (int i = 0; i <= x; i++) {
            for (int j = 0; j <= y; j++) {
                if (i == 0 && j == 0) {
                    currentRow[j] = cost[i][j];
                } else {
                    int up = i != 0 ? prevRow[j] : Integer.MAX_VALUE;
                    int left = j != 0 ? currentRow[j - 1] : Integer.MAX_VALUE;
                    currentRow[j] = min(up, left) + cost[i][j];
                }
            }
            prevRow = currentRow;
        }
        return prevRow[y];
    }
}