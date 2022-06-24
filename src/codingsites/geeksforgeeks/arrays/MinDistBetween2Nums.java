package codingsites.geeksforgeeks.arrays;

public class MinDistBetween2Nums {

    /*
     * @Link: https://practice.geeksforgeeks.org/problems/minimum-distance-between-two-numbers/0/?track=amazon-arrays&batchId=192
     */

    public static void main(String[] args) {
        int[] a = new int[]{3, 5, 4, 2, 6, 5, 6, 6, 5, 4, 8, 3}; //res = 4
        int x = 3, y = 6;
        // Min: index based distance
        int minDistance = minDistance(a, a.length, x, y);
        System.out.println("Min. distance: " + minDistance);
    }

    private static boolean isValidPosition(int[] a, int pos, int x, int y) {
        return a[pos] == x || a[pos] == y;
    }

    private static int minDistance(int[] a, int n, int x, int y) {
        int minDist = Integer.MAX_VALUE;
        int runner = 0;
        int flag = 0;
        while (runner < n) {
            if (isValidPosition(a, runner, x, y)) {
                if (a[flag] != a[runner]) {
                    minDist = Math.min(minDist, Math.abs(flag - runner));
                }
                flag = runner;
            }
            runner++;
        }
        if (minDist > n) return -1;
        return minDist;
    }
}
