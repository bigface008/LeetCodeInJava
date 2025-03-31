package org.leetcode.p1745;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;

// https://leetcode.cn/problems/palindrome-partitioning-iv/?envType=daily-question&envId=2025-03-04
class Solution {
    public boolean checkPartitioning(String s) {
        final int N = s.length();
        boolean[][] isParlindrome = new boolean[N][N];
        for (boolean[] row : isParlindrome) {
            Arrays.fill(row, true);
        }
        for (int l = N - 2; l >= 0; l--) {
            isParlindrome[l][l] = true;
            for (int r = l + 1; r < N; r++) {
                isParlindrome[l][r] = s.charAt(l) == s.charAt(r) && isParlindrome[l + 1][r - 1];
            }
        }
        for (int i = 0; i < N - 2; i++) {
            if (!isParlindrome[0][i]) {
                continue;
            }
            for (int j = i + 1; j < N - 1; j++) {
                if (isParlindrome[i + 1][j] && isParlindrome[j + 1][N - 1]) {
                    return true;
                }
            }
        }
        return false;
    }
}

public class Main {
    public static void main(String[] args) {
        check("bcbddxy", false);
    }

    private static void check(String s, boolean expect) {
        boolean actual = new Solution().checkPartitioning(s);
        String desc = String.format("s=%s", s);
        LeetCodeUtils.test(desc, actual, expect);
    }
}
