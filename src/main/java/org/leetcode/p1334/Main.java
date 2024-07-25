package org.leetcode.p1334;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;

// https://leetcode.cn/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/
class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] dp = new int[n][n];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE / 2);
        }
        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
        }
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1], w = edge[2];
            dp[x][y] = Math.min(dp[x][y], w);
            dp[y][x] = dp[x][y];
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }
        int ans = -1;
        int minCnt = Integer.MAX_VALUE / 2;
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (j != i && dp[i][j] <= distanceThreshold) {
                    cnt++;
                }
            }
            if (minCnt >= cnt) {
                ans = i;
                minCnt = cnt;
            }
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
        test(4, "[[0,1,3],[1,2,1],[1,3,4],[2,3,1]]", 4, 3);
        test(5, "[[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]]", 2, 0);
    }

    private static void test(int n, String edgesStr, int distanceThreshold, int expect) {
        Solution solution = new Solution();
        int[][] edges = LeetCodeUtils.make2DIntArray(edgesStr);
        int output = solution.findTheCity(n, edges, distanceThreshold);
        String desc = String.format("find the city n=%d, edges=%s, distanceThreshold=%d", n, edgesStr, distanceThreshold);
        LeetCodeUtils.test(desc, output, expect);
    }
}
