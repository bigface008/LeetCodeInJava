package org.leetcode.p0452;

import java.util.Arrays;
import java.util.Comparator;

// https://leetcode.cn/problems/minimum-number-of-arrows-to-burst-balloons/?envType=company&envId=meituan&favoriteSlug=meituan-all
class Solution {
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (a, b) -> Long.compare(a[1], b[1]));
        int ans = 0;
        long prevPoint = Long.MIN_VALUE;
        for (int[] point : points) {
            long start = point[0], end = point[1];
            if (start > prevPoint) {
                ans++;
                prevPoint = end;
            }
        }
        return ans;
    }
}

public class Main {
}
