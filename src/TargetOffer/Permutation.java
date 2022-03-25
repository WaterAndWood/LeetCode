package TargetOffer;

import java.util.*;

/**
 * offer38: 字符串的排列
 * 深度优先搜索，通过字符交换，从第1位开始固定
 * 回溯法撤销交换
 */
public class Permutation {
    List<String> res = new LinkedList<>();
    char[] c;
    public String[] permutation(String s) {
        if (s == null || s.isEmpty()) {
            return new String[0];
        }
        c = s.toCharArray();
        dfs(0);
        return res.toArray(new String[res.size()]);
    }

    private void dfs(int x) {
        // 搜索终止条件
        if (x == c.length - 1) {
            res.add(String.valueOf(c));
            return;
        }
        // set重复数字剪枝
        Set<Character> set = new HashSet<>();
        for (int i = x; i < c.length; i++) {

            if (set.contains(c[i])) {
                continue;
            }
            set.add(c[i]);
            // 交换，将c[i]固定在x位
            swap(i, x);
            // 开启固定第x+1位字符
            dfs(x + 1);
            // 恢复交换
            swap(x, i);
        }
    }

    private void swap(int a, int b) {
        char temp = c[a];
        c[a] = c[b];
        c[b] = temp;
    }

    public static void main(String[] args) {
        String s = "abc";
        Permutation p = new Permutation();
        System.out.println(Arrays.asList(p.permutation(s)));
    }
}
