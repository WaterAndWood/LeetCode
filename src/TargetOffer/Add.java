package TargetOffer;

/**
 *
 * Offer65: 不用加减乘除做加法
 *
 * @author Richa
 * @date 2022/4/16 23:11
 */
public class Add {
    /**
     * 二进制加法，计算机数值使用补码，同时适用正负
     * 非进位和 n = a ^ b
     * 进位：(a & b) << 1
     */
    public int add1(int a, int b) {
        // 进位为0跳出
        while (b != 0) {
            int c = (a & b) << 1;
            a = a ^ b;
            b = c;
        }
        return a;
    }

    /**
     * 递归更容易理解
     */
    public int add(int a, int b) {
        if (b == 0) {
            return a;
        }
        // a + b转为非进位和+进位
        return add(a ^ b, (a & b) << 1);
    }
}
