package dp.array_and_grid;

import utils.Log;

import static java.lang.Math.abs;
import static java.lang.Math.min;

public class FrogJump {

    /*
     * There is a frog on the 1st step of an N stair long staircase.
     * The frog wants to reach the Nth stair.
     * Height[i] is the height of the (i+1)th stair.
     * If the frog jumps from ith to jth stair,
     * the energy lost in the jump is given by Height[i - 1] - Height[j - 1]
     * Frog movement: currentPosition = i;
     * Possible Jump locations: (i+1) or (i+2)
     * What is the minimum total energy required [1st ---> Nth] stair
     */

    public static void main(String[] args) {
        var frog = new FrogJump();
//        int[] height = new int[]{10, 20, 30, 10};   //-> 20
        int[] height = new int[]{30, 10, 60, 10, 60, 50};   //->40
        int n = height.length;
        int minEnergy = frog.getMinimumEnergyTBS(height, n - 1);
        System.out.println("Minimum Energy: " + minEnergy + " [TBS]");
        System.out.println("Minimum Energy: " + frog.getMinimumEnergy(height, n - 1, new int[n]) + " [memo]");
        System.out.println("Minimum Energy: " + frog.getMinimumEnergyWithVariableStepSize(height, n - 1, 2) + " [variable-k]");
        Log.callSummaries();
    }

    private int getJumpEnergy(int[] height, int from, int to) {
        return abs(height[from] - height[to]);
    }

    /**
     * @param height heights of steps
     * @param n lastIndex of the height array
     * @return Minimum Energy required to Jump from 0 to n
     */
    private int getMinimumEnergy(int[] height, int n) {
        Log.callCounter("BS");
        if (n <= 0) return 0;
        if (n == 1) return getJumpEnergy(height, 0, 1);
        return min(getMinimumEnergy(height, n - 1) + getJumpEnergy(height, n - 1, n),
                getMinimumEnergy(height, n - 2) + getJumpEnergy(height, n - 2, n));
    }

    // [(start) 0, .... , (end) (n-1)] -> array.length = n
    private int getMinimumEnergy(int[] height, int n, int[] memo) {
        Log.callCounter("memo");
        if (n <= 0) return 0;
        if (n == 1) return getJumpEnergy(height, 0, 1);
        if (memo[n] == 0) {
            memo[n] = min(
                    getMinimumEnergy(height, n - 1, memo) + getJumpEnergy(height, n - 1, n),
                    getMinimumEnergy(height, n - 2, memo) + getJumpEnergy(height, n - 2, n)
            );
        }
        return memo[n];
    }


    // Tabulation Method
    private int getMinimumEnergyTB(int[] height, int n) {
        if (n <= 0) return 0;
        int energyToFirstStep = getJumpEnergy(height, 0, 1);
        if (n == 1) return energyToFirstStep;
        //Base Cases
        int[] memo = new int[n + 1];
        memo[0] = 0;
        memo[1] = energyToFirstStep;
        int i;
        for (i = 2; i <= n && i < height.length; i++) {
            memo[i] = min(memo[i - 1] + getJumpEnergy(height, i - 1, i),
                    memo[i - 2] + getJumpEnergy(height, i - 2, i));
        }
        return memo[i - 1];
    }


    //Tabulation Method: Space optimized
    private int getMinimumEnergyTBS(int[] height, int n) {
        if (n <= 0) return 0;
        int energyToFirstStep = getJumpEnergy(height, 0, 1);
        if (n == 1) return energyToFirstStep;
        int prev2 = 0;
        int prev1 = energyToFirstStep;
        for (int i = 2; i <= n; i++) {
            int current = min(prev1 + getJumpEnergy(height, i - 1, i),
                    prev2 + getJumpEnergy(height, i - 2, i));
            prev2 = prev1;
            prev1 = current;
        }
        return prev1;
    }


    // Frog Jump size: [1 ... maxStepSize]
    private int getMinimumEnergyWithVariableStepSize(int[] height, int n, int maxStepSize) {
        if (n <= 0) return 0;
        int energyToFirstStep = getJumpEnergy(height, 0, 1);
        if (n == 1) return energyToFirstStep;
        int[] memo = new int[height.length];
        memo[0] = 0;
        memo[1] = energyToFirstStep;
        int currentStep;
        for (currentStep = 2; currentStep <= n; currentStep++) {

            int minEnergyToCurrentStep = Integer.MAX_VALUE;
            int startStep = currentStep - 1;
            for (int stepSize = 1;
                 stepSize <= maxStepSize && startStep >= 0;
                 stepSize++) {
                startStep = currentStep - stepSize;
                int stepEnergy = memo[startStep] + getJumpEnergy(height, startStep, currentStep);
                if (stepEnergy < minEnergyToCurrentStep) minEnergyToCurrentStep = stepEnergy;
            }
            memo[currentStep] = minEnergyToCurrentStep;
        }
        return memo[currentStep - 1];
    }
}
