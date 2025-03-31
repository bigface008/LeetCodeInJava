package org.leetcode.p2588;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://leetcode.cn/problems/count-the-number-of-beautiful-subarrays/?envType=daily-question&envId=2025-03-06
class Solution {
    public long beautifulSubarrays(int[] nums) {
        final int N = nums.length;
        int[] preSum = new int[N + 1];
        for (int i = 0; i < N; i++) {
            preSum[i + 1] = preSum[0] ^ nums[i];
        }
        long ans = 0;
        int preXor = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        cnt.put(0, 1);
        for (int x : nums) {
            preXor ^= x;
            int val = cnt.getOrDefault(preXor, 0);
            ans += val;
            cnt.put(preXor, val + 1);
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
        check(new int[]{4, 3, 1, 2, 4}, 2);
    }

    private static void check(int[] nums, long expect) {
        long actual = new Solution().beautifulSubarrays(nums);
        String desc = String.format("nums=%s", Arrays.toString(nums));
        LeetCodeUtils.test(desc, actual, expect);
    }
}
