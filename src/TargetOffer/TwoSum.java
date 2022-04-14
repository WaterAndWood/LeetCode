package TargetOffer;

import java.util.ArrayList;
import java.util.List;

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
                // 找到即退出循环
                res[0] = nums[l];
                res[1] = nums[r];
                break;
            }
        }
        return res;
    }

    /**
     * 和为s的连续正数序列，从1开始，1,2,3...
     * 滑动窗口，左闭右开
     * 滑动条件：
     * 窗口和小于target，窗口右边界向右移动
     * 窗口和大于target，窗口左边界向右移动
     */
    public int[][] findContinusSequence(int target) {
        int l = 1, r = 1;
        int sum = 0;
        List<int[]> res = new ArrayList<>();
        while (l <= target / 2) {
            if (sum < target) {
                sum += r;
                r++;
            } else if (sum > target) {
                sum -= l;
                l++;
            } else {
                int[] arr = new int[r - l];
                for (int k = l; k < r; k++) {
                    arr[k - l] = k;
                }
                res.add(arr);
                sum -= l;
                l++;
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
