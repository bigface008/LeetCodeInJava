package org.leetcode.p1278;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;

// https://leetcode.cn/problems/palindrome-partitioning-iii/?envType=daily-question&envId=2025-03-03
class Solution {
    public int palindromePartition(String s, int k) {
        final int N = s.length();

        int[][] palindromeDiffCnt = new int[N][N];
        for (int l = N - 2; l >= 0; l--) {
            palindromeDiffCnt[l][l] = 0;
            for (int r = l + 1; r < N; r++) {
                if (s.charAt(l) == s.charAt(r)) {
                    palindromeDiffCnt[l][r] = palindromeDiffCnt[l + 1][r - 1];
                } else {
                    palindromeDiffCnt[l][r] = palindromeDiffCnt[l + 1][r - 1] + 1;
                }
            }
        }
        // p[l][r] =
        //   if s[l] == s[r]:
        //     p[l+1][r-1]
        //   else:
        //     p[l+1][r-1] + 1

        int[][] dp = new int[N][k + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 1; j <= Math.min(i + 1, k); j++) {
                if (j == 1) {
                    dp[i][j] = palindromeDiffCnt[0][i];
                    continue;
                }
                int temp = i;
                for (int len = 1; i - len + 1 >= j - 1; len++) {
                    temp = Math.min(temp, dp[i - len][j - 1] + palindromeDiffCnt[i - len + 1][i]);
                }
                dp[i][j] = temp;
            }
        }
        return dp[N - 1][k];
        // dp[i][j] =
        //   min(dp[i-len][j-1] + diff_ch_cnt)
    }
}

public class Main {
    public static void main(String[] args) {
        test("abc", 2, 1);
        test("leetcode", 8, 0);
    }

    private static void test(String s, int k, int expect) {
        int output = new Solution().palindromePartition(s, k);
        String desc = String.format("s=%s, k=%d", s, k);
        LeetCodeUtils.test(desc, output, expect);
    }
}
