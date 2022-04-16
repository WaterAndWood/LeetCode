package TargetOffer;

/**
 *
 * Offer63: 股票的最大利润
 * 假设n天，第a天买，第b天卖，a < b，交易方案数(n+1)+(n+2)+...+2+1=n(n-1)/2，暴力时间复杂度：O(n^2)
 * 限定买卖一次
 * 动态规划
 *
 * @author Richa
 * @date 2022/4/16 20:43
 */
public class MaxProfit {
    /**
     * 动态规划列表dp，dp[i]表示以prices[i]结尾的子数组最大利润，前i日的最大利润
     * dp[0] = 0，返回值是dp[n-1]
     */
    public int maxProfit1(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int minPrice = prices[0];
        int[] dp = new int[prices.length];
        for (int i = 1; i < prices.length; i++) {
            // 转移方程
            dp[i] = Math.max(prices[i] - minPrice, dp[i-1]);
            minPrice = Math.min(minPrice, prices[i]);
        }
        return dp[prices.length - 1];
    }

    /**
     * 空间复杂度优化，从O(n) -> O(1)，只保留minPrice和最大利润
     */
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int minPrice = prices[0];
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            minPrice = Math.min(minPrice, prices[i]);
        }
        return maxProfit;
    }

    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int cost = Integer.MAX_VALUE;
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            profit = Math.max(profit, prices[i] - cost);
            cost = Math.min(cost, prices[i]);
        }
        return profit;

    }
}
