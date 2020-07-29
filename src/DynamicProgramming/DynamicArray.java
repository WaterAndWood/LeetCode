package DynamicProgramming;

import java.util.Arrays;

/**
 *
 * 动态数组类问题，子数组或序列求和等，很多用动态规划算法
 * LeetCode 53: 最大子序和
 * LeetCode 300: 最长上升子序列
 *
 * @author Richa
 * @date 2020/7/29 21:26
 */
public class DynamicArray {

    /**
     * 最大子序和：动态规划，记录最大子序的起始left，right位置
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
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

}
