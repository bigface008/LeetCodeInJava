package org.leetcode.p3162;

// https://leetcode.cn/problems/find-the-number-of-good-pairs-i/?envType=daily-question&envId=2024-10-10
class Solution {
    public int numberOfPairs(int[] nums1, int[] nums2, int k) {
        final int N = nums1.length, M = nums2.length;
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (nums1[i] % (nums2[j] * k) == 0) {
                    ans++;
                }
            }
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {

    }
}
