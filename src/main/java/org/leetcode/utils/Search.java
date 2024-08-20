package org.leetcode.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Search {
    // nums是一个严格单调递减的数组，请找出nums中最右侧的大于a的数的索引，如果不存在大于a的数，就返回-1。
//    static int search(List<Integer> list, int a) {
//        Collections.binarySearch(list, )
//    }

    static int search(int[] nums, int a) {
        int res = -1;
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int val = nums[mid];
            if (val > a) {
                start = mid + 1;
                res = mid;
            } else {
                end = mid - 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        test(new int[]{10, 8, 6, 4, 2}, 9, 0);
        test(new int[]{10, 8, 6, 4, 2}, 8, 0);
        test(new int[]{12, 10, 8, 6, 4, 2}, 11, 0);
        test(new int[]{12, 10, 8, 6, 4, 2}, 13, -1);
        test(new int[]{12, 10, 8, 6, 4, 2}, 12, -1);
        test(new int[]{12, 10, 8, 6, 4, 2}, 9, 1);
        test(new int[]{12, 10, 8, 6, 4, 2}, 7, 2);
        test(new int[]{12, 10, 8, 6, 4, 2}, 5, 3);
        test(new int[]{12, 10, 8, 6, 4, 2}, 3, 4);
        test(new int[]{12, 10, 8, 6, 4, 2}, 1, 5);
    }

    private static void test(int[] nums, int a, int expect) {
        int output = search(nums, a);
        String desc = String.format("test search nums=%s a=%d", Arrays.toString(nums), a);
        LeetCodeUtils.test(desc, output, expect);
    }
}
