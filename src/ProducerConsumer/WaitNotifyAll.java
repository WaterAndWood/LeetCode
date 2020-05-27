package ProducerConsumer;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * 使用wait和nofifyAll实现生产者消费者模式
 *
 * @author Richa on 2020/5/27 22:22
 * @param
 * @return
 * @throws
 */
public class WaitNotifyAll {
    static List<String> listObject = new LinkedList<>();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(15);
        for (int i = 0; i < 10; i++) {
            executorService.submit(new Producer(listObject, 5));
        }
        for (int i = 0; i < 10; i++) {
            executorService.submit(new Consumer(listObject));
        }
    }

    static class Producer extends Thread {
        private List<String> lock;
        private int full;

        public Producer(List lock, int full) {
            this.lock = lock;
            this.full = full;
        }
        @Override
        public void run() {
            try {
                synchronized (lock) {
                    while (lock.size() == full) {
                        System.out.println("生产者 " + Thread.currentThread().getName() + "  list以达到最大容量，进行wait");
                        lock.wait();
                        System.out.println("生产者 " + Thread.currentThread().getName() + "  退出wait");
                    }
                }
                Random random = new Random();
                int i = random.nextInt(100);
                System.out.println("生产者 " + Thread.currentThread().getName() + " 生产数据 " + i);
                lock.add(Thread.currentThread().getName() + " 生产数据 " + i);
                lock.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    static class Consumer extends Thread {
        private List<String> lock;

        public Consumer(List lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock) {
                try {
                    // lock.isEmpty()相当于状态变量，防止提前通知而wait
                    // 这里使用if的话，就会存在wait条件变化可能造成程序错误的问题
                    while (lock.isEmpty()) {
                        System.out.println("消费者 " + Thread.currentThread().getName() + " list为空，调用wait方法");
                        lock.wait();
                        System.out.println("消费者 " + Thread.currentThread().getName() + " wait方法结束");
                    }
                    String str = lock.remove(0);
                    System.out.println("消费者 " + Thread.currentThread().getName() + " 取出第一个元素为：" + str);
                    lock.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
