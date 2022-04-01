package SortAlgorithm;

import java.util.Arrays;

/**
 *
 * 快速排序：从小到大
 * 分治法
 * 把比基准大的放到基准右边，把基准小的放到基准左边
 *
 * 先从队尾开始向前扫描且当low < high时,如果a[high] > tmp,
 * 则high–-,但如果a[high] < tmp,则将high的值赋值给low,即arr[low] = a[high],
 * 同时要转换数组扫描的方式,即需要从队首开始向队尾进行扫描了
 *
 * 当从队首开始向队尾进行扫描时,如果a[low] < tmp,则low++,
 * 但如果a[low] > tmp了,则就需要将low位置的值赋值给high位置,即arr[high] = arr[low],
 * 同时将数组扫描方式换为由队尾向队首进行扫描
 *
 * 直到low == high，low或者high的位置就是这一轮正确索引位置
 *
 * @author Richa
 * @date 2020/6/2 12:13
 */
public class QuickSort {
    public void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivot = partition(array, low, high);
            quickSort(array, low, pivot - 1);
            quickSort(array, pivot + 1, high);
        }
    }

    public static int partition(int[] array, int low, int high) {
        // 初始基准为队首元素
        int pivot = array[low];
        while (low < high) {
            // 从队尾向前移动high指针
            while(low < high && array[high] >= pivot) {
                high--;
            }
            // 队尾元素小于pivot，元素赋值给低端，array[high]已经小于pivot
            array[low] = array[high];
            // 从队首向后移动low指针
            while(low < high && array[low] <= pivot) {
                low++;
            }
            // 队首元素大于pivot，赋值给高端，array[low]已经大于pivot，low现在的位置比开始的low大，比high小
            array[high] = array[low];
        }
        // low和high都是pivot基准的位置
        array[low] = pivot;
        return low;
    }

    public void quickSortWithSwap(int[] arr, int left, int right) {
        if (left < right) {
            int pivot = partitionWithSwap(arr, left, right);
            quickSortWithSwap(arr, left, pivot - 1);
            quickSortWithSwap(arr, pivot + 1, right);
        }
    }
    
    public static int partitionWithSwap(int[] arr, int left, int right) {
        // 最右边选择主元
        int x = arr[right];
        // p指向小于主元的元素中最右的元素
        int p = left - 1;
        for (int i = left; i < right; i++) {
            if (arr[i] <= x) {
                p++;
                swap(arr, p, i);
            }
        }
        swap(arr, p + 1, right);
        return p + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void main(String[] args) {
        int[] array = {8, 9, 4, 7, 2, 3, 0, 1, 6, 5};
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(array, 0, array.length - 1);
//        quickSort.quickSortWithSwap(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }
}
