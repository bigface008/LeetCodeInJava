package org.leetcode.p2579;

// https://leetcode.com/problems/count-total-number-of-colored-cells/?envType=daily-question&envId=2025-03-05
class Solution {
    public long coloredCells(int n) {
        int ans = 0;
        long dp = 1;
        for (int i = 1; i < n; i++) {
            dp = dp + 4L * i;
        }
        return dp;
    }
}

// i  1  2  3
// 1  2  3  4
//    1  3  5

public class Main {
    public static void main(String[] args) {

    }
}
