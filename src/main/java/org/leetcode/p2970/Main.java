package org.leetcode.p2970;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;

// https://leetcode.com/problems/count-the-number-of-incremovable-subarrays-i/description/
// 这题的解法和这个 https://leetcode.cn/problems/count-the-number-of-incremovable-subarrays-ii/description/ 是完全一致的，
// 只是数据量不一样，但是数据量一大就成hard了。

class Solution {
    public int incremovableSubarrayCount(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int firstAscendIdx = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                firstAscendIdx = i;
            } else {
                break;
            }
        }
        if (firstAscendIdx == nums.length - 1) {
            return (nums.length + 1) * nums.length / 2;
        }
        int i = firstAscendIdx;
        int j = nums.length - 1;
        int ans = i + 2;
        while (j == nums.length - 1 || nums[j] < nums[j + 1]) {
            while (i >= 0 && nums[i] >= nums[j]) {
                i--;
            }
            ans += i + 2;
            j--;
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
        test(new int[]{1, 2, 3, 4}, 10);
        test(new int[]{6, 5, 7, 8}, 7);
        test(new int[]{8, 7, 6, 6}, 3);
    }

    private static void test(int[] nums, int expect) {
        Solution solution = new Solution();
        int output = solution.incremovableSubarrayCount(nums);
        String desc = String.format("incremovable subarray count=%s", Arrays.toString(nums));
        LeetCodeUtils.test(desc, output, expect);
    }
}
