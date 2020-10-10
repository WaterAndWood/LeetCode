package CustomDataStructure;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/**
 *
 * n个线分别打印1-n
 * 
 * @author Richa
 * @date 2020/10/10 12:31
 */
public class NThread {
    ReentrantLock lock = new ReentrantLock();
    Condition conditon = lock.newCondition();
    private volatile int state = 1;
    public void printNum(int n) {
        for (int i = 0; i < n; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    lock.lock();
                    try {
                        while (state == finalI) {
                            conditon.await();
                        }
                        System.out.println(state);
                        state++;
                        conditon.signalAll();
                    } catch (InterruptedException e) {

                    } finally {
                        lock.unlock();
                    }
                }
            }).start();
        }
    }

    public static void main(String[] args) {
        NThread nThread = new NThread();
        nThread.printNum(12);
    }
}
