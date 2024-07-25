package org.leetcode.p1605;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;

// https://leetcode.com/problems/find-valid-matrix-given-row-and-column-sums/description/?envType=daily-question&envId=2024-07-20
class Solution {
    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        final int M = rowSum.length;
        final int N = colSum.length;
        int[][] ans = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                ans[i][j] = Math.min(rowSum[i], colSum[j]);
                rowSum[i] -= ans[i][j];
                colSum[j] -= ans[i][j];
            }
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
        test(new int[]{3, 8}, new int[]{4, 7}, "[[3,0],[1,7]]");
        test(new int[]{5, 7, 10}, new int[]{8, 6, 8}, "[[0,5,0],[6,1,0],[2,0,8]]");
    }

    private static void test(int[] rowSum, int[] colSum, String expectedStr) {
        var solution = new Solution();
        var output = solution.restoreMatrix(rowSum, colSum);
        var expect = LeetCodeUtils.make2DIntArray(expectedStr);
        var desc = String.format("restore matrix from rowSum=%s, colSum=%s", Arrays.toString(rowSum), Arrays.toString(colSum));
        LeetCodeUtils.test(desc, output, expect);
    }
}
