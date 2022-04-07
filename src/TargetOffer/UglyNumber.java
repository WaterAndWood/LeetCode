package TargetOffer;

/**
 *
 * Offer49: 丑数
 * 
 * @author Richa
 * @date 2022/4/7 23:17
 */
public class UglyNumber {
    public int nthUglyNumber(int n) {
        /**
         * 分别对应2,3,5的索引
         */
        int a = 0, b = 0, c = 0;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int n2 = dp[a] * 2;
            int n3 = dp[b] * 3;
            int n5 = dp[c] * 5;
            dp[i] = Math.min(n5, Math.min(n2, n3));
            if (dp[i] == n2) {
                a++;
            }
            if (dp[i] == n3) {
                b++;
            }
            if (dp[i] == n5) {
                c++;
            }
        }
        return dp[n - 1];
    }
}
