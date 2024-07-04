package org.leetcode.utils;

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

public class Sorter {
    public static void main(String[] args) {

    }
}
