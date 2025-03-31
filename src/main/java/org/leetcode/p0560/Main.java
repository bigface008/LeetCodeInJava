package org.leetcode.p0560;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.cn/problems/subarray-sum-equals-k/
class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        cnt.put(0, 1);
        int preSum = 0;
        int ans = 0;
        for (int x : nums) {
            preSum += x;
            ans += cnt.getOrDefault(preSum - k, 0);
            cnt.put(preSum, cnt.getOrDefault(preSum, 0) + 1);
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {

    }
}
