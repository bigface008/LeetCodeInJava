package org.leetcode.learn;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Solution {
    String lengthOfLongestSubstring(String s) {
        if (s.isEmpty()) {
            return "";
        }
        String ans = "";
        Map<Character, Integer> mp = new HashMap<>();
        int start = 0, i = 0;
        for (; i < s.length(); i++) {
            Character cc = s.charAt(i);
            if (mp.containsKey(cc)) {
                int index = mp.get(cc);
                if (index > start) {
                    int len = i - start + 1;
                    if (len > ans.length()) {
                        ans = s.substring(start, i);
                    }
                }
            }
            mp.put(cc, i);
        }
        return ans;
    }
}

public class Main {
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("My Thread");
        }
    }

    static class Info {
        int time;
        int cost;

        Info(int time, int cost) {
            this.time = time;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return String.format("{time: %d, cost: %d}", time, cost);
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        int[][] a1 = new int[10][];
//        int[][] a2 = new int[10][];
//        int[] b = new int[]{1, 2, 3};
//        a1[0] = b;
//        a2[1] = b;
//        b[1] = 13;
//        System.out.println(Arrays.toString(a1[0]));
//        System.out.println(Arrays.toString(a2[1]));
    }

    static void learnArrayList() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(32, -2, 41, 5, 22, 9));
        list.add(-5);
        Collections.reverse(list);
        System.out.println(list);
        System.out.println(Collections.min(list));
        list.sort(Comparator.naturalOrder());
        System.out.println(list);
        System.out.println(Collections.indexOfSubList(list, Arrays.asList(9, 22)));

        String[] myArr = {"Apple", "Banana", "Orange"};
        List<String> myList = new ArrayList<>(Arrays.asList(myArr));
        myList.add("Pineapple");
        System.out.println(myList);


    }

    static void learnQueue() {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(12);
        q.add(5);
        q.add(13);
        q.poll();
        q.poll();
        q.poll();
        System.out.println(q.isEmpty());
        System.out.println(q.peek());
        System.out.println(q.poll());

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.add(12);
        maxHeap.add(5);
        maxHeap.add(13);
        maxHeap.add(-1);
        System.out.println(maxHeap.peek());

        Integer.compare(1, 2);

        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);
    }

    static void learnMap() {
        TreeMap<String, Integer> mp = new TreeMap<>();
        mp.put("hello", 1);
        mp.put("world", 2);
        System.out.println(mp);

        Set<String> st = new HashSet<>();
        st.add("hello");
        st.add("world");
        boolean b = st.add("hello");
        System.out.println(b);

        for (var entry : mp.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        mp.forEach((k, v) -> System.out.println(k + " " + v));


    }

    static void blockedTest() throws InterruptedException {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                testMethod();
            }
        }, "a");

        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                testMethod();
            }
        }, "b");

        a.start();
        Thread.sleep(1000L);
        b.start();
        System.out.println(a.getName() + ": " + a.getState());
        System.out.println(b.getName() + ": " + b.getState());
    }


    private static synchronized void testMethod() {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyTask implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        Thread.sleep(1000);
        return 2;
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newCachedThreadPool();
        var task = new MyTask();
        Future<Integer> result = executor.submit(task);
        System.out.println(result.get());
    }
}

class ObjectLock {
    private static Object lock = new Object();

    static class ThreadA implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                for (int i = 0; i < 100; i++) {
                    System.out.println("Thread A " + i);
                }
            }
        }
    }

    static class ThreadB implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                for (int i = 0; i < 100; i++) {
                    System.out.println("Thread B " + i);
                }
            }
        }
    }

    static void run() throws InterruptedException {
        new Thread(new ThreadA()).start();
        Thread.sleep(10);
        new Thread(new ThreadB()).start();
    }
}

//class Solution {
//    int reverse(int x) {
//        boolean isNegative = x < 0;
//        String strX = String.valueOf(Math.abs(x));
//        StringBuilder builder = new StringBuilder(strX).reverse();
//        int res = 0;
//        try {
//            res = Integer.parseInt(builder.toString());
//            if (isNegative) {
//                res = -res;
//            }
//        } catch (NumberFormatException e) {
//            return 0;
//        }
//        return res;
//    }
//
//    int reverseV2(int x) {
//        int res = 0;
//        boolean isNegative = x < 0;
//        x = Math.abs(x);
//        while (x > 0) {
//            res = 10 * res + (x % 10);
//            x /= 10;
//        }
//        if (isNegative) {
//            res = -res;
//        }
//        return res;
//    }
//}

class DeadLockDemo {
    private static Object resource1 = new Object();
    private static Object resource2 = new Object();

