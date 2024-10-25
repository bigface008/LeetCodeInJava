package org.leetcode.p3194;

import java.util.Arrays;

// https://leetcode.cn/problems/minimum-average-of-smallest-and-largest-elements
class Solution {
    public double minimumAverage(int[] nums) {
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1;
        double ans = -1;
        while (left < right) {
            double d = ((double) nums[left] + (double) nums[right]) / 2;
            if (ans == -1) {
                ans = d;
            } else {
                ans = Math.min(ans, d);
            }
            left++;
            right--;
        }
        return ans;
    }
}

public class Main {
}
