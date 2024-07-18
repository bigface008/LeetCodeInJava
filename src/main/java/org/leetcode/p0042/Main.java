package org.leetcode.p0042;

// https://leetcode.com/problems/trapping-rain-water/
class Solution {
    public int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int preMax = 0, sufMax = 0;
        int ans = 0;
        while (left <= right) {
            preMax = Math.max(height[left], preMax);
            sufMax = Math.max(height[right], sufMax);
            if (preMax < sufMax) {
                ans += preMax - height[left];
                left++;
            } else {
                ans += sufMax - height[right];
                right--;
            }
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {

    }
}
