package SortAlgorithm;

import java.util.Arrays;

/**
 *
 * 归并排序：从小到大
 *
 * @author Richa
 * @date 2020/6/1 16:45
 */
public class MergeSort {
    public static void mergeSort(int[] array, int[] temp, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, temp, left, mid);
            mergeSort(array, temp, mid + 1, right);
            merge(array, temp, left, mid + 1, right);
        }
    }

    public static void merge(int[] array, int[] temp, int leftPos, int rightPos, int rightEnd) {
        int index = leftPos;
        int leftEnd = rightPos - 1;
        int elementsNum = rightEnd - leftPos + 1;
        // 交集部分
        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            if (array[leftPos] <= array[rightPos]) {
                temp[index++] = array[leftPos++];
            } else {
                temp[index++] = array[rightPos++];
            }
        }
        // 差集部分
        while(leftPos <= leftEnd) {
            temp[index++] = array[leftPos++];
        }
        while(rightPos <= rightEnd) {
            temp[index++] = array[rightPos++];
        }

        for (int i = 0; i < elementsNum; i++, rightEnd--) {
            array[rightEnd] = temp[rightEnd];
        }
    }

    public static int[] mergeSortWithCopy(int[] array) {
        if (array.length < 2) {
            return array;
        }
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);
        return mergeWithCopy(mergeSortWithCopy(left), mergeSortWithCopy(right));
    }

    public static int[] mergeWithCopy(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        // i: left index; j: right index
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {
            if (j >= right.length) {
                // right已经遍历完成，只加left
                result[index] = left[i++];
            } else if (i >= left.length) {
                // left已经遍历完成，只加right
                result[index] = right[j++];
            } else if (left[i] < right[j]) {
                result[index] = left[i++];
            } else {
                result[index] = right[j++];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] array = {8, 9, 4, 7, 2, 3, 5, 1, 6, 0};
//        mergeSort(array, new int[array.length], 0, array.length - 1);
        int[] arrayWithCopy = mergeSortWithCopy(array);
        System.out.println(Arrays.toString(arrayWithCopy));
    }
}
