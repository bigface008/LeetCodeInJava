package org.leetcode.learn;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LearnAsync {
    public static void main(String[] args) {
        System.out.println("good");
    }
}

class AsyncAdder {
    int count;

    static void run() {
        var p = new AsyncAdder();
        var t1 = new Thread(() -> p.print(0));
        var t2 = new Thread(() -> p.print(1));
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("count " + p.count);
    }

    synchronized void add() {
        count++;
        System.out.println(Thread.currentThread().getName() + " " + count);
    }

    void print(int remainder) {
        for (int i = 0; i < 100; i++) {
            add();
        }
    }
}

class AsyncPrinter {
    private static final int MAX_COUNT = 100;

    private int count;

    private final Lock lock = new ReentrantLock();

    static void run() {
        var p = new AsyncPrinter();
        var t1 = new Thread(() -> p.print(0));
        var t2 = new Thread(() -> p.print(1));
        var t3 = new Thread(() -> p.print(2));
        t1.start();
        t2.start();
        t3.start();
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void print(int remainder) {
        while (true) {
            lock.lock();
            try {
                if (count > MAX_COUNT) {
                    break;
                }
                if (count % 3 == remainder) {
                    System.out.printf("thread %d %d\n", remainder, count);
                    count++;
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
