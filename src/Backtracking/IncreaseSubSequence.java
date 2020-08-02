package Backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * LeetCode 491: 递增子序列
 *
 * @author Richa
 * @date 2020/8/2 18:59
 */
public class IncreaseSubSequence {
    private List<List<Integer>> res;
    private int n;

    public List<List<Integer>> findSubSequence(int[] nums) {
        res = new ArrayList<>();
        n = nums.length;
        if (n == 0) {
            return res;
        }
        backTrack(nums, new int[n], 0, -1, Integer.MIN_VALUE);
        return res;
    }

    /**
     * temp[] : 存放已经选择的数字
     * curPos: temp的索引
     * preIndex: 表示上一个选定元素的索引，这一次数字选择从 preIndex + 1开始
     * pre: 上一个数字，当前数字要大于等于pre
     */
    private void backTrack(int[] nums, int[] temp, int curPos, int preIndex, int pre) {
        /**
         * 要求递增数组长度大于等于2
         * 满足结果条件，已经存放数字的temp数组加入结果
         */
        if (curPos > 1) {
            List<Integer> tempRes = new ArrayList<>();
            for (int i = 0; i < curPos; i++) {
                tempRes.add(temp[i]);
            }
            res.add(tempRes);
        }
        // 避免curPos的数字选择重复
        Set<Integer> set = new HashSet<>();
        for (int i = preIndex + 1; i < n; i++) {
            if (!set.contains(nums[i]) && nums[i] >= pre) {
                set.add(nums[i]);
                // temp[curPos]的数字不断被覆盖，相当于回溯
                temp[curPos] = nums[i];
                backTrack(nums, temp, curPos + 1, i, nums[i]);
            }
        }
    }
}
