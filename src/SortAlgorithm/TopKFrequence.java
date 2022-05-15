package SortAlgorithm;

import java.util.*;

/**
 *
 * LeetCode 347: 前K个高频元素
 * 
 * @author Richa
 * @date 2020/9/8 14:49
 */
public class TopKFrequence {
    public int[] topKFreq(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        // 字典统计数字出现次数
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        // 小顶堆：排序按照数字出现次数，不能按照数字本身的大小排序，需要重写Comparator
        Queue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return map.get(a) - map.get(b);
            }
        });
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (queue.size() < k) {
                queue.add(entry.getKey());
            } else if (map.get(queue.peek()) < entry.getValue()) {
                queue.poll();
                queue.offer(entry.getKey());
            }
        }
        int[] ans = new int[k];
        int idx = 0;
        for (Integer j : queue) {
            ans[idx++] = j;
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] nums = new int[] {4,1,-1,2,-1,2,3};
        int k = 2;
        TopKFrequence topKFrequence = new TopKFrequence();
        int[] ans = topKFrequence.topKFreq(nums, k);
        System.out.println(Arrays.toString(ans));
    }
}

