package arrays;

import java.util.Arrays;

public class sort0s1sAnd2s {
    public static void main(String[] args) {
        int[] arr = new int[]{0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1};
        System.out.printf("Input array : %s%n", Arrays.toString(arr));
        sort(arr, arr.length);
        String outputFormat = "Sorted array: %s [%s]%n";
        System.out.printf(outputFormat, Arrays.toString(arr), "O(n) - Single Iteration");
    }

    private static void swap(int aIndex, int bIndex, int[] arr) {
        int temp = arr[aIndex];
        arr[aIndex] = arr[bIndex];
        arr[bIndex] = temp;
    }


    private static void sort(int[] arr, int n) {
        int zeros = 0;
        int ones = 0;
        int twos = n - 1;
        while (ones <= twos) {
            if (arr[ones] == 0) {
                swap(ones++, zeros++, arr);
            } else if (arr[ones] == 2) {
                swap(ones, twos--, arr);
            }
            if (arr[ones] == 1) ones++;
        }
    }
}
