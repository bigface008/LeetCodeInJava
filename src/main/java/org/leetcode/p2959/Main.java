package org.leetcode.p2959;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;

// https://leetcode.com/problems/number-of-possible-sets-of-closing-branches
class Solution {
    public int numberOfSets(int n, int maxDistance, int[][] roads) {
        int[][] graph = new int[n][n];
        for (int[] row : graph) {
            Arrays.fill(row, Integer.MAX_VALUE / 2);
        }
        for (int[] edge : roads) {
            int x = edge[0], y = edge[1], w = edge[2];
            graph[x][y] = Math.min(graph[x][y], w);
            graph[y][x] = graph[x][y];
        }

        int ans = 1;
        int[][][] dp = new int[1 << n][n][n];
        for (int[][] matrix : dp) {
            for (int[] row : matrix) {
                Arrays.fill(row, Integer.MAX_VALUE / 2);
            }
        }
        dp[0] = graph; // if no middle point, x -> y
        for (int s = 1; s < (1 << n); s++) {
            int k = Integer.numberOfTrailingZeros(s);
            int t = s ^ (1 << k);
            boolean ok = true;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dp[s][i][j] = Math.min(dp[t][i][j], dp[t][i][k] + dp[t][k][j]);
                    if (ok && j < i && (s >> i & 1) != 0 && (s >> j & 1) != 0 && dp[s][i][j] > maxDistance) {
                        ok = false;
                    }
                }
            }
            ans += ok ? 1 : 0;
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
        test(3, 5, "[[0,1,2],[1,2,10],[0,2,10]]", 5);
        test(3, 5, "[[0,1,20],[0,1,10],[1,2,2],[0,2,2]]", 7);
        test(1, 10, "[]", 2);
    }

    private static void test(int n, int maxDistance, String roadsStr, int expect) {
        int[][] roads = LeetCodeUtils.make2DIntArray(roadsStr);
        Solution solution = new Solution();
        int output = solution.numberOfSets(n, maxDistance, roads);
        String desc = String.format("number of sets n=%d maxDistance=%d roads=%s", n, maxDistance, roadsStr);
        LeetCodeUtils.test(desc, output, expect);
    }
}
