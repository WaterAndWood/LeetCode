package ProducerConsumer;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * LeetCode 1188: 设计有限阻塞队列
 * ReentrantLock实现
 *
 * @author Richa
 * @date 2020/9/13 22:44
 */
public class BoundedBlockingQueue {
    // 阻塞队列容量
    private volatile int capicity;
    private LinkedList<Integer> list;
    private static ReentrantLock lock = new ReentrantLock();
    Condition consumer = lock.newCondition();
    Condition producer = lock.newCondition();

    public BoundedBlockingQueue(int capicity) {
        this.capicity = capicity;
        this.list = new LinkedList<>();
    }

    // 队首增加element，如果队列满，调用线程被阻塞直到队列非满。
    public void enqueue(int element) throws InterruptedException {
        lock.lock();
        try {
            while (list.size() >= capicity) {
                producer.await();
            }
            list.addFirst(element);
            consumer.signal();
        } finally {
            lock.unlock();
        }
    }

    // 返回队尾元素并从队列中将其删除，如果队列为空，调用线程被阻塞直到队列非空
    public int dequeue() throws InterruptedException {
        lock.lock();
        try {
            while (list.size() == 0) {
                consumer.await();
            }
            int res = list.pollLast();
            producer.signal();
            return res;
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        lock.lock();
        try {
            return list.size();
        } finally {
            lock.unlock();
        }
    }
}
