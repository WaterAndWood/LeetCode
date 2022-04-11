package TargetOffer;

/**
 *
 * Offer57: 和为s的两个数字
 *
 * @author Richa
 * @date 2022/4/11 22:41
 */
public class TwoSum {
    /**
     * 递增排序考虑双指针
     */
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int[] res = new int[2];
        int l = 0, r = nums.length - 1;
        while (l < r) {
            if (nums[l] + nums[r] < target) {
                l++;
            } else if (nums[l] + nums[r] > target) {
                r--;
            } else {
                res[0] = nums[l];
                res[1] = nums[r];
                break;
            }
        }
        return res;
    }
}
