package Multithreading;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * Leetcode1115: 交替打印FooBar
 *
 * @author Richa
 * @date 2022/6/4 10:00
 */
public class PrintFooBar {
    private int n;

    ReentrantLock lock = new ReentrantLock();
    Condition fooCondition = lock.newCondition();
    Condition barCondition = lock.newCondition();
    private volatile boolean flag = false;

    public PrintFooBar(int n) {
        this.n = n;
    }

    public void foo() {
        lock.lock();
        try {
            flag = true;
            for (int i = 0; i < n; i++) {
                System.out.println("Foo");
                barCondition.signal();
                fooCondition.await();
            }
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    public void bar() {
        lock.lock();
        try {
            while (!flag) {
                barCondition.await();
            }
            for (int i = 0; i < n; i++) {
                System.out.println("Bar");
                fooCondition.signal();
                barCondition.await();
            }
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }
}
