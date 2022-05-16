package arrays;

import java.util.Arrays;

public class MaximumSubArray {
    /**
     * What is a sub array?
     * A contiguous array formed from the given array
     */
    public static void main(String[] args) {
        int[] arr = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        // Max. sub array sum = 6
        // [4, -1, 2, 1] has the largest sum
        int largestSum = maxSubArraySum(arr);
        String outputFormat = "Max. sub array sum of %s is: %d [%s]%n";
        System.out.printf(outputFormat, Arrays.toString(arr), largestSum, "BF: O(n^3)");

        largestSum = maxSubArraySum_O1(arr);
        System.out.printf(outputFormat, Arrays.toString(arr), largestSum, "O1: O(n^3)");

        largestSum = maxSubArraySum_O2(arr);
        System.out.printf(outputFormat, Arrays.toString(arr), largestSum, "O2: O(n^2)");

        largestSum = maxSubArraySum_O3(arr);
        System.out.printf(outputFormat, Arrays.toString(arr), largestSum, "O3-Kadane's Algo: O(n)");
    }

    private static int getSum(int[] arr, int start, int end) {
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += arr[i];
        }
        return sum;
    }

    // O(n^3)
    private static int maxSubArraySum(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        for (int windowStart = 0; windowStart < arr.length; windowStart++) {
            for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
                int sum = getSum(arr, windowStart, windowEnd);
                maxSum = Math.max(sum, maxSum);
            }
        }
        return maxSum;
    }


    // O(n^3) with optimization
    private static int maxSubArraySum_O1(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        for (int windowSize = 0; windowSize < arr.length; windowSize++) {
            int sum = Integer.MIN_VALUE;
            for (int start = 0, end = windowSize; end < arr.length; start++, end++) {
                if (sum != Integer.MIN_VALUE) {
                    sum += arr[end];
                } else {
                    sum = getSum(arr, start, end);
                }
                maxSum = Math.max(sum, maxSum);
                sum -= arr[start];
            }
        }
        return maxSum;
    }

    // O(n^2)
    private static int maxSubArraySum_O2(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        for (int windowStart = 0; windowStart < arr.length; windowStart++) {
            int sum = 0;
            for (int windowEnd = windowStart; windowEnd < arr.length; windowEnd++) {
                sum += arr[windowEnd];
                maxSum = Math.max(sum, maxSum);
            }
        }
        return maxSum;
    }


    // Kadane's algorithm: O(n)
    private static int maxSubArraySum_O3(int[] arr) {
        int sum = 0;
        int maxi = arr[0];
        for (int element : arr) {
            sum += element;
            maxi = Math.max(maxi, sum);
            if (sum < 0) {
                // -ve sum means, all the prev. values are cancelled and there is a loss
                sum = 0; // Because carrying a -ve sum decreases the maxSum}
            }
        }
        return maxi;
    }
}
