package TargetOffer;

/**
 *
 * Offer51: 数组中的逆序对
 *
 * @author Richa
 * @date 2022/4/9 21:32
 */
public class InversePairs {
    /**
     * 分治思想：借助归并排序统计逆序对
     * 分的时候什么都不做，合的时候统计逆序对个数
     * 将原始数组拷贝到辅助数组，再使用双指针，每次较小元素归并回去
     */
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int len = nums.length;
        int[] copy = new int[len];
        for (int i = 0; i < len; i++) {
            copy[i] = nums[i];
        }
        int[] temp = new int[len];
        return reversePairs(copy, 0, len - 1, temp);
    }

    private int reversePairs(int[] nums, int left, int right, int[] temp) {
        if (left == right) {
            return 0;
        }
        int mid = left + (right - left) / 2;
        // 逆序对来自左边区间，右边区间和跨区间的逆序对
        int leftPairs = reversePairs(nums, left, mid, temp);
        int rightPairs = reversePairs(nums, mid + 1, right, temp);
        // 代码到此[left, mid]和[mid + 1, right]已经排序并计算好逆序对。当前if条件满足时，说明[mid + 1, right]所有数字
        // 都比[left, mid]的大，继续计算横跨逆序对没有意义
        if (nums[mid] <= nums[mid + 1]) {
            return leftPairs + rightPairs;
        }
        int crossPairs = mergeSort(nums, left, mid, right, temp);
        return leftPairs + rightPairs + crossPairs;
    }

    /**
     * 归并的时候采用双指针统计逆序对
     * 找到符合逆序对条件的双指针，此时逆序对个数是mid - left + 1
     */
    private int mergeSort(int[] nums, int left, int mid, int right, int[] temp) {
        for (int i = left; i <= right; i++) {
            temp[i] = nums[i];
        }
        int i = left;
        int j = mid + 1;
        int count = 0;
        for (int k = left; k <= right; k++) {
            if (i == mid + 1) {
                nums[k] = temp[j];
                j++;
            } else if (j == right + 1) {
                nums[k] = temp[i];
                i++;
            } else if (temp[i] <= temp[j]) {
                nums[k] = temp[i];
                i++;
            } else {
                nums[k] = temp[j];
                j++;
                // j指向的元素归并时，计算逆序对的个数
                count += (mid - i + 1);
            }
        }
        return count;
    }
}
