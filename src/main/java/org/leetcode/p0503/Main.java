package org.leetcode.p0503;

import org.leetcode.utils.LeetCodeUtils;

import java.util.*;

// https://leetcode.cn/problems/next-greater-element-ii/description
// 这个用单调栈
// https://algo.itcharge.cn/03.Stack/02.Monotone-Stack/01.Monotone-Stack/#_2-%E5%8D%95%E8%B0%83%E6%A0%88%E9%80%82%E7%94%A8%E5%9C%BA%E6%99%AF
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int N = nums.length;
        int[] ans = new int[N];
        Arrays.fill(ans, -1);
        int limit = 2 * N - 1;
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i <= limit; i++) {
            int realIndex = i % N;
            int curr = nums[realIndex];
            while (!stack.isEmpty() && curr > nums[stack.peek()]) {
                ans[stack.pop()] = curr;
            }
            stack.push(realIndex);
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
        test(new int[]{1, 2, 1}, new int[]{2, -1, 2});
        test(new int[]{1, 2, 3, 4, 3}, new int[]{2, 3, 4, -1, 4});
    }

    private static void test(int[] nums, int[] expect) {
        String desc = String.format("nums=%s", Arrays.toString(nums));
        var solution = new Solution();
        int[] output = solution.nextGreaterElements(nums);
        LeetCodeUtils.test(desc, output, expect);
    }
}
