package org.leetcode.p1706;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;

class Solution {
    int M;
    int N;
    int[][] dp;
    int[][] grid;

    public int[] findBall(int[][] grid) {
        M = grid.length;
        N = grid[0].length;
        dp = new int[M + 1][];
        this.grid = grid;
        for (int r = 0; r <= M; r++) {
            dp[r] = new int[N];
            Arrays.fill(dp[r], -2);
        }

        int[] ans = new int[N];
        for (int ball = 0; ball < N; ball++) {
            ans[ball] = dfs(0, ball);
        }
        return ans;
    }

    int dfs(int row, int col) {
        if (row == M) {
            dp[row][col] = col;
            return col;
        }
        if (dp[row][col] != -2) {
            return dp[row][col];
        }
        if (grid[row][col] == -1) {
            if (col == 0 || grid[row][col - 1] == 1) {
                dp[row][col] = -1;
                return -1;
            }
        } else if (grid[row][col] == 1) {
            if (col == N - 1 || grid[row][col + 1] == -1) {
                dp[row][col] = -1;
                return -1;
            }
        }
        int res = 0;
        if (grid[row][col] == 1) {
            res = dfs(row + 1, col + 1);
        } else {
            res = dfs(row + 1, col - 1);
        }
        dp[row][col] = res;
        return res;
    }
}

public class Main {
    public static void main(String[] args) {
        test("[[1,1,1,-1,-1],[1,1,1,-1,-1],[-1,-1,-1,1,1],[1,1,1,1,-1],[-1,-1,-1,-1,-1]]", new int[]{1, -1, -1, -1, -1});
    }

    private static void test(String gridStr, int[] expect) {
        int[][] grid = LeetCodeUtils.make2DIntArray(gridStr);
        int[] output = new Solution().findBall(grid);
        String desc = String.format("grid=%s", gridStr);
        LeetCodeUtils.test(desc, output, expect);
    }
}
