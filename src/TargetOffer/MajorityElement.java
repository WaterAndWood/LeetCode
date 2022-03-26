package TargetOffer;

/**
 * @author wangzhen
 * @creatTime 2022/3/26 3:24 下午
 * @description Offer39: 数组中出现次数超过一半的数字
 */
public class MajorityElement {
    /**
     * 摩尔投票法
     */
    public int majorNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int x = 0, vote = 0;
        for (int num : nums) {
            if (vote == 0) {
                x = num;
            }
            if (x == num) {
                vote++;
            } else {
                vote--;
            }
        }

        // 验证x是否是众数
        int count = 0;
        for (int num : nums) {
            if (num == x) {
                count++;
            }
        }
        return count > nums.length / 2 ? x : 0;
    }

    public int majorNumberSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int mid = nums.length / 2;
        int start = 0, end = nums.length - 1;
        int index = partition(nums, start, end);
        while (index != mid) {
            // 下标大于n / 2, 中位数在它左边；相反则在右边
            if (index > mid) {
                end = index - 1;
                index = partition(nums, start, end);
            } else {
                start = index + 1;
                index = partition(nums, start, end);
            }
        }
        int x = nums[mid];
        int count = 0;
        for (int num : nums) {
            if (num == x) {
                count++;
            }
        }
        return count > nums.length / 2 ? x : 0;
    }

    public int partition(int[] arr, int left, int right) {
        int x = arr[right];
        int p = left - 1;
        for (int i = left; i < right; i++) {
            if (arr[i] < x) {
                p++;
                swap(arr, p, i);
            }
        }
        swap(arr, p + 1, right);
        return p + 1;
    }
    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
