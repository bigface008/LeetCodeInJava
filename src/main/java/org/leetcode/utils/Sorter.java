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

class HeapSort {
    static void sort(int[] nums) {
        for (int i = (nums.length - 1) / 2; i >= 0; i--) {
            adjustHeap(nums, i, nums.length);
        }
        for (int i = nums.length - 1; i > 0; i--) {
            int temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;
            adjustHeap(nums, 0, i);
        }
    }

    /**
     * 调整堆
     * 这个函数的真正效果是，在parent的左右子树已经是堆/叶子节点/空的前提下，下移parent的值，让parent以下的所有点都小于parent
     * 那么为何这个前提一定能够确保？因为我们是从最后一个非叶子节点往前遍历的！
     * @param nums   待排序列
     * @param parent 父节点
     * @param length 待排序列尾长度
     */
    static void adjustHeap(int[] nums, int parent, int length) {
        int temp = nums[parent];
        int lChild = 2 * parent + 1;
        while (lChild < length) {
            int rChild = 2 * lChild + 1;
            if (rChild < length && nums[rChild] > nums[lChild]) {
                lChild = rChild;
            }
            if (temp >= nums[lChild]) {
                break;
            }
            nums[parent] = nums[lChild];
            parent = lChild;
            lChild = 2 * lChild + 1;
        }
        nums[parent] = temp;
    }
}

class QuickSort {
    static void sort(int[] nums) {
        recSort(nums, 0, nums.length - 1);
    }

    static void recSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivotIdx = partition(nums, left, right);
        recSort(nums, left, pivotIdx - 1);
        recSort(nums, pivotIdx + 1, right);
    }

    static int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        while (left < right) {
            while (left < right && nums[right] > pivot) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[left] < pivot) {
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = pivot;
        return left;
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
//        QuickSort.sort(a);
        HeapSort.sort(a);
        System.out.println(Arrays.toString(a));

//        Arrays.sort(a);
//
//        List<Integer> list = new ArrayList(List.of(12, 451, -2, -12, 99, -1, 1, 22, 5, 65));
//        list.sort(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2 - o1;
//            }
//        });
//        System.out.println(list);
    }
}
