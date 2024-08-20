package org.leetcode.p0840;

import org.leetcode.utils.LeetCodeUtils;

// https://leetcode.com/problems/magic-squares-in-grid/?envType=daily-question&envId=2024-08-09
class Solution {
    public int numMagicSquaresInside(int[][] grid) {
        final int R = grid.length;
        final int C = grid[0].length;
        if (R < 3 || C < 3) {
            return 0;
        }
        int ans = 0;
        int[][] dp = new int[R - 2][C - 2];
        if (checkMagic(grid, 0, 0)) {
            ans = 1;
        };
        return ans;
    }

    boolean checkMagic(int[][] grid, int rBase, int cBase) {
        final int R = grid.length;
        final int C = grid[0].length;
        int prevSum = -1;
        for (int i = 0; i < 3; i++) {
            int sum = 0;
            for (int j = 0; j < 3; j++) {
                sum += grid[rBase + i][cBase + j];
            }
            if (prevSum == -1) {
                prevSum = sum;
            } else {
                if (prevSum != sum) {
                    return false;
                }
                prevSum = sum;
            }
        }
        for (int i = 0; i < 3; i++) {
            int sum = 0;
            for (int j = 0; j < 3; j++) {
                sum += grid[rBase + j][cBase + i];
            }
            if (prevSum != sum) {
                return false;
            }
            prevSum = sum;
        }
        return true;
    }
}

public class Main {
    public static void main(String[] args) {
        test("[[4,3,8,4],[9,5,1,9],[2,7,6,2]]", 1);
        test("[[8]]", 0);
    }

    private static void test(String gridStr, int expect) {
        int[][] grid = LeetCodeUtils.make2DIntArray(gridStr);
        Solution solution = new Solution();
        int output = solution.numMagicSquaresInside(grid);
        String desc = String.format("num magic squares grid=%s", gridStr);
        LeetCodeUtils.test(desc, output, expect);
    }
}
