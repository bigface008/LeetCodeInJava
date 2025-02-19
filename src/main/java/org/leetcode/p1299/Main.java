package org.leetcode.p1299;

// https://leetcode.cn/problems/replace-elements-with-greatest-element-on-right-side/?envType=daily-question&envId=2025-02-16
class Solution {
    public int[] replaceElements(int[] arr) {
        int rightMax = -1;
        for (int i = arr.length - 1; i >= 0; i--) {
            int val = arr[i];
            arr[i] = rightMax;
            rightMax = Math.max(rightMax, val);
        }
        return arr;
    }
}

public class Main {
    public static void main(String[] args) {

    }
}
