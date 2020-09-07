package GreedyAlgorithm;

import java.util.Arrays;

/**
 *
 * LeetCode :135 分发糖果
 * 设学生 A 和学生 B左右相邻，A 在 B 左边
 * 左规则：当ratings[B] > ratings[A]，B分的糖果多于A
 * 右规则：当ratings[A] > ratings[B]，A分的糖果多余B
 * 本质是既满足左规则，有满足右规则。
 * 
 * @author Richa
 * @date 2020/9/3 23:05
 */
public class Candy {
    public int candy1(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }
        int[] left = new int[ratings.length];
        int[] right = new int[ratings.length];

        Arrays.fill(left, 1);
        Arrays.fill(right, 1);
        // 左遍历
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }
        // 左右两端是1
        int count = left[ratings.length - 1];
        // 右遍历
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
            // 满足左右规则，取最大值
            count += Math.max(left[i], right[i]);
        }
        return count;
    }

    public int candy2(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }
        int len = ratings.length, sum = 0;
        int[] num = new int[len];
        Arrays.fill(num, 1);
        boolean flag = true;

        /**
         * num[i] <= num[i + 1]是关键，如果num[i]大于num[i + 1]，就已满足条件，不需要修改
         * num[i]
         */
        while (flag) {
            flag = false;
            for (int i = 0; i < len; i++) {
                // 判断右规则
                if (i != len - 1 && ratings[i] > ratings[i + 1] && num[i] <= num[i + 1]) {
                    num[i] = num[i + 1] + 1;
                    flag  = true;
                }
                // 判断左规则
                if (i > 0 && ratings[i]  > ratings[i - 1] && num[i] <= num[i - 1]) {
                    num[i] = num[i - 1] + 1;
                    flag = true;
                }
            }
        }
        for (int n : num) {
            sum += n;
        }
        return sum;
    }
}
