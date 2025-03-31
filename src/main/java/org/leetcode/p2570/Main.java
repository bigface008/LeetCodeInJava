package org.leetcode.p2570;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

// https://leetcode.com/problems/merge-two-2d-arrays-by-summing-values/?envType=daily-question&envId=2025-03-02
class Solution {
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        List<int[]> ans = new ArrayList<>();
        final int N1 = nums1.length, N2 = nums2.length;
        int i = 0, j = 0;
        while (i < N1 && j < N2) {
            if (nums1[i][0] < nums2[j][0]) {
                ans.add(nums1[i]);
                i++;
            } else if (nums1[i][0] > nums2[j][0]) {
                ans.add(nums2[j]);
                j++;
            } else {
                nums1[i][1] += nums2[j][1];
                ans.add(nums1[i]);
                i++;
                j++;
            }
        }
        while (i < N1) {
            ans.add(nums1[i]);
            i++;
        }
        while (j < N2) {
            ans.add(nums2[j]);
            j++;
        }
        return ans.toArray(new int[0][]);
    }
}

public class Main {
    public static void main(String[] args) {
        Integer[] arr = new Integer[]{12, 13, 14};
        List<Integer> ans = Arrays.asList(arr);
        System.out.println(ans);
    }
}
