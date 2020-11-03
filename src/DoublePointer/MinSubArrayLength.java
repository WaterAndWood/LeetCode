package DoublePointer;

/**
 *
 * 找到最短子数组长度
 * 给定一个正整数数组和一个正整数n，从数组中寻求和值大于等于n 的最短子数组长度
 * 如果存在，返回最短子数组长度，如果不存在，返回0
 * 双指针/滑动窗口
 *
 * @author Richa
 * @date 2020/10/27 16:00
 */
public class MinSubArrayLength {
    public int findShortestArrayLength(int[] array, int n) {
        if (array == null || array.length == 0) {
            return 0;
        }

        int ans = 0;
        int len = array.length;
        // r相当于右指针，i相当于左指针
        int r = -1;
        int temp = 0;
        for (int i = 0; i < len; i++) {
            // 向右收缩左边界，临时和减去前一个数
            if (i != 0) {
                temp -= array[i - 1];
            }
            while ((r + 1 < len) && temp < n) {
                temp += array[r + 1];
                r++;
            }
            ans = Math.min(ans, r - i + 1);
        }
        return ans;
    }
}
