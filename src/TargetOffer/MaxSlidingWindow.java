package TargetOffer;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * Offer 59: 队列最大值
 * 长度为n的数组，滑动窗口大小为k，滑动窗口个数为n-k+1
 *
 * @author Richa
 * @date 2022/4/15 20:14
 */
public class MaxSlidingWindow {
    /**
     * 窗口对应的数据结构是双端队列deque，deque内仅包含窗口中的元素，每轮滑动删除nums[i-1]，deque内元素一并删除
     * deque内元素非严格递减，队首deque[0]最大，每轮添加nums[j+1],将deque内小于nums[j+1]的元素删除
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) {
            return new int[0];
        }
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        for (int i = 1 - k, j = 0; j < nums.length; i++, j++) {
            // 窗口已经形成，num[i - 1]是被移除的元素，如果是deque队首，则删除
            if (i > 0 && deque.peekFirst() == nums[i - 1]) {
                deque.removeFirst();
            }
            // 保持deque递减
            while (!deque.isEmpty() && deque.peekLast() < nums[j]) {
                deque.removeLast();
            }
            deque.addLast(nums[j]);
            // 记录窗口最大值
            if (i >= 0) {
                res[i] = deque.peekFirst();
            }
        }
        return res;
    }

    // 遍历nums分成窗口形成前(小于k)和形成窗口后，减少冗余判断是否形成窗口
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) {
            return new int[0];
        }
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        // 形成窗口前
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.removeLast();
            }
            deque.addLast(nums[i]);
        }
        res[0] = deque.peekFirst();
        for (int i = k; i < nums.length; i++) {
            if (deque.peekFirst() == nums[i - k]) {
                deque.removeFirst();
            }
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.removeLast();
            }
            deque.addLast(nums[i]);
            res[i - k + 1] = deque.peekFirst();
        }
        return res;
    }

    /**
     * 对于队列，入队和出队的时间复杂度是O(1)
     * 数据结构实现查找最大值O(1)，空间换时间，使用递减队列来保存队列所有递减的元素
     */
    Queue<Integer> queue;
    Deque<Integer> deque;

    public MaxSlidingWindow() {
        this.queue = new LinkedList<>();
        this.deque = new LinkedList<>();
    }

    public int max_value() {
        return deque.isEmpty() ? -1 : deque.peekFirst();
    }

    public void push_back(int value) {
        queue.offer(value);
        while (!deque.isEmpty() && deque.peekLast() < value) {
            deque.removeLast();
        }
        deque.addLast(value);
    }

    public int pop_front() {
        if (queue.isEmpty()) {
            return -1;
        }
        if (queue.peek().equals(deque.peekFirst())) {
            deque.poll();
        }
        return queue.remove();
    }
}
