package org.async;

import java.util.LinkedList;
import java.util.Queue;

public class LeakyBucket {
    private final int capacity;
    private final int leakRate;
    private final Queue<Long> queue;

    public LeakyBucket(int capacity, int leakRate) {
        this.capacity = capacity;
        this.leakRate = leakRate;
        this.queue = new LinkedList<>();
    }


}
