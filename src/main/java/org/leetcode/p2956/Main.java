package org.leetcode.p2956;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// https://leetcode.cn/problems/find-common-elements-between-two-arrays/description/
class Solution {
    public int[] findIntersectionValues(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int ans1 = 0;
        int ans2 = 0;
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            int n1 = nums1[i];
            int n2 = nums2[j];
            int idx = exist(n1, nums2);
            if (idx == -1) {
                int k = i + 1;
                for (; k < nums1.length; k++) {
                    if (nums1[k] != n1) {
                        break;
                    }
                }
                i = k;
            } else {
                j = idx;
                int k = i + 1;
                for (; k < nums1.length; k++) {
                    if (nums1[k] != n1) {
                        break;
                    }
                }
                ans1 += k - i;
                i = k;
                k = j + 1;
                for (; k < nums2.length; k++) {
                    if (nums2[k] != n1) {
                        break;
                    }
                }
                ans2 += k - j;
                j = k;
            }
        }
        return new int[]{ans1, ans2};
    }

    int exist(int num, int[] arr) {
        int start = 0, end = arr.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            int val = arr[mid];
            if (val == num) {
                while (mid >= 1 && arr[mid - 1] == val) {
                    mid--;
                }
                return mid;
            } else if (val > num) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }
}

public class Main {
    public static void main(String[] args) {
        test(new int[]{2, 3, 2}, new int[]{1, 2}, new int[]{2, 1});
        test(new int[]{4, 3, 2, 3, 1}, new int[]{2, 2, 5, 2, 3, 6}, new int[]{3, 4});
        test(new int[]{3, 4, 2, 3}, new int[]{1, 5}, new int[]{0, 0});
    }

    private static void test(int[] nums1, int[] nums2, int[] expect) {
        Solution solution = new Solution();
        int[] output = solution.findIntersectionValues(nums1, nums2);
        String desc = String.format("find intersection values nums1=%s nums2=%s", Arrays.toString(nums1), Arrays.toString(nums2));
        LeetCodeUtils.test(desc, output, expect);
    }
}
