package BinarySearch;

/**
 *
 * 剑指offer: 旋转数组的最小元素
 * 原来的递增数组旋转后是两个递增数组，最小值是分界处，使用二分法处理
 *
 * @author Richa
 * @date 2020/8/18 22:17
 */
public class RotateArray {
    public int minArray(int[] numbers) {
        int low = 0, high = numbers.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (numbers[mid] > numbers[high]) {
                // 在旋转点的左边
                low = mid + 1;
            } else if (numbers[mid] < numbers[high]) {
                // 在旋转点的右边
                high = mid;
            } else {
                // mid和high相等，忽略右端点
                high = high - 1;
            }
        }
        // 最小值在mid的后一个
        return numbers[low];
    }
}
