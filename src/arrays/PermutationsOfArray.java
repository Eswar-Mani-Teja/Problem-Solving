package arrays;

import java.util.Arrays;

public class PermutationsOfArray {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3};
        permutate(arr, 0);
        System.out.println("----------");
        permutate(0, arr, new int[arr.length], new boolean[arr.length]);
    }

    private static void swap(int aIndex, int bIndex, int[] arr) {
        if (aIndex != bIndex) {
            int temp = arr[aIndex];
            arr[aIndex] = arr[bIndex];
            arr[bIndex] = temp;
        }
    }

    /**
     * [1, 2, 3] -> n = 3
     * Total number of permutations = n! = 3! = 3x2x1 = 6
     */
    // Time complexity: O(n! * n)
    // Space complexity: O(n): result + O(n): marker
    private static void permutate(int position, int[] arr,
                                  int[] result, boolean[] marker) {
        if (result.length == position) {
            System.out.println(Arrays.toString(result));
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (!marker[i]) { // is i'th element of arr available to be picked?
                result[position] = arr[i]; // pick i'th element of arr for results' position
                marker[i] = true; // mark i'th element as picked
                permutate(position + 1, arr, result, marker);
                marker[i] = false; // un-mark i'th element as picked
            }
        }
    }


    // Order is not maintained
    // Time Complexity: O(n! * n)
    private static void permutate(int[] arr, int index) {
        if (index == arr.length - 1) {
            System.out.println(Arrays.toString(arr));
            return;
        }
        for (int i = index; i < arr.length; i++) {
            swap(i, index, arr);
            permutate(arr, index + 1);
            swap(i, index, arr);
        }
    }
}