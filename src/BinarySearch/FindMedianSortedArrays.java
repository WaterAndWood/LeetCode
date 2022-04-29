package BinarySearch;

/**
 * LeetCode4: 寻找两个正序数组的中位数
 * 时间复杂度O(log(m+n))
 * 这种复杂度一般使用二分法
 */
public class FindMedianSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null && nums2 == null) {
            return 0d;
        } else if (nums1 == null) {
            return singleArrayMedian(nums2);
        } else if (nums2 == null) {
            return singleArrayMedian(nums1);
        } else {
            int len1 = nums1.length, len2 = nums2.length;
            int totalLen = len1 + len2;
            if (totalLen % 2 == 1) {
                int midIndex = totalLen / 2;
                double median = getKthElement(nums1, nums2, midIndex + 1);
                return median;
            } else {
                int midIndex1 = totalLen / 2 - 1, midIndex2 = totalLen / 2;
                double median = (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
                return median;
            }
        }
    }

    /**
     * 中位数：
     * 奇数个数字，有序数组中间的数字是中位数
     * 偶数个数字，有序数组中间的两个数字的平均数是中位数
     * 处理一个数组是null的情况
     */
    private double singleArrayMedian(int[] nums) {
        if (nums.length % 2 == 1) {
            return (double)nums[nums.length/2];
        } else {
            return (double)(nums[nums.length/2] + nums[nums.length/2 - 1]) / 2;
        }
    }

    /**
     * 找到第k小的元素
     * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
     * 这样 pivot 本身最大也只能是第 k-1 小的元素
     */
    private int getKthElement(int[] nums1, int[] nums2, int k) {
        int len1 = nums1.length, len2 = nums2.length;
        int index1 = 0, index2 = 0;
        
        while (true) {
            /**
             * 如果一个数组为空，说明该数组元祖都被排除，返回另一个数组第k小的元素
             */
            if (index1 == len1) {
                return nums2[index2 + k - 1];
            }
            if (index2 == len2) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, len1) - 1;
            int newIndex2 = Math.min(index2 + half, len2) - 1;
            int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
            /**
             * 排除元素后，更新k
             */
            if (pivot1 <= pivot2) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }
}
