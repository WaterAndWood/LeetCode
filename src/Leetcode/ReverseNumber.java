package Leetcode;

/**
 *
 * LeetCode7: 整数反转
 * 考虑int的边界值-2^31， 2^31-1
 * 
 * @author Richa
 * @date 2022/4/29 22:00
 */
public class ReverseNumber {
    public int reverseNum(int x) {
        int ans = 0;
        while (x != 0) {
            int pop = x % 10;
            /**
             * 判断是否越界
             */
            if ((ans > Integer.MAX_VALUE / 10) || (ans == Integer.MAX_VALUE / 10 && pop > Integer.MAX_VALUE % 10)) {
                return 0;
            }
            if ((ans < Integer.MIN_VALUE / 10) || (ans == Integer.MIN_VALUE / 10 && pop > -(Integer.MIN_VALUE % 10))) {
                return 0;
            }
            ans = ans * 10 + pop;
            x /= 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MIN_VALUE %10);
    }
}
