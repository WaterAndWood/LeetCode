package Backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * LeetCode 491: 递增子序列
 * 子序列中元素的相对顺序与数组中相对顺序一致
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
                // 同一个递归层，temp[curPos]的数字不断被覆盖，相当于回溯
                temp[curPos] = nums[i];
                backTrack(nums, temp, curPos + 1, i, nums[i]);
            }
        }
    }

    public List<List<Integer>> findSubIncreaseSeq(int[] nums) {
        // idx 初始化为 -1，开始 dfs 搜索
        dfs(nums, -1, new ArrayList<>());
        return res;
    }

    private void dfs(int[] nums, int idx, List<Integer> curList) {
        // 只要当前的递增序列长度大于 1，就加入到结果 res 中，然后继续搜索递增序列的下一个值。此处需要new一个List，因为java值传递
        if (curList.size() > 1) {
            res.add(new ArrayList<>(curList));
        }
        // 借助 set 对 [idx + 1, nums.length - 1] 范围内的数去重
        Set<Integer> set = new HashSet<>();
        // 在 [idx + 1, nums.length - 1] 范围内遍历搜索递增序列的下一个值
        for (int i = idx + 1; i < nums.length; i++) {
            // 1. 如果 set 中已经有与 nums[i] 相同的值了，说明加上 nums[i] 后的所有可能的递增序列之前已经被搜过一遍了，因此停止继续搜索
            if (set.contains(nums[i])) {
                continue;
            }
            set.add(nums[i]);
            // 2. 如果 nums[i] >= nums[idx] 的话，说明出现了新的递增序列
            // 因此继续 dfs 搜索，回溯体现在curList先add后remove
            if (idx == -1 || nums[i] >= nums[idx]) {
                curList.add(nums[i]);
                dfs(nums, i, curList);
                curList.remove(curList.size() - 1);
            }
        }
    }
}
