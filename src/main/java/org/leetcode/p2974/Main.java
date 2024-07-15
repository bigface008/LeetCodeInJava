package org.leetcode.p2974;

import org.leetcode.utils.LeetCodeUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

// https://leetcode.cn/problems/minimum-number-game/
class Solution {
    public int[] numberGame(int[] nums) {
        Arrays.sort(nums);
        int[] ans = new int[nums.length];
        int cnt = 0;
        while (cnt != nums.length) {
            int valA = nums[cnt];
            int valB = nums[cnt + 1];
            ans[cnt] = valB;
            ans[cnt + 1] = valA;
            cnt += 2;
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
        test(new int[]{5, 4, 2, 3}, new int[]{3, 2, 5, 4});
        test(new int[]{2, 5}, new int[]{5, 2});
    }

    private static void test(int[] nums, int[] expect) {
        Solution solution = new Solution();
        int[] output = solution.numberGame(nums);
        String desc = String.format("number game nums=%s", Arrays.toString(nums));
        LeetCodeUtils.test(desc, output, expect);
    }
}
