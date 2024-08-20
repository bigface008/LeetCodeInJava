package org.leetcode.p3130;

import org.leetcode.utils.LeetCodeUtils;

// https://leetcode.cn/problems/find-all-possible-stable-binary-arrays-ii/description/
class Solution {
    static final int MOD = 1_000_000_007;

    public int numberOfStableArrays(int zero, int one, int limit) {
        int[][][] dp = new int[zero + 1][one + 1][2];
        // dp[0][j][?]
        for (int j = 0; j <= one; j++) {
            if (j <= limit) {
                dp[0][j][1] = 1;
            } else {
                dp[0][j][1] = 0;
            }
        }
        // dp[i][0][?]
        for (int i = 0; i <= zero; i++) {
            if (i <= limit) {
                dp[i][0][0] = 1;
            } else {
                dp[i][0][0] = 0;
            }
        }

        for (int i = 1; i <= zero; i++) {
            for (int j = 1; j <= one; j++) {
                if (i > limit) {
                    dp[i][j][0] = (int)(((long)dp[i - 1][j][1] + dp[i - 1][j][0] + MOD - dp[i - 1 - limit][j][1]) % MOD);
                } else {
                    dp[i][j][0] = (int)((long)dp[i - 1][j][1] + dp[i - 1][j][0]) % MOD;
                }
                if (j > limit) {
                    dp[i][j][1] = (int)(((long)dp[i][j - 1][0] + dp[i][j - 1][1] + MOD - dp[i][j - 1 - limit][0]) % MOD);
                } else {
                    dp[i][j][1] = (int)((long)dp[i][j - 1][0] + dp[i][j - 1][1]) % MOD;
                }
            }
        }
        return (dp[zero][one][0] + dp[zero][one][1]) % MOD;
    }
}

public class Main {
    public static void main(String[] args) {
        test(1, 1, 2, 2);
        test(1, 2, 1, 1);
        test(3, 3, 2, 14);
    }

    private static void test(int zero, int one, int limit, int expect) {
        Solution solution = new Solution();
        int ans = solution.numberOfStableArrays(zero, one, limit);
        String desc = String.format("number of stable arrays zero=%d, one=%d, limit=%d", zero, one, limit);
        LeetCodeUtils.test(desc, ans, expect);
    }
}
