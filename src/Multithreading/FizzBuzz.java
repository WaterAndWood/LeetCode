package Multithreading;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 *
 * LeetCode 1195: 交替打印字符串
 * 
 * @author Richa
 * @date 2020/8/31 19:46
 */
public class FizzBuzz {
    Semaphore semaphore = new Semaphore(1);
    private int curNum = 1;
    private int n;

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for(;;) {
            this.semaphore.acquire(1);
            try {
                if (curNum > n) {
                    return;
                }
                if (curNum % 3 == 0 && (curNum % 5 != 0)) {
                    printFizz.run();
                    curNum++;
                }
            } finally {
                this.semaphore.release(1);
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while(true) {
            this.semaphore.acquire(1);
            try {
                if (curNum > n) {
                    return;
                }
                if (curNum % 5 == 0 && (curNum % 3 != 0)) {
                    printBuzz.run();
                    curNum++;
                }
            } finally {
                this.semaphore.release(1);
            }

        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzBuzz(Runnable printFizzBuzz) throws InterruptedException {
        while(true) {
            this.semaphore.acquire(1);
            try {
                if (curNum > n) {
                    return;
                }
                if (curNum % 5 == 0 && curNum % 3 == 0) {
                    printFizzBuzz.run();
                    curNum++;
                }
            } finally {
                this.semaphore.release(1);
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for(;;) {
            this.semaphore.acquire(1);
            try {
                if (curNum > n) {
                    return;
                }
                if (curNum % 5 != 0 && (curNum % 3 != 0)) {
                    printNumber.accept(curNum);
                    curNum++;
                }
            } finally {
                this.semaphore.release(1);
            }
        }
    }
}
