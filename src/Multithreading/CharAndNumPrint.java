package Multithreading;

import java.lang.reflect.Constructor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 交替打印字母和数字
 *
 * @author Richa
 * @date 2020/8/30 14:31
 */
public class CharAndNumPrint {
    private char[] arrayChar = "ABCDEFG".toCharArray();
    private char[] arrayNum = "1234567".toCharArray();
    private volatile boolean flag = true;

    public void printInOrder1() {
        Lock lock = new ReentrantLock();
        Condition conditionChar = lock.newCondition();
        Condition conditionNum = lock.newCondition();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    flag = false;
                    for (char ch : arrayChar) {
                        System.out.println(ch);
                        conditionNum.signal();
                        conditionChar.await();
                    }
                    conditionNum.signal();
                } catch (Exception e) {

                } finally {
                    lock.unlock();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    // 确保首先执行打印字母
                    while (flag) {
                        conditionNum.await();
                    }
                    for (char ch : arrayNum) {
                        System.out.println(ch);
                        conditionChar.signal();
                        conditionNum.await();
                    }
                    conditionChar.signal();
                } catch (Exception e) {

                } finally {
                    lock.unlock();
                }
            }
        }).start();
    }

    private Thread threadChar;
    private Thread threadNum;
    public void printInOrder2() {
        threadChar = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (char ch : arrayChar) {
                        // 打印之后，唤醒threadNum，阻塞自身
                        System.out.println(ch);
                        LockSupport.unpark(threadNum);
                        LockSupport.park();
                    }
                } catch (Exception e) {

                }
            }
        });
        threadNum = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (char ch : arrayNum) {
                        // 阻塞自身，防止首先获得锁，打印之后唤醒threadChar
                        LockSupport.park();
                        System.out.println(ch);
                        LockSupport.unpark(threadChar);
                    }
                } catch (Exception e) {

                }
            }
        });

        threadChar.start();
        threadNum.start();
    }

    public static void main(String[] args) {
        CharAndNumPrint charAndNumPrint = new CharAndNumPrint();
        charAndNumPrint.printInOrder1();
//        charAndNumPrint.printInOrder2();
    }
}
