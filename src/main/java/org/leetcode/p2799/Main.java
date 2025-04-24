package org.leetcode.p2799;

import org.leetcode.utils.LeetCodeUtils;

import java.util.*;

// https://leetcode.cn/problems/count-complete-subarrays-in-an-array/?envType=daily-question&envId=2025-04-24
class Solution {
    public int countCompleteSubarrays(int[] nums) {
        final int N = nums.length;
        int left = 0;
        Set<Integer> numSet = new HashSet<>();
        for (int x : nums) {
            numSet.add(x);
        }
        final int uniqueCnt = numSet.size();
        Map<Integer, Integer> num2Freq = new HashMap<>();
        int ans = 0;
        for (int right = 0; right < N; right++) {
            int x = nums[right];
            num2Freq.put(x, num2Freq.getOrDefault(x, 0) + 1);
            while (num2Freq.size() >= uniqueCnt) {
                int lv = nums[left];
                num2Freq.put(lv, num2Freq.get(lv) - 1);
                if (num2Freq.get(lv) == 0) {
                    num2Freq.remove(lv);
                }
                left++;
            }
            ans += left;
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
        test(new int[]{1, 3, 1, 2, 2}, 4);
    }

    private static void test(int[] nums, int expect) {
        int output = new Solution().countCompleteSubarrays(nums);
        String fmt = String.format("nums=%s", Arrays.toString(nums));
        LeetCodeUtils.test(fmt, output, expect);
    }
}
