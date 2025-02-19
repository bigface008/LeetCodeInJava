package org.leetcode.learn;

import java.util.BitSet;

class BloomFilter {
    private static class SimpleHash {
        private int cap;
        private int seed;

        SimpleHash(int cap, int seed) {
            this.cap = cap;
            this.seed = seed;
        }

        int hash(Object value) {
            if (value == null) {
                return 0;
            }
            int h = value.hashCode();
            return Math.abs((cap - 1) & seed * (h ^ (h >>> 16)));
        }
    }

    private static final int DEFAULT_SIZE = 2 << 24;

    private static final int[] SEEDS = {3, 13, 46, 71, 91, 134};

    private BitSet bits = new BitSet(DEFAULT_SIZE);

    private SimpleHash[] func = new SimpleHash[SEEDS.length];

    BloomFilter() {
        for (int i = 0; i < SEEDS.length; i++) {
            func[i] = new SimpleHash(DEFAULT_SIZE, SEEDS[i]);
        }
    }

    void add(Object value) {
        for (SimpleHash f : func) {
            bits.set(f.hash(value), true);
        }
    }

    boolean contains(Object value) {
        boolean ret = true;
        for (SimpleHash f : func) {
            ret = ret && bits.get(f.hash(value));
        }
        return ret;
    }
}

public class LearnBloomFilter {
    public static void main(String[] args) {
        String value1 = "https://javaguide.cn/";
        String value2 = "https://github.com/Snailclimb";
        BloomFilter filter = new BloomFilter();
        System.out.println(filter.contains(value1));
        System.out.println(filter.contains(value2));
        filter.add(value1);
        filter.add(value2);
        System.out.println(filter.contains(value1));
        System.out.println(filter.contains(value2));
    }
}
