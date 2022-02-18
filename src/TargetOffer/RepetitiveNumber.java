package TargetOffer;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * LeetCode3: 数组中重复的数字
 * 排序算法需要时间复杂度O(nlogn)
 *
 * @author Richa
 * @date 2022/2/17 22:50
 */
public class RepetitiveNumber {
    /**
     * 使用HashSet
     */
    public static int findNumberWithSet(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.contains(num)) {
                set.add(num);
            } else {
                return num;
            }
        }
        return -1;
    }

    /**
     * 0~n-1个数字能放入长度为n的数组
     */
    public static int findNumberWithArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == i) {
                continue;
            } else if (nums[i] == nums[nums[i]]) {
                return nums[i];
            } else {
                int temp = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = temp;
                // 交换之后，从交换位置开始判断
                i--;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[] {3, 2, 1, 0, 2, 5, 3};
        System.out.println(findNumberWithSet(nums1));
        System.out.println(findNumberWithArray(nums1));

        int[] nums2 = new int[] {2, 3, 5, 4, 3, 2, 6, 7};
        System.out.println(findDuplication(nums2, 8));
    }

    /**
     * 题目二 不修改数组找出重复的数字
     * 一是可以辅助空间O(n)数组
     * 二是可以使用二分法，时间复杂度O(nlogn)，遍历整个数组，查询在每段范围内数字个数
     */
    public static int findDuplication(int[] nums, int length) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 1;
        int end = length - 1;
        while (start <= end) {
            int mid = (end - start) / 2 + start;
            int count = countRange(nums, length, start, mid);
            if (end == start) {
                if (count > 1) {
                    return start;
                } else {
                    break;
                }
            }
            if (count > (mid - start + 1)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    private static int countRange(int[] nums, int length, int start, int end) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (nums[i] >= start && nums[i] <= end) {
                count++;
            }
        }
        return count;
    }

}
