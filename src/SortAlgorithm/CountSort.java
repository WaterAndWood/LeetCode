package SortAlgorithm;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * 计数排序：从小到大
 *
 * @author Richa
 * @date 2020/5/31 22:44
 */
public class CountSort {
    public static int[] countSort(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        int min = array[0], max = array[0];
        // 确定桶的范围
        for(int i : array) {
            if (i > max) {
                max = i;
            }
            if (i < min) {
                min = i;
            }
        }

        // buckets[i]：数值i在array中的个数
        int[] buckets = new int[max - min + 1];
        for(int j = 0; j < array.length; j++) {
            buckets[array[j] - min]++;
        }

        int[] ans = new int[array.length];
        int index = 0;
        for (int i = 0; i < buckets.length; i++) {
            int bucket = buckets[i];
            // buckets中的数值回填到array
            for (; bucket > 0; bucket--) {
                ans[index++] = i + min;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] array = {8, 9, 4, 7, 2, 3, 5, 1, 6, 0};
        int[] result = countSort(array);
        System.out.println(Arrays.toString(result));
    }
}
