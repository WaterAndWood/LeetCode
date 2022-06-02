package DynamicProgramming;

import java.util.Scanner;

/**
 *
 * 牛客：石头碰撞
 * 转换成0-1背包问题，分成两部分，使两部分的值接近 sum / 2
 *
 * @author Richa
 * @date 2020/8/8 20:25
 */
public class StonesWeight {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] stones = new int[n];
        for (int i = 0; i < n; i++) {
            stones[i] = sc.nextInt();
        }
        int ans = lastStoneWeight(stones);
        System.out.println(ans);
    }
    /**
     * 0-1背包dp[i][w]定义：前i个商品，当容量为w时，最大价值dp[i][w]
     * dp[i][j]定义：前i个石头，当容量为j时，最大重量dp[i][j]
     */
    public static int lastStoneWeight(int[] stones) {
        int len = stones.length;
        int totalStone = 0;
        for (int stone : stones) {
            totalStone += stone;
        }
        int maxCapicity = totalStone / 2;
        int[][] dp = new int[len + 1][maxCapicity + 1];
        for (int i = 1; i < len + 1; i++) {
            for (int j = 1; j < maxCapicity + 1; j++) {
                if (j - stones[i - 1] < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j - stones[i - 1]] + stones[i - 1], dp[i - 1][j]);
                }
            }
        }
        return totalStone - 2 * dp[len][maxCapicity];
    }

    /**
     *
     * 空间简化：dp第一维始终是i-1，可以消除
     */
    public static int lastStoneWeightII(int[] stones) {
        int len = stones.length;
        int totalStone = 0;
        for (int stone : stones) {
            totalStone += stone;
        }
        int maxCapicity = totalStone / 2;
        int[] dp = new int[maxCapicity + 1];
        for (int i = 0; i < len; i++) {
            for (int j = maxCapicity; j - stones[i] >= 0; j--) {
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }
        return totalStone - 2 * dp[maxCapicity];
    }
}
