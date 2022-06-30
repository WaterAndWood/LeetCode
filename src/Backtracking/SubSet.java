package Backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * LeetCode78: 子集
 *
 * @author Richa
 * @date 2022/6/30 19:45
 */
public class SubSet {
    public List<List<Integer>> subset(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(0, nums, res, new ArrayList<>());
        return res;
    }

    public void backtrack(int i, int[] nums, List<List<Integer>> res, List<Integer> temp) {
        res.add(new ArrayList<>(temp));
        for (int j = i; j < nums.length; j++) {
            temp.add(nums[j]);
            backtrack(j + 1, nums, res, temp);
            temp.remove(temp.size() - 1);
        }
    }
}
