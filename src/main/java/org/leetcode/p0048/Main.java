package org.leetcode.p0048;

// https://leetcode.cn/problems/rotate-image/?envType=company&envId=meituan&favoriteSlug=meituan-all
class Solution {
    public void rotate(int[][] matrix) {
        final int N = matrix.length;
        final int half = N / 2;

        // 3 -> 0 -> 0,1
        // 4 -> 0,1 -> 0,1

        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < (N + 1) / 2; j++) {
                int a = matrix[i][j];
                int b = matrix[j][N - 1 - i];
                int c = matrix[N - 1 - i][N - 1 - j];
                int d = matrix[N - 1 - j][i];
                matrix[j][N - 1 - i] = a;
                matrix[N - 1 - i][N - 1 - j] = b;
                matrix[N - 1 - j][i] = c;
                matrix[i][j] = d;
            }
        }

        // 0,0 -> 0,3
        // 0,1 -> 1,2
        // i,j -> j,N - 1 - i
        // 2,1 -> 1,0
    }
}

public class Main {
}
