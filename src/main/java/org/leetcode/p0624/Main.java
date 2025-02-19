package org.leetcode.p0624;

import java.util.List;

// https://leetcode.cn/problems/maximum-distance-in-arrays/?envType=daily-question&envId=2025-02-19
class Solution {
    public int maxDistance(List<List<Integer>> arrays) {
        int min1Idx = -1, min2Idx = -1;
        int max1Idx = -1, max2Idx = -1;
        for (int i = 0; i < arrays.size(); i++) {
            List<Integer> arr = arrays.get(i);
            if (min1Idx == -1 || arrays.get(min1Idx).get(0) > arr.get(0)) {
                min2Idx = min1Idx;
                min1Idx = i;
            } else if (min2Idx == -1 || arrays.get(min2Idx).get(0) > arr.get(0)) {
                min2Idx = i;
            }
            if (max1Idx == -1 || arrays.get(max1Idx).get(arrays.get(max1Idx).size() - 1) < arr.get(arr.size() - 1)) {
                max2Idx = max1Idx;
                max1Idx = i;
            } else if (max2Idx == -1 || arrays.get(max2Idx).get(0) < arr.get(arr.size() - 1)) {
                max2Idx = i;
            }
        }
        int i1 = 0, i2 = 0;
        if (max1Idx != min1Idx) {
            return getDiff(arrays, max1Idx, min1Idx);
        } else {
            return Math.max(getDiff(arrays, max1Idx, min2Idx), getDiff(arrays, max2Idx, min1Idx));
        }
    }

    private int getDiff(List<List<Integer>> arrays, int i1, int i2) {
        return getVal(arrays, i1, false) - getVal(arrays, i2, true);
    }

    private int getVal(List<List<Integer>> arrays, int i, boolean first) {
        List<Integer> arr = arrays.get(i);
        if (first) {
            return arr.get(0);
        }
        return arr.get(arr.size() - 1);
    }
}

public class Main {
}
