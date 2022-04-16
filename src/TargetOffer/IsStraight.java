package TargetOffer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * Offer 60: 扑克中的顺子
 * 先排序，然后大小王视为0，去填补排序后的空缺
 * @author Richa
 * @date 2022/4/16 16:40
 */
public class IsStraight {
    public boolean isStraight(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        Arrays.sort(nums);
        // 检查重复
        Set<Integer> set = new HashSet<>();
        int numOfZero = 0;
        for (int num : nums) {
            if (num == 0) {
                numOfZero++;
                continue;
            }
            if (set.contains(num)) {
                return false;
            }
            set.add(num);
        }
        int gap = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == 0) {
                continue;
            }
            gap += nums[i + 1] - nums[i] - 1;
        }
        return numOfZero >= gap ? true : false;
    }

}
