package DoublePointer;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 * LeetCode 42: 接雨水
 * 
 * @author Richa
 * @date 2020/8/30 9:27
 */
public class TrapWater {
    /**
     * 求出左边和右边最高墙，求当前列和其中较矮的墙之间的差值即为水的体积
     * 按列求值
     * O(n^2)超时
     */
    public int trap1(int[] heights) {
        int sum = 0;
        // 左右两端一定不会存水，故不进行求和
        for (int i = 1; i < heights.length - 1; i++) {
            int maxLeft = 0;
            // 找左边的最大值
            for (int j = i - 1; j >= 0; j--) {
                if (heights[j] > maxLeft) {
                    maxLeft = heights[j];
                }
            }
            int maxRight = 0;
            // 找右边的最大值
            for (int j = i + 1; j < heights.length; j++) {
                if (heights[j] > maxRight) {
                    maxRight = heights[j];
                }
            }
            int min = Math.min(maxLeft, maxRight);
            if (heights[i] < min) {
                sum += min - heights[i];
            }
        }
        return sum;
    }

    /**
     * 单调栈，维护栈内元素的单调性
     * 当前列和栈顶元素比较，小于继续加入，大于可以求水的体积：高度差 * 宽度，横向求和
     * 栈内元素是索引，按照height[i] 单调减
     */
    public int trap2(int[] height) {
        if (height == null) {
            return 0;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        int ans = 0;
        for (int i = 0; i < height.length; i++) {
            // 栈顶值小于当前值，会积水
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int curIndex = stack.pop();
                // 值相等的元素pop，其值表示下一行水的高度
                while (!stack.isEmpty() && height[stack.peek()] == height[curIndex]) {
                    stack.pop();
                }

                if (!stack.isEmpty()) {
                    // 这一行水的左边界
                    int top = stack.peek();
                    // 右边界是i，左右边界的最小值是这一行水的高度，与下一行的差为这一行水的宽度
                    int minHeight = Math.min(height[top], height[i]) - height[curIndex];
                    // 这一行水的长度
                    int len = i - top - 1;
                    ans += minHeight * len;
                }
            }
            stack.push(i);
        }
        return ans;
    }

    /**
     * 不需要遍历找出周左右最大最小值，直接使用数组记录
     * 对trap1的改进
     */
    public int trap3(int[] heights) {
        if (heights == null) {
            return 0;
        }
        int sum = 0;
        int[] maxLeft = new int[heights.length];
        int[] maxRight = new int[heights.length];
        // max_left[i] 代表第 i 列左边最高的墙的高度，从1开始，0不存在左边，而且 height.length - 1 不需要计算
        for (int i = 1; i < heights.length - 1; i++) {
            maxLeft[i] = Math.max(heights[i - 1], maxLeft[i - 1]);
        }
        // max_right[i] 代表第 i 列右边最高的墙的高度，从倒数第二位开始
        for (int i = heights.length - 2; i >= 0; i--) {
            maxRight[i] = Math.max(heights[i + 1], maxRight[i + 1]);
        }
        for (int i = 1; i < heights.length - 1; i++) {
            int min = Math.min(maxLeft[i], maxRight[i]);
            if (min > heights[i]) {
                sum += min - heights[i];
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        TrapWater trapWater = new TrapWater();
        int[] heights = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trapWater.trap2(heights));
    }

}
