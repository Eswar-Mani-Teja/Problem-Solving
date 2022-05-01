package dp;

public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        LongestIncreasingSubsequence longestIncreasingSubsequence
                = new LongestIncreasingSubsequence();
        int[] sequence = new int[]{1, 2, 6, 3, 5};
        //int[] sequence = new int[]{0,3,1,6,2,2,7};
        System.out.println(longestIncreasingSubsequence.
                getLengthOfLISS(sequence, 0, Integer.MIN_VALUE,
                        new int[sequence.length]));
    }

    // [1, 2, 6, 3, 5] -> 4 [1, 2, 3, 5]
    private int getLengthOfLISS(int[] sequence,
                                int currentIndex,
                                int depthMax,
                                int[] memo) {
        if (currentIndex >= sequence.length) return 0;
        if (memo[currentIndex] != 0) {
            return memo[currentIndex];
        }
        int levelMax = 0;
        for (int i = currentIndex; i < sequence.length; i++) {
            if (sequence[i] > depthMax) {
                int seqLen = getLengthOfLISS(sequence, i + 1, sequence[i], memo) + 1;
                if (seqLen > levelMax) levelMax = seqLen;
            }
        }
        memo[currentIndex] = levelMax;
        return memo[currentIndex];
    }
}