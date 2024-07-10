package org.leetcode.utils;

import java.util.*;

// TODO
class MergeSort {
    static void sort(int[] nums) {
        int[] temp = new int[nums.length];
        recSort(nums, 0, nums.length - 1, temp);
    }

    static void recSort(int[] nums, int start, int end, int[] temp) {
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        recSort(nums, start, mid, temp);
        recSort(nums, mid + 1, end, temp);
        merge(nums, start, mid, end, temp);
    }

    static void merge(int[] nums, int start, int mid, int end, int[] temp) {
        int i = start;
        int j = end;
    }
}

class InsertSort {
    static void sort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int start = 0, end = i - 1;
            while (start <= end) {
                int mid = (start + end) / 2;
                if (nums[mid] >= nums[i]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
            int newVal = nums[i];
            for (int j = i - 1; j >= start; j--) {
                nums[j + 1] = nums[j];
            }
            nums[start] = newVal;
        }
    }
}

public class Sorter {
    public static void main(String[] args) {
        int[] a = new int[]{12, 451, -2, -12, 99, -1, 1, 22, 5, 65};
//        InsertSort.sort(a);
        System.out.println(Arrays.toString(a));

        Arrays.sort(a);

        List<Integer> list = new ArrayList(List.of(12, 451, -2, -12, 99, -1, 1, 22, 5, 65));
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        System.out.println(list);
    }
}
