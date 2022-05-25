package DoublePointer;

/**
 *
 * LeetCode 209: 长度最小子数组
 * 给定一个正整数数组和一个正整数n，从数组中寻求和值大于等于n 的最短子数组长度
 * 如果存在，返回最短子数组长度，如果不存在，返回0
 * 双指针/滑动窗口
 *
 * @author Richa
 * @date 2020/10/27 16:00
 */
public class MinSubArrayLength {
    public int findShortestArrayLength(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int left = 0, right = 0, ans = Integer.MAX_VALUE, sum = 0;
        while (right < n) {
            sum += nums[right];
            while (sum >= target) {
                ans = Math.min(ans, right - left + 1);
                sum -= nums[left];
                left++;
            }
            right++;
        }
        return ans == Integer.MIN_VALUE ? 0 : ans;
    }
}
