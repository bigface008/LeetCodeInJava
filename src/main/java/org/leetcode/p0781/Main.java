package org.leetcode.p0781;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://leetcode.cn/problems/rabbits-in-forest/?envType=daily-question&envId=2025-04-20
class Solution {
    public int numRabbits(int[] answers) {
        Map<Integer, Integer> ans2Cnt = new HashMap<>();
        for (int x : answers) {
            ans2Cnt.put(x, ans2Cnt.getOrDefault(x, 0) + 1);
        }
        int ans = 0;
        // 2 4, 1 4
        for (Map.Entry<Integer, Integer> entry : ans2Cnt.entrySet()) {
            int k = entry.getKey();
            int v = entry.getValue();
            if (k == 0) {
                ans += v;
            } else if (v <= k + 1) {
                ans += k + 1;
            } else {
                ans += v / (k + 1) * (k + 1) + ((v % (k + 1) != 0) ? (k + 1) : 0);
            }
            // if k == 0:
            //   ans += v
            // else:
            //   if v <= k + 1:
            //     ans += k + 1
            //   else:
            //     ans += v / (k + 1) * (k + 1) + k + 1
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
        test(new int[]{0, 1, 0, 2, 0, 1, 0, 2, 1, 1}, 11);
    }

    private static void test(int[] answers, int expect) {
        int output = new Solution().numRabbits(answers);
        String desc = String.format("answers=%s expect=%d", Arrays.toString(answers), expect);
        LeetCodeUtils.test(desc, output, expect);
    }
}
