package Backtracking;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 *
 * LeetCode77：组合
 * 回溯加剪枝，画出二叉树
 * 
 * @author Richa
 * @date 2022/6/30 17:05
 */
public class Combine {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (k <= 0 || n < k) {
            return res;
        }
        Deque<Integer> path = new ArrayDeque<>();
        dfs(n, k, 1, path, res);
        return res;
    }

    private void dfs(int n, int k, int begin, Deque<Integer> path, List<List<Integer>> res) {
        if (path.size() == k) {
            List<Integer> temp = new ArrayList<>(path);
            res.add(temp);
            return;
        }
        for (int i = begin; i <= n; i++) {
            // path中添加一个数
            path.addLast(i);
            // 下一轮搜索，搜索点起点加1
            dfs(n, k, i + 1, path, res);
            // 回溯需要删除递归前的操作
            path.removeLast();
        }
    }

}
