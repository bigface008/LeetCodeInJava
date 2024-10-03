package org.leetcode.p2516;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// To start with, we can a counter map to calculate the number of each letter, if any of them is less k, we can direclty return -1
// Next, we can actually use dynamic programming, to solve this problem.

// Let's define a function dfs, which takes 2 indices, left and right, that point to a substr of the given s, and we also
// need the remaining numbers of needed a, b and c.

// https://leetcode.com/problems/take-k-of-each-character-from-left-and-right
class Solution {
    public int takeCharacters(String s, int k) {
        int[] counter = new int[3];
        final int N = s.length();
        for (int i = 0; i < N; i++) {
            counter[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 3; i++) {
            if (counter[i] < k) {
                return -1;
            }
        }

        int ans = Integer.MAX_VALUE;
        int left = 0;
        for (int right = 0; right < N; right++) {
            char ch = s.charAt(right);
            int offset = ch - 'a';
            counter[offset]--;
            while (k > counter[0] || k > counter[1] || k > counter[2]) {
                int lOffset = s.charAt(left) - 'a';
                counter[lOffset]++;
                left++;
            }
            ans = Math.min(ans, N - (right - left + 1));
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
        test("abc", 1, 3);
    }

    private static void test(String s, int k, int expect) {
        int output = new Solution().takeCharacters(s, k);
        String desc = String.format("take ch s=%s k=%d", s, k);
        LeetCodeUtils.test(desc, output, expect);
    }
}
