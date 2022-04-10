package TargetOffer;

/**
 *
 * Offer53: 在排序数组中查找数字
 * 在排序的数组中第一个想到是二分法查找，对二分法进行改进
 *
 * @author Richa
 * @date 2022/4/10 10:57
 */
public class FindNumInSortedArray {
    /**
     * 数字在排序数组中出现的次数
     * 二分法查找该数字的z左边出现和右边出现的索引位置
     * 左边界i = 0, 右边界 j = nums.length - 1
     * 1.nums[m] < target，则 target 在闭区间 [m + 1, j]中，因此执行 i = m + 1
     * 2.nums[m] > target，则 target 在闭区间 [i, m - 1]中，因此执行 j = m - 1
     * 3.nums[m] = target, 则左边界left在闭区间[i, m - 1]中，右边界right在闭区间[m + 1, j]中
     * 查找左边界left，执行j = m - 1；查找右边界right，执行i = m + 1。可将 nums[m] = target情况合并至其他两种情况中
     *
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 搜索右边界
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            int mid = (i + j) / 2;
            if (nums[mid] <= target) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        int right = i;
        // 查找完右边界后，j指向右边界，可用 nums[j] == target，若不包含target则直接提前返回 0 ，无需后续查找左边界
        if (j >= 0 && nums[j] != target) {
            return 0;
        }
        // 搜索左边界
        i = 0;
        j = nums.length - 1;
        while (i <= j) {
            int mid = (i + j) / 2;
            if (nums[mid] >= target) {
                j = mid - 1;
            } else {
                i = mid + 1;
            }
        }
        int left = j;
        return right - left + 1;
    }

    /**
     * 0~n-1中缺失的数字
     * 其实是找出第一个下标和值不相等的下标
     */
    public int missingNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 0, j = nums.length - 1;
        if (nums[0] == 1) {
            return 0;
        }
        while (i <= j) {
            int mid = (i + j) / 2;
            if (nums[mid] == mid) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        return i;
    }
}
