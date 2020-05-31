package SortAlgorithm;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * 选择排序：从小到大
 * 保持前i-1是排序的
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
        int temp;
        for (int i = 0; i < array.length - 1; i++) {
            temp = array[i+1];
            int j = i;
            for(; j >= 0 && temp <= array[j] ; j--) {
                array[j+1] = array[j];
            }
            array[j+1] = temp;
        }
    }

    public static void main(String[] args) {
        int[] array = {8, 9, 4, 7, 2, 3, 5, 1, 6, 0};
        insertSort(array);
        List<Integer> list = Arrays.stream(array).boxed().collect(Collectors.toList());
        System.out.println(list);
    }
}
