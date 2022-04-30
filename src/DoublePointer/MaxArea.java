package DoublePointer;

/**
 *
 * LeetCode11: 盛水最多的容器
 * 双指针，找最大面积。主要是短板决定，将短板向中间移动
 * 
 * @author Richa
 * @date 2022/4/30 11:06
 */
public class MaxArea {
    public int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        int left = 0, right = height.length - 1, res = 0;
        while (left < right) {
            res = Math.max(res, Math.min(height[left], height[right]) * (right - left));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }
}
