package org.leetcode.p0871;

import org.leetcode.utils.LeetCodeUtils;

import java.util.PriorityQueue;

// https://leetcode.cn/problems/minimum-number-of-refueling-stops/?envType=daily-question&envId=2024-10-07
class Solution {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        if (startFuel >= target) {
            return 0;
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> {
            return b - a;
        });
        int ans = 0, prePosition = 0;
        int curFuel = startFuel;
        final int N = stations.length;
        for (int i = 0; i <= N; i++) {
            int fuel = 0, pos = 0;
            if (i == N) {
                pos = target;
            } else {
                pos = stations[i][0];
                fuel = stations[i][1];
            }
            curFuel -= pos - prePosition;
            while (!maxHeap.isEmpty() && curFuel < 0) {
                curFuel += maxHeap.poll();
                ans++;
            }
            if (curFuel < 0) {
                return -1;
            }
            maxHeap.offer(fuel);
            prePosition = pos;
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
//        test(1, 1, "[]", 0);
//        test(100, 1, "[[10,100]]", -1);
        test(100, 10, "[[10,60],[20,30],[30,30],[60,40]]", 2);
    }

    static void test(int target, int startFuel, String stationsStr, int expect) {
        int[][] stations = LeetCodeUtils.make2DIntArray(stationsStr);
        int output = new Solution().minRefuelStops(target, startFuel, stations);
        String desc = String.format("target=%d startFuel=%d stations=%s", target, startFuel, stationsStr);
        LeetCodeUtils.test(desc, output, expect);
    }
}
