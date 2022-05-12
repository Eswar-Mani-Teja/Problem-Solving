package dp.array_and_grid;

import java.util.Arrays;
import java.util.List;

import static java.lang.Math.max;

public class MaxSumOfNonAdjacent {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(2, 1, 4, 9);

        int sum = maximumNonAdjacentSum(list, list.size() - 1);
        System.out.println("max. non-adjacent sum of " + list + " is: " + sum + " [BF-R]");

        sum = maximumNonAdjacentSumTB1(list);
        System.out.println("max. non-adjacent sum of " + list + " is: " + sum + " [TB-S]");
    }

    private static int maximumNonAdjacentSum(List<Integer> list, int currentIndex) {
        if (currentIndex < 0) return 0;
        if (currentIndex == 0) return list.get(0);
        return max(maximumNonAdjacentSum(list, currentIndex - 1),
                maximumNonAdjacentSum(list, currentIndex - 2) + list.get(currentIndex));
    }

    private static int maximumNonAdjacentSumTB1(List<Integer> list) {
        if (list.isEmpty()) return 0;
        if (list.size() == 1) return list.get(0);
        int[] memo = new int[list.size()];
        memo[0] = list.get(0);
        int i;
        for (i = 1; i < list.size(); i++) {
            memo[i] = max(memo[i - 1], (i == 1 ? 0 : memo[i - 2]) + list.get(i));
        }
        return memo[i - 1];
    }

    private static int maximumNonAdjacentSumTB2(List<Integer> list) {
        if (list.isEmpty()) return 0;
        if (list.size() == 1) return list.get(0);
        int prev1 = list.get(0);
        int prev2 = 0;
        for (int i = 1; i < list.size(); i++) {
            int x = max(prev1, prev2 + list.get(i));
            prev2 = prev1;
            prev1 = x;
        }
        return prev1;
    }
}