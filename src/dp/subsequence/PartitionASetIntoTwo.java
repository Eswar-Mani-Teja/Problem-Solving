package dp.subsequence;

import java.util.Arrays;

public class PartitionASetIntoTwo {

    /*
     * Partition a set into two equal/unequal subsets such that,
     * the difference of sum of the two subsets is "Zero"
     * S -> s1 and s2
     * |s1 - s2| is 0
     * [2,3,3,3,4,5] -> |[2,3,5] - [3,3,4]| = |10-10| = 0
     */
    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 3, 3, 4, 5};
        boolean possibility = canPartition(arr, arr.length);
        String outputFormat = "Possibility of partition %s such that |s1-s2| is 0: %s [%s]%n";
        System.out.printf(outputFormat, Arrays.toString(arr), possibility, "TB");
    }


    public static int getSum(int[] arr) {
        int sum = 0;
        for (int element : arr) {
            sum += element;
        }
        return sum;
    }


    // Tabulation
    public static boolean canPartition(int[] arr, int n) {
        int sum = getSum(arr);
        if ((sum & 1) == 1) return false;
        sum = sum / 2;
        boolean[] prev = new boolean[sum + 1];
        boolean[] current = new boolean[sum + 1];
        prev[0] = current[0] = true;
        prev[arr[0]] = true;
        for (int index = 1; index < n; index++) {
            for (int target = 1; target <= sum; target++) {
                boolean pick = target >= arr[index] && prev[target - arr[index]];
                boolean skip = prev[target];
                current[target] = pick || skip;
            }
            prev = current;
        }
        return prev[n - 1];
    }

}
