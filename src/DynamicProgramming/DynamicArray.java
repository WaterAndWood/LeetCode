package DynamicProgramming;

import java.util.Arrays;

/**
 *
 * 动态数组类问题，子数组或序列求和等，很多用动态规划算法
 * LeetCode 53: 最大连续子数组和
 * LeetCode 300: 最长上升子序列
 * LeetCode 152: 乘积最大数组
 *
 * @author Richa
 * @date 2020/7/29 21:26
 */
public class DynamicArray {

    /**
     * 最大连续子数组和：动态规划，记录最大子序的起始left，right位置
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int l = 0, r = 0;
        int left = 0, right = 0;
        /**
         * 一维数组简化为一个变量，pre表示前一个状态
         * dp[i] = max(dp[i-1]+nums[i], nums[i])
         */
        int pre = nums[0];
        int ans = pre;
        for (int i = 1; i < nums.length; i++) {
            // pre = Math.max(pre + nums[i], nums[i]);
            if (pre + nums[i] > nums[i]) {
                pre = pre + nums[i];
                r = i;
            } else {
                pre = nums[i];
                l = r = i;
            }
            // ans = Math.max(ans, pre);
            if (ans < pre) {
                ans = pre;
                left = l;
                right = r;
                l = i;
            }
        }
        System.out.println(left + String.valueOf(right));
        return ans;
    }

    /**
     * 最长上升子序列
     * 子数组要求必须连续，子序列不一定连续
     * dp[i] 的值代表 nums 前 i 个数字的最长子序列长度
     * dp[i] = max(dp[i], dp[j] + 1) for j in [0, i)
     * 计算dp[i]需要遍历i之前的元素，如果nums[i]大于nums[j]，子序列长度为dp[j]+1
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            /**
             * 遍历i之前的数组
             */
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
    
    /**
     * 乘积最大子数组，需要以nums[i]的正负为依据求最大值（正数）和最小值（负数）
     */
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        /**
         * dp[i][0]：以nums[i]结尾的连续子数组的最小值
         * dp[i][1]: 以nums[i]结尾的连续子数组的最大值
         */
        int[][] dp = new int[nums.length][2];
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            /**
             * 最大值来源：之前正数最大值 * 正数 或者 之前负数最小值 * 负数
             * 最小值来源：之前负数最小值 * 正数 或者 之前正数最大值 * 负数
             */
            if (nums[i] >= 0) {
                dp[i][1] = Math.max(nums[i], dp[i - 1][1] * nums[i]);
                dp[i][0] = Math.min(nums[i], dp[i - 1][0] * nums[i]);
            } else {
                dp[i][1] = Math.max(nums[i], dp[i - 1][0] * nums[i]);
                dp[i][0] = Math.min(nums[i], dp[i - 1][1] * nums[i]);
            }
        }
        int res = dp[0][1];
        for (int j = 1; j < nums.length; j++) {
            res = Math.max(res, dp[j][1]);
        }
        return res;
    }

}
