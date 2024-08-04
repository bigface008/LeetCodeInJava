package org.leetcode.p2134;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;

// https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together-ii/?envType=daily-question&envId=2024-08-02
class Solution {
    public int minSwaps(int[] nums) {
        int ans = 0;
        int[] buffer = new int[nums.length * 2];
        int oneCnt = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num == 1) {
                oneCnt++;
            }
            buffer[i] = num;
            buffer[nums.length + i] = num;
        }
        int currWindowCnt = 0;
        for (int i = 0; i < oneCnt; i++) {
            if (buffer[i] == 1) {
                currWindowCnt++;
            }
            if (currWindowCnt == oneCnt) {
                return 0;
            }
        }
        int maxWindowCnt = 0;
        for (int i = oneCnt; i < nums.length + oneCnt - 1; i++) {
            int curr = buffer[i];
            int drop = buffer[i - oneCnt];
            if (curr == 1) {
                if (drop == 0) {
                    currWindowCnt++;
                }
            } else {
                if (drop == 1) {
                    currWindowCnt--;
                }
            }
            maxWindowCnt = Math.max(currWindowCnt, maxWindowCnt);
        }
        return oneCnt - maxWindowCnt;
    }
}

public class Main {
    public static void main(String[] args) {
        test(new int[]{0, 1, 0, 1, 1, 0, 0}, 1);
        test(new int[]{0, 1, 1, 1, 0, 0, 1, 1, 0}, 2);
        test(new int[]{1, 1, 0, 0, 1}, 0);
    }

    private static void test(int[] nums, int expect) {
        Solution solution = new Solution();
        int output = solution.minSwaps(nums);
        String desc = String.format("minimum swaps nums=%s", Arrays.toString(nums));
        LeetCodeUtils.test(desc, output, expect);
    }
}
