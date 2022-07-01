package Backtracking;

import java.util.*;

/**
 *
 * LeetCode39: 组合总和
 * 什么时候使用used数组，什么时候使用begin变量
 * 排列问题：讲究顺序，顺序不同视为不同列表，需要记录哪些数字已经使用过，使用used数组
 * 组合问题：不讲究顺序，需要按照某种顺序搜索，使用begin变量
 *
 * @author Richa
 * @date 2022/7/1 10:21
 */
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }
        // 排序是剪枝的前提
        Arrays.sort(candidates);
        Deque<Integer> path = new ArrayDeque<>();
        dfs(candidates, 0, len, target, path, res);
        return res;
    }

    private void dfs(int[] candidates, int begin, int len, int target, Deque<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i < len; i++) {
            // 剪枝，前提是候选数组有序
            if (target - candidates[i] < 0) {
                break;
            }
            path.addLast(candidates[i]);
            dfs(candidates, i, len, target - candidates[i], path, res);
            path.removeLast();
        }
    }
}
