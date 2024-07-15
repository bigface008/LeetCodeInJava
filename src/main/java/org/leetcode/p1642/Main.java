package org.leetcode.p1642;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;

// https://leetcode.com/problems/furthest-building-you-can-reach/description/
class Solution {
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        return 0;
    }
}

public class Main {
    public static void main(String[] args) {
        test(new int[]{4, 2, 7, 6, 9, 14, 12}, 5, 1, 4);
        test(new int[]{4, 12, 2, 7, 3, 18, 20, 3, 19}, 10, 2, 7);
        test(new int[]{14, 3, 19, 3}, 17, 0, 3);
    }

    private static void test(int[] heights, int bricks, int ladders, int expect) {
        Solution solution = new Solution();
        int output = solution.furthestBuilding(heights, bricks, ladders);
        String desc = String.format("furthest building heights=%s, bricks=%d, ladders=%d", Arrays.toString(heights), bricks, ladders);
        LeetCodeUtils.test(desc, output, expect);
    }
}
