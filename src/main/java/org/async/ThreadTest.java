package org.async;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest {
    public static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("I'm a child thread");
        }
    }

    public static class RunnableTask implements Runnable {
        @Override
        public void run() {
            System.out.println("I'm a child thread");
        }
    }

    public static class CallerTask implements Callable<String> {
        @Override
        public String call() throws Exception {
            return "hello";
        }

        public static void main(String[] args) {
            FutureTask<String> futureTask = new FutureTask<>(new CallerTask());
            new Thread(futureTask).start();
            try {
                String result = futureTask.get();
                System.out.println(result);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class LearnThread00 {
        private static volatile Object resourceA = new Object();

        private static volatile Object resourceB = new Object();

        public static void main(String[] args) {
            Thread threadA = new Thread(() -> {
                try {
                    synchronized (resourceA) {
                        System.out.println("threadA get resourceA lock");
                        synchronized (resourceB) {
                            System.out.println("threadA get resourceB lock");
                            System.out.println("threadA release resourceA lock");
                            resourceA.wait();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            Thread threadB = new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    synchronized (resourceA) {
                        System.out.println("threadB get resourceA lock");
                        System.out.println("threadB try get resourceB lock");
                        synchronized (resourceB) {
                            System.out.println("threadB get resourceB lock");
                            System.out.println("threadB release resourceA lock");
                            resourceA.wait();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            threadA.start();
            threadB.start();
            try {
                threadA.join();
                threadB.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main over");
        }
    }

    public static class WaitNotifyInterupt {
        static Object obj = new Object();

        public static void main(String[] args) throws InterruptedException {
            Thread threadA = new Thread(() -> {
                try {
                    System.out.println("--- begin ---");
                    synchronized (obj) {
                        obj.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            threadA.start();
            Thread.sleep(1000);
            System.out.println("--- begin interrupt thread A ---");
            threadA.interrupt();
            System.out.println("--- end interrupt thread A ---");
        }
    }

    public static class LearnThread01 {
        private static volatile Object resourceA = new Object();

        public static void main(String[] args) throws InterruptedException {
            Thread threadA = new Thread(() -> {
                System.out.println("threadA get resourceA");
                synchronized (resourceA) {
                    try {
                        System.out.println("threadA begin wait");
                        resourceA.wait();
                        System.out.println("threadA end wait");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            Thread threadB = new Thread(() -> {
                System.out.println("threadB get resourceA");
                synchronized (resourceA) {
                    try {
                        System.out.println("threadB begin wait");
                        resourceA.wait();
                        System.out.println("threadB end wait");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            Thread threadC = new Thread(() -> {
                synchronized (resourceA) {
                    System.out.println("threadC begin notify");
                    resourceA.notifyAll();
                }
            });

            threadA.start();
            threadB.start();
            Thread.sleep(1000);
            threadC.start();
            threadA.join();
            threadB.join();
            threadC.join();
            System.out.println("main over");
        }
    }

    public class SleepTest2 {
        private static final Lock lock = new ReentrantLock();

        public static void main(String[] args) throws InterruptedException {
            Thread threadA = new Thread(() -> {
                lock.lock();
                try {
                    System.out.println("child threadA is in sleep");
                    Thread.sleep(1000);
                    System.out.println("child threadA is in awaked");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            });

            Thread threadB = new Thread(() -> {
                lock.lock();
                try {
                    System.out.println("child threadB is in sleep");
                    Thread.sleep(1000);
                    System.out.println("child threadB is in awaked");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            });

            threadA.start();
            threadB.start();
        }
    }

    public static class YieldTest implements Runnable {
        YieldTest() {
            Thread t = new Thread(this);
            t.start();
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                if ((i % 5) == 0) {
                    System.out.println(Thread.currentThread() + "yield cpu...");
                    Thread.yield();
                }
            }
            System.out.println(Thread.currentThread() + " is over");
        }

        public static void main(String[] args) throws InterruptedException {
            new YieldTest();
            new YieldTest();
            new YieldTest();
        }
    }

    public static class TestRWLong {

        public static long t = 0;

        public static class ChangeT implements Runnable {
            private long to;

            ChangeT(long to) {
                this.to = to;
            }

            @Override
            public void run() {
                while (true) {
                    TestRWLong.t = to;
                    Thread.yield();
                }
            }
        }

        public static class ReadT implements Runnable {
            @Override
            public void run() {
                while (true) {
                    long tmp = TestRWLong.t;
                    if (tmp != 111L && tmp != -999L && tmp != 333L && tmp != -444L) {
                        System.out.println(tmp);
                    }
                    Thread.yield();
                }
            }
        }

        public static void main(String[] args) {
            new Thread(new ChangeT(111L)).start();
            new Thread(new ChangeT(-999L)).start();
            new Thread(new ChangeT(333L)).start();
            new Thread(new ChangeT(-444L)).start();
            new Thread(new ReadT()).start();
        }
    }

    public static class TestInterrupt {
        public static void main(String[] args) {
            Thread t1 = new Thread() {
                @Override
                public void run() {
                    while (true) {
                        if (Thread.currentThread().isInterrupted()) {
                            System.out.println("Interrupted!");
                            break;
                        }
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            System.out.println("Interrupted When Sleep");
                            Thread.currentThread().interrupt();
                            System.out.println(Thread.currentThread().isInterrupted());
                        }
                        Thread.yield();
                    }
                }
            };
            t1.start();
            try {
                Thread.sleep(2000);
                t1.interrupt();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
    }
}
