package org.leetcode.p2597;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.cn/problems/the-number-of-beautiful-subsets/?envType=daily-question&envId=2025-03-07
class Solution {
    Map<Integer, Integer> cnt = new HashMap<>();
    int[] nums;
    int N = 0;
    int ans = 0;
    int k = 0;

    public int beautifulSubsets(int[] nums, int k) {
        N = nums.length;
        this.k = k;
        this.nums = nums;
        dfs(0);
        return ans - 1;
    }

    private void dfs(int i) {
        if (i == N) {
            ans++;
            return;
        }
        dfs(i + 1);
        int x = nums[i];
        if (cnt.getOrDefault(x - k, 0) == 0 && cnt.getOrDefault(x + k, 0) == 0) {
            cnt.put(x, cnt.getOrDefault(x, 0) + 1);
            dfs(i + 1);
            cnt.put(x, cnt.get(x) - 1);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        String s = "我觉得不行";
        System.out.println(s.length());
        System.out.println(s.charAt(0));
        System.out.println(s.charAt(1));
        System.out.println(s.charAt(2));
    }
}
