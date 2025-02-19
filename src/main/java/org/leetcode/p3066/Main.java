package org.leetcode.p3066;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;
import java.util.PriorityQueue;

// https://leetcode.com/problems/minimum-operations-to-exceed-threshold-value-ii/?envType=daily-question&envId=2025-02-13
class Solution {
    public int minOperations(int[] nums, int k) {
        PriorityQueue<Long> priorityQueue = new PriorityQueue<>();
        for (int x : nums) {
            priorityQueue.add((long) x);
        }
        int ans = 0;
        while (priorityQueue.size() >= 2 && priorityQueue.peek() < k) {
            long x = priorityQueue.poll();
            long y = priorityQueue.poll();
            priorityQueue.add(Math.min(x, y) * 2 + Math.max(x, y));
            ans++;
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
        test(new int[]{2, 11, 10, 1, 3}, 10, 2);
    }

    private static void test(int[] nums, int k, int expect) {
        int output = new Solution().minOperations(nums, k);
        String desc = String.format("nums=%s, k=%d", Arrays.toString(nums), k);
        LeetCodeUtils.test(desc, output, expect);
    }
}
