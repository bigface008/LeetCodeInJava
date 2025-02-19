package org.leetcode.p1079;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final int MX = 8;
    static final int[][] comb = new int[MX][MX];

    static {
        for (int i = 0; i < MX; i++) {
            comb[i][0] = comb[i][i] = 1;
            for (int j = 1; j < i; j++) {
                comb[i][j] = comb[i - 1][j - 1] + comb[i - 1][j];
            }
        }
    }

    int[] freqMap = new int[26];
    int[][] dp = new int[26 + 1][];

    public int numTilePossibilities(String tiles) {
        for (int i = 0; i <= 26; i++) {
            dp[i] = new int[tiles.length() + 1];
            Arrays.fill(dp[i], -1);
        }
        for (char c : tiles.toCharArray()) {
            freqMap[c - 'A']++;
        }
        int ans = 0;
        for (int resLen = 1; resLen <= tiles.length(); resLen++) {
            ans += dfs(26, resLen);
        }
        return ans;
    }

    int dfs(int i, int j) {
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int res = 0;
        if (i == 0) {
            if (j == 0) {
                res = 1;
            } else {
                res = 0;
            }
        } else {
            int limit = Math.min(j, freqMap[i - 1]);
            for (int k = 0; k <= limit; k++) {
                res += dfs(i - 1, j - k) * comb[j][k];
            }
        }
        dp[i][j] = res;
        return res;
    }
}

public class Main {
    public static void main(String[] args) {
        test("AAB", 8);
        System.out.println(Arrays.deepToString(Solution.comb));
    }

    private static void test(String tiles, int expect) {
        try {
            int output = new Solution().numTilePossibilities(tiles);
            String desc = String.format("tiles=%s", tiles);
            LeetCodeUtils.test(desc, output, expect);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
