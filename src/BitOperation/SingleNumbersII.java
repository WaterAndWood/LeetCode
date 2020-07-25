package BitOperation;

/**
 *
 * 在一个数组中一个数字出现1次，其余数字出现3次，找出出现一次的数字
 * 不能使用异或
 *
 * @author Richa
 * @date 2020/7/25 22:55
 */
public class SingleNumbersII {
    public int singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        /**
         * 每一位记录数字对应二进制的和，bitSum[0]符号位
         * bitSum对应的每一位对3取余就是出现一次数字的二进制位
         * 出现1次数字的二进制位，如果是1，则bitSum对应为是1；如果是0，对应为是0
         */
        int[] bitSum = new int[32];
        int res = 0;
        for (int num : nums) {
            int bitMask = 1;
            for (int j = 31; j >= 0; j--) {
                if ((num & bitMask) != 0) {
                    bitSum[j]++;
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

    public static void main(String[] args) {
        int[] nums = new int[] {-3, -3, 4, -3};
        SingleNumbersII sn = new SingleNumbersII();
        System.out.println(sn.singleNumber(nums));
    }
}
