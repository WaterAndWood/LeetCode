package CustomDataStructure;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * LeetCode 1188: 设计有限阻塞队列
 * 
 * @author Richa
 * @date 2020/9/11 20:50
 */
public class BoundedBlockingQueue {
    // 其他线程可见
    private volatile int capicity;
    private LinkedList<Integer> list;
    // 可重入锁和消费者以及生产者
    private ReentrantLock lock = new ReentrantLock();
    Condition producer = lock.newCondition();
    Condition consumer = lock.newCondition();

    public BoundedBlockingQueue(int capicity) {
        this.capicity = capicity;
        this.list = new LinkedList<>();
    }

    /**
     * 入队，队列满的时候生产者阻塞
     *
     */
    public void enqueue(int element) throws InterruptedException {
        lock.lock();
        try {
            while (list.size() >= capicity) {
                producer.await();
            }
            list.add(element);
            consumer.signal();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 出队，队列空的时候消费者阻塞
     *
     */
    public int dequeue() throws InterruptedException {
        lock.lock();
        try {
            while (list.size() == 0) {
                consumer.await();
            }
            int ans = list.poll();
            producer.signal();
            return ans;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 获取size的时候，已经获得锁，所以不需要对size原子操作
     *
     */
    public int size() {
        lock.lock();
        try {
            return list.size();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BoundedBlockingQueue queue = new BoundedBlockingQueue(10);
        queue.enqueue(1);
    }
}
