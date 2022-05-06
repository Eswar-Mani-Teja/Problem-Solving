package dp;

import utils.Log;

public class NinjaTraining {

    public static void main(String[] args) {
        //int[][] meritPoints = new int[][]{{1, 2, 5}, {3, 1, 1}, {3, 3, 3}};
        //int[][] meritPoints = new int[][]{{18, 11, 19}, {4, 13, 7}, {1, 8, 13}};
        int[][] meritPoints = new int[][]{{10, 50, 1}, {5, 100, 11}};  //110
        int numberOfTrainingDays = meritPoints.length;
        int maxMeritPoints = getMaximumMeritPoints(meritPoints, numberOfTrainingDays, -1);
        System.out.println("Maximum Merit Points: " + maxMeritPoints + " [BF]");
        maxMeritPoints = getMaximumMeritPoints(meritPoints, numberOfTrainingDays, -1,
                new int[numberOfTrainingDays][meritPoints[0].length]);
        System.out.println("Maximum Merit Points: " + maxMeritPoints + " [MEMO-F]");
        maxMeritPoints = getMaxMeritPoints(meritPoints, numberOfTrainingDays);
        System.out.println("Maximum Merit Points: " + maxMeritPoints + " [TAB]");
        Log.callSummaries();
    }

    //Brute Force
    private static int getMaximumMeritPoints(int[][] points, int day, int nextAct) {
        Log.callCounter("bf");
        if (day == -1) return 0;
        int maxPoint = 0;
        for (int act = 0; act < points[0].length; act++) {
            if (act != nextAct) {
                int current = nextAct != -1 ? points[day][act] : 0;
                int meritPoint = getMaximumMeritPoints(points, day - 1, act) + current;
                if (meritPoint > maxPoint)
                    maxPoint = meritPoint;
            }
        }
        return maxPoint;
    }

    //Memoization
    private static int getMaximumMeritPoints(int[][] points, int day, int nextAct, int[][] memo) {
        Log.callCounter("memo-full");
        if (day == -1) return 0;
        if (nextAct != -1) {
            int merit = memo[day][nextAct];
            if (merit != 0) {
                //System.out.printf("memory: (%d, %d): %d%n", day, nextAct, merit);
                return merit;
            }
        }
        int maxMeritPoint = 0;
        for (int act = 0; act < points[0].length; act++) {
            if (act != nextAct) {
                int current = nextAct != -1 ? points[day][act] : 0;
                int meritPoint = getMaximumMeritPoints(points, day - 1, act, memo) + current;
                if (meritPoint > maxMeritPoint)
                    maxMeritPoint = meritPoint;
            }
        }
        if (nextAct != -1) {
            //System.out.printf("function: (%d, %d): %d%n", day, nextAct, maxMeritPoint);
            memo[day][nextAct] = maxMeritPoint;
        }
        return maxMeritPoint;
    }


    //Tabulation
    private static int getMaxMeritPoints(int[][] points, int days) {
        int activities = points[0].length;
        int[] currentMemo = new int[activities];
        int[] prevMemo = new int[activities];
        for (int day = 0; day < days; day++) {
            for (int currentAct = 0; currentAct < activities; currentAct++) {
                int pointMax = Integer.MIN_VALUE;
                for (int prevAct = 0; prevAct < activities; prevAct++) {
                    if (currentAct != prevAct) {
                        int point = prevMemo[prevAct] + points[day][currentAct];
                        if (point > pointMax) {
                            pointMax = point;
                            currentMemo[currentAct] = point;
                        }
                    }
                }
            }
            int[] temp = prevMemo;
            prevMemo = currentMemo;
            currentMemo = temp;
        }
        int maxMeritPoints = Integer.MIN_VALUE;
        for (int i = 0; i < activities; i++) {
            if (maxMeritPoints < prevMemo[i])
                maxMeritPoints = prevMemo[i];
        }
        return maxMeritPoints;
    }
}
