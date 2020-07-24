package DynamicProgramming;

/**
 *
 * LeetCode买卖股票最佳时机的系列题目
 *
 * 作者：labuladong
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/solution/yi-ge-tong-yong-fang-fa-tuan-mie-6-dao-gu-piao-wen/
 * dp[i][k][0 or 1] 第一个是天数，第二个是允许交易的最大次数，第三个是当前的持有状态（即之前说的 rest 的状态，用 1 表示持有，0 表示没有持有）
 *
 * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
 *               max(   选择 rest  ,             选择 sell      )
 *
 * 解释：今天我没有持有股票，有两种可能：
 * 要么是我昨天就没有持有，然后今天选择 rest，所以我今天还是没有持有；
 * 要么是我昨天持有股票，但是今天我 sell 了，所以我今天没有持有股票了。
 *
 * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
 *               max(   选择 rest  ,           选择 buy         )
 *
 * 解释：今天我持有着股票，有两种可能：
 * 要么我昨天就持有着股票，然后今天选择 rest，所以我今天还持有着股票；
 * 要么我昨天本没有持有，但今天我选择 buy，所以今天我就持有股票了。
 *
 * base case：
 * dp[-1][k][0] = 0
 * 解释：因为 i 是从 0 开始的，所以 i = -1 意味着还没有开始，这时候的利润当然是 0 。
 * dp[-1][k][1] = -infinity
 * 解释：还没开始的时候，是不可能持有股票的，用负无穷表示这种不可能。
 * dp[i][0][0] = 0
 * 解释：因为 k 是从 1 开始的，所以 k = 0 意味着根本不允许交易，这时候利润当然是 0 。
 * dp[i][0][1] = -infinity
 * 解释：不允许交易的情况下，是不可能持有股票的，用负无穷表示这种不可能。
 *
 * dp[-1][k][0] = dp[i][0][0] = 0
 * dp[-1][k][1] = dp[i][0][1] = -infinity
 *
 *
 *
 * @author Richa
 * @date 2020/7/22 22:34
 */
public class StockJobbing {
    // LeetCode 121：一次交易
    public int maxProfit1Sentinel(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        // 遍历一遍，记录当前索引前的数组的最低价格
        int minPrice = prices[0];
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            // 当前价格减去Sentinel代表的之前的最低价
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            minPrice = Math.min(minPrice, prices[i]);
        }
        return maxProfit;
    }

    // LeetCode 121：动态规划，k=1
    public int maxProfit1Dynamic(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        /* dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])
        dp[i][1][1] = max(dp[i-1][1][1], dp[i-1][0][0] - prices[i])
                = max(dp[i-1][1][1], -prices[i])
        解释：k = 0 的 base case，所以 dp[i-1][0][0] = 0。

        现在发现 k 都是 1，不会改变，即 k 对状态转移已经没有影响了。
        可以进行进一步化简去掉所有 k：
        dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
        dp[i][1] = max(dp[i-1][1], -prices[i])*/
        int n = prices.length;
        int[][] dp = new int[n][2];

        //   dp[0][0]
        // = max(dp[-1][0], dp[-1][1] + prices[i])
        // = max(0, -infinity + prices[i]) = 0
        dp[0][0] = 0;
        //   dp[0][1]
        // = max(dp[-1][1], dp[-1][0] - prices[i])
        // = max(-infinity, 0 - prices[i])
        // = -prices[i]
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], -prices[i]);
        }
        return dp[n-1][0];
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
    // LeetCode 122：可参与多次交易，k=+infinity
    // 动态规划：二维数组dp[i][j]:i表示索引i天获得的最大利润，索引j表示现金（0）还是股票（1）
    public int maxProfit2Dynamic(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }
        /*
        * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
        * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
        *             = max(dp[i-1][k][1], dp[i-1][k][0] - prices[i])
        * k和k-1一样，消除k状态
        * 所以状态转移方程：
        * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
        * dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])
        */
        int[][] dp = new int[len][2];
        // dp[0][0] = max(dp[-1][0], dp[-1][1] + prices[0])
        //          = max(0, -infinity + prices[0]) = 0
        dp[0][0] = 0;
        // dp[0][1] = max(dp[-1][1], dp[-1][0] - prices[0])
        //          = max(-infinity, 0 - prices[0]) = -prices[0]
        dp[0][1] = -prices[0];
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
        }
        return dp[len-1][0];
    }

    // LeetCode 123：交易次数k=2
    public int maxProfit3Dynamic(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        /*
        * 状态转移方程：
        * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
        * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
        * base case:
        * dp[0][k][0] = max(dp[-1][k][0], dp[-1][k][1] + prices[0])
        *             = max(0, -infinity + prices[0]) = 0
        * dp[0][k][1] = max(dp[-1][k][1], dp[-1][k-1][0] - prices[0])
        *             = max(-infinity, 0 - prices[0]) = -prices[0]
        */
        int maxK = 2;
        int n = prices.length;
        int[][][] dp = new int[n][maxK+1][2];
        dp[0][0][0] = 0;
        dp[0][1][0] = 0;
        dp[0][2][0] = 0;
        dp[0][0][1] = -prices[0];
        dp[0][1][1] = -prices[0];
        dp[0][2][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= 2; j++) {
                dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i-1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j-1][0] - prices[i]);
            }
        }
        return Math.max(dp[n-1][1][0], dp[n-1][2][0]);
    }

    public static void main(String[] args) {
        int[] prices = new int[] {7, 1, 5, 3, 6, 4};
        StockJobbing sj = new StockJobbing();
        System.out.println(sj.maxProfit1Dynamic(prices));
        System.out.println(sj.maxProfit2Dynamic(prices));
    }
}
