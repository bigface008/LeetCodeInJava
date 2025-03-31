package org.leetcode.p2965;

import org.leetcode.utils.LeetCodeUtils;

// https://leetcode.com/problems/find-missing-and-repeated-values/?envType=daily-question&envId=2025-03-06
class Solution {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        final int N = grid.length;
        int[][] temp = new int[N][N];
        int a = 0, b = 0;
        for (int[] row : grid) {
            for (int x : row) {
                x--;
                int r = x / N;
                int c = x % N;
                temp[r][c]++;
                if (temp[r][c] == 2) {
                    a = x + 1;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (temp[i][j] == 0) {
                    b = N * i + j + 1;
                    break;
                }
            }
        }
        return new int[]{a, b};
    }
}

public class Main {
    public static void main(String[] args) {
        check("[[1,3],[2,2]]", new int[]{2, 4});
    }

    private static void check(String gridStr, int[] expect) {
        int[] actual = new Solution().findMissingAndRepeatedValues(LeetCodeUtils.make2DIntArray(gridStr));
        String desc = String.format("grid=%s", gridStr);
        LeetCodeUtils.test(desc, actual, expect);
    }
}
