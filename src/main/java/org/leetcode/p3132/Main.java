package org.leetcode.p3132;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;

// https://leetcode.cn/problems/find-the-integer-added-to-array-ii/description/?envType=daily-question&envId=2024-08-09
class Solution {
    static final int INF = Integer.MAX_VALUE;

    public int minimumAddedInteger(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        for (int i : new int[]{2, 1, 0}) {
            int diff = nums2[0] - nums1[i];
            int k = 0;
            for (int n1 : nums1) {
                if (n1 + diff == nums2[k]) {
                    k++;
                }
                if (k == nums2.length) {
                    return diff;
                }
            }
        }
        return 0;
    }
}

class Solution2 {
    static final int INF = Integer.MAX_VALUE;

    public int minimumAddedInteger(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int ans = INF;
        for (int i = 0; i < nums1.length; i++) {
            for (int j = i + 1; j < nums1.length; j++) {
                int sameDiffCnt = 0;
                int k1 = 0; // num1
                int k2 = 0; // num2
                int diff = INF;
                boolean allDiffSame = true;
                while (sameDiffCnt <= nums2.length && k1 < nums1.length && k2 < nums2.length) {
                    if (k1 == j || k1 == i) {
                        k1++;
                        continue;
                    }
                    if (diff == INF) {
                        diff = nums2[k2] - nums1[k1];
                        sameDiffCnt = 1;
                    } else {
                        int newDiff = nums2[k2] - nums1[k1];
                        if (newDiff == diff) { // same diff
                            sameDiffCnt++;
                            k1++;
                            k2++;
                        } else {
                            allDiffSame = false;
                            break;
                        }
                    }
                }
                if (allDiffSame) {
                    ans = Math.min(ans, diff);
                }
            }
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
        test(new int[]{4, 20, 16, 12, 8}, new int[]{14, 18, 10}, -2);
        test(new int[]{3, 5, 5, 3}, new int[]{7, 7}, 2);
    }

    private static void test(int[] nums1, int[] nums2, int expect) {
        Solution solution = new Solution();
        int output = solution.minimumAddedInteger(nums1, nums2);
        String desc = String.format("minimum added integer nums1=%s, nums2=%s", Arrays.toString(nums1), Arrays.toString(nums2));
        LeetCodeUtils.test(desc, output, expect);
    }
}
