package practice.meta.leetcode.p121;

public class Solution1 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int curMin = prices[0];
        
        int maxProfit = 0;

        for (int i = 1; i < prices.length; ++i) {
            int price = prices[i];

            if (price < curMin) { // a new bottom
                curMin = price;
            }

            int profit = price - curMin;

            if (profit > maxProfit) {
                maxProfit = profit;
            }
        }

        return maxProfit;
    }
}