    static void main(String[] args) {
        new Thread(() -> {
            synchronized (resource1) {
                System.out.println(Thread.currentThread() + ": locked resource 1");
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get resource 2");
                synchronized (resource2) {
                    System.out.println(Thread.currentThread() + ": locked resource 2");
                }
            }
        }, "Thread 1").start();

        new Thread(() -> {
            synchronized (resource1) {
                System.out.println(Thread.currentThread() + ": locked resource 1");
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get resource 2");
                synchronized (resource2) {
                    System.out.println(Thread.currentThread() + ": locked resource 2");
                }
            }
        }, "Thread 2").start();
    }
}

class SequentialPrinter {
    private static final int MAX_COUNT = 100;
    private int currentNumber = 1;

    void printNumbers(int remainder, String threadName) {
        synchronized (this) {
            while (currentNumber <= MAX_COUNT) {
                while (currentNumber % 3 != remainder) {
                    if (currentNumber >= MAX_COUNT) {
                        break;
                    }
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.out.println("Thread interrupted " + threadName);
                    }
                }
                if (currentNumber >= MAX_COUNT) {
                    break;
                }
                currentNumber++;
                System.out.println(currentNumber);
                notifyAll();
            }
        }
    }

    static void test() {
        SequentialPrinter printer = new SequentialPrinter();

        Thread t1 = new Thread(() -> printer.printNumbers(0, "Thread 1"));
        Thread t2 = new Thread(() -> printer.printNumbers(1, "Thread 2"));
        Thread t3 = new Thread(() -> printer.printNumbers(2, "Thread 3"));

        t1.start();
        t2.start();
        t3.start();
    }
}


class MyPrinter {
    private static final int MAX_COUNT = 100;
    private int count;
    private final Lock lock = new ReentrantLock();

    static void test() {
        var p = new MyPrinter();
        var t1 = new Thread(() -> p.print(0));
        var t2 = new Thread(() -> p.print(1));
        var t3 = new Thread(() -> p.print(2));
        t1.start();
        t2.start();
        t3.start();
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

class MyPrinterV2 {
    private static final int MAX_COUNT = 100;
    private int count;

    static void test() {
        var p = new MyPrinterV2();
        var t1 = new Thread(() -> p.print(0));
        var t2 = new Thread(() -> p.print(1));
        var t3 = new Thread(() -> p.print(2));
        t1.start();
        t2.start();
        t3.start();
    }

    void print(int remainder) {
        while (true) {
            synchronized (this) {
                if (count > MAX_COUNT) {
                    break;
                }
                if (count % 3 == remainder) {
                    System.out.printf("thread printer v2 %d %d\n", remainder, count);
                    count++;
                } else {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                notifyAll();
            }
        }
    }
}

class ReentrantLockExample {
    private int counter = 1;
    private static final int MAX = 100;
    private final Lock lock = new ReentrantLock();

    void printNumbers(int result) {
        while (true) {
            lock.lock();
            try {
                if (counter > MAX) {
                    break;
                }
                if (counter % 3 == result) {
                    System.out.println("Thread " + (result + 1) + ": " + counter);
                    counter++;
                }
            } finally {
                lock.unlock();
            }
        }
    }

    static void test() {
        var example = new ReentrantLockExample();
        Thread t1 = new Thread(() -> example.printNumbers(0));
        Thread t2 = new Thread(() -> example.printNumbers(1));
        Thread t3 = new Thread(() -> example.printNumbers(2));

        t1.start();
        t2.start();
        t3.start();
    }
}

class Wait_Notify_100 {
    private int num;
    private static final Object LOCK = new Object();
    private final int maxnum = 10;
    private void printABC(int targetNum) {
        while (true) {
            synchronized (LOCK) {
                while (num % 3 != targetNum) {
                    if(num >= maxnum){
                        break;
                    }
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(num >= maxnum){
                    break;
                }
                num++;
                System.out.println(Thread.currentThread().getName() + ": " + num);
                LOCK.notifyAll();
            }
        }
    }

    static void test() {
        Wait_Notify_100  wait_notify_100 = new Wait_Notify_100 ();
        new Thread(() -> {
            wait_notify_100.printABC(0);
        }, "thread1").start();
        new Thread(() -> {
            wait_notify_100.printABC(1);
        }, "thread2").start();
        new Thread(() -> {
            wait_notify_100.printABC(2);
        }, "thread3").start();
    }
}

class BPlusTree<K extends Comparable<K>, V> {
    static class Node {

    }

    void insert(K key, V value) {

    }

    void delete(K key) {

    }
}



class VolatileAtomicityDemo {
//    public volatile static int inc = 0;
    public static AtomicInteger inc = new AtomicInteger(0);

    public void increase() {
        inc.getAndIncrement();
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        VolatileAtomicityDemo volatileAtomicityDemo = new VolatileAtomicityDemo();
        for (int i = 0; i < 5; i++) {
            threadPool.execute(() -> {
                for (int j = 0; j < 500; j++) {
                    volatileAtomicityDemo.increase();
                }
            });
        }
        // 等待1.5秒，保证上面程序执行完成
        Thread.sleep(1500);
        System.out.println(inc.get());
        threadPool.shutdown();
    }
}
