package TargetOffer;

/**
 *
 * Offer56: 数组中数字出现的次数
 * 利用 p^p=0的性质，根据某一位不为零区分为两个数组
 * 
 * @author Richa
 * @date 2022/4/11 21:23
 */
public class SingleNumbers {
    /**
     * 遍历nums执行异或
     * 循环左移计算m，找出第一个1的二进制位
     * 根据二进制位拆分为两个数组，每个数组中有一个一次的数字
     * 遍历两个数组执行异或
     */
    public int[] singleNumbers(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int x = 0, y = 0, n = 0, m = 1;
        for (int num : nums) {
            n ^= num;
        }
        // 找出第一个1的位置
        while ((n & m) == 0) {
            m <<= 1;
        }
        // 分两组异或
        for (int num : nums) {
            if ((num & m) != 0) {
                x ^= num;
            } else {
                y ^= num;
            }
        }
        return new int[] {x, y};
    }

    /**
     * 一个数字出现3次，所有出现3次的二进制位的每一位相加，可以被3整除
     * 对3求余，结果为只出现一次的数字
     */
    public int singleNumbersInThree(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        // 每个位置都是所有数字该位置二进制的和
        int[] bitSum = new int[32];
        int res = 0;
        for (int num : nums) {
            int bitMask = 1;
            // bitSum的i = 31是最低位
            for (int i = 31; i >= 0; i--) {
                if ((num & bitMask) != 0) {
                    bitSum[i]++;
                }
                bitMask <<= 1;
            }
        }
        for (int i = 0; i < 32; i++) {
            res = res << 1;
            res += bitSum[i] % 3;
        }
        return res;
    }
}
