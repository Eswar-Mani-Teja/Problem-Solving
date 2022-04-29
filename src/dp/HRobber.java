package dp;

import static java.lang.Math.max;

public class HRobber {

    public static void main(String[] args) {
        HRobber hRobber = new HRobber();

        int[] houses = new int[]{2, 7, 9, 3, 1};

        System.out.println("Max. Loot: " + hRobber.getMaximumLootValue(houses, 0));

        System.out.println("Max. Loot using Memo: "
                + hRobber.getMaximumLootValueMEMO(houses, 0, new int[houses.length]));

        System.out.println("Max. Loot using Tabulation: "
                + hRobber.getMaximumLootValueTABULATION(houses));
    }

    // Top-Down approach WITHOUT memoization
    public int getMaximumLootValue(int[] houses, int current) {
        if (current >= houses.length) return 0;
        int adjacentLoot = getMaximumLootValue(houses, current + 1);
        int currentLoot = getMaximumLootValue(houses, current + 2) + houses[current];
        return max(currentLoot, adjacentLoot);
    }

    // Top-Down approach WITH memoization
    public int getMaximumLootValueMEMO(int[] houses, int current, int[] memo) {
        if (current >= houses.length) return 0;
        if (memo[current] == 0) {
            int adjacentLoot = getMaximumLootValueMEMO(houses, current + 1, new int[houses.length]);
            int currentLoot = getMaximumLootValueMEMO(houses, current + 2, new int[houses.length]) + houses[current];
            memo[current] = max(currentLoot, adjacentLoot);
        }
        return memo[current];
    }

    // Bottom-up approach (Iterative method)
    public int getMaximumLootValueTABULATION(int[] houses) {
        final int first_house = 0;
        final int second_house = 1;
        int maxLoot = 0;
        int[] lootTill = new int[houses.length];
        if (houses.length > 0) { // Base Case #1
            maxLoot = lootTill[first_house] = houses[first_house];
        }
        if (houses.length > 1) { // Base Case #2
            maxLoot = max(maxLoot, houses[second_house]);
            lootTill[second_house] = maxLoot;
        }
        for (int currentHouse = 2; currentHouse < houses.length; currentHouse++) {
            int currentLoot = lootTill[currentHouse - 2] + houses[currentHouse];
            int adjacentLoot = lootTill[currentHouse - 1];
            int loot = max(currentLoot, adjacentLoot);
            lootTill[currentHouse] = loot;
            if (maxLoot < loot) {
                maxLoot = loot;
            }
        }
        return maxLoot;
    }
}
