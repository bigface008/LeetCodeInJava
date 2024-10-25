package org.leetcode.utils;

import java.io.IOException;
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

class ShellSort {
    static void sort(int[] nums) {

    }
}

class HeapSort {
    static void sort(int[] arr) {
        for (int i = (arr.length - 1) / 2; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }
        for (int i = arr.length - 1; i > 0; i--) {
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            heapify(arr, 0, i);
        }
    }

    /**
     * 调整堆
     * 这个函数的真正效果是，在parent的左右子树已经是堆/叶子节点/空的前提下，下移parent的值，让parent以下的所有点都小于parent
     * 那么为何这个前提一定能够确保？因为我们是从最后一个非叶子节点往前遍历的！
     *
     * @param arr    待排序列
     * @param parent 父节点
     * @param length 待排序列尾长度
     */
    static void heapify(int[] arr, int parent, int length) {
        int temp = arr[parent];
        int lChild = 2 * parent + 1;
        while (lChild < length) {
            int rChild = lChild + 1;
            if (rChild < length && arr[lChild] < arr[rChild]) {
                lChild = rChild;
            }
            if (temp >= arr[lChild]) {
                break;
            }
            arr[parent] = arr[lChild];
            parent = lChild;
            lChild = 2 * lChild + 1;
        }
        arr[parent] = temp;
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
    private static void testSort(int[] arr) {
        String before = Arrays.toString(arr);
        HeapSort.sort(arr);
        String after = Arrays.toString(arr);
        System.out.printf("before=%s after=%s\n", before, after);
    }

    public static void main(String[] args) {
//        int[] a = new int[]{12, 451, -2, -12, 99, -1, 1, 22, 5, 65};
//        int[] a = new int[]{12, 451, -2, -12, 99, -1, 1, 22, 5, 65};
//        int[] a = new int[]{12, 451, -2, -12, 99, -1, 1, 22, 5, 65};
//        int[] a = new int[]{12, 451, -2, -12, 99, -1, 1, 22, 5, 65};
//        int[] a = new int[]{12, 451, -2, -12, 99, -1, 1, 22, 5, 65};
//        int[] a = new int[]{12, 451, -2, -12, 99, -1, 1, 22, 5, 65};
//        InsertSort.sort(a);
//        QuickSort.sort(a);
//        HeapSort.sort(a);
//        System.out.println(Arrays.toString(a));
//        int[] b = new int[]{68, 1, 2, 3, 4, 5, 6};
//        QuickSort.partition(b, 0, b.length - 1);
//        System.out.println(Arrays.toString(b));
        testSort(new int[]{12, 451, -2, -12, 99, -1, 1, 22, 5, 65});

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
