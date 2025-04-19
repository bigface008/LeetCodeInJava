package org.leetcode.p2873;

// https://leetcode.cn/problems/maximum-value-of-an-ordered-triplet-i/?envType=daily-question&envId=2025-04-02
class Solution {
    public long maximumTripletValue(int[] nums) {
        final int N = nums.length;
        long ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    ans = Math.max(ans, ((long) nums[i] - (long) nums[j]) * (long) nums[k]);
                }
            }
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {

    }
}
