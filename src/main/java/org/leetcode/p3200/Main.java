package org.leetcode.p3200;

// https://leetcode.cn/problems/maximum-height-of-a-triangle/description/?envType=daily-question&envId=2024-10-15
class Solution {
    public int maxHeightOfTriangle(int red, int blue) {
        int h = 1;
        int ans = h;
        while (true) {
            int all = (h + 1) * h / 2;
            int c1 = (((h % 2 == 0) ? (h - 1) : h) + 1) * ((h % 2 == 0) ? (h / 2) : ((h + 1) / 2)) / 2;
            int c2 = all - c1;
            if (c1 <= red && c2 <= blue || c2 <= red && c1 <= blue) {
                ans = h;
                h++;
            } else {
                break;
            }
        }
        return ans;
    }
}

public class Main {
}
