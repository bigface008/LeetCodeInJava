package org.leetcode.p1287;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int findSpecialInteger(int[] arr) {
        int l = 0, r = arr.length / 4;
        while (r < arr.length) {
            if (arr[l] == arr[r]) {
                return arr[l];
            }
            l++;
            r++;
        }
        return arr[0];
    }
}

public class Main {
    public static void main(String[] args) {

    }
}
