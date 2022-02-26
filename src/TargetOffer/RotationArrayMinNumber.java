package TargetOffer;

/**
 * @author wangzhen
 * @creatTime 2022/2/26 12:41 下午
 * @description Offer11:旋转数组的最小数字
 */
public class RotationArrayMinNumber {
    /**
     * 旋转后可以看成两个递增的数组，局部是排序的，所以使用二分查找
     */
    public int minArray1(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return 0;
        }
        int len = numbers.length;
        int left = 0;
        int right = len - 1;
        while (left < right) {
            int mid = (left + right) >>> 1;
            /**
             * mid以及mid左边一定不是最小数字
             */
            if (numbers[mid] > numbers[right]) {
                left = mid + 1;
            } else if (numbers[mid] == numbers[right]) {
                /**
                 * 无法确定mid是左边数组还是右边数组，只排除right
                 * 1 0 1 1 1
                 * 1 1 1 0 1
                 */
                right = right - 1;
            } else {
                /**
                 * mid的右边一定不是最小数字，mid可能是
                 */
                right = mid;
            }
        }
        return numbers[left];
    }

    /**
     * 分治法，二分本是分治的特殊情况
     */
    public int minArray2(int[] nums) {
        int len = nums.length;
        return minArray(nums, 0, len - 1);
    }

    private int minArray(int[] nums, int left, int right) {
        /**
         * 递归终止条件，数组有一个数或者两个数
         */
        if (left + 1 >= right) {
            return Math.min(nums[left], nums[right]);
        }
        /**
         * 数组内递增，返回左边小的数
         */
        if (nums[left] < nums[right]) {
            return nums[left];
        }
        int mid = left + (right - left) / 2;
        return Math.min(minArray(nums, left, mid - 1), minArray(nums, mid, right));
    }

}
