package org.leetcode.p0674;

import java.util.Stack;

// https://leetcode.com/problems/longest-continuous-increasing-subsequence/
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        final int N = nums.length;
        int left = 0, right = 1;
        int ans = 1;
        for (; right < N; right++) {
            if (nums[right] > nums[right - 1]) {
                ans = Math.max(ans, right - left + 1);
            } else {
                left = right;
            }
        }
        return ans;
    }
}

public class Main {
}
