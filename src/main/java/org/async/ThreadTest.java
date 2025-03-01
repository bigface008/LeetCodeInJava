package org.async;

import com.google.common.util.concurrent.MoreExecutors;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

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

    public static class SimpleWN {
        final static Object object = new Object();

        public static class T1 extends Thread {
            @Override
            public void run() {
                synchronized (object) {
                    System.out.println(System.currentTimeMillis() + ": T1 start!");
                    try {
                        System.out.println(System.currentTimeMillis() + ": T1 wait for object");
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(System.currentTimeMillis() + ": T1 end!");
                }
            }
        }

        public static class T2 extends Thread {
            @Override
            public void run() {
                synchronized (object) {
                    System.out.println(System.currentTimeMillis() + ": T2 start! notify one thread");
                    object.notify();
                    System.out.println(System.currentTimeMillis() + ": T2 end!");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public static void main(String[] args) {
            Thread t1 = new T1();
            t1.start();
            Thread t2 = new T2();
            t2.start();
        }
    }

    public static class GoodSuspend {
        public static Object u = new Object();

        public static class ChangeObjectThread extends Thread {
            volatile boolean suspendMe = false;

            public void suspendMe() {
                suspendMe = true;
            }

            public void resumeMe() {
                suspendMe = false;
                synchronized (this) {
                    notify();
                }
            }

            @Override
            public void run() {
                while (true) {
                    synchronized (this) {
                        while (suspendMe) {
                            try {
                                wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        synchronized (u) {
                            System.out.println("in ChangeObjectThread");
                        }
                        Thread.yield();
                    }
                }
            }
        }

        public static class ReadObjectThread extends Thread {
            @Override
            public void run() {
                while (true) {
                    synchronized (u) {
                        System.out.println("in ReadObjectThread");
                    }
                    Thread.yield();
                }
            }
        }

        public static void main(String[] args) {
            try {
                ChangeObjectThread t1 = new ChangeObjectThread();
                ReadObjectThread t2 = new ReadObjectThread();
                t1.start();
                t2.start();
                Thread.sleep(1000);
                t1.suspendMe();
                System.out.println("suspend t1 2 sec");
                Thread.sleep(2000);
                System.out.println("resume t1");
                t1.resumeMe();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class ThreadGroupName implements Runnable {
        public static void main(String[] args) {
            ThreadGroup tg = new ThreadGroup("PrintGroup");
            Thread t1 = new Thread(tg, new ThreadGroupName(), "T1");
            Thread t2 = new Thread(tg, new ThreadGroupName(), "T2");
            t1.start();
            t2.start();
            System.out.println(tg.activeCount());
            tg.list();
        }

        @Override
        public void run() {
            String groupAndName = Thread.currentThread().getThreadGroup().getName() + "-" + Thread.currentThread().getName();
            while (true) {
                System.out.println("I am " + groupAndName);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class DaemonDemo {
        public static class DaemonT extends Thread {
            @Override
            public void run() {
                while (true) {
                    System.out.println("I'm alive");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public static void main(String[] args) {
            try {
                Thread t = new DaemonT();
                t.setDaemon(true);
                t.start();
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class PriorityDemo {
        public static class HighPriority extends Thread {
            static int count = 0;

            @Override
            public void run() {
                while (true) {
                    synchronized (PriorityDemo.class) {
                        count++;
                        if (count > 100000000) {
                            System.out.println("High Priority is Complete");
                            break;
                        }
                    }
                }
            }
        }

        public static class LowPriority extends Thread {
            static int count = 0;

            @Override
            public void run() {
                while (true) {
                    synchronized (PriorityDemo.class) {
                        count++;
                        if (count > 100000000) {
                            System.out.println("Low Priority is Complete");
                            break;
                        }
                    }
                }
            }
        }

        public static void main(String[] args) {
            Thread high = new HighPriority();
            LowPriority low = new LowPriority();
            high.setPriority(Thread.MAX_PRIORITY);
            low.setPriority(Thread.MIN_PRIORITY);
            low.start();
            high.start();
        }
    }

    public static class AccountVol implements Runnable {
        static AccountVol instance = new AccountVol();

        static volatile int i = 0;

        @Override
        public void run() {
            for (int j = 0; j < 10000000; j++) {
                synchronized (instance) {
                    increase();
                }
            }
        }

        public static void increase() {
            i++;
        }

        public static void main(String[] args) throws InterruptedException {
            Thread t1 = new Thread(instance);
            Thread t2 = new Thread(instance);
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            System.out.println(i);
        }
    }

    public static class ConcurrentArrayList {
        static ArrayList<Integer> list = new ArrayList<>(10);

        public static class AddThread implements Runnable {
            @Override
            public void run() {
                for (int i = 0; i < 1000000; i++) {
                    list.add(i);
                }
            }
        }

        public static void main(String[] args) throws InterruptedException {
            Thread t1 = new Thread(new AddThread());
            Thread t2 = new Thread(new AddThread());
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            System.out.println(list.size());
        }
    }

    public static class BadLockOnInt implements Runnable {
        @Override
        public void run() {
        }
    }

    public static class TestInt {
        public static class AddInt implements Runnable {
            static Integer value = 0;

            @Override
            public void run() {
                for (int i = 0; i < 10000000; i++) {
                    synchronized (AddInt.class) {
                        AddInt.value++;
                    }
                }
            }
        }

        public static void main(String[] args) throws InterruptedException {
            Thread t1 = new Thread(new AddInt());
            Thread t2 = new Thread(new AddInt());
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            System.out.println(AddInt.value);
        }
    }

    public static class IntLock implements Runnable {
        static ReentrantLock lock1 = new ReentrantLock();
        static ReentrantLock lock2 = new ReentrantLock();
        int lock;

        IntLock(int lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                if (lock == 1) {
                    lock1.lockInterruptibly();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {

                    }
                    lock2.lockInterruptibly();
                } else {
                    lock2.lockInterruptibly();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {

                    }
                    lock1.lockInterruptibly();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (lock1.isHeldByCurrentThread()) {
                    lock1.unlock();
                }
                if (lock2.isHeldByCurrentThread()) {
                    lock2.unlock();
                }
                System.out.println(Thread.currentThread().getId() + ": Thread Exit");
            }
        }

        public static void main(String[] args) throws InterruptedException {
            IntLock r1 = new IntLock(1);
            IntLock r2 = new IntLock(2);
            Thread t1 = new Thread(r1);
            Thread t2 = new Thread(r2);
            t1.start();
            t2.start();
            Thread.sleep(1000);
            t2.interrupt();
        }
    }

    public class TimeLock implements Runnable {
        public static ReentrantLock lock = new ReentrantLock();

        @Override
        public void run() {
            try {
                if (lock.tryLock(5, TimeUnit.SECONDS)) {
                    Thread.sleep(6000);
                } else {
                    System.out.println("get lock failed");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
        }

        public static void main(String[] args) {
        }
    }

    public static class ReenterLockCond implements Runnable {
        public static ReentrantLock lock = new ReentrantLock();
        public static Condition condition = lock.newCondition();

        @Override
        public void run() {
            try {
                lock.lock();
                condition.wait();
                System.out.printf("Thread is going on");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public static void main(String[] args) throws InterruptedException {
            ReenterLockCond tl = new ReenterLockCond();
            Thread t1 = new Thread(tl);
            t1.start();
            Thread.sleep(2000);
            lock.lock();
            condition.signal();
            lock.unlock();
        }
    }

    public static class SemapDemo implements Runnable {
        final Semaphore semp = new Semaphore(5);

        @Override
        public void run() {
            try {
                semp.acquire();
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getId() + ": done!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semp.release();
            }
        }

        public static void main(String[] args) {
            ExecutorService exec = Executors.newFixedThreadPool(20);
            final SemapDemo demo = new SemapDemo();
            for (int i = 0; i < 20; i++) {
                exec.submit(demo);
            }
        }
    }

    public static class RWLockDemo {
        private static Lock lock = new ReentrantLock();

        private static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

        private int value;

        public Object handleRead(Lock lock) throws InterruptedException {
            try {
                lock.lock();
                Thread.sleep(1000);
                return value;
            } finally {
                lock.unlock();
            }
        }

        public void handleWrite(Lock lock, int index) throws InterruptedException {
            try {
                lock.lock();
                Thread.sleep(1000);
                value = index;
            } finally {
                lock.unlock();
            }
        }

        public static void main(String[] args) {
            final RWLockDemo demo = new RWLockDemo();
            Runnable read = new Runnable() {
                @Override
                public void run() {
                    try {
                        demo.handleRead(lock);
//                        demo.handleRead(rwLock.readLock());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            Runnable write = new Runnable() {
                @Override
                public void run() {
                    try {
                        demo.handleWrite(lock, new Random().nextInt());
//                        demo.handleWrite(rwLock.writeLock(), new Random().nextInt());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            for (int i = 0; i < 18; i++) {
                new Thread(read).start();
            }
            for (int i = 18; i < 20; i++) {
                new Thread(write).start();
            }
        }
    }

    public static class CountDownLatchDemo implements Runnable {
        static final CountDownLatch end = new CountDownLatch(10);

        static final CountDownLatchDemo demo = new CountDownLatchDemo();

        @Override
        public void run() {
            try {
                Thread.sleep(new Random().nextInt(10) * 1000);
                System.out.println("check complete");
                end.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) throws InterruptedException {
            ExecutorService exec = Executors.newFixedThreadPool(10);
            for (int i = 0; i < 10; i++) {
                exec.submit(demo);
            }
            end.await();
            System.out.println("Fire!");
            exec.shutdown();
        }
    }

    public static class CyclicBarrierDemo {
        public static class Soldier implements Runnable {
            private String soldier;

            private final CyclicBarrier cyclic;

            Soldier(CyclicBarrier barrier, String soldierName) {
                this.cyclic = barrier;
                this.soldier = soldierName;
            }

            @Override
            public void run() {
                try {
                    cyclic.await();
                    doWork();
                    cyclic.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }

            void doWork() {
                try {
                    Thread.sleep(Math.abs(new Random().nextInt() % 10000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(soldier + ": Task Completed");
            }
        }

        public static class BarrierRun implements Runnable {
            boolean flag;

            int N;

            public BarrierRun(boolean flag, int N) {
                this.flag = flag;
                this.N = N;
            }

            @Override
            public void run() {
                if (flag) {
                    System.out.println("Commander: [Soldier " + N + " Task Completed!]");
                } else {
                    System.out.println("Commander: [Soldier " + N + " Assemble!]");
                    flag = true;
                }
            }
        }

        public static void main(String[] args) {
            final int N = 10;
            Thread[] allSoldier = new Thread[N];
            boolean flag = false;
            CyclicBarrier cyclic = new CyclicBarrier(N, new BarrierRun(flag, N));
            System.out.println("Assemble Team!");
            for (int i = 0; i < N; i++) {
                System.out.println("Soldier " + i + " Report!");
                allSoldier[i] = new Thread(new Soldier(cyclic, "Soldier" + i));
                allSoldier[i].start();
                if (i == 5) {
                    allSoldier[0].interrupt();
                }
            }
        }
    }

    public static class LockSupportDemo {
        public static Object u = new Object();
        public static Thread t1 = new ChangeObjectThread("t1");
        public static Thread t2 = new ChangeObjectThread("t2");

        public static class ChangeObjectThread extends Thread {
            ChangeObjectThread(String name) {
                super.setName(name);
            }

            @Override
            public void run() {
                synchronized (u) {
                    System.out.println("in " + getName());
                    LockSupport.park();
                    if (Thread.interrupted()) {
                        System.out.println(getName() + " interrupted");
                    }
                }
            }
        }

        public static void main(String[] args) throws InterruptedException {
            t1.start();
            Thread.sleep(1000);
            t2.start();
//            LockSupport.unpark(t1);
            t1.interrupt();
            LockSupport.unpark(t2);
            t1.join();
            t2.join();
        }
    }

    public static class ThreadPoolDemo {
        public static class MyTask implements Runnable {
            @Override
            public void run() {
                System.out.println(System.currentTimeMillis() + ": Thread ID:" + Thread.currentThread().getId());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public static void main(String[] args) {
            MyTask task = new MyTask();
            ExecutorService es = Executors.newFixedThreadPool(5);
            for (int i = 0; i < 10; i++) {
                es.submit(task);
            }
        }
    }

    public static class ScheduledExecutorServiceDemo {
        public static void main(String[] args) {
            ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);
            ses.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        System.out.println(System.currentTimeMillis() / 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, 0, 2, TimeUnit.SECONDS);
        }
    }

    public static class RejectThreadDemo {
        public static class MyTask implements Runnable {
            @Override
            public void run() {
                System.out.println(System.currentTimeMillis() + ":Thread ID:" + Thread.currentThread().getId());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public static void main(String[] args) throws InterruptedException {
            MyTask task = new MyTask();
            ExecutorService es = new ThreadPoolExecutor(
                    5, 5, 0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<>(),
                    Executors.defaultThreadFactory(),
                    new RejectedExecutionHandler() {
                        @Override
                        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                            System.out.println(r.toString() + " is discarded.");
                        }
                    });
            for (int i = 0; i < 5; i++) {
                es.submit(task);
            }
            Thread.sleep(2000);
        }
    }

    public static class TryFinallyExample {
        public static void main(String[] args) {
            try {
                System.out.println("start");
                int res = 10 / 0;
            } finally {
                System.out.println("finally");
                return;
            }
        }
    }

    public static class ExtThreadPool {
        public static class MyTask implements Runnable {
            public String name;

            public MyTask(String name) {
                this.name = name;
            }

            @Override
            public void run() {
                System.out.println("Executing Thread ID:" + Thread.currentThread().getId() + ", Task Name:" + name);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public static void main(String[] args) throws InterruptedException {
            ExecutorService es = new ThreadPoolExecutor(
                    5, 5, 0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<>()) {
                @Override
                protected void beforeExecute(Thread t, Runnable r) {
                    System.out.println("Exec Start: " + ((MyTask) r).name);
                }

                @Override
                protected void afterExecute(Runnable r, Throwable t) {
                    System.out.println("Exec Complete: " + ((MyTask) r).name);
                }

                @Override
                protected void terminated() {
                    System.out.println("ThreadPool exit");
                }
            };
            for (int i = 0; i < 5; i++) {
                MyTask task = new MyTask("TASK-GEYM-" + i);
                es.execute(task);
                Thread.sleep(10);
            }
            es.shutdown();
        }
    }

    public static class DivTask implements Runnable {
        int a, b;

        public DivTask(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            double res = a / b;
            System.out.println(res);
        }

        public static void main(String[] args) {
            ThreadPoolExecutor pool = new ThreadPoolExecutor(
                    0, Integer.MAX_VALUE, 0L, TimeUnit.SECONDS,
                    new SynchronousQueue<>());
            for (int i = 0; i < 5; i++) {
                pool.execute(new DivTask(100, i));
            }
        }
    }

    public static class TraceThreadPoolExecutor extends ThreadPoolExecutor {
        public TraceThreadPoolExecutor(
                int corePoolSize,
                int maximumPoolSize,
                long keepAliveTime,
                TimeUnit unit,
                BlockingQueue<Runnable> workQueue
        ) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        @Override
        public void execute(Runnable command) {
            super.execute(wrap(command, clientTrace(), Thread.currentThread().getName()));
        }

        @Override
        public Future<?> submit(Runnable task) {
            return super.submit(wrap(task, clientTrace(), Thread.currentThread().getName()));
        }

        private Exception clientTrace() {
            return new Exception("Client stack trace");
        }

        private Runnable wrap(final Runnable task, final Exception clientStack, String clientThreadName) {
            return new Runnable() {
                @Override
                public void run() {
                    try {
                        task.run();
                    } catch (Exception e) {
                        clientStack.printStackTrace();
                        throw e;
                    }
                }
            };
        }

        public static void main(String[] args) {
            ThreadPoolExecutor pool = new TraceThreadPoolExecutor(
                    0, Integer.MAX_VALUE, 0L, TimeUnit.SECONDS, new SynchronousQueue<>()
            );
            for (int i = 0; i < 5; i++) {
                pool.execute(new DivTask(100, i));
            }
        }
    }

    public static class CountTask extends RecursiveTask<Long> {
        private static final int THRESHOLD = 10000;

        private long start;

        private long end;

        public CountTask(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public Long compute() {
            long sum = 0;
            boolean canCompute = (end - start) < THRESHOLD;
            if (canCompute) {
                for (long i = start; i <= end; i++) {
                    sum += i;
                }
            } else {
                long step = (start + end) / 100;
                ArrayList<CountTask> subTasks = new ArrayList<>();
                long pos = start;
                for (int i = 0; i < 100; i++) {
                    long lastOne = pos + step;
                    if (lastOne > end) {
                        lastOne = end;
                    }
                    CountTask subTask = new CountTask(pos, lastOne);
                    pos += step + 1;
                    subTasks.add(subTask);
                    subTask.fork();
                }
                for (CountTask t : subTasks) {
                    sum += t.join();
                }
            }
            return sum;
        }

        public static void main(String[] args) {
            ForkJoinPool forkJoinPool = new ForkJoinPool();
            CountTask task = new CountTask(0, 200000L);
            ForkJoinTask<Long> result = forkJoinPool.submit(task);
            try {
                long res = result.get();
                System.out.println("sum=" + res);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public class DeadLockTest2 {
        private static Object resA = new Object();
        private static Object resB = new Object();

        public static void main(String[] args) {
            Thread thread = new Thread(() -> {
                synchronized (resA) {
                    System.out.println(Thread.currentThread() + " get resA");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread() + " wait resB");
                    synchronized (resB) {
                        System.out.println(Thread.currentThread() + " get resB");
                    }
                }
            });
            Thread threadB = new Thread(() -> {
                synchronized (resB) {
                    System.out.println(Thread.currentThread() + " get resB");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread() + " wait get resA");
                    synchronized (resA) {
                        System.out.println(Thread.currentThread() + " get resA");
                    }
                }
            });
        }
    }

    public static class ThreadLocalTest {
        static void print(String str) {
            System.out.println(str + ":" + localVar.get());
            localVar.remove();
        }

        static ThreadLocal<String> localVar = new ThreadLocal<>();

        public static void main(String[] args) {
            Thread t1 = new Thread(() -> {
                localVar.set("T1");
                print("t1");
                System.out.println("after " + localVar.get());
            });
            Thread t2 = new Thread(() -> {
                localVar.set("T2");
                print("t2");
                System.out.println("after " + localVar.get());
            });
            t1.start();
            t2.start();
        }
    }

    public static void main(String[] args) {
        try {
            Thread threadOne = new Thread(new Runnable() {
                public void run() {
                    while (!Thread.interrupted()) {
                    }
                    System.out.println("thread one isInterrupted:" + Thread.currentThread().isInterrupted());
                }
            });
            threadOne.start();
            threadOne.interrupt();
            threadOne.join();
        } catch (RuntimeException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
