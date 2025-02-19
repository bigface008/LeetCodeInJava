package org.leetcode.learn;

class Singleton {
    private volatile static Singleton shared;

    private Singleton() {}

    public static Singleton getShared() {
        if (shared == null) {
            synchronized (Singleton.class) {
                if (shared == null) {
                    shared = new Singleton();
                }
            }
        }
        return shared;
    }
}

//class Singleton {
//    private volatile static Singleton uniqueInstance;
//
//    private int counter;
//
//    public static Singleton getUniqueInstance() {
//        if (uniqueInstance == null) {
//            synchronized (Singleton.class) {
//                if (uniqueInstance == null) {
//                    uniqueInstance = new Singleton();
//                }
//            }
//        }
//        return uniqueInstance;
//    }
//}

public class LearnSingleton {
    public static void main(String[] args) {
        Singleton.getShared();
    }
}
