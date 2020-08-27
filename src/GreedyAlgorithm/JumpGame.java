package GreedyAlgorithm;

/**
 *
 * LeetCode 55: 跳跃游戏 canJump
 *
 * LeetCode 45: 跳跃游戏II jump
 * 
 * @author Richa
 * @date 2020/8/27 12:49
 */
public class JumpGame {
    /**
     * 贪心算法：更新每个索引能达到的最远位置
     *
     */
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int len = nums.length;
        // 当前索引起跳后组员位置
        int range = 0;
        for (int i = 0; i < len; i++) {
            // 索引i大于range，表示该索引无法达到，其后的索引均无法达到
            if (i > range) {
                return false;
            }
            // 比较能到达的最远位置，贪心找出最大值
            range = Math.max(range, i + nums[i]);
        }
        return true;
    }

    public int jump1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 从后往前贪心找，每次找最小的坐标（贪心），即从后往前跳的幅度最大。直到找到开始位置，从左向右遍历数组
        int position = nums.length - 1;
        int steps = 0;
        while (position > 0) {
            for (int i = 0; i < position; i++) {
                if (i + nums[i] >= position) {
                    // 表示从i可以跳到position。更新最远位置（从后向前看）为当前坐标，之前的点跳到此坐标即可跳到后面，直到最后
                    position = i;
                    steps++;
                    break;
                }
            }
        }
        return steps;
    }

    public int jump2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        /**
         * end 是向右跳最远的边界，如果当前坐标到达边界，说明需要再跳一次，跳向的位置就是maxPosition
         * maxPosition记录前一跳的范围内的点，可以向后跳的最远的位置
         */
        int end = 0;
        int steps = 0;
        int maxPosition = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            // 未到达边界，还在前一跳的范围内，记录此范围内可以到达的最远位置
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                // 向后跳
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }
    
}
