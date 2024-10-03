package org.leetcode.p1381;

// https://leetcode.com/problems/design-a-stack-with-increment-operation
class CustomStack {
    int[] buffer;
    int topIdx = -1;

    public CustomStack(int maxSize) {
        buffer = new int[maxSize];
    }

    public void push(int x) {
        if (topIdx == buffer.length - 1) {
            return;
        }
        topIdx++;
        buffer[topIdx] = x;
    }

    public int pop() {
        if (topIdx < 0) {
            return -1;
        }
        int ans = buffer[topIdx];
        topIdx--;
        return ans;
    }

    public void increment(int k, int val) {
        int limit = Math.min(k, buffer.length);
        for (int i = 0; i < limit; i++) {
            buffer[i] += val;
        }
    }
}

public class Main {
    public static void main(String[] args) {

    }
}
