package arrays;

public class MaxProfitStock {
    // Buy and Sell Stock to make Max. Profit
    public static void main(String[] args) {
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        int maxProfit = getMaxProfit(prices);
        System.out.println("Max. Profit: " + maxProfit);
    }

    // O(n)
    private static int getMaxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = Integer.MIN_VALUE;
        for (int price : prices) {
            minPrice = Math.min(minPrice, price);
            maxProfit = Math.max(maxProfit, price - minPrice);
        }
        return maxProfit;
    }
}
