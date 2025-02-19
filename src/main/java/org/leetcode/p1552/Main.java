package org.leetcode.p1552;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;

class Solution {
    int[] position;

    public int maxDistance(int[] position, int m) {
        this.position = position;
        Arrays.sort(position);
        int left = 0;
        int right = (position[position.length - 1] - position[0]) / (m - 1) + 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int amout = mostBuckets(mid);
            if (amout >= m) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (mostBuckets(left) < m) {
            return left - 1;
        }
        return left;
    }

    private int mostBuckets(int dist) {
        int cnt = 1;
        int prev = this.position[0];
        for (int p : this.position) {
            if (p >= prev + dist) {
                cnt++;
                prev = p;
            }
        }
        return cnt;
    }
}

public class Main {
    public static void main(String[] args) {
        test(new int[]{1, 2, 3, 4, 7}, 3, 3);
    }

    private static void test(int[] position, int m, int expect) {
        int output = new Solution().maxDistance(position, m);
        String desc = String.format("position=%s m=%d", Arrays.toString(position), m);
        LeetCodeUtils.test(desc, output, expect);
    }
}
