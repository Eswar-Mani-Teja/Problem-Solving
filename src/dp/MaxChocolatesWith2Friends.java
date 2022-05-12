package dp;

import utils.Log;

public class MaxChocolatesWith2Friends {

    public static void main(String[] args) {
        Log.currentCallFlag = true;
        int[][] grid = new int[][]{
                {2, 3, 1, 2},
                {3, 4, 2, 2},
                {5, 6, 3, 5}
        };
        int rows = grid.length;
        int columns = grid[0].length;
        int chocolates = collectMaxChocolates(0, 0, columns - 1, grid);
        String outputFormat = "Max. chocolates collected by Alice and Bob: %d [%s]%n";
        System.out.printf(outputFormat, chocolates, "BF");
        chocolates = collectMaxChocolatesMEMO(0, 0,
                columns - 1, grid,
                new int[rows][columns][columns]);
        System.out.printf(outputFormat, chocolates, "MEMO");

        Log.callSummaries();
    }


    public static int collectMaxChocolates(int currentRow, int aliceColumn,
                                           int bobColumn, int[][] grid) {
        Log.callCounter("BF");
        if (aliceColumn < 0 || aliceColumn >= grid[0].length
                || bobColumn < 0 || bobColumn >= grid[0].length
                || currentRow == grid.length) {
            return Integer.MIN_VALUE;
        }
        if (currentRow == grid.length - 1) {
            if (aliceColumn == bobColumn) return grid[currentRow][aliceColumn];
            return grid[currentRow][aliceColumn] + grid[currentRow][bobColumn];
        }
        int maxChocolates = Integer.MIN_VALUE;
        for (int aliceInc = -1; aliceInc <= 1; aliceInc++) {
            for (int bobInc = -1; bobInc <= 1; bobInc++) {
                int chocolates = collectMaxChocolates(currentRow + 1,
                        aliceColumn + aliceInc,
                        bobColumn + bobInc,
                        grid);
                if (chocolates != Integer.MIN_VALUE) {
                    chocolates += grid[currentRow][aliceColumn];
                    if (aliceColumn != bobColumn) chocolates += grid[currentRow][bobColumn];
                    maxChocolates = Math.max(maxChocolates, chocolates);
                }
            }
        }
        return maxChocolates;
    }


    private static int collectMaxChocolatesMEMO(int currentRow,
                                                int aliceColumn,
                                                int bobColumn,
                                                int[][] grid,
                                                int[][][] memo) {
        Log.callCounter("MEMO");
        if (aliceColumn < 0 || aliceColumn >= grid[0].length ||
                bobColumn < 0 || bobColumn >= grid[0].length ||
                currentRow >= grid.length) return Integer.MIN_VALUE;
        if (currentRow == grid.length - 1) {
            if (aliceColumn == bobColumn) return grid[currentRow][aliceColumn];
            return grid[currentRow][aliceColumn] + grid[currentRow][bobColumn];
        }
        if (memo[currentRow][aliceColumn][bobColumn] != 0)
            return memo[currentRow][aliceColumn][bobColumn];
        int maxChocolates = Integer.MIN_VALUE;
        for (int aliceInc = -1; aliceInc <= 1; aliceInc++) {
            for (int bobInc = -1; bobInc <= 1; bobInc++) {
                int chocolates = collectMaxChocolatesMEMO(currentRow + 1,
                        aliceColumn + aliceInc, bobColumn + bobInc,
                        grid, memo);
                if (chocolates != Integer.MIN_VALUE) {
                    chocolates += grid[currentRow][aliceColumn];
                    if (aliceColumn != bobColumn) chocolates += grid[currentRow][bobColumn];
                    maxChocolates = Math.max(maxChocolates, chocolates);
                }
            }
        }
        return memo[currentRow][aliceColumn][bobColumn] = maxChocolates;
    }

    /*
    private static int getMax(int a, int... elements) {
        int maximum = a;
        for (int element : elements) {
            maximum = Math.max(element, maximum);
        }
        return maximum;
    }

    // Logical Approach
    public static int[] maximumChocolates(int r, int ac, int bc, int[][] grid) {
        int[] chocolates = new int[2];
        if (ac < 0 || ac >= grid[0].length) chocolates[0] = Integer.MIN_VALUE;
        if (bc < 0 || bc >= grid[0].length) chocolates[1] = Integer.MIN_VALUE;
        if (r == grid.length - 1) {
            chocolates[0] = ac < 0 || ac >= grid[0].length ? Integer.MIN_VALUE : grid[r][ac];
            chocolates[1] = bc < 0 || bc >= grid[0].length ? Integer.MIN_VALUE : grid[r][bc];
            return chocolates;
        }

        int[] dLeft1 = maximumChocolates(r + 1, ac - 1, bc - 1, grid);
        int[] dLeft2 = maximumChocolates(r + 1, ac - 1, bc, grid);
        int[] dLeft3 = maximumChocolates(r + 1, ac - 1, bc + 1, grid);

        int[] down1 = maximumChocolates(r + 1, ac, bc - 1, grid);
        int[] down2 = maximumChocolates(r + 1, ac, bc, grid);
        int[] down3 = maximumChocolates(r + 1, ac, bc + 1, grid);

        int[] dRight1 = maximumChocolates(r + 1, ac + 1, bc - 1, grid);
        int[] dRight2 = maximumChocolates(r + 1, ac + 1, bc, grid);
        int[] dRight3 = maximumChocolates(r + 1, ac + 1, bc + 1, grid);

        int alice = getMax(dLeft1[0], dLeft2[0], dLeft3[0],
                down1[0], down2[0], down3[0],
                dRight1[0], dRight2[0], dRight3[0]);
        int bob = getMax(dLeft1[1], dLeft2[1], dLeft3[1],
                down1[1], down2[1], down3[1],
                dRight1[1], dRight2[1], dRight3[1]);
        int currentAlice = ac < 0 || ac >= grid[0].length ? 0 : grid[r][ac];
        int currentBob = bc < 0 || bc >= grid[0].length ? 0 : grid[r][bc];
        chocolates[0] = alice + currentAlice;
        chocolates[1] = bob + currentBob;

        return chocolates;
    }
    */
}
