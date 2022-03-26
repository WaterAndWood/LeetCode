package SortAlgorithm;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author wangzhen
 * @creatTime 2022/3/26 4:35 下午
 * @description offer40: 最小的K个数
 */
public class MinKNumber {
    /**
     * 大根堆/最大堆：顶点是最大的，堆内其他元素比它小，适合找最小的n个数
     * 使用堆的方式适合在大量数据中找出最大/最小topK
     * 时间复杂度：O(NlogK)
     */
    public int[] getLeastNumber(int[] arr, int k) {
        if (k == 0 || arr.length == 0) {
            return new int[0];
        }

        // 默认小根堆，重写比较器实现大根堆
        Queue<Integer> priorityQueue = new PriorityQueue<>((v1, v2) -> v2 - v1);
        for (int num : arr) {
            if (priorityQueue.size() < k) {
                priorityQueue.offer(num);
            } else if (num < priorityQueue.peek()) {
                priorityQueue.poll();
                priorityQueue.offer(num);
            }
        }
        int[] res = new int[priorityQueue.size()];
        int i = 0;
        for (int num : priorityQueue) {
            res[i++] = num;
        }
        return res;
    }

    /**
     * 与出现次数超过一半的数字一样，前K大或者前k小使用快排，但不需要对整个数组进行 O(NlogN) 的排序
     * 直接通过快排切分排好第 K 小的数（下标为 K-1），那么它左边的数就是比它小的另外 K-1 个数
     * 时间复杂度：O(N)
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0 || arr.length == 0) {
            return new int[0];
        }
        return quickSearch(arr, 0, arr.length - 1, k - 1);
    }

    private int[] quickSearch(int[] nums, int lo, int hi, int k) {
        int j = partition(nums, lo, hi);
        if (j == k) {
            return Arrays.copyOf(nums, j + 1);
        }
        // 根据下标j与k的大小关系来决定继续切分左段还是右段
        return j > k ? quickSearch(nums, lo, j - 1, k) : quickSearch(nums, j + 1, hi, k);
    }

    private int partition(int[] nums, int lo, int hi) {
        int v = nums[lo];
        int i = lo, j = hi + 1;
        while(true) {
            while (i <= hi && nums[i] < v) {
                i++;
            }
            while (j >= lo && nums[i] > v) {
                j--;
            }
            if (i >= j) {
                break;
            }
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        nums[lo] = nums[j];
        nums[j] = v;
        return j;
    }
}
