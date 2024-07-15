package org.leetcode.p0807;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;

// https://leetcode.cn/problems/max-increase-to-keep-city-skyline/description/
class Solution {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        final int N = grid.length;
        int[] rowMax = new int[N];
        for (int i = 0; i < N; i++) {
            rowMax[i] = Arrays.stream(grid[i]).max().getAsInt();
        }
        int[] colMax = new int[N];
        for (int c = 0; c < N; c++) {
            int maxVal = Integer.MIN_VALUE / 2;
            for (int r = 0; r < N; r++) {
                maxVal = Math.max(maxVal, grid[r][c]);
            }
            colMax[c] = maxVal;
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int maxVal = Math.min(rowMax[i], colMax[j]);
                ans += maxVal - grid[i][j];
            }
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
        test("[[3,0,8,4],[2,4,5,7],[9,2,6,3],[0,3,1,0]]", 35);
        test("[[0,0,0],[0,0,0],[0,0,0]]", 0);
    }

    private static void test(String gridStr, int expect) {
        int[][] grid = LeetCodeUtils.make2DIntArray(gridStr);
        Solution solution = new Solution();
        int output = solution.maxIncreaseKeepingSkyline(grid);
        String desc = String.format("max increase keeping skyline grid=%s", gridStr);
        LeetCodeUtils.test(desc, output, expect);
    }
}
