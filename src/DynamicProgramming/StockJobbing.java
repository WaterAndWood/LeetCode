package DynamicProgramming;

/**
 *
 * LeetCode买卖股票最佳时机的系列题目
 * 有的题是动态规划求解，有的是其他方法
 *
 * @author Richa
 * @date 2020/7/22 22:34
 */
public class StockJobbing {
    // LeetCode 121：一次交易
    public int maxProfit1(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        // 遍历一遍，记录当前索引前的数组的最低价格
        int minPrice = prices[0];
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            // 当前价格减去哨兵代表的之前的最低价
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            minPrice = Math.min(minPrice, prices[i]);
        }
        return maxProfit;
    }

    // LeetCode 122：可参与多次交易
    // 贪心算法，只加正数
    public int maxProfit2Greedy(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            int diff = prices[i + 1] - prices[i];
            if (diff > 0) {
                res += diff;
            }
        }
        return res;
    }
    // LeetCode 122：可参与多次交易
    // 动态规划：二维数组dp[i][j]:i表示索引i天获得的最大利润，索引j表示现金（0）还是股票（1）
    public int maxProfit2Dynamic(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }
        // 0：持有现金
        // 1：持有股票
        // 状态转移：0 → 1 → 0 → 1 → 0 → 1 → 0
        int[][] dp = new int[len][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < len; i++) {
            // 比较昨天持有现金还是持有股票加今天卖股票
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 比较昨天持有股票还是持有现金加今天买股票
            dp[i][1] = Math.max(dp[i - 1][1] , dp[i - 1][0] - prices[i]);
        }
        return dp[len - 1][0];
    }

    public static void main(String[] args) {
        int[] prices = new int[] {7, 1, 5, 3, 6, 4};
        StockJobbing sj = new StockJobbing();
        System.out.println(sj.maxProfit1(prices));
        System.out.println(sj.maxProfit2Dynamic(prices));
    }
}
