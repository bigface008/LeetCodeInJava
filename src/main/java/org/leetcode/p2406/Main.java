package org.leetcode.p2406;

// https://leetcode.com/problems/divide-intervals-into-minimum-number-of-groups/?envType=daily-question&envId=2024-10-12
class Solution {
    public int minGroups(int[][] intervals) {
        int maxRight = 0;
        int minLeft = Integer.MAX_VALUE;
        for (int[] interval : intervals) {
            maxRight = Math.max(maxRight, interval[1]);
            minLeft = Math.min(minLeft, interval[0]);
        }
        int[] diff = new int[maxRight + 2];
        for (int[] interval : intervals) {
            int l = interval[0], r = interval[1];
            diff[l]++;
            diff[r + 1]--;
        }
        int maxDupCnt = 0;
        int acc = 0;
        for (int i = 1; i < diff.length; i++) {
            acc += diff[i];
            maxDupCnt = Math.max(acc, maxDupCnt);
        }
        return maxDupCnt;
    }
}

public class Main {
    public static void main(String[] args) {

    }
}
