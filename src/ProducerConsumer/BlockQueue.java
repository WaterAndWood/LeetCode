package ProducerConsumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
/**
 *
 * 使用阻塞队列实现生产者消费者模式
 *
 * @author Richa on 2020/5/28 0:16
 * @param
 * @return
 * @throws
 */
public class BlockQueue {
    private static LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(15);
        for (int i = 0; i < 10; i++) {
            executorService.submit(new BlockQueue.Producer(queue));
        }
        for (int i = 0; i < 10; i++) {
            executorService.submit(new BlockQueue.Consumer(queue));
        }
    }

    static class Consumer implements Runnable {
        private BlockingQueue queue;

        public Consumer(BlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    // 消费者从阻塞队列获取数据
                    Integer element = (Integer) queue.take();
                    System.out.println("消费者 " + Thread.currentThread().getName() + " 正在消费数据 " + element);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    static class Producer implements Runnable {
        private BlockingQueue queue;

        public Producer(BlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Random random = new Random();
                    int i = random.nextInt(100);
                    System.out.println("生产者 " + Thread.currentThread().getName() + " 生产数据 " + i);
                    // 生产者向阻塞队列增加数据
                    queue.put(i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
