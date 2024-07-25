package org.algo.graph;

import java.util.Arrays;

// https://houbb.github.io/2020/01/23/data-struct-learn-03-graph-floyd
// Floyd算法
// 你可以看这个例题的解来深度理解
// https://leetcode.cn/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/solutions/2525946/dai-ni-fa-ming-floyd-suan-fa-cong-ji-yi-m8s51/
public class Floyd {
    public static int[][] run(int[][] edges, int n) {
        int[][] ans = new int[n][n];
        for (int[] row : ans) {
            Arrays.fill(row, Integer.MAX_VALUE / 2);
        }
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1], w = edge[2];
            ans[x][y] = Math.min(ans[x][y], w);
            ans[y][x] = ans[x][y];
        }
        // 下面才是核心代码，记住这部分就可以了
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    ans[i][j] = Math.min(ans[i][j], ans[i][k] + ans[k][j]);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        {
            int[][] edges = new int[][]{
                    {0, 1, 3},
                    {1, 2, 1},
                    {1, 3, 4},
                    {2, 3, 1}
            };
            int n = 4;
            int[][] ans = run(edges, n);
            printRes(ans);
        }
        System.out.println();
        {
            int[][] edges = new int[][]{
                    {0, 1, 2},
                    {0, 4, 8},
                    {1, 2, 3},
                    {1, 4, 2},
                    {2, 3, 1},
                    {3, 4, 1}
            };
            int n = 5;
            int[][] ans = run(edges, n);
            printRes(ans);
        }
    }

    static void printRes(int[][] ans) {
        for (int i = 0; i < ans.length; i++) {
            for (int j = i + 1; j < ans.length; j++) {
                System.out.println(i + " -> " + j + " : " + ans[i][j]);
            }
        }
    }
}
