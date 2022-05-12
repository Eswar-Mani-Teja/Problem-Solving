package dp.array_and_grid;

import utils.Log;

import java.util.HashMap;
import java.util.Map;

public class ClimbingStairs {

    public static void main(String[] args) {
        Log.currentCallFlag = false;

        var climbingStairs = new ClimbingStairs();
        int n = 3;

        climbingStairs.print(n, climbingStairs.waysToClimb(n), " [BF]");
        climbingStairs.print(n, climbingStairs.waysToClimb(n, new int[n]), " [Memo-AR]");
        climbingStairs.print(n, climbingStairs.waysToClimb(n, new HashMap<>()), " [Memo-HM]");
        Log.callSummaries();
    }

    private void print(int n, int ways, String tag) {
        System.out.println("Number of ways to climb " + n + " steps: " + ways + tag);
    }

    // Brute Force
    private int waysToClimb(int n) {
        Log.callCounter("BF");
        if (n == 0) return 1;
        if (n < 0) return 0;
        return waysToClimb(n - 1) + waysToClimb(n - 2);
    }

    // Memoization
    private int waysToClimb(int n, int[] memo) {
        Log.callCounter("memo");
        if (n == 0) return 1;
        if (n < 0) return 0;
        if (memo[n - 1] == 0)
            memo[n - 1] = waysToClimb(n - 1, memo) + waysToClimb(n - 2, memo);
        return memo[n - 1];
    }

    // Memoization using Hashmap
    private int waysToClimb(int n, Map<Integer, Integer> memo) {
        Log.callCounter("memo-hm");
        if (n == 0) return 1;
        if (n < 0) return 0;
        if (!memo.containsKey(n))
            memo.put(n, waysToClimb(n - 1, memo) + waysToClimb(n - 2, memo));
        return memo.get(n);
    }

}
