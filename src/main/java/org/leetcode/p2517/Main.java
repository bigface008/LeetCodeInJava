package org.leetcode.p2517;

import java.util.Arrays;

class Solution {
    int[] price;

    public int maximumTastiness(int[] price, int k) {
        this.price = price;
        Arrays.sort(price);
        int left = 0;
        int right = (price[price.length - 1] - price[0]) / (k - 1) + 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int val = mostCandies(mid);
            if (val >= k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (mostCandies(left) < k) {
            return left - 1;
        }
        return left;
    }

    private int mostCandies(int diff) {
        int cnt = 1;
        int prev = price[0];
        for (int p : price) {
            if (prev + diff <= p) {
                cnt++;
                prev = p;
            }
        }
        return cnt;
    }
}

public class Main {
    public static void main(String[] args) {

    }
}
