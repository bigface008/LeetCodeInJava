package org.leetcode.p3154;

import org.leetcode.utils.LeetCodeUtils;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

// dfs(i, j, true) = dfs(i+1, j, false) + dfs(i - 2**(j - 1), j - 1, true)
// dfs(i, j, false) = dfs(i - 2**(j - 1), j - 1, true)



// https://leetcode.cn/problems/find-number-of-ways-to-reach-the-k-th-stair/
class Solution {
    int ans = 0;
    int k = 0;
    Map<Long, Integer> memo;

    public int waysToReachStair(int k) {
        this.ans = 0;
        this.k = k;
        this.memo = new HashMap<>();
        return dfs(1, 0, 0);
    }

    int dfs(int i, int j, int preDown) {
        if (i > k + 1) {
            return 0;
        }
        long mask = ((long) i << 32) | ((long) j << 1) | preDown;
        if (memo.containsKey(mask)) {
            return memo.get(mask);
        }
        int res = i == k ? 1 : 0;
        res += dfs(i + (1 << j), j + 1, 0);
        if (preDown == 0 && i > 0) {
            res += dfs(i - 1, j, 1);
        }
        memo.put(mask, res);
        return res;
    }
}

public class Main {
    public static void main(String[] args) {
        test(0, 2);
        test(1, 4);
    }

    private static void test(int k, int expect) {
        Solution solution = new Solution();
        int output = solution.waysToReachStair(k);
        String desc = String.format("ways to reach stair k=%d", k);
        LeetCodeUtils.test(desc, output, expect);
    }
}
