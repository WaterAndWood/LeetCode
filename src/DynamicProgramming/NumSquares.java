package DynamicProgramming;

/**
 *
 * LeetCode 279: 完全平方数
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * 贪心算法：需要提前准备小于n的平方数的表，每一步选取大的平方数
 * 动态规划
 *
 * @author Richa
 * @date 2020/9/23 0:43
 */
public class NumSquares {
    public int numSquares(int n) {
        int[] dp= new int[n + 1];
        for (int i = 1; i <= n; i++) {
            // 组成dp[i]全是1，个数最大
            dp[i] = i;
            for (int j = 1; j * j <= i; j++) {
                // 状态转移方程
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }
}
