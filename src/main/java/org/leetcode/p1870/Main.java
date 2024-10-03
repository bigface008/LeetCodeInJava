package org.leetcode.p1870;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;

// https://leetcode.com/problems/minimum-speed-to-arrive-on-time/description/
class Solution {
    int[] dist;

    public int minSpeedOnTime(int[] dist, double hour) {
        if (dist.length == 0 || dist.length > Math.ceil(hour)) {
            return -1;
        }
        // sum(ceil(d / speed)) <= hour
        this.dist = dist;
        int start = 1, end = (int) Math.pow(10, 7);
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (time(mid) <= hour) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return end;
    }

    double time(int speed) {
        double ans = 0;
        for (int i = 0; i < dist.length; i++) {
            int d = dist[i];
            double t = ((double) d) / ((double) speed);
            if (i == dist.length - 1) {
                ans += t;
                continue;
            }
            double ceil = Math.ceil(t);
            ans += ceil;
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
//        test(new int[]{1, 3, 2}, 6, 1);
        test(new int[]{1, 3, 2}, 2.7, 3);
//        test(new int[]{1, 3, 2}, 1.9, -1);
//        test(new int[]{1, 1, 100000}, 2.01, 10000000);
    }

    private static void test(int[] dist, double hour, int expect) {
        int output = new Solution().minSpeedOnTime(dist, hour);
        String desc = String.format("min speed on time dist=%s hour=%f", Arrays.toString(dist), hour);
        LeetCodeUtils.test(desc, output, expect);
    }
}
