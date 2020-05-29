package SortAlgorithm;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * 冒泡排序：从小到大
 *
 * @author Richa
 * @date 2020/5/29 0:00
 */
public class BubbleSort {
     static public void bubbleSortForward(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        // 提前终止排序
        boolean flag = false;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] >= array[j + 1]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                    flag = true;
                }
                System.out.println(Arrays.toString(array));
            }
            // 已经排序完毕
            if (!flag) {
                break;
            }
        }
    }

    static public void bubbleSortBackward(int[] array) {
         if (array == null || array.length <= 0) {
             return;
         }
         for (int i = array.length - 1; i >= 0; i--) {
             for (int j = 0; j < i; j++) {
                 if (array[j] >= array[j + 1]) {
                     int temp = array[j + 1];
                     array[j + 1] = array[j];
                     array[j] = temp;
                 }
             }
         }
    }

    public static void main(String[] args) {
        int[] array = {8, 9, 4, 7, 2, 3, 5, 1, 6, 0};
//        bubbleSortForward(array);
        bubbleSortBackward(array);
        List<Integer> list = Arrays.stream(array).boxed().collect(Collectors.toList());
        System.out.println(list);
    }
}
