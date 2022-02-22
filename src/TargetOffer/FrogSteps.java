package TargetOffer;

/**
 *
 * Offer10-2: 青蛙跳台阶问题
 * 动态规划问题：f(n+1)=f(n) + f(n-1)，状态转移方程dp[i+1]=dp[i] + dp[i-1]
 * 与斐波那契数列不同的是前两个值f(0) = 1, f(1) = 1
 *
 * 注意：这个问题有很强的拓展性
 *
 * @author Richa
 * @date 2022/2/22 21:39
 */
public class FrogSteps {
    public int numWays1(int n) {
        if (n == 0) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            // dp[i]=dp[i-1] + dp[i-2]
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        }
        return dp[n];
    }

    /**
     * 优化空间
     */
    public int numWays2(int n) {
        if (n == 0) {
            return 1;
        }
        int cur = 1, next = 1;
        for (int i = 0; i < n; i++) {
            int temp = (cur + next) % 1000000007;
            cur = next;
            next = temp;
        }
        return cur;
    }
}
