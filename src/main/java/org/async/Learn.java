package org.async;

import java.io.IOException;

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
}
