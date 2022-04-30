package Leetcode;

/**
 *
 * LeetCode8: 字符串转为整数
 *
 * @author Richa
 * @date 2022/4/30 9:10
 */
public class Atoi {
    public int myAtoi(String str) {
        if (str == null) {
            return 0;
        }
        char[] c = str.trim().toCharArray();
        if (c.length == 0) {
            return 0;
        }
        int i = 1, sign = 1;
        if (c[0] == '-') {
            sign = -1;
        } else if (c[0] != '+') {
            i = 0;
        }
        int res = 0, positiveBoundary = Integer.MAX_VALUE / 10, negativeBoundary = Integer.MIN_VALUE / 10;
        for (int j = i; j < c.length; j++) {
            if (c[j] > '9' || c[j] < '0') {
                break;
            }
            int cur = c[j] - '0';
            if (res > positiveBoundary || (res == positiveBoundary && cur > Integer.MAX_VALUE % 10)) {
                return Integer.MAX_VALUE;
            }
            if (res < negativeBoundary || (res == negativeBoundary && cur > -(Integer.MIN_VALUE % 10))) {
                return Integer.MIN_VALUE;
            }
            res = res * 10 + cur * sign;
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "-91283472332";
        Atoi atoi = new Atoi();
        System.out.println(atoi.myAtoi(s));
    }
}
