package dp;

public class NinjaTraining {

    public static void main(String[] args) {
        //int[][] meritPoints = new int[][]{{1, 2, 5}, {3, 1, 1}, {3, 3, 3}};
        //int[][] meritPoints = new int[][]{{18, 11, 19}, {4, 13, 7}, {1, 8, 13}};
        int[][] meritPoints = new int[][]{{10, 50, 1}, {5, 100, 11}};  //110
        int numberOfTrainingDays = meritPoints.length;
        int maxMeritPoints = getMaximumMeritPoints(meritPoints, numberOfTrainingDays, -1);
        System.out.println("Maximum Merit Points: " + maxMeritPoints + " [BF]");
    }

    //Brute Force
    private static int getMaximumMeritPoints(int[][] points, int day, int nextAct) {
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
}
