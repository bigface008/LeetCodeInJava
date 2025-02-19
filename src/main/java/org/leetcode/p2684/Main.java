package org.leetcode.p2684;

import java.util.Arrays;

// https://leetcode.com/problems/maximum-number-of-moves-in-a-grid/?envType=daily-question&envId=2024-10-29
class Solution {
    int[][] grid;
    int[][] memo;
    int ans;
    int M;
    int N;

    public int maxMoves(int[][] grid) {
        this.grid = grid;
        ans = 0;
        M = grid.length;
        N = grid[0].length;
        memo = new int[M][N];
        for (int i = 0; i < M; i++) {
            Arrays.fill(memo[i], -1);
        }
        // (r, c) following biggest step cnt
        int ans = 0;
        for (int i = 0; i < M; i++) {
            ans = Math.max(ans, dfs(i, 0));
        }
        return ans;
    }

    int dfs(int row, int col) {
        if (memo[row][col] != -1) {
            return memo[row][col];
        }
        if (col == N - 1) {
            memo[row][col] = 0;
            return 0;
        }
        int x = grid[row][col];
        boolean foundNext = false;
        int len = 0;
        if (x < grid[row][col + 1]) {
            foundNext = true;
            len = dfs(row, col + 1);
        }
        if (row - 1 >= 0 && x < grid[row - 1][col + 1]) {
            foundNext = true;
            len = Math.max(len, dfs(row - 1, col + 1));
        }
        if (row + 1 < M && x < grid[row + 1][col + 1]) {
            foundNext = true;
            len = Math.max(len, dfs(row + 1, col + 1));
        }
        int res = 0;
        if (foundNext) {
            res = len + 1;
        }
        memo[row][col] = res;
        return res;
    }
}

//class Solution {
//    int[][] grid;
//    int ans;
//    int M;
//    int N;
//
//    public int maxMoves(int[][] grid) {
//        this.grid = grid;
//        ans = 0;
//        M = grid.length;
//        N = grid[0].length;
//        for (int i = 0; i < M; i++) {
//            dfs(i, 0, 0);
//        }
//        return ans;
//    }
//
//    void dfs(int row, int col, int stepCnt) {
//        if (col == N - 1) {
//            ans = N - 1;
//            return;
//        }
//        int x = grid[row][col];
//        ans = Math.max(ans, stepCnt);
//        if (row - 1 >= 0 && x < grid[row - 1][col + 1]) {
//            dfs(row - 1, col + 1, stepCnt + 1);
//            if (ans == N - 1) {
//                return;
//            }
//        }
//        if (x < grid[row][col + 1]) {
//            dfs(row, col + 1, stepCnt + 1);
//            if (ans == N - 1) {
//                return;
//            }
//        }
//        if (row + 1 < M && x < grid[row + 1][col + 1]) {
//            dfs(row + 1, col + 1, stepCnt + 1);
//            if (ans == N - 1) {
//                return;
//            }
//        }
//    }
//}

public class Main {
}
