package org.leetcode.p0013;

import org.leetcode.utils.LeetCodeUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        return new ArrayList<>();
    }
}

public class Main {
    public static void main(String[] args) {

    }

    private static void test(int[] nums, String expectStr) {
        Solution solution = new Solution();
        List<List<Integer>> output = solution.threeSum(nums);
        String desc = String.format("3sum nums=%s", Arrays.toString(nums));
        LeetCodeUtils.test(desc, output, LeetCodeUtils.make2DIntList(expectStr));
    }
}
