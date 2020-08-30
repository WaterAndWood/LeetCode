package Multithreading;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * LeetCode 1114: 按序打印
 * first, second, third 三个线程竞争，需要保证second，third线程抢到锁也会进入等待，first先执行
 *
 * 1. CountDownLatch
 * 2. Lock
 *
 * @author Richa
 * @date 2020/8/30 10:53
 */

/*public class PrintInOrder {
    private CountDownLatch latch2;
    private CountDownLatch latch2;
    public PrintInOrder() {
        latch2 = new CountDownLatch(1);
        latch3 = new CountDownLatch(2);
    }

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        latch2.countDown();
        latch3.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        // second抢到线程也会等待
        latch2.await();
        printSecond.run();
        latch3.countDown();
    }

    public void third(Runnable third) throws InterruptedException {
        // third抢到线程也会等待
        latch3.await();
        third.run();
    }
}*/

/*public class PrintInOrder {
    Lock lock = new ReentrantLock();
    volatile int flag = 1;
    Condition con2 = lock.newCondition();
    Condition con3 = lock.newCondition();

    public PrintInOrder() {}

    public void first(Runnable printFirst) throws InterruptedException {
        lock.lock();
        try {
            printFirst.run();
            flag = 2;
            con2.signal();
        } finally {
            lock.unlock();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        lock.lock();
        try {
            // 当flag不是2时，即使抢到锁second阻塞
            while (flag != 2) {
                con2.await();
            }
            printSecond.run();
            flag = 3;
            con3.signal();
        } finally {
            lock.unlock();
        }
    }

    public void thrid(Runnable printThird) throws InterruptedException {
        lock.lock();
        try {
            // 当flag不是3，即使抢到锁third阻塞
            while (flag != 3) {
                con3.await();
            }
            printThird.run();
        } finally {
            lock.unlock();
        }
    }
}*/

/*public class PrintInOrder {
    private Semaphore semaphore1 = new Semaphore(1);
    private Semaphore semaphore2 = new Semaphore(0);
    private Semaphore semaphore3 = new Semaphore(0);

    public PrintInOrder() {}

    public void first(Runnable printFirst) throws InterruptedException {
        semaphore1.acquire();
        printFirst.run();
        semaphore2.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        // 开始时semaphore2=0，acquire阻塞
        semaphore2.acquire();
        printSecond.run();
        semaphore3.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        // 开始时semaphore3=0，acquire阻塞
        semaphore3.acquire();
        printThird.run();
    }
}*/

public class PrintInOrder {
    private int flag = 0;
    Object lock = new Object();
    public PrintInOrder() {}

    public void first(Runnable printFirst) throws InterruptedException {
        synchronized (lock) {
            while (flag != 0) {
                lock.wait();
            }
            printFirst.run();
            flag = 1;
            lock.notifyAll();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (lock) {
            while (flag != 1) {
                lock.wait();
            }
            printSecond.run();
            flag = 2;
            lock.notifyAll();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (lock) {
            while (flag != 2) {
                lock.wait();
            }
            printThird.run();
            flag = 0;
            lock.notifyAll();
        }
    }
}
