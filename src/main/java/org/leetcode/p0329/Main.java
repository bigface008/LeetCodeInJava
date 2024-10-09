package org.leetcode.p0329;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
//class Solution {
//    int M, N;
//    int ans;
//    int[] dxs = {1, 0, -1, 0};
//    int[] dys = {0, 1, 0, -1};
//    int[][] matrix;
//    int[][] memo;
//
//    public int longestIncreasingPath(int[][] matrix) {
//        M = matrix.length;
//        N = matrix[0].length;
//        ans = 0;
//        this.matrix = matrix;
//        memo = new int[M][N];
//        for (int i = 0; i < M; i++) {
//            Arrays.fill(memo[i], -1);
//        }
//        for (int i = 0; i < M; i++) {
//            for (int j = 0; j < N; j++) {
//                dfs(i, j);
//            }
//        }
//        return ans;
//    }
//
//    int dfs(int row, int col) {
//        if (memo[row][col] != -1) {
//            return memo[row][col];
//        }
//        int ans = 1;
//        for (int k = 0; k < 4; k++) {
//            int dx = dxs[k], dy = dys[k];
//            int x = row + dx, y = col + dy;
//            if (0 <= x && x < M && 0 <= y && y < N && matrix[x][y] < matrix[row][col]) {
//                int temp = dfs(x, y);
//                ans = Math.max(ans, temp + 1);
//            }
//        }
//        this.ans = Math.max(this.ans, ans);
//        memo[row][col] = ans;
//        return ans;
//    }
//}


class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        final int M = matrix.length, N = matrix[0].length;
        int[][] inDegrees = new int[M][N];
        int[] dxs = {1, 0, -1, 0};
        int[] dys = {0, 1, 0, -1};
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 4; k++) {
                    int dx = dxs[k], dy = dys[k];
                    int x = i + dx, y = j + dy;
                    if (0 <= x && x < M && 0 <= y && y < N && matrix[i][j] < matrix[x][y]) {
                        inDegrees[i][j]++;
                    }
                }
            }
        }

        Deque<int[]> deque = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (inDegrees[i][j] == 0) {
                    deque.offer(new int[]{i, j});
                }
            }
        }
        int ans = 0;
        while (!deque.isEmpty()) {
            ans++;
            int size = deque.size();
            for (int n = 0; n < size; n++) {
                int[] node = deque.poll();
                int i = node[0], j = node[1];
                for (int k = 0; k < 4; k++) {
                    int dx = dxs[k], dy = dys[k];
                    int x = i + dx, y = j + dy;
                    if (0 <= x && x < M && 0 <= y && y < N && matrix[i][j] > matrix[x][y]) {
                        inDegrees[x][y]--;
                        if (inDegrees[x][y] == 0) {
                            deque.offer(new int[]{x, y});
                        }
                    }
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
