package org.async;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Learn {
    class Printer {
        static int val = 0;

        synchronized void add() {
            val += 1;
        }

        void run() throws IOException {
            var t1 = new Thread(() -> {
                for (int i = 0; i < 100; i++) {
                    add();
                }
            });
            var t2 = new Thread(() -> {
                for (int i = 0; i < 100; i++) {
                    add();
                }
            });
            t1.start();
            t2.start();

        }
    }

    public static void main(String[] args) {
//        Thread thread = new Thread(() -> {
//            for (int i = 0; i < 10; i++) {
//                System.out.println("i:" + i);
//            }
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        thread.setDaemon(true);
//        thread.start();
        System.out.println(Thread.currentThread().isDaemon());
    }
}
