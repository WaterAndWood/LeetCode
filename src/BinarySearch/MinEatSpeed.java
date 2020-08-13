package BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 *
 * 小招喵喜欢吃喵粮。这里有 N 堆喵粮，第 i 堆中有 p[i] 粒喵粮。喵主人离开了，将在 H 小时后回来。
 *
 * 小招喵可以决定她吃喵粮的速度 K （单位：粒/小时）。每个小时，她将会选择一堆喵粮，从中吃掉 K 粒。
 * 如果这堆喵粮少于 K 粒，她将吃掉这堆的所有喵粮，然后这一小时内不会再吃更多的喵粮。
 *
 * 小招喵喜欢慢慢吃，但仍然想在喵主人回来前吃掉所有的喵粮。
 *
 * 返回她可以在 H 小时内吃掉所有喵粮的最小速度 K（K 为整数）。
 * 第一行输入为喵粮数组，以空格分隔的N个整数
 *
 * 第二行输入为H小时数
 * 
 * @author Richa
 * @date 2020/8/13 15:05
 */
public class MinEatSpeed {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String l1 = bf.readLine();
        int h = Integer.parseInt(bf.readLine());
        String[] strs = l1.split(" ");
        int len = strs.length;
        int[] nums = new int[len];

        int sum = 0, max = 0;
        for (int i = 0; i < len; i++) {
            nums[i] = Integer.parseInt(strs[i]);
            max = Math.max(max, nums[i]);
            sum += nums[i];
        }
        /**
         * max speed : max
         * min speed : min
         * 在min 和 max 之间使用二分法，因为速度区间是递增的
         */
        int min = sum / h;

        while (min < max) {
            int mid = min + (max - min) / 2;
            if (canEat(mid, h, nums)) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }
        System.out.println(min);
    }

    public static boolean canEat(int v, int h, int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % v == 0) {
                ans += nums[i] / v;
            } else {
                ans += nums[i] / v + 1;
            }
        }
        return ans <= h;
    }
}
