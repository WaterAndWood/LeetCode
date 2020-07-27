package ProducerConsumer;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 *
 * 设计同步工具，在同一时刻，只允许最多两个线程同时访问，超过两个线程阻塞
 * 重写tryAcquireShare和tryReleaseShared，通过acquireShared和releaseShared调用重写的方法
 *
 * @author Richa
 * @date 2020/7/27 17:16
 */
public class TwinsLock implements Lock {
    /**
     * 内部类实现AQS
     */
    private static final class Sync extends AbstractQueuedSynchronizer {
        public Sync(int count) {
            if (count <= 0) {
                throw new IllegalArgumentException("count must be large than zero.");
            }
            setState(count);
        }

        @Override
        public int tryAcquireShared(int reduceCount) {
            for(;;) {
                int current = getState();
                int newCount = current - reduceCount;
                if (newCount < 0 || compareAndSetState(current, newCount)) {
                    return newCount;
                }
            }
        }

        @Override
        public boolean tryReleaseShared(int addCount) {
            for(;;) {
                int current = getState();
                int newCount = current + addCount;
                if (compareAndSetState(current, newCount)) {
                    return true;
                }
            }
        }

        private Condition newCondition() {
            return new ConditionObject();
        }
    }

    private final Sync sync = new Sync(2);

    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }

    @Override
    public void lockInterruptibly() {}

    @Override
    public boolean tryLock() {
        return sync.tryAcquireShared(1) >= 0;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) {
        if (time > 100) {
            return false;
        } else {
            return false;
        }
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    public static void main(String[] args) throws Exception {
        final Lock lock = new TwinsLock();

        class Worker extends Thread {
            @Override
            public void run() {

                while (true) {
                    try {
                        lock.lock();
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            Worker w = new Worker();
            w.setDaemon(true);
            w.start();
        }
        for (int i = 0;i < 10; i++) {
            Thread.sleep(1000);
            System.out.println();
        }
    }
}
