package MathProblem;

import java.util.Scanner;

/**
 *
 * 牛客网：
 * 扔n个骰子，第i个骰子有可能投掷出Xi种等概率的不同的结果，数字从1到Xi。
 * 所有骰子的结果的最大值将作为最终结果。求最终结果的期望。
 * 第一行一个整数n，表示有n个骰子。（1 <= n <= 50）
 * 第二行n个整数，表示每个骰子的结果数Xi。(2 <= Xi <= 50)
 *
 * 前缀和：
 * 一维前缀和 num[i] = sum[i+1] - sum[i]
 * 二维前缀和 dp[i][j] = num[i][j] + dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1]
 *
 * 概率的计算方式：二维前缀和，利用面积相减计算某可能值的概率
 *
 * @author Richa
 * @date 2020/8/5 21:13
 */
public class DiceExpectation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        /**
         * pre: 前一个可能值的概率
         * ans: 期望 = 可能值 * 概率
         * max: 题意中可能值的最大值，表示可能值的范围
         */
        double pre = 0;
        double ans = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
            max = Math.max(max, nums[i]);
        }

        /**
         * i：表示可能值
         * cur: 计算每个骰子所围成的面积/ 总面积，也就是概率
         */
        for (int i = 1; i <= max; i++) {
            double cur = 1;
            for (int j = 0; j < n; j++) {
                cur *= (double)(Math.min(i, nums[j]) / nums[j]);
            }
            ans += (cur - pre) * i;
            pre = cur;
        }
        System.out.println(String.format("%.2f", ans));
    }
}
