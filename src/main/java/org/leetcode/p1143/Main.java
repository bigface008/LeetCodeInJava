package org.leetcode.p1143;

import java.util.Arrays;

// https://leetcode.com/problems/longest-common-subsequence/
class Solution {
    String text1;
    String text2;
    int[][] memo;

    public int longestCommonSubsequence(String text1, String text2) {
        this.text1 = text1;
        this.text2 = text2;
        final int N1 = text1.length(), N2 = text2.length();
        memo = new int[N1][N2];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dfs(N1 - 1, N2 - 1);
    }

    int dfs(int i, int j) {
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        if (i == 0 && j == 0) {
            if (text1.charAt(0) == text2.charAt(0)) {
                memo[0][0] = 1;
                return 1;
            } else {
                memo[0][0] = 0;
                return 0;
            }
        }
        if (i == 0) {
            if (text1.charAt(0) == text2.charAt(j)) {
                memo[0][j] = 1;
                return 1;
            } else {
                memo[0][j] = dfs(0, j - 1);
                return memo[0][j];
            }
        }
        if (j == 0) {
            if (text1.charAt(i) == text2.charAt(0)) {
                memo[i][0] = 1;
                return 1;
            } else {
                memo[i][0] = dfs(i - 1, 0);
                return memo[i][0];
            }
        }
        char ch1 = text1.charAt(i);
        char ch2 = text2.charAt(j);
        if (ch1 == ch2) {
            memo[i][j] = dfs(i - 1, j - 1) + 1;
            return memo[i][j];
        } else {
            memo[i][j] = Math.max(dfs(i - 1, j), dfs(i, j - 1));
            return memo[i][j];
        }
    }
}

public class Main {
}
