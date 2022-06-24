package codingsites.geeksforgeeks.arrays;

public class MaxPathSumIn2Arrays {

    public static void main(String[] args) {
        // Two sorted arrays
        int[] a = new int[]{2, 3, 7, 10, 12};
        int[] b = new int[]{1, 5, 7, 8};
        //can switch lanes only at common junction
        int maxPathSum = getMaxPathSum(a, b);
        System.out.println("Max. path sum: " + maxPathSum);
    }

    private static int getMaxPathSum(int[] ar1, int[] ar2) {
        int i = 0, j = 0;
        int aSum = 0;
        int bSum = 0;
        int maxSum = 0;
        while (i < ar1.length && j < ar2.length) {
            if (ar1[i] < ar2[j]) {
                aSum += ar1[i++];
            } else if (ar1[i] > ar2[j]) {
                bSum += ar2[j++];
            } else {
                maxSum += Math.max(aSum, bSum) + ar1[i];
                i++;
                j++;
                aSum = bSum = 0;
            }
        }
        while (i < ar1.length) {
            aSum += ar1[i++];
        }
        while (j < ar2.length) {
            bSum += ar2[j++];
        }
        maxSum += Math.max(aSum, bSum);
        return maxSum;
    }
}
