package TargetOffer;

import java.util.Arrays;

/**
 *
 * Offer21：调整数组顺序使奇数位于偶数前面
 * 双指针 + 可扩展（类似策略模式）
 *
 * @author Richa
 * @date 2022/3/6 17:45
 */
public class ExchangeNumber {
    public static NumberSelect selectPolicy = new Even();
    public int[] exchange(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int start = 0, end = nums.length - 1;
        while (start < end) {
            while (start < end && !selectPolicy.select(nums[start])) {
                start++;
            }
            while (start < end && selectPolicy.select(nums[end])) {
                end--;
            }
            if (start < end) {
                int temp = nums[end];
                nums[end] = nums[start];
                nums[start] = temp;
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6};
        ExchangeNumber exchangeNumber = new ExchangeNumber();
        exchangeNumber.exchange(nums);
        System.out.println(Arrays.toString(nums));
    }

}

interface NumberSelect {
    boolean select(int n);
}

// 判断偶数
class Even implements NumberSelect {
    @Override
    public boolean select(int n) {
        return (n & 1) == 0;
    }
}
