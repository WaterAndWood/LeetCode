package MonotonicStack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 84: 柱状图最大面积
 * 单调栈：内部元素单调递增或者递减
 *
 * 位置i 的面积是以i 为中心，向左找第一个小于 heights[i] 的位置 left_i；
 * 向右找第一个小于于 heights[i] 的位置 right_i，即最大面积为 heights[i] * (right_i - left_i -1)
 * 问题转变为如何找到left_i 和 right_i
 *
 * @author Richa
 * @date 2020/8/28 14:50
 */
public class RectangleArea {

    public int largestArea1(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int area = 0, n = height.length;
        for (int i = 0; i < n; i++) {
            int width = 1, h = height[i], j = i;
            /**
             * 以当前柱子高度为矩形高度，向左右两侧找到边界
             *
             */
            // 左边界
            while (--j >= 0 && height[j] >= h) {
                width++;
            }
            j = i;
            // 右边界
            while (++j < n && height[j] >= h) {
                width++;
            }
            area = Math.max(area, width * h);
        }
        return area;
    }

    public int largestArea2(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        // 方便计算，柱体数组的头和尾加了两个高度为 0 的柱体
        int[] temp = new int[heights.length + 2];
        System.arraycopy(heights, 0, temp, 1, heights.length);

        Deque<Integer> stack = new ArrayDeque<>();
        int area = 0;
        // 加入 0 是防止stack.peek空指针
        for (int i = 0; i < temp.length; i++) {
            /**
             * 对于栈中柱体，栈中下一个柱体就是左边第一个小于自身的柱体，因为栈是单调递增的；而且小于栈顶的柱体会计算面积，不会入栈
             * while循环是在不断扩展找到左边界并求面积
             * 若当前柱体 i 的高度小于栈顶柱体的高度，说明 i 是栈顶柱体右边第一个小于栈顶柱体高度的柱体
             * 左右边界确定
             */
            while (!stack.isEmpty() && temp[i] < temp[stack.peek()]) {
                int h = temp[stack.pop()];
                area = Math.max(area, h * (i - stack.peek() - 1));
            }
            // 大于栈顶的柱体入栈，构成单调增
            stack.push(i);
        }
        return area;
    }
}
