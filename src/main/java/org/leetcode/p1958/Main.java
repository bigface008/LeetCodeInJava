package org.leetcode.p1958;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;

class Solution {
    public boolean checkMove(char[][] board, int rMove, int cMove, char color) {

    }
}

public class Main {
    public static void main(String[] args) {
        test();
    }

    private static void test(char[][] board, int rMove, int cMove, char color, boolean expect) {
        Solution solution = new Solution();
        boolean output = solution.checkMove(board, rMove, cMove, color);
        String desc = String.format("check move board=%s rMove=%s cMove=%s color=%s", Arrays.toString(board), rMove, cMove, color);
        LeetCodeUtils.test(desc, output, expect);
    }
}
