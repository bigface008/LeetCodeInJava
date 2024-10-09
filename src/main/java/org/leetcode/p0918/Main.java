package org.leetcode.p0918;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;

// https://leetcode.com/problems/maximum-sum-circular-subarray/?envType=study-plan-v2&envId=top-interview-150
class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        final int N = nums.length;
        int maxDp = nums[0];
        int minDp = nums[0];
        int ans = nums[0];
        int minSum = 0;
        int sum = nums[0];
        for (int i = 1; i < N; i++) {
            if (maxDp > 0) {
                maxDp = maxDp + nums[i];
            } else {
                maxDp = nums[i];
            }
            if (minDp > 0) {
                minDp = nums[i];
            } else {
                minDp = minDp + nums[i];
            }
            ans = Math.max(ans, maxDp);
            minSum = Math.min(minSum, minDp);
            sum += nums[i];
        }
        if (minSum != sum) {
            ans = Math.max(ans, sum - minSum);
        }
        return ans;
    }
}

//class Solution {
//    public int maxSubarraySumCircular(int[] nums) {
//        final int N = nums.length;
//        int[] maxDp = new int[N];
//        maxDp[0] = nums[0];
//        int[] minDp = new int[N];
//        minDp[0] = nums[0];
//        int ans = nums[0];
//        int minSum = 0;
//        int sum = nums[0];
//        for (int i = 1; i < N; i++) {
//            int prevMax = maxDp[i - 1];
//            int prevMin = minDp[i - 1];
//            if (prevMax > 0) {
//                maxDp[i] = prevMax + nums[i];
//            } else {
//                maxDp[i] = nums[i];
//            }
//            if (prevMin > 0) {
//                minDp[i] = nums[i];
//            } else {
//                minDp[i] = prevMin + nums[i];
//            }
//            ans = Math.max(ans, maxDp[i]);
//            minSum = Math.min(minSum, minDp[i]);
//            sum += nums[i];
//        }
//        if (minSum != sum) {
//            ans = Math.max(ans, sum - minSum);
//        }
//        return ans;
//    }
//}

public class Main {
    public static void main(String[] args) {
//        test(new int[]{5, -3, 5}, 10);
//        test(new int[]{-2}, -2);
//        test(new int[]{9, -4, -7, 9}, 18);
        test(new int[]{-3, -2, -3}, -2);
    }

    static void test(int[] nums, int expect) {
        int output = new Solution().maxSubarraySumCircular(nums);
        String desc = String.format("max sum nums=%s", Arrays.toString(nums));
        LeetCodeUtils.test(desc, output, expect);
    }
}
