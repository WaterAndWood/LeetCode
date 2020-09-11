package DepthOrBreadthSearch;

import java.util.*;

/**
 *
 * LeetCode 386: 字典序排数
 * N叉树的前序遍历dfs或者广度搜索优先遍历10叉树
 * LeetCode 440: 字典序的第K小数字
 * 计算两个前缀之间的个数累加
 *
 * @author Richa
 * @date 2020/8/17 21:35
 */
public class DictionarySortNumber {
    List<Integer> res = new ArrayList<>();
    public List<Integer> lexicalOrderDfs(int n) {
        dfs(n, 0, 0);
        return res;
    }
    public void dfs(int maxValue, int num, int start) {
        if (num > maxValue) {
            return;
        }
        if (num > 0) {
            res.add(num);
        }
        for (int i = start > 0 ? 0 : 1; i <= 9; i++) {
            dfs(maxValue, 10 * num + i, start + 1);
        }
    }

    public List<Integer> lexicalOrderBfs(int n) {
        Deque<Integer> stack = new LinkedList<>();
        if (n < 10) {
            for (int i = n; i > 0; i--) {
                stack.push(i);
            }
        } else {
            for (int i = 9; i > 0; i--) {
                stack.push(i);
            }
        }
        int t, m;
        while (!stack.isEmpty()) {
            t = stack.peek();
            res.add(t);
            stack.pop();
            if (10 * t > n) {
                continue;
            } else {
                m = n - 10 * t;
                // 子树的分支个数
                if (m > 9) {
                    m = 9;
                }
            }
            /**
             * 第一层从1开始，前面已经计算
             * 之后都是从0开始
             */
            for (int i = m; i >= 0; i--) {
                stack.push(10 * t + i);
            }
        }
        return res;
    }

    public int findKthNumber(int n, int k) {
        int cur = 1;
        // 扣除第一个0节点
        k = k -1;
        while (k > 0) {
            int num = getCount(n, cur, cur+1);
            if (num <= k) {
                /**
                 * 第k个数不在cur前缀为节点的子树上，在字典序中找下一个前缀
                 * 从左向右
                 */
                k -= num;
                cur += 1;
            } else {
                /**
                 * 在cur前缀的子树中，找下一层
                 * 从上到下
                 */
                cur *= 10;
                k -= 1;
            }
        }
        return cur;
    }

    /**
     * 计算前缀之间小于n的数字的个数，first和last是两个前缀的起点
     *
     */
    public int getCount(int n, long first, long last) {
        int num = 0;
        while (first <= n) {
            num += Math.min(last, n + 1) - first;
            first *= 10;
            last *= 10;
        }
        return num;
    }
    

    public static void main(String[] args) {
        DictionarySortNumber dsn = new DictionarySortNumber();
        List<Integer> ans = dsn.lexicalOrderDfs(13);
        System.out.println(ans.toString());
    }
}
