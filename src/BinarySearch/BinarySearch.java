package BinarySearch;

/**
 * @author wangzhen
 * @creatTime 2022/2/26 3:27 下午
 * @description 二分法查找，缺点待查表为有序表，时间复杂度O(logn)
 */
public class BinarySearch {
    /**
     * 递归实现
     * @param arr
     * @param key
     * @param low
     * @param high
     * @return 目标值位置
     */
    public static int recursionBinarySearch(int[] arr, int key, int low, int high) {
        if (key < arr[low] || key > arr[high] || low > high) {
            return -1;
        }
        int middle = low + (high - low) / 2;
        if (arr[middle] > key) {
            return recursionBinarySearch(arr, key, low, middle - 1);
        } else if (arr[middle] < key) {
            return recursionBinarySearch(arr, key, middle + 1, high);
        } else {
            return middle;
        }
    }

    /**
     * 循环实现
     * @param arr
     * @param key
     * @return 目标值位置
     */
    public static int commonBinarySearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int middle = 0;

        if (key < arr[low] || key > arr[high]) {
            return -1;
        }

        while (low <= high) {
            middle = low + (high - low) / 2;
            if (arr[middle] < key) {
                low = middle + 1;
            } else if (arr[middle] > key) {
                high = middle - 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1,3,5,7,9,11};
        int key1 = 4, key2 = 7;
        int position = commonBinarySearch(arr, key1);
        if(position == -1){
            System.out.println("查找的是"+key1+",序列中没有该数！");
        }else{
            System.out.println("查找的是"+key1+",找到位置为："+position);
        }

        position = recursionBinarySearch(arr, key2, 0, arr.length - 1);
        if(position == -1){
            System.out.println("查找的是"+key2+",序列中没有该数！");
        }else{
            System.out.println("查找的是"+key2+",找到位置为："+position);
        }
    }
}
