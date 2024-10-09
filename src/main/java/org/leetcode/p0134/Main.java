package org.leetcode.p0134;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;

// https://leetcode.cn/problems/gas-station/description/?envType=daily-question&envId=2024-10-06
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        final int N = gas.length;
        if (N == 0) {
            return 0;
        }
        int gasSum = Arrays.stream(gas).sum();
        int costSum = Arrays.stream(cost).sum();
        if (gasSum < costSum) {
            return -1;
        }
        int currOil = gas[0];
        int minOil = gas[0];
        int minIdx = 0;
        for (int i = 1; i <= N; i++) {
            currOil -= cost[i - 1];
            if (currOil < minOil) {
                minOil = currOil;
                minIdx = i % N;
            }
            if (i < N) {
                currOil += gas[i];
            }
        }
        return minIdx;
    }
}

public class Main {
    public static void main(String[] args) {
//        test(new int[]{5, 1, 2, 3, 4}, new int[]{4, 4, 1, 5, 1}, 4);
        test(new int[]{3, 1, 1}, new int[]{1, 2, 2}, 0);
    }

    static void test(int[] gas, int[] cost, int expect) {
        int output = new Solution().canCompleteCircuit(gas, cost);
        String desc = String.format("can complete gas=%s cost=%s", Arrays.toString(gas), Arrays.toString(cost));
        LeetCodeUtils.test(desc, output, expect);
    }
}
