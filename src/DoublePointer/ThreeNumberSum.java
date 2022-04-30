package DoublePointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * LeetCode15: 三数之和
 * 暴力搜索时间复杂度O(n^3)
 * 排序后使用双指针，时间复杂度O(n^2)
 * 固定3个指针中最小（左）的指针k，双指针在（k, len(nums))之间按照双指针处理
 *
 * @author Richa
 * @date 2022/4/30 17:18
 */
public class ThreeNumberSum {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        /**
         * 排序
         */
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int k = 0; k < nums.length - 2; k++) {
            /**
             * 最小值都大于0，所以不会相加得0
             */
            if (nums[k] > 0) {
                break;
            }
            /**
             * 重复的值不计算
             */
            if (k > 0 && nums[k] == nums[k - 1]) {
                continue;
            }
            int i = k + 1, j = nums.length - 1;
            while (i < j) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum < 0) {
                    /**
                     * 去重
                     */
                    while (i < j && nums[i] == nums[++i]) {
                    }

                } else if (sum > 0) {
                    while (i < j && nums[j] == nums[--j]) {
                    }

                } else {
                    List<Integer> temp = new ArrayList<>(Arrays.asList(nums[k], nums[i], nums[j]));
                    res.add(temp);
                    /**
                     * 获得一种结果后需要移动i, j并去重
                     */
                    while (i < j && nums[i] == nums[++i]) {
                    }
                    while (i < j && nums[j] == nums[--j]) {
                    }

                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {-1, 0, 1, 2, -1, -4};
        ThreeNumberSum t = new ThreeNumberSum();
        System.out.println(t.threeSum(nums));
    }
}
