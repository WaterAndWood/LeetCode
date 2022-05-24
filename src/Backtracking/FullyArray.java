package Backtracking;

import java.util.*;

/**
 *
 * LeetCode 46：全排列
 * 回溯+深度优先搜索
 *
 * @author Richa
 * @date 2020/8/1 21:03
 */
public class FullyArray {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        int len = nums.length;
        // 剪枝
        boolean[] used = new boolean[len];

        List<Integer> path = new ArrayList<>();
        dfs(nums, len, 0, path, used, res);
        return res;
    }

    private void dfs(int[] nums, int len, int depth, List<Integer> path, boolean[] used,
                     List<List<Integer>> res) {
        // 回溯终止条件
        if (depth == len) {
            // 值传递，需要拷贝
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len; i++) {
            if (!used[i]) {
                used[i] = true;
                path.add(nums[i]);
                dfs(nums, len, depth + 1, path, used, res);
                used[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }

    // 输出不重复的全排列
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        int len = nums.length;
        // 排序是剪枝的前提，剪枝是为了去重
        Arrays.sort(nums);
        boolean[] used = new boolean[len];
        Deque<Integer> path = new LinkedList<>();
        dfsUnique(nums, len, 0, used, path, res);
        return res;
    }

    private void dfsUnique(int[] nums, int len, int depth, boolean[] used, Deque<Integer> path, List<List<Integer>> res) {
        if (depth == len) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len; i++) {
            if (used[i]) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i-1] && !used[i - 1]) {
                continue;
            }
            used[i] = true;
            path.add(nums[i]);
            dfsUnique(nums, len, depth + 1, used, path, res);
            used[i] = false;
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        FullyArray fa = new FullyArray();
        List<List<Integer>> ans = fa.permute(nums);
        System.out.println(ans);
    }
}
