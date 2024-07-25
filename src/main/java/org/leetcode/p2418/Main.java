package org.leetcode.p2418;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;

// https://leetcode.com/problems/sort-the-people/description/?envType=daily-question&envId=2024-07-22
class Solution {
    public String[] sortPeople(String[] names, int[] heights) {
        return new String[]{};
    }
}

public class Main {
    public static void main(String[] args) {
        test(new String[]{"Mary", "John", "Emma"}, new int[]{180, 165, 170}, new String[]{"Mary", "Emma", "John"});
        test(new String[]{"Alice", "Bob", "Bob"}, new int[]{155, 185, 150}, new String[]{"Bob", "Alice", "Bob"});
    }

    private static void test(String[] names, int[] heights, String[] expect) {
        Solution solution = new Solution();
        String[] output = solution.sortPeople(names, heights);
        String desc = String.format("sort the people names=%s, heights=%s", Arrays.toString(names), Arrays.toString(heights));
        LeetCodeUtils.test(desc, output, expect);
    }
}
