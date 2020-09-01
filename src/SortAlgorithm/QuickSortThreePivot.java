package SortAlgorithm;

import java.util.Arrays;

/**
 *
 * 快速排序-三数取中法
 * 三数取中法，也就是取左端、中间、右端三个数，然后进行排序，将中间数作为枢纽值。
 * 
 * @author Richa
 * @date 2020/9/1 14:14
 */
public class QuickSortThreePivot {
    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            // 获取枢纽值，将枢纽至移至倒数第二位，最后一位是right
            dealPivot(arr, left, right);
            int pivot = right - 1;
            // 左指针指向三个数中的left
            int i = left;
            // 右指针初始指向pivot
            int j = right - 1;

            while (true) {
                // 找到左边大于arr[pivot]的值
                while (arr[++i] < arr[pivot]) {}
                // 找到右边小于arr[pivot]的值
                while (j > left && arr[--j] > arr[pivot]) {}
                if (i < j) {
                    swap(arr, i, j);
                } else {
                    break;
                }
            }
            // 交换枢纽值到索引i，i的左边值小于arr[pivot], 右边值大于arr[pivot]
            if (i < right) {
                swap(arr, i, right - 1);
            }
            // 以枢纽值为界继续快排
            quickSort(arr, left, i - 1);
            quickSort(arr, i + 1, right);
        }
    }


    /**
     * 将三个数的中间值交换到中间作为枢纽值
     * left < mid < right
     *
     */
    public static void dealPivot(int[] arr, int left, int right) {
        int mid = left + (right - left) / 2;
        // 交换后，left是最小的
        if (arr[left] > arr[mid]) {
            swap(arr, left, mid);
        }
        if (arr[left] > arr[right]) {
            swap(arr, left, right);
        }
        // 交换后，right是最大的
        if (arr[right] < arr[mid]) {
            swap(arr, right, mid);
        }
        // 枢纽值放在倒数第二位
        swap(arr, right - 1, mid);
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("排序结果：" + Arrays.toString(arr));
    }
}
