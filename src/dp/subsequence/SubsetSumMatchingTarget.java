package dp.subsequence;

import utils.Log;

import java.util.Arrays;

public class SubsetSumMatchingTarget {
    public static void main(String[] args) {
        checkIfSubsetSumEqualsTarget();
        countSubsetsWithSumEqualToK();

        Log.callSummaries();
    }

    // Driver
    private static void countSubsetsWithSumEqualToK() {
        int[] arr = new int[]{1, 2, 2, 3};
        int k = 3;
        int n = arr.length;
        String outputFormat = "No. of Subsets with sum %d for %s is: %d [%s]%n";
        int count = countSubsetsWithSumK(n - 1, k, arr);
        System.out.printf(outputFormat, k, Arrays.toString(arr), count, "BF-REC");

        count = countSubsetsWithSumK(n - 1, k, arr, new int[n][k + 1]);
        System.out.printf(outputFormat, k, Arrays.toString(arr), count, "REC-memo");

        count = countSubsetsWithSumK_TAB(n, k, arr);
        System.out.printf(outputFormat, k, Arrays.toString(arr), count, "TAB");

        count = countSubsetsWithSumK_TAB_SO(n, k, arr);
        System.out.printf(outputFormat, k, Arrays.toString(arr), count, "TAB-SO");
    }

    // Driver
    private static void checkIfSubsetSumEqualsTarget() {
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
        // Can 'target' be formed till 'arr[index]' (index: 0 -> currentIndex)
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

    //----------------------------------------------------------------------------------------------//

    // Recursion
    private static int countSubsetsWithSumK(int index, int target, int[] arr) {
        Log.callCounter("BF-REC");
        if (target == 0) return 1;
        if (index < 0) return 0;
        if (target < 0) return 0;
        int pick = countSubsetsWithSumK(index - 1, target - arr[index], arr);
        int skip = countSubsetsWithSumK(index - 1, target, arr);
        return pick + skip;
    }


    // Recursion - Memoization
    private static int countSubsetsWithSumK(int index, int target, int[] arr, int[][] memo) {
        Log.callCounter("REC-MEMO");
        if (target == 0) return 1;
        if (index < 0) return 0;
        if (target < 0) return 0;
        if (memo[index][target] != 0) return memo[index][target];
        int pick = countSubsetsWithSumK(index - 1, target - arr[index], arr, memo);
        int skip = countSubsetsWithSumK(index - 1, target, arr, memo);
        return memo[index][target] = pick + skip;
    }


    // Tabulation
    private static int countSubsetsWithSumK_TAB(int n, int k, int[] arr) {
        int[][] memo = new int[arr.length][k + 1];
        if (arr[0] <= k) { // out of bounds check
            // Target == arr[0]
            memo[0][arr[0]] = 1;
        }
        // Subsets that can form sum = 0 at any index
        for (int i = 0; i < n; i++) {
            memo[i][0] = 1;
        }
        for (int index = 1; index < memo.length; index++) {
            for (int target = 1; target <= k; target++) {
                int pick = target >= arr[index] ? memo[index - 1][target - arr[index]] : 0;
                int skip = memo[index - 1][target];
                memo[index][target] = pick + skip;
            }
        }
        return memo[n - 1][k];
    }


    // Tabulation - Space Optimized
    private static int countSubsetsWithSumK_TAB_SO(int n, int k, int[] arr) {
        int[] prev = new int[k + 1];
        int[] current = new int[k + 1];
        if (arr[0] <= k) { // out of bounds check
            // Target == arr[0]
            prev[arr[0]] = 1;
        }
        // Subsets that can form sum = 0 at any index
        prev[0] = current[0] = 1;
        for (int index = 1; index < arr.length; index++) {
            for (int target = 1; target <= k; target++) {
                int pick = target >= arr[index] ? prev[target - arr[index]] : 0;
                int skip = prev[target];
                current[target] = pick + skip;
            }
            prev = current;
        }
        return prev[k];
    }
}
