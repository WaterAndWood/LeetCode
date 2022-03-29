package TargetOffer;

/**
 *
 * Offer43: 1-n整数中1出现的次数
 *
 * @author Richa
 * @date 2022/3/29 23:37
 */
public class CountDigitOne {
    public int countDigitOne(int n) {
        return f(n);
    }

    private int f(int n) {
        if (n <= 0) {
            return 0;
        }
        // 分成高位和低位
        String s = String.valueOf(n);
        int high = s.charAt(0) - '0';
        int pow = (int)Math.pow(10, s.length() - 1);
        int last = n - high * pow;
        if (high == 1) {
            return f(pow - 1) + last + 1 + f(last);
        } else {
            return high * f(pow - 1) + pow + f(last);
        }
    }

    public int countDigitOneLoop(int n) {
        if (n <= 0) {
            return 0;
        }
        int digit = 1, res = 0;
        int high = n / 10, cur = n % 10, low = 0;
        while (high != 0 || cur != 0) {
            if (cur == 0) {
                res += high * digit;
            } else if (cur == 1) {
                res += high * digit + low + 1;
            } else {
                res += (high + 1) * digit;
            }
            low += cur * digit;
            cur = high % 10;
            high /= 10;
            digit *= 10;
        }
        return res;
    }
}
