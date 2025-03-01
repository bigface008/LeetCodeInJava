package org.leetcode.p1524;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/number-of-sub-arrays-with-odd-sum/?envType=daily-question&envId=2025-02-25
class Solution {
    static final long MOD = 1000_000_007;

    public int numOfSubarrays(int[] arr) {
        final int N = arr.length;
        int[] preSum = new int[N + 1];
        int oddCnt = 0;
        for (int i = 0; i < N; i++) {
            preSum[i + 1] = preSum[i] + arr[i];
            if (preSum[i + 1] % 2 == 1) {
                oddCnt++;
            }
        }
        int evenCnt = N + 1 - oddCnt;
        long res = ((long) evenCnt) * (long) oddCnt;
        return (int) (res % MOD);
    }
}

public class Main {
    public static void main(String[] args) {
        test(new int[]{1, 2, 3, 4, 5, 6, 7}, 16);
    }

    private static void test(int[] arr, int expect) {
        int output = new Solution().numOfSubarrays(arr);
        String desc = String.format("arr=%s", Arrays.toString(arr));
        LeetCodeUtils.test(desc, output, expect);
    }
}
