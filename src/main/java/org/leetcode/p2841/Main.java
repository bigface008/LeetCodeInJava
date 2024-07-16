package org.leetcode.p2841;

import org.leetcode.utils.LeetCodeUtils;

import java.util.*;

// https://leetcode.com/problems/maximum-sum-of-almost-unique-subarray/
class Solution {
    public long maxSum(List<Integer> nums, int m, int k) {
        Map<Integer, Integer> numToCnt = new HashMap<>();
        long ans = 0;
        int uniqueCnt = k;
        long windowSum = 0;
        for (int i = 0; i < k; i++) {
            int num = nums.get(i);
            windowSum += num;
            numToCnt.compute(num, (key, val) -> (val == null) ? 1 : val + 1);
        }
        uniqueCnt = numToCnt.size();
        if (uniqueCnt >= m) {
            ans = windowSum;
        }
        for (int i = k; i < nums.size(); i++) {
            int currNum = nums.get(i);
            int dropNum = nums.get(i - k);
            if (dropNum == currNum) {
                continue;
            }
            int currCnt = numToCnt.getOrDefault(currNum, 0);
            int dropCnt = numToCnt.getOrDefault(dropNum, 0);
            if (dropCnt == 1) {
                uniqueCnt--;
                numToCnt.remove(dropNum);
            } else {
                numToCnt.put(dropNum, dropCnt - 1);
            }
            if (currCnt == 0) {
                uniqueCnt++;
                numToCnt.put(currNum, 1);
            } else {
                numToCnt.put(currNum, currCnt + 1);
            }
            long temp = windowSum + currNum - dropNum;
            if (uniqueCnt >= m) {
                ans = Math.max(ans, temp);
            }
            windowSum = temp;
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
        test(List.of(2, 6, 7, 3, 1, 7), 3, 4, 18);
        test(List.of(5, 9, 9, 2, 4, 5, 4), 1, 3, 23);
        test(List.of(1, 2, 1, 2, 1, 2, 1), 3, 3, 0);
        test(List.of(1), 1, 1, 1);
        test(List.of(1, 2, 3, 3), 2, 3, 8);
        test(List.of(2, 2, 1, 2), 3, 4, 0);
    }

    private static void test(List<Integer> nums, int m, int k, long expect) {
        Solution solution = new Solution();
        long output = solution.maxSum(nums, m, k);
        String desc = String.format("max sum nums=%s m=%d k=%d", nums, m, k);
        LeetCodeUtils.test(desc, output, expect);
    }
}
