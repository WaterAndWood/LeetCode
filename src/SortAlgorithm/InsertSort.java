package SortAlgorithm;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * 插入排序：从小到大
 * 相邻位移动，有序队列后的第一个元素插入；保持前i-1是排序的
 *
 * @author Richa on 2020/5/31 21:31
 * @param
 * @return
 * @throws
 */
public class InsertSort {
    public static void insertSort(int [] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i;
            // 找比array[i]该插入的位置
            while(j > 0 && array[j - 1] > temp) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = temp;
        }
    }

    public static void main(String[] args) {
        int[] array = {8, 9, 4, 7, 2, 3, 5, 1, 6, 0};
        insertSort(array);
        List<Integer> list = Arrays.stream(array).boxed().collect(Collectors.toList());
        System.out.println(list);
    }
}
