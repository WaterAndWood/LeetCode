package ProducerConsumer;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 使用ReentrantLock和condition实现生产者消费者模式
 *
 * @author Richa on 2020/5/27 22:22
 * @param
 * @return
 * @throws
 */
public class LockCondition {
    static List<String> listObject = new LinkedList<>();
    static ReentrantLock lock = new ReentrantLock();
    static Condition empty = lock.newCondition();
    static Condition full = lock.newCondition();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(15);
        for (int i = 0; i < 10; i++) {
            executorService.submit(new Producer(listObject, 8, lock));
        }
        for (int i = 0; i < 10; i++) {
            executorService.submit(new Consumer(listObject, lock));
        }
    }

    static class Producer extends Thread {
        private List<String> list;
        private int maxSize;
        private Lock lock;

        public Producer(List list, int maxSize, Lock lock) {
            this.list = list;
            this.maxSize = maxSize;
            this.lock = lock;
        }
        @Override
        public void run() {
            lock.lock();
            try {
                while (list.size() == maxSize) {
                    System.out.println("生产者 " + Thread.currentThread().getName() + "  list以达到最大容量，进行wait");
                    full.wait();
                    System.out.println("生产者 " + Thread.currentThread().getName() + "  退出wait");
                }
                Random random = new Random();
                int i = random.nextInt(100);
                System.out.println("生产者 " + Thread.currentThread().getName() + " 生产数据 " + i);
                list.add(Thread.currentThread().getName() + " 生产数据 " + i);
                empty.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }
    }

    static class Consumer extends Thread {
        private List<String> list;
        private Lock lock;

        public Consumer(List list, Lock lock) {
            this.list = list;
            this.lock = lock;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                // lock.isEmpty()相当于状态变量，防止提前通知而wait
                // 这里使用if的话，就会存在wait条件变化可能造成程序错误的问题
                while (list.isEmpty()) {
                    System.out.println("消费者 " + Thread.currentThread().getName() + " list为空，调用wait方法");
                    empty.wait();
                    System.out.println("消费者 " + Thread.currentThread().getName() + " wait方法结束");
                }
                String str = list.remove(0);
                System.out.println("消费者 " + Thread.currentThread().getName() + " 取出第一个元素为：" + str);
                full.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
