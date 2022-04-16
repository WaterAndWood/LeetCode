package TargetOffer;

import java.util.Arrays;

/**
 *
 * Offer60: n个骰子的点数
 *
 * @author Richa
 * @date 2022/4/16 10:18
 */
public class PrintProbability {
    /**
     * 暴力递归
     */
    public double[] sumProbability(int n) {
        if (n < 1) {
            return new double[0];
        }
        double[] res = new double[5 * n + 1];
        // sum是n个骰子的和，范围从n~6n
        int sum = n;
        // res中依次存放sum, sum+1, ...的概率
        for (int i = 0; i < res.length; i++) {
            res[i] = summa(sum, n) / Math.pow(6, n);
            sum++;
        }
        return res;
    }

    /**
     * 递归运算，大量重复计算
     * @param sum n个骰子的和
     * @param n 骰子数量
     */
    private double summa(int sum, int n) {
        if (sum <= 0) {
            return 0;
        }
        if (n == 1) {
            return sum <= 6 ? 1 : 0;
        }
        return summa(sum-1, n-1) + summa(sum-2, n-1) + summa(sum-3, n-1) +
                summa(sum-4, n-1) + summa(sum-5, n-1) + summa(sum-6, n-1);
    }

    /**
     * 动态规划，使用二维数组dp[i][j]表示前i个骰子的点数和为j的概率，执行状态转移
     */
    public double[] twoSum(int n) {
        if (n < 1) {
            return new double[0];
        }

        int[][] dp = new int[n+1][6*n+1];
        // 表示一个骰子掷出i点的次数为1
        for (int i = 1; i <= 6; i++) {
            dp[1][i] = 1;
        }
        for (int i = 2; i <= n; i++) {
            for (int s = i; s <= 6 * i; s++) {
                for (int j = 1; j <= 6; j++) {
                    // 第i个骰子的最小值是i，点数和减去这一次骰子的点数，如果小于i-1，这次骰子的点数可以不用加
                    if (s - j < i - 1) {
                        break;
                    }
                    /**
                     * 当前n个骰子出现的点数和s等于前一个骰子出现的点数和加上这一次骰子的点数
                     * 这次骰子点数2，dp[i][9] += dp[i-1][9-2]
                     */
                    dp[i][s] += dp[i-1][s-j];
                }
            }
        }
        double total = Math.pow(6, n);
        double[] res = new double[5*n+1];
        for (int i = n; i <= 6 * n; i++) {
            res[i-n] = dp[n][i] / total;
        }
        return res;
    }

    /**
     * 由于dp[i]仅由dp[i-1]递推得出，降低时间复杂度，只建立dp, temp交替前进即可
     */
    public double[] twoSum2(int n) {
        if (n < 1) {
            return new double[0];
        }
        double[] dp = new double[6];
        Arrays.fill(dp, 1.0 / 6.0);
        for (int i = 2; i <= n; i++) {
            double[] temp = new double[5 * i + 1];
            for (int j = 0; j < dp.length; j++) {
                for (int k = 0; k < 6; k++) {
                    temp[j + k] += dp[j]*(1.0/6.0);
                }
            }
            //i个骰子的点数之和全都算出来后，要将temp数组移交给dp数组，
            // dp数组就会代表i个骰子时的可能出现的点数之和的概率；用于计算i+1个骰子时的点数之和的概率
            dp = temp;
        }
        return dp;
    }

}
