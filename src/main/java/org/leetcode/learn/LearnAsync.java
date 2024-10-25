package org.leetcode.learn;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Printer {
    static final int MAX_VAL = 300;
    int i = 0;
    ReentrantLock lock = new ReentrantLock();

    void print(int offset) {
        while (true) {
            lock.lock();
            try {
                if (i > MAX_VAL) {
                    break;
                }
                if (i % 3 == offset) {
                    System.out.printf("thread %d %d\n", offset, i);
                    i++;
                }
            } finally {
                lock.unlock();
            }
        }
    }

    static void run() {
        var p = new Printer();
        var t1 = new Thread(() -> p.print(1));
        var t2 = new Thread(() -> p.print(2));
        var t3 = new Thread(() -> p.print(3));
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
}

public class LearnAsync {
    public static void main(String[] args) throws InterruptedException {
        AsyncPrinter.run();
//        Printer.run();
//        final Semaphore semaphore = new Semaphore(5);
//        semaphore.acquire();
//        semaphore.release();
//        LearnCountDown.run();
//        LearnThreadLocal obj = new LearnThreadLocal();
//        for (int i = 0; i < 10; i++) {
//            Thread t = new Thread(obj, "" + i);
//            Thread.sleep(new Random().nextInt(1000));
//            t.start();
//        }
    }
}

class LearnCountDown {
    static void run() {
        CountDownLatch latch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    System.out.println("thread " + finalI + " start");
                    Thread.sleep(1000);
                    System.out.println("thread " + finalI + " end");
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        try {
            System.out.println("await");
            latch.await();
            System.out.println("await finish");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class LearnFuture {
    static void run() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<Integer> task = () -> {
            Thread.sleep(1000);
            return 42;
        };
        Future<Integer> future = executor.submit(task);
        try {
            if (!future.isDone()) {
                System.out.println("Task is still running...");
            }
            Integer result = future.get();
            System.out.println("Task completed! Result " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}

class LearnThreadLocal implements Runnable {
    static final ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd HHmm"));

    @Override
    public void run() {
        System.out.println("Thread Name = " + Thread.currentThread().getName() + " default formatter = " + formatter.get().toPattern());
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        formatter.set(new SimpleDateFormat());
        System.out.println("Thread Name = " + Thread.currentThread().getName() + " default formatter = " + formatter.get().toPattern());
    }
}

class LRUCache {
    static class Node {
        int key;
        int val;
        LRUCache.Node prev;
        LRUCache.Node next;
    }

    static class NodeList {
        LRUCache.Node head;
        LRUCache.Node tail;

        NodeList() {
            head = new LRUCache.Node();
            tail = new LRUCache.Node();
            head.next = tail;
            tail.prev = head;
        }

        void remove(LRUCache.Node node) {
            LRUCache.Node prev = node.prev;
            LRUCache.Node next = node.next;
            if (prev != null) {
                prev.next = next;
            }
            if (next != null) {
                next.prev = prev;
            }
            node.prev = null;
            node.next = null;
        }

        void addFirst(LRUCache.Node node) {
            LRUCache.Node next = head.next;
            node.next = next;
            head.next = node;
            node.prev = head;
            if (next != null) {
                next.prev = node;
            }
        }

        LRUCache.Node removeLast() {
            LRUCache.Node last = tail.prev;
            tail.prev = last.prev;
            if (last.prev != null) {
                last.prev.next = tail;
            }
            last.prev = null;
            last.next = null;
            return last;
        }
    }

    int capacity;
    Map<Integer, LRUCache.Node> map = new HashMap<>();
    LRUCache.NodeList list = new LRUCache.NodeList();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        LRUCache.Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        list.remove(node);
        list.addFirst(node);
        return node.val;
    }

    public void put(int key, int value) {
        LRUCache.Node node = map.get(key);
        if (node == null) {
            if (map.size() == capacity) {
                LRUCache.Node drop = list.removeLast();
                map.remove(drop.key);
            }
            node = new LRUCache.Node();
            node.key = key;
            node.val = value;
            map.put(key, node);
            list.addFirst(node);
        } else {
            node.val = value;
            list.remove(node);
            list.addFirst(node);
        }
    }
}

//class LRUCache<T> {
//    private final Map<String, T> cache;
//    private final ReadWriteLock lock = new ReentrantReadWriteLock();
//
//    LRUCache(int capacity) {
//        this.cache = new LinkedHashMap<>(capacity, 0.75f, true) {
//            @Override
//            protected boolean removeEldestEntry(Map.Entry<String, T> eldest) {
//                return size() > capacity;
//            }
//        };
//    }
//
//    public T get(String key) {
//        lock.readLock().lock();
//        try {
//            return cache.get(key);
//        } finally {
//            lock.readLock().unlock();
//        }
//    }
//
//
//}

class AtomicIntegerExample {
    private static final int MAX = 100;
    private AtomicInteger counter = new AtomicInteger(1);

    public void printNumbers(int result) {
        while (counter.get() <= MAX) {
            if (counter.get() % 3 == result) {
                System.out.println("Thread " + (result + 1) + ": " + counter.getAndIncrement());
            }
        }
    }

    static void test() {
        AtomicIntegerExample example = new AtomicIntegerExample();
        Thread t1 = new Thread(() -> example.printNumbers(0));
        Thread t2 = new Thread(() -> example.printNumbers(1));
        Thread t3 = new Thread(() -> example.printNumbers(2));

        t1.start();
        t2.start();
        t3.start();
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
