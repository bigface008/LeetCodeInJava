package org.leetcode.p0368;

import java.util.*;

// https://leetcode.com/problems/largest-divisible-subset/
class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        final int N = nums.length;
        Arrays.sort(nums);
        int[] f = new int[N];
        int[] g = new int[N];
        for (int i = 0; i < N; i++) {
            int len = 1, prev = i;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    if (f[j] + 1 > len) {
                        len = f[j] + 1;
                        prev = j;
                    }
                }
            }
            f[i] = len;
            g[i] = prev;
        }

        int maxLen = 0;
        int maxIdx = -1;
        for (int i = 0; i < N; i++) {
            if (f[i] > maxLen) {
                maxLen = f[i];
                maxIdx = i;
            }
        }

        List<Integer> ans = new LinkedList<>();
        int pos = maxIdx;
        while (pos != g[pos]) {
            ans.add(0, nums[pos]);
            pos = g[pos];
        }
        ans.add(0, nums[pos]);
        return ans;
    }
}

//class Solution {
//    int N = 0;
//    int[] nums;
//    Map<Integer, Integer> mp = new HashMap();
//    boolean[] visited;
//    List<Integer> ans;
//    List<Integer> group = new ArrayList<>();
//
//    public List<Integer> largestDivisibleSubset(int[] nums) {
//        Arrays.sort(nums);
//        N = nums.length;
//        this.nums = nums;
//        visited = new boolean[N];
//        for (int i = 0; i < N; i++) {
//            mp.put(nums[i], i);
//        }
//        return ans;
//    }
//
////    private void dfs(int i) {
////        if (i == N) {
////            return;
////        }
////        int x = nums[i];
////        List<Integer> group = new ArrayList<>();
////        int val = x;
////        while (val < nums[N - 1]) {
////            if (visited)
////            if (mp.containsKey(val)) {
////                visited[mp.get(val)] = true;
////                group.add(val);
////            }
////            val *= 2;
////        }
////    }
//}

public class Main {
    public static void main(String[] args) {

    }

    private static void check(int[] nums, List<Integer> list) {

    }
}
