package TargetOffer;

import java.util.ArrayList;

/**
 *
 * Offer 62: 圆圈中最后剩的数字
 *
 * @author Richa
 * @date 2022/4/16 19:40
 */
public class LastRemaining {
    /**
     * 模拟循环链表，时间复杂度O(n^2)
     */
    public int lastRemaining(int n, int m) {
        if (n < 1 || m < 1) {
            return -1;
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int index = 0;
        while (n > 1) {
            // 循环链表所以取余
            index = (index + m - 1) % n;
            list.remove(index);
            n--;
        }
        return list.get(0);
    }

    /**
     * 动态规划
     * 反推过程（当前index+m)%上一轮剩余数字个数
     */
    public int lastRemain(int n, int m) {
        int ans = 0;
        for (int i = 2; i <= n; i++) {
            ans = (ans + m) % i;
        }
        return ans;
    }
}
