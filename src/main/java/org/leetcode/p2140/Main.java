package org.leetcode.p2140;

// https://leetcode.cn/problems/solving-questions-with-brainpower/?envType=daily-question&envId=2025-04-01
class Solution {
    public long mostPoints(int[][] questions) {
        final int N = questions.length;
        long[] dp = new long[N];
        for (int i = N - 1; i >= 0; i--) {
            long notSelect = 0;
            if (i + 1 < N) {
                notSelect = dp[i + 1];
            }
            long select = questions[i][0];
            if (i + questions[i][1] + 1 < N) {
                select += dp[i + questions[i][1] + 1];
            }
            dp[i] = Math.max(select, notSelect);
        }
        return dp[0];
    }
}

public class Main {
    public static void main(String[] args) {

    }
}
