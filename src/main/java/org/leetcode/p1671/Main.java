package org.leetcode.p1671;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// https://leetcode.com/problems/minimum-number-of-removals-to-make-mountain-array/?envType=daily-question&envId=2024-10-30
class Solution {
    public int minimumMountainRemovals(int[] nums) {
        final int N = nums.length;
        if (N < 3) {
            return 0;
        }
        int[] leftDP = new int[N];
        int[] rightDP = new int[N];
        List<Integer> dp = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            int cur = nums[i];
            int idx = Collections.binarySearch(dp, cur);
            if (idx < 0) {
                idx = -idx - 1;
                if (idx == dp.size()) {
                    dp.add(cur);
                } else {
                    dp.set(idx, cur);
                }
            }
            leftDP[i] = dp.size();
        }
        dp.clear();
        for (int i = N - 1; i >= 0; i--) {
            int cur = nums[i];
            int idx = Collections.binarySearch(dp, cur);
            if (idx < 0) {
                idx = -idx - 1;
                if (idx == dp.size()) {
                    dp.add(cur);
                } else {
                    dp.set(idx, cur);
                }
            }
            rightDP[i] = dp.size();
        }
        int ans = N - 1;
        for (int i = 0; i < N; i++) {
            if (rightDP[i] >= 2 && leftDP[i] >= 2) {
                ans = Math.min(ans, N - rightDP[i] - leftDP[i] + 1);
            }
        }
        return ans;
    }
}

public class Main {
}
