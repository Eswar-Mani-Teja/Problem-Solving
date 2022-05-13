package dp.subsequence;

import utils.Log;

import java.util.Arrays;

public class SubsetSumMatchingTarget {
    public static void main(String[] args) {
        int[] arr = new int[]{5, 2, 1, 1};
        int k = 4;
        boolean result = subsetSumToK(arr, k, 0);
        String outputFormat = "%d found in %s as a subset sum: %s%n";
        System.out.printf(outputFormat, k, Arrays.toString(arr), result);

        result = subsetSumToK_REC(arr, k, 0);
        System.out.printf(outputFormat, k, Arrays.toString(arr), result);

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


    // TODO: Add Memoization and Tabulation logics
}
