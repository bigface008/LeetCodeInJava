package org.leetcode.p1791;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int findCenter(int[][] edges) {
        Set<Integer> set = new HashSet<>();
        for (int[] edge : edges) {
            if (set.contains(edge[0])) {
                return edge[0];
            }
            if (set.contains(edge[1])) {
                return edge[1];
            }
            set.add(edge[0]);
            set.add(edge[1]);
        }
        return 0;
    }
}

public class Main {
    public static void main(String[] args) {
        test(new int[][]{new int[]{1, 2}, new int[]{2, 3}, new int[]{4, 2}}, 2);
        test(new int[][]{new int[]{1, 2}, new int[]{5, 1}, new int[]{1, 3}, new int[]{1, 4}}, 1);
    }

    private static void test(int[][] edges, int expect) {
        Solution solution = new Solution();
        int output = solution.findCenter(edges);
        String desc = String.format("find center edges=%s", Arrays.toString(edges));
        LeetCodeUtils.test(desc, output, expect);
    }
}
