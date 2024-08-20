package org.leetcode.p1140;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// dfs(i,m) = sufSum[i] - min(dfs(i+x, max(x,m)))
// https://leetcode.com/problems/stone-game-ii/?envType=daily-question&envId=2024-08-20
class Solution {
    int N;
    Map<Integer, Map<Integer, Integer>> memo;
    int[] sufSum;

    public int stoneGameII(int[] piles) {
        this.N = piles.length;
        this.sufSum = new int[N];
        this.memo = new HashMap<>();
        int sum = 0;
        for (int i = N - 1; i >= 0; i--) {
            sum += piles[i];
            sufSum[i] = sum;
        }
        return dfs(0, 1);
    }

    int dfs(int i, int m) {
        if (i >= N) {
            return 0;
        }
        if (i + 2 * m >= N) { // pick all remaining stones
            return sufSum[i];
        }
        if (memo.containsKey(i) && memo.get(i).containsKey(m)) {
            return memo.get(i).get(m);
        }
        int sum = sufSum[i];
        int next = -1;
        for (int x = 1; x <= 2 * m; x++) {
            int val = dfs(i + x, Math.max(m, x));
            if (next == -1) {
                next = val;
            } else {
                next = Math.min(next, val);
            }
        }
        int res = sum - next;
        memo.computeIfAbsent(i, key -> new HashMap<>()).put(m, res);
        return res;
    }
}

public class Main {
    public static void main(String[] args) {
        test(new int[]{2, 7, 9, 4, 4}, 10);
        test(new int[]{1, 2, 3, 4, 5, 100}, 104);
    }

    private static void test(int[] piles, int expect) {
        Solution solution = new Solution();
        int output = solution.stoneGameII(piles);
        String desc = String.format("stone game II piles=%s", Arrays.toString(piles));
        LeetCodeUtils.test(desc, output, expect);
    }
}
