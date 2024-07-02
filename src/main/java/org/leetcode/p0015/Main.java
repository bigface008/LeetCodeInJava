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
        final int N = nums.length;
        for (int first = 0; first < N; first++) {
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            int third = N - 1;
            int target = -nums[first];
            for (int second = first + 1; second < N; ++second) {
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }
}

// dp
class SolutionV2 {
//    public List<List<Integer>> threeSum(int[] nums) {
//        Arrays.sort(nums);
//        List<Integer> numList = new ArrayList<>(nums.length);
//        for (int i = 0; i < nums.length; i++) {
//            numList.set(i, nums[i]);
//        }
//        HashMap<Integer, List<List<Integer>>> memo = new HashMap<>();
//        List<List<Integer>> ans = new ArrayList<>();
//        return ans;
//    }
//
//    private static List<List<Integer>> rec(List<Integer> nums, int target, int cnt, HashMap<Integer, List<List<Integer>>> memo) {
//        if (cnt == 3) {
//            if (target == 0) {
//                return new ArrayList<>();
//            } else {
//                return null;
//            }
//        }
//
//        if (memo.containsKey(target)) {
//
//        }
//
//        cnt++;
//        for (int i = 0; i < nums.size(); i++) {
//            int num = nums.get(i);
//            int remain = target - num;
//            List<List<Integer>> temp = rec();
//        }
//    }
}

public class Main {
    public static void main(String[] args) {
        test(new int[]{-1, 0, 1, 2, -1, -4}, "[[-1,-1,2],[-1,0,1]]");
        test(new int[]{0, 1, 1}, "[]");
        test(new int[]{0, 0, 0}, "[[0,0,0]]");
    }

    private static void test(int[] nums, String expectStr) {
        Solution solution = new Solution();
        List<List<Integer>> expect = LeetCodeUtils.make2DIntList(expectStr);
        List<List<Integer>> output = solution.threeSum(nums);
        String desc = String.format("three sum nums=%s", nums);
        LeetCodeUtils.test(desc, output, expect);
    }
}
