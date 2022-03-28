package TargetOffer;

import java.util.PriorityQueue;

/**
 *
 * Offer41: 数据流中的中位数
 * 大顶堆存储较小的一半，小顶堆存放较大的一半
 *
 * @author Richa
 * @date 2022/3/28 22:44
 */
public class MedianFinder {
    /**
     * 大顶堆，存放较小的一半数字，堆顶是最大的
     */
    private PriorityQueue<Integer> maxHeap;
    /**
     * 小顶堆，存放较大的一半数字，堆顶是最小的，Java默认是小顶堆
     */
    private PriorityQueue<Integer> minHeap;

    private int count;


    public MedianFinder() {
        this.count = 0;
        this.maxHeap = new PriorityQueue<>((v1, v2) ->(v2 - v1));
        this.minHeap = new PriorityQueue<>();
    }

    /**
     * 奇数放到大顶堆，偶数放到小顶堆
     */
    public void addNum(int num) {
        count++;
        maxHeap.offer(num);
        // 偶数个则把最大值放入小顶堆
        minHeap.offer(maxHeap.poll());
        // 奇数个则放到大顶堆，所以回填大顶堆
        if ((count & 1) == 1) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        if ((count & 1) == 0) {
            return (minHeap.peek() + maxHeap.peek()) / 2d;
        } else {
            return (double)maxHeap.peek();
        }
    }
}
