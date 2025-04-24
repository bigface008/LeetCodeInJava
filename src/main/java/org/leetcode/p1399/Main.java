package org.leetcode.p1399;

import org.leetcode.utils.LeetCodeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.cn/problems/count-largest-group/?envType=daily-question&envId=2025-04-23
class Solution {
    public int countLargestGroup(int n) {
        Map<Integer, List<Integer>> sum2Nums = new HashMap<>();
        int maxCnt = 0;
        for (int x = 1; x <= n; x++) {
            int sum = 0;
            int val = x;
            while (val > 0) {
                sum += val % 10;
                val /= 10;
            }
            sum2Nums.computeIfAbsent(sum, k -> new ArrayList<>()).add(x);
            maxCnt = Math.max(maxCnt, sum2Nums.get(sum).size());
        }
        int ans = 0;
        for (Map.Entry<Integer, List<Integer>> entry : sum2Nums.entrySet()) {
            if (entry.getValue().size() == maxCnt) {
                ans++;
            }
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
        test(13, 4);
    }

    private static void test(int n, int expect) {
        int output = new Solution().countLargestGroup(n);
        String fmt = String.format("n=%d", n);
        LeetCodeUtils.test(fmt, output, expect);
    }
}
