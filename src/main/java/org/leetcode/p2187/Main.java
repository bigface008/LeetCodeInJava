package org.leetcode.p2187;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;

// https://leetcode.cn/problems/minimum-time-to-complete-trips/description/?envType=daily-question&envId=2024-10-05
class Solution {
    public long minimumTime(int[] time, int totalTrips) {
        if (time.length == 0) {
            return 0;
        }
        int minTimeCost = Arrays.stream(time).min().getAsInt();
        long start = 1, end = (long) totalTrips * minTimeCost;
        long ans = 0;
        while (start < end) {
            long mid = start + (end - start) / 2;
            if (check(mid, time, totalTrips)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return end;
    }

    boolean check(long mid, int[] time, int totalTrips) {
        int tripsCnt = 0;
        for (int i = 0; i < time.length; i++) {
            int t = time[i];
            tripsCnt += (int) (mid / (long) t);
            if (tripsCnt >= totalTrips) {
                return true;
            }
        }
        return false;
    }
}

public class Main {
    public static void main(String[] args) {
//        test(new int[]{1, 2, 3}, 5, 3);
//        test(new int[]{2}, 1, 2);
        test(new int[]{88690, 69213}, 90165, 3505206180L);
    }

    static void test(int[] time, int totaltrips, long expect) {
        long output = new Solution().minimumTime(time, totaltrips);
        String desc = String.format("min time time=%s totaltrips=%d expect=%d", Arrays.toString(time), totaltrips, expect);
        LeetCodeUtils.test(desc, output, expect);
    }
}
