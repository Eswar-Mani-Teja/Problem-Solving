package arrays;

import java.util.Arrays;

public class NextPermutation {

    public static void main(String[] args) {
        driver();
    }

    private static void driver() {
        int[] arr = new int[]{1, 3, 5, 4, 2};
        //int[] arr = new int[]{2, 1};
        String outputFormat = "Next permutation of %s is %s [%s]%n";
        System.out.printf(outputFormat, Arrays.toString(arr), Arrays.toString(nextPermutation(arr)), "O(n!)");
    }

    private static int[] nextPermutation(int[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            if (canBeIncremented(i, arr)) {
                int ngei = findNextGreaterElementIndex(i, arr);
                swap(i, ngei, arr);
                reverse(i + 1, arr.length - 1, arr);
                return arr;
            }
        }
        reverse(0, arr.length - 1, arr);
        return arr;
    }

    private static void reverse(int start, int end, int[] arr) {
        for (int i = start, j = end; i < j; i++, j--) {
            swap(i, j, arr);
        }
    }

    private static void swap(int indexA, int indexB, int[] arr) {
        int temp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = temp;
    }

    private static int findNextGreaterElementIndex(int currentIndex, int[] arr) {
        int currentElement = arr[currentIndex];
        int mini = Integer.MAX_VALUE;
        int index = currentIndex;
        for (int i = currentIndex + 1; i < arr.length; i++) {
            if (arr[i] > currentElement && mini > arr[i]) {
                mini = arr[i];
                index = i;
            }
        }
        return index;
    }

    private static boolean canBeIncremented(int index, int[] arr) {
        if (index >= arr.length - 1) return false;
        int currentElement = arr[index];
        for (int i = index + 1; i < arr.length; i++) {
            if (currentElement < arr[i]) return true;
        }
        return false;
    }

}
