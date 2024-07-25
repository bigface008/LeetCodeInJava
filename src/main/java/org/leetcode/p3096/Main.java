package org.leetcode.p3096;

import java.util.Arrays;

// https://leetcode.cn/problems/minimum-levels-to-gain-more-points/description/?envType=daily-question&envId=2024-07-19
class Solution {
    public int minimumLevels(int[] possible) {
        int sum = 0;
        for (int i = 0; i < possible.length; i++) {
            if (possible[i] == 0) {
                sum--;
            } else {
                sum++;
            }
        }
        int sumA = 0;
        for (int i = 0; i < possible.length - 1; i++) {
            if (possible[i] == 0) {
                sumA--;
            } else {
                sumA++;
            }
            int b = sum - sumA;
            if (sumA > b) {
                return i + 1;
            }
        }
        return -1;
    }
}

public class Main {
    public static void main(String[] args) {

    }
}
