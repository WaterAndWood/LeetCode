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
     * 最大连续子数组和：动态规划
     * dp[i] = dp[i-1]+nums[i], dp[i-1]>0
     * dp[i] = nums[i], dp[i-1]<0
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        // dp[i]表示以nums[i]结尾的连续子数组的最大和
        int[] dp = new int[len];
        dp[0] = nums[0];
        int res = dp[0];
        for (int i = 1; i < len; i++) {
            if (dp[i-1] > 0) {
                dp[i] = dp[i-1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     * 空间进行优化，使用变量代替数组
     */
    public int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int res = nums[0], pre = 0;
        for (int i = 0; i < len; i++) {
            if (pre > 0) {
                pre = pre + nums[i];
            } else {
                pre = nums[i];
            }
            res = Math.max(pre, res);
        }
        return res;
    }


    /**
     * 最长上升子序列
     * 子数组要求必须连续，子序列不一定连续
     * dp[i] 表示nums[i]结尾的上升子序列长度，nums[i]是必须被选中的且是子序列的最后一个元素
     * dp[i] = max(dp[i], (dp[j] + 1 (for j in [0, i]))) ，表示nums[i]严格大于在它位置之前的某个数
     * 计算dp[i]需要遍历i之前的元素，如果nums[i]大于nums[j]，子序列长度为dp[j]+1
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        if (len < 2) {
            return len;
        }
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int res = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
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
