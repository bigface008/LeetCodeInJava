package org.leetcode.p3216;

// https://leetcode.cn/problems/lexicographically-smallest-string-after-a-swap/description/?envType=daily-question&envId=2024-10-30
class Solution {
    public String getSmallestString(String s) {
        final int N = s.length();
        char[] arr = s.toCharArray();
        for (int i = 0; i < N - 1; i++) {
            int curr = arr[i] - '0';
            int next = arr[i + 1] - '0';
            if (curr % 2 == next % 2) {
                if (curr > next) {
                    char temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    break;
                }
            }
        }
        StringBuilder builder = new StringBuilder();
        for (char ch : arr) {
            builder.append(ch);
        }
        return builder.toString();
    }
}

public class Main {
}
