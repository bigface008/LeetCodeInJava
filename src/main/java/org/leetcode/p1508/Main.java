package org.leetcode.p1508;

import org.leetcode.utils.LeetCodeUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// https://leetcode.com/problems/range-sum-of-sorted-subarray-sums/description/?envType=daily-question&envId=2024-08-04
class Solution {
    public int rangeSum(int[] nums, int n, int left, int right) {
        final int N = nums.length;
        final int M = N * (N + 1) / 2;
        final long MOD = (long) (Math.pow(10, 9)) + 7;
        int[] sums = new int[M];
        int[] preSum = new int[N + 1];
        for (int i = 0; i < N; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        int pos = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                sums[pos] = preSum[j + 1] - preSum[i];
                pos++;
            }
        }
        Arrays.sort(sums);
        long ans = 0;
        for (int i = left - 1; i <= right - 1 && i < M; i++) {
            ans += sums[i] % MOD;
            ans %= MOD;
        }
        return (int) ans;
    }
}

public class Main {
    public static void main(String[] args) {
        test(new int[]{1, 2, 3, 4}, 4, 1, 5, 13);
        test(new int[]{1, 2, 3, 4}, 4, 3, 4, 6);
        test(new int[]{1, 2, 3, 4}, 4, 1, 10, 50);
    }

    private static void test(int[] nums, int n, int left, int right, int expect) {
        Solution solution = new Solution();
        int ans = solution.rangeSum(nums, n, left, right);
        String desc = String.format("range sum nums=%s, n=%d, left=%d, right=%d", Arrays.toString(nums), n, left, right);
        LeetCodeUtils.test(desc, ans, expect);
    }
}
