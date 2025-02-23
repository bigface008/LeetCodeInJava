package org.async;

public class ThreadSafeList<E> {
    private static class Node<E> {
        volatile E time;
        volatile Node<E> next;
    }

    public boolean offer(E e) {
        return false;
    }
}
