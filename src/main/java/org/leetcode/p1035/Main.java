package org.leetcode.p1035;

import org.leetcode.utils.LeetCodeUtils;

import java.util.*;

// https://leetcode.cn/problems/uncrossed-lines/?envType=daily-question&envId=2024-08-11

// f(i, j)
// if n1[i] == n2[j]:
//   f(i,j) = f(i-1,j-1) + 1
// else:
//   f(i,j) = max(f(i-1,j),f(i,j-1))

// 这道题其实就是最长公共子序列问题 https://leetcode.cn/problems/longest-common-subsequence/description/
class Solution {
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        final int M = nums1.length;
        final int N = nums2.length;
        int[][] dp = new int[M][N];
        boolean hasSameVal = false;
        for (int i = 0; i < M; i++) {
            if (nums1[i] == nums2[0]) {
                hasSameVal = true;
            }
            if (hasSameVal) {
                dp[i][0] = 1;
            }
        }
        hasSameVal = false;
        for (int i = 0; i < N; i++) {
            if (nums1[0] == nums2[i]) {
                hasSameVal = true;
            }
            if (hasSameVal) {
                dp[0][i] = 1;
            }
        }
        for (int i = 1; i < M; i++) {
            for (int j = 1; j < N; j++) {
                if (nums1[i] == nums2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[M - 1][N - 1];
    }
}

public class Main {
    public static void main(String[] args) {
        test(new int[]{1, 4, 2}, new int[]{1, 2, 4}, 2);
        test(new int[]{2, 5, 1, 2, 5}, new int[]{10, 5, 2, 1, 5, 2}, 3);
        test(new int[]{1, 3, 7, 1, 7, 5}, new int[]{1, 9, 2, 5, 1}, 2);
        test(new int[]{3, 2}, new int[]{2, 2, 2, 3}, 1);
    }

    private static void test(int[] nums1, int[] nums2, int expect) {
        Solution solution = new Solution();
        int output = solution.maxUncrossedLines(nums1, nums2);
        String desc = String.format("max uncrossed lines nums1=%s, nums2=%s", Arrays.toString(nums1), Arrays.toString(nums2));
        LeetCodeUtils.test(desc, output, expect);
    }
}
