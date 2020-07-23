package BitOperation;

import java.util.Arrays;

/**
 *
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。
 * 请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 *
 * @author Richa
 * @date 2020/7/23 10:48
 */
public class SingleNumbers {
    // 分组异或，两个只出现一次的数字分在不同的数组，分别异或
    public int[] singleNumbers(int[] nums) {
        if (nums == null || nums.length <2) return new int[0];
        int resultOR = 0;
        for(int i = 0; i < nums.length; i++) {
            resultOR = resultOR ^ nums[i];
        }
        // 获得最低位1
//        int flag = resultOR & (-resultOR);
        int flag = 1;
        while((resultOR & flag) == 0) {
            flag <<= 1;
        }
        int[] ans = new int[2];
        int num1 = 0;
        int num2 = 0;
        for (int num : nums) {
            if ((num & flag) == 0) {
                num1 ^= num;
            } else {
                num2 ^= num;
            }
        }
        ans[0] = num1;
        ans[1] = num2;
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1,2,10,4,1,4,3,3};
        SingleNumbers sn = new SingleNumbers();
        System.out.println(Arrays.toString(sn.singleNumbers(nums)));
    }
}
