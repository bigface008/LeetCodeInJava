package org.leetcode.p1823;

import org.leetcode.utils.LeetCodeUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/find-the-winner-of-the-circular-game/description
class Solution {
    public int findTheWinner(int n, int k) {
        List<Integer> members = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            members.add(i);
        }
        int startIdx = 0;
        while (members.size() > 1) {
            int failIdx = (startIdx + k) % members.size() - 1;
            if (failIdx == -1) {
                failIdx = members.size() - 1;
                startIdx = 0;
            } else {
                startIdx = failIdx;
            }
            members.remove(failIdx);
        }
        return members.get(0) + 1;
    }
}

public class Main {
    public static void main(String[] args) {
        test(5, 2, 3);
        test(6, 5, 1);
    }

    private static void test(int n, int k, int expect) {
        Solution solution = new Solution();
        int output = solution.findTheWinner(n, k);
        String desc = String.format("findTheWinner n=%s k=%s", n, k);
        LeetCodeUtils.test(desc, output, expect);
    }
}
