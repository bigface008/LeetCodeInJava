package org.leetcode.p1518;

import org.leetcode.utils.LeetCodeUtils;

// https://leetcode.com/problems/water-bottles/?envType=daily-question&envId=2024-07-07
class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        int ans = 0;
        int cnt = numBottles;
        int fullCnt = numBottles;
        do {
            ans += fullCnt;
            fullCnt = cnt / numExchange;
            cnt = fullCnt + cnt % numExchange;
        } while (fullCnt > 0);
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
        test(9, 3, 13);
        test(15, 4, 19);
    }

    private static void test(int numBottles, int numExchange, int expect) {
        Solution solution = new Solution();
        int output = solution.numWaterBottles(numBottles, numExchange);
        String desc = String.format("numWaterBottles(%s, %s) = %s, expect %s", numBottles, numExchange, output, expect);
        LeetCodeUtils.test(desc, output, expect);
    }
}
