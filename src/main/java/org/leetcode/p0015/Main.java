package org.leetcode.p0015;

import org.leetcode.utils.LeetCodeUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

// https://leetcode.com/problems/3sum/description/
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            int target = -nums[i];
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                int sum = nums[j] + nums[k];
                if (sum == target) {
                    ans.add(List.of(nums[i], nums[j], nums[k]));
                    do {
                        j++;
                    } while (j < k && nums[j] == nums[j - 1]);
                    do {
                        k--;
                    } while (j < k && nums[k] == nums[k + 1]);
                } else if (sum > target) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
//        test(new int[]{-1, 0, 1, 2, -1, -4}, "[[-1,-1,2],[-1,0,1]]");
//        test(new int[]{0, 1, 1}, "[]");
//        test(new int[]{0, 0, 0}, "[[0,0,0]]");
        test(new int[]{1, -1, -1, 0}, "[[-1,0,1]]");
    }

    private static void test(int[] nums, String expectStr) {
        Solution solution = new Solution();
        List<List<Integer>> expect = LeetCodeUtils.make2DIntList(expectStr);
        List<List<Integer>> output = solution.threeSum(nums);
        String desc = String.format("three sum nums=%s", nums);
        LeetCodeUtils.test(desc, output, expect);
    }
}
