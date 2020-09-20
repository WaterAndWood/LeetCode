package DynamicProgramming;

import java.util.Scanner;

/**
 *
 * 牛客：合并金币
 *
 * 有 N 堆金币排成一排，第 i 堆中有 C[i] 块金币。每次合并都会将相邻的两堆金币合并为一堆，
 * 成本为这两堆金币块数之和。经过N-1次合并，最终将所有金币合并为一堆。请找出将金币合并为一堆的最低成本。
 *
 * 第一行输入一个数字 N 表示有 N 堆金币
 * 第二行输入 N 个数字表示每堆金币的数量 C[i]
 *
 * @author Richa
 * @date 2020/9/20 13:44
 */
public class LinearSum {
    /**
     * 区间动态规划：
     * 比如计算第i个到第j个的成本F(i, j), 最后一步是把两个小的堆到一起
     * 用k来标记划分点的下标，则第一个小堆的成本为F(i, k), 第二个小堆的成本是F(k+1, j)，新的成本是sum(i, j)
     * 所以状态方程是F(i, j) = F(i, k) + F(k+1, j) + sum(i, j),需要遍历k
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] money = new int[n + 1];
        int[] preSum = new int[n + 1];
        /**
         * 索引0是0
         * preSum是累加和
         */
        for (int i = 1; i <= n; i++) {
            money[i] = sc.nextInt();
            if (i == 1) {
                preSum[i] = money[i];
            } else {
                preSum[i] = preSum[i - 1] + money[i];
            }
        }
        sc.close();

        int[][] dp = new int[n + 1][n + 1];
        // 遍历区间的长度
        for (int len = 2; len <= n; len++) {
            for (int i = 1; i <= n - len + 1; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
                int sum = preSum[j] - preSum[i - 1];
                // 找出分割点k
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + sum);
                }
            }
        }
        System.out.println(dp[1][n]);
    }
}
