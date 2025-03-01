package org.leetcode.p0132;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;

// https://leetcode.cn/problems/palindrome-partitioning-ii/?envType=daily-question&envId=2025-03-02
class Solution {
    public int minCut(String s) {
        final int N = s.length();
        int[] dp = new int[N];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        boolean[][] isParlindrome = new boolean[N][N];
        for (boolean[] row : isParlindrome) {
            Arrays.fill(row, true);
        }
        for (int l = N - 2; l >= 0; l--) {
            for (int r = l + 1; r < N; r++) {
                isParlindrome[l][r] = s.charAt(l) == s.charAt(r) && isParlindrome[l + 1][r - 1];
            }
        }

        for (int i = 1; i < N; i++) {
            for (int j = i; j >= 0; j--) {
                if (j != 0 && dp[j - 1] == Integer.MAX_VALUE) {
                    continue;
                }
                if (isParlindrome[j][i] && (j == 0 || dp[j - 1] != Integer.MAX_VALUE)) {
                    dp[i] = Math.min(dp[i], (j == 0 ? 0 : dp[j - 1] + 1));
                }
            }
        }
        return dp[N - 1];
    }
}

public class Main {
    public static void main(String[] args) {
        check("aab", 1);
    }

    private static void check(String s, int expect) {
        int output = new Solution().minCut(s);
        String desc = String.format("s=%s", s);
        LeetCodeUtils.test(desc, output, expect);
    }
}
