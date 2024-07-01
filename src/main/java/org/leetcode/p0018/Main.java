package org.leetcode.p0018;

import org.leetcode.utils.LeetCodeUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/4sum/description/
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        return new ArrayList<>();
    }
}

public class Main {
    public static void main(String[] args) {
        test(new int[]{1, 0, -1, 0, -2, 2}, 0, "[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]");
        test(new int[]{2, 2, 2, 2, 2}, 8, "[[2,2,2,2]]");
    }

    private static void test(int[] nums, int target, String expect) {
        String desc = String.format("nums=%s target=%d", Arrays.toString(nums), target);
        Solution sol = new Solution();
        var output = sol.fourSum(nums, target);
        LeetCodeUtils.test(desc, output, LeetCodeUtils.make2DIntList(expect));
    }
}
