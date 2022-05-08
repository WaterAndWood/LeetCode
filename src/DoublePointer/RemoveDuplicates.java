package DoublePointer;

/**
 *
 * LeetCode26: 删除重复元素
 * 把后面不同元素复制到前面
 *
 * @author Richa
 * @date 2022/5/8 16:40
 */
public class RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int fast = 1, slow = 0;
        while (fast < nums.length) {
            if (nums[slow] != nums[fast]) {
                nums[slow + 1] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow + 1;
    }

    public int removeVal(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int ans = 0;
        /**
         * ans是指向前面的指针，指向val值的元素
         */
        for (int num : nums) {
            if (num != val) {
                nums[ans] = num;
                ans++;
            }
        }
        return ans;
    }

    /**
     * 等于val的移到后面
     */
    public int removeValDouble(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int j = nums.length - 1;
        for (int i = 0; i <= j; i++) {
            if (nums[i] == val) {
                // i--，所以即使交换过来的是val，i也是在它前面
                swap(nums, i--, j--);
            }
        }
        return j + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
