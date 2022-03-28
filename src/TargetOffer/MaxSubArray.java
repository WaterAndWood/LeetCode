package TargetOffer;

/**
 *
 * Offer42: 连续子数组的最大和
 * 动态规划
 *
 * @author Richa
 * @date 2022/3/28 23:27
 */
public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // nums[i - 1]表示前i - 1个数的连续数组的最大和，相当于dp[i - 1]
            nums[i] += Math.max(0, nums[i - 1]);
            res = Math.max(res, nums[i]);
        }
        return res;
    }

    /**
     * 使用数组保存连续子数组的最大和。动态规划表dp[i]表示以nums[i]为结尾的连续子数组最大和
     */
    public int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = dp[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] < 0) {
                dp[i] = nums[i];
            } else {
                dp[i] = dp[i - 1] + nums[i];
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
