package TargetOffer;

/**
 *
 * Offer44: 数字序列中的某一位数
 * 找规律，分段寻找的思想
 *
 * @author Richa
 * @date 2022/3/30 8:30
 */
public class FindNthNumber {
    public int findNthDigit(int n) {
        if (n <= 9) {
            return n;
        }
        int digit = 1;
        /**
         * 每 digit 位数的起始数字是start
         * digit是位数
         */
        long start = 1, count = 9;
        while (n > count) {
            n -= count;
            digit += 1;
            start *= 10;
            count = start * digit * 9;
        }
        // 找出在哪个数字中， start为第 0 个数字
        long num = start + (n - 1) / digit;
        // 在某个数字中的位数
        return Long.toString(num).charAt((n - 1) % digit) - '0';
    }
    
}
