package org.leetcode.p1701;

import org.leetcode.utils.LeetCodeUtils;

import java.util.LinkedList;

// https://leetcode.com/problems/average-waiting-time/description/?envType=daily-question&envId=2024-07-09
class Solution {
    public double averageWaitingTime(int[][] customers) {
        double progress = 0;
        double waitTimeSum = 0;
        for (int i = 0; i < customers.length; i++) {
            int arrival = customers[i][0];
            int time = customers[i][1];
            if (progress < arrival) {
                waitTimeSum += time;
                progress = arrival + time;
            } else {
                waitTimeSum += progress - arrival + time;
                progress += time;
            }
        }
        return waitTimeSum / customers.length;
    }
}

public class Main {
    public static void main(String[] args) {
        test("[[1,2],[2,5],[4,3]]", 5.0);
        test("[[5,2],[5,4],[10,3],[20,1]]", 3.25);
    }

    private static void test(String customersStr, double expect) {
        int[][] customers = LeetCodeUtils.make2DIntArray(customersStr);
        Solution solution = new Solution();
        double output = solution.averageWaitingTime(customers);
        String desc = String.format("average waiting time customers=%s", customersStr);
        LeetCodeUtils.test(customersStr, output, expect);
    }
}
