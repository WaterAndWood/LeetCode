package TargetOffer;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 *
 * Offer10: 斐波那契数列
 * 对1000000007取模防止溢出
 *
 * @author Richa
 * @date 2022/2/22 0:03
 */
public class Fibonacci {
    /**
     * 递归，时间复杂度O(2^n)
     */
    public int fibonacci1(int n) {
        if (n <= 1) {
            return 1;
        }
        return fibonacci1(n - 1) + fibonacci1(n - 2);
    }

    /**
     * 动态规划，时间复杂度O(n)
     */
    public int fibonacci2(int n) {
        int mod = (int)1e9 + 7;
        if (n <= 1) {
            return n;
        }
        int a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            int c = a + b;
            c %= mod;
            a = b;
            b = c;
        }
        return b;
    }

    /**
     * 记忆化递归，使用数组保存中间状态
     */
    public int fibonacci3(int n) {
        if (n <= 1) {
            return n;
        }
        return memorize(n);
    }

    private int memorize(int n) {
        int[] cache = new int[n + 1];
        cache[0] = 0;
        cache[1] = 1;
        for (int i = 2; i <=n; i++) {
            cache[i] = (cache[i - 1] + cache[i - 2]) % 1000000007;
        }
        return cache[n];
    }

    /**
     * 矩阵快速幂，时间复杂度O(logn)
     */
    long[][] mul(long[][] a, long[][] b) {
        int r = a.length, c = b[0].length, z = b.length;
        long[][] ans = new long[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                for (int k = 0; k < z; k++) {
                    ans[i][j] += a[i][k] * b[k][j];
                    ans[i][j] %= 1000000007;
                }
            }
        }
        return ans;
    }

    public int fibonacci4(int n) {
        if (n <= 1) {
            return n;
        }
        long[][] mat = new long[][]{
                {1, 1},
                {1, 0}
        };
        long[][] ans = new long[][]{
                {1},
                {0}
        };
        int x = n - 1;
        while (x != 0) {
            if ((x & 1) != 0) {
                ans = mul(mat, ans);
            }
            mat = mul(mat, mat);
            x >>= 1;
        }
        return (int)(ans[0][0] % 1000000007);
    }

    /**
     * 通项公式，时间复杂度O(1)
     */
    public int fibonacci5(int n) {
        double c1 = (1.0 + sqrt(5)) / 2;
        double c2 = (1.0 - sqrt(5)) / 2;
        return (int)((pow(c1, n) - pow(c2, n)) / sqrt(5));
    }
}
