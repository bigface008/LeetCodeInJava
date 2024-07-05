package org.leetcode.p3033;

// https://leetcode.cn/problems/modify-the-matrix/?envType=daily-question&envId=2024-07-05
class Solution {
    public int[][] modifiedMatrix(int[][] matrix) {
        final int M = matrix.length;
        if (M == 0) {
            return new int[][]{};
        }
        final int N = matrix[0].length;
        int[][] ans = new int[M][N];
        for (int c = 0; c < N; c++) {
            int colMaxVal = Integer.MIN_VALUE / 2;
            for (int r = 0; r < M; r++) {
                colMaxVal = Math.max(matrix[r][c], colMaxVal);
            }
            for (int r = 0; r < M; r++) {
                if (matrix[r][c] == -1) {
                    ans[r][c] = colMaxVal;
                } else {
                    ans[r][c] = matrix[r][c];
                }
            }
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {

    }


}
