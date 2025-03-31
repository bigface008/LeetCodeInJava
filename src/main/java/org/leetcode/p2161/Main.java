package org.leetcode.p2161;

import java.util.Arrays;

//  A G B C E F H

// https://leetcode.com/problems/partition-array-according-to-given-pivot/?envType=daily-question&envId=2025-03-03
class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        final int N = nums.length;
        int[] temp = new int[N];
        int lessCnt = 0;
        int sameCnt = 0;
        for (int x : nums) {
            if (x < pivot) {
                temp[lessCnt] = x;
                lessCnt++;
            } else if (x == pivot) {
                sameCnt++;
            }
        }
        for (int i = 0; i < sameCnt; i++) {
            temp[lessCnt + i] = pivot;
        }
        int greaterCnt = 0;
        for (int x : nums) {
            if (x > pivot) {
                temp[greaterCnt + lessCnt + sameCnt] = x;
                greaterCnt++;
            }
        }
        System.arraycopy(temp, 0, nums, 0, temp.length);
        return nums;
    }
}

// p:3
//   l   r
// 1 2 3 2 3

public class Main {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        int[] arr2 = arr.clone();
        arr2[0] = 21;
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr2));
    }
}
