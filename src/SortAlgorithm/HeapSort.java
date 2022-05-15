package SortAlgorithm;

import java.util.Arrays;

/**
 *
 * 堆排序：从小到大
 * 堆可以是符合以下规则的数组：
 * 大顶堆：arr[i] >= arr[2i+1] && arr[i] >= arr[2i+2]
 * 小顶堆：arr[i] <= arr[2i+1] && arr[i] <= arr[2i+2]
 * 一般升序采用大顶堆，降序采用小顶堆
 *
 * @author Richa
 * @date 2020/6/2 16:52
 */
public class HeapSort {
    public void heapSort(int[] array) {
        // 构建大顶堆
        for (int i = array.length / 2 -1; i >= 0; i--) {
            adjustHeap(array, i, array.length);
        }
        System.out.println(Arrays.toString(array));
        for (int j = array.length - 1; j >= 0; j--) {
            int temp = array[0];
            array[0] = array[j];
            array[j] = temp;
            adjustHeap(array, 0, j);
        }
    }

    public void adjustHeap(int[] array, int i, int length) {
        int temp = array[i];
        // 从i节点左子节点开始，也就是2*i+1
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            // 如果左节点小于右节点，k指向右节点
            if (k + 1 < length && array[k] < array[k + 1]) {
                k++;
            }
            // 如果子节点大于父节点，子节点赋值给父节点
            if (array[k] > temp) {
                array[i] = array[k];
                i = k;
            } else {
                break;
            }
        }
        array[i] = temp;
    }

    public static void main(String[] args) {
        int[] array = {8, 9, 4, 7, 2, 3, 5, 1, 6, 0};
        HeapSort heapSort = new HeapSort();
        heapSort.heapSort(array);
        System.out.println(Arrays.toString(array));
    }
}
