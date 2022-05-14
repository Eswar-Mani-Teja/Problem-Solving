package dp.subsequence;

import utils.Log;

import java.util.Arrays;

public class SubsetSumMatchingTarget {
    public static void main(String[] args) {
        int[] arr = new int[]{4, 3, 2, 1};
        int k = 5;
        boolean result = subsetSumToK(arr, k, 0);
        String outputFormat = "%d found in %s as a subset sum: %s [%s]%n";
        System.out.printf(outputFormat, k, Arrays.toString(arr), result, "REC+ITER");

        result = subsetSumToK_REC(arr, k, 0);
        System.out.printf(outputFormat, k, Arrays.toString(arr), result, "REC-Pick/Skip");

        result = subsetSumToK(arr.length - 1, k, arr, new boolean[arr.length][k + 1]);
        System.out.printf(outputFormat, k, Arrays.toString(arr), result, "REC-MEMO");

        result = subsetSumToK_TAB(k, arr.length - 1, arr);
        System.out.printf(outputFormat, k, Arrays.toString(arr), result, "TAB");

        result = subsetSumToK_TAB_SO(k, arr.length - 1, arr);
        System.out.printf(outputFormat, k, Arrays.toString(arr), result, "TAB-SO");

        Log.callSummaries();
    }


    // Recursion + Iteration
    private static boolean subsetSumToK(int[] arr, int k, int pos) {
        Log.callCounter("REC+ITER");
        if (k < 0) return false;
        if (k == 0) return true;
        for (int i = pos; i < arr.length; i++) {
            if (subsetSumToK(arr, k - arr[i], i + 1))
                return true;
        }
        return false;
    }


    // Brute Force - Recursion
    private static boolean subsetSumToK_REC(int[] arr, int k, int pos) {
        Log.callCounter("REC-F");
        if (k < 0) return false;
        if (k == 0) return true;
        if (pos == arr.length) return false;
        //Take value at pos (index)
        boolean take = subsetSumToK_REC(arr, k - arr[pos], pos + 1);
        boolean skip = subsetSumToK_REC(arr, k, pos + 1);
        return take || skip;
    }


    // Memoization
    private static boolean subsetSumToK(int index, int k, int[] arr, boolean[][] memo) {
        Log.callCounter("MEMO-F");
        if (k == 0) return true;
        if (index == 0 && arr[index] == k) return true;
        if (index < 0 || k < 0) return false;
        if (memo[index][k]) return true;
        boolean skip = subsetSumToK(index - 1, k, arr, memo);
        boolean take = subsetSumToK(index - 1, k - arr[index], arr, memo);
        return memo[index][k] = take || skip;
    }


    // Tabulation
    private static boolean subsetSumToK_TAB(int k, int n, int[] arr) {
        // memo[index][target]
        boolean[][] memo = new boolean[n][k + 1];
        // When target is arr[0] and the no. of elements in array is 1: (index, arr[index]) is true
        memo[0][arr[0]] = true; // Target= arr[0]
        for (int i = 0; i < n; i++) {
            memo[i][0] = true;
        }
        for (int index = 1; index < n; index++) {
            for (int target = 1; target <= k; target++) {
                int targetIndex = target - arr[index];
                boolean skip = memo[index - 1][target];
                boolean pick = targetIndex >= 0 && memo[index - 1][targetIndex];
                memo[index][target] = pick || skip;
            }
        }
        return memo[n - 1][k];
    }


    // Tabulation with space optimization
    private static boolean subsetSumToK_TAB_SO(int k, int n, int[] arr) {
        // memo[index][target]
        boolean[] prev = new boolean[k + 1];
        boolean[] current = new boolean[k + 1];
        prev[0] = current[0] = true;
        // When target is arr[0] and the no. of elements in array is 1: (index, arr[index]) is true
        prev[arr[0]] = true; // Target= arr[0]

        for (int index = 1; index < n; index++) {
            for (int target = 1; target <= k; target++) {
                int targetIndex = target - arr[index];
                boolean skip = prev[target];
                boolean pick = targetIndex >= 0 && prev[targetIndex];
                current[target] = pick || skip;
            }
            prev = current;
        }
        return prev[k];
    }
}
