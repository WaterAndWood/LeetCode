package TargetOffer;

/**
 *
 * Offer16: 数值的整数次方
 * 考虑指数为0，负数的情况
 *
 * @author Richa
 * @date 2022/2/28 23:21
 */
public class Power {
    public double myPow1(double x, int n) {
        if (x == 0) {
            return 0d;
        }
        if (n == 0 || x == 1) {
            return 1d;
        }

        double res = 1;
        if (n > 0) {
            while (n > 0) {
                res *= x;
                n--;
            }
        } else {
            while (n < 0)  {
                res /= x;
                n++;
            }
        }
        return res;
    }

    /**
     * 快速幂，偶数次幂拆成n/2的x相乘，奇数次幂乘以x
     */
    public double myPow2(double x, int n) {
        if (x == 0) {
            return 0d;
        }
        if (n == 0) {
            return 1d;
        }
        long num = n;
        if (num < 0) {
            x = 1 / x;
            num = -num;
        }
        double res = 1.0;
        while (num > 0) {
            /**
             * 奇数乘x
             */
            if ((num & 1) == 1) {
                res *= x;
            }
            x *= x;
            num >>= 1;
        }
        return res;
    }

    /**
     * 递归实现快速幂
     */
    public double myPow3(double x, int n) {
        if (x == 0) {
            return 0d;
        }
        if (n == 0) {
            return 1d;
        }
        // n = -2147483648n=−2147483648 时执行 n = -nn=−n 会因越界而赋值出错
        long N = n;
        if (N > 0) {
            return myPow3(x, N);
        } else {
            return 1 / myPow3(x, -N);
        }
    }

    public double myPow3(double x, long n) {
        if (n == 0) {
            return 1d;
        }

        if ((n & 1) == 1) {
            double square = myPow3(x, (n - 1) / 2);
            return square * square * x;
        } else {
            double square = myPow3(x, n / 2);
            return square * square;
        }
    }
}
