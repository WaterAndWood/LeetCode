package TargetOffer;

/**
 *
 * Offer14: 剪绳子
 *
 * @author Richa
 * @date 2022/2/27 22:58
 */
public class CuttingRope {
    /**
     * 动态规划
     * dp数组记录从0到n长度的绳子的最大乘积，dp[i]表示长度为i的绳子剪掉成m段的最大乘积
     */
    public int cuttingRope1(int n) {
        if (n < 2) {
            return 0;
        } else if (n == 2) {
            return 1;
        } else if (n == 3) {
            return 2;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        int max = 0;
        for (int i = 4; i <= n; i++) {
            for (int j = 1; j <= i / 2; j++) {
                max = Math.max(max, dp[j] * dp[i - j]);
                dp[i] = max;
            }
        }
        return dp[n];
    }

    /**
     * 计算结果太大，需要取模，取模后无法反映真实大小，使用贪心算法，尽量剪长度3的绳子
     */
    public int cuttingRopt2(int n) {
        if (n < 2) {
            return 0;
        } else if (n == 2) {
            return 1;
        } else if (n == 3) {
            return 2;
        }
        long res = 1;
        while (n > 4) {
            res = res * 3 % 1000000007;
            n -= 3;
        }
        return (int)(res * n % 1000000007);
    }
}
