package org.leetcode.p2145;

// https://leetcode.cn/problems/count-the-hidden-sequences/?envType=daily-question&envId=2025-04-21
class Solution {
    public int numberOfArrays(int[] differences, int lower, int upper) {
        long maxDiff = 0, minDiff = 0;
        int diff = 0;
        for (int x : differences) {
            diff += x;
            maxDiff = Math.max(maxDiff, diff);
            minDiff = Math.min(minDiff, diff);
        }
        long dist = maxDiff - minDiff;
        long minMax = lower + dist;
        if (upper < minMax) {
            return 0;
        }
        return (int) (upper - minMax) + 1;
    }
}

public class Main {
}
