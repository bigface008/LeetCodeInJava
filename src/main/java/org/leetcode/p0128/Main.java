package org.leetcode.p0128;

import java.util.*;

// https://leetcode.com/problems/longest-consecutive-sequence/
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> allNums = new HashSet<>();
        for (int x : nums) {
            allNums.add(x);
        }
        int ans = 0;
        for (int x : allNums) {
            if (allNums.contains(x - 1)) {
                continue;
            }
            int cnt = 1;
            while (allNums.contains(x + 1)) {
                x++;
                cnt++;
            }
            ans = Math.max(ans, cnt);
        }
        return ans;
    }

//    private int dfsAdd(int val, int cnt) {
//        if (allNums.contains(val + 1)) {
//            return 1 + cnt + dfsAdd(val + 1, cnt);
//        } else {
//            return cnt;
//        }
//    }
//
//    private int dfsSub(int val, int cnt) {
//        if (allNums.contains(val - 1)) {
//            return 1 + cnt + dfsAdd(val - 1, cnt);
//        } else {
//            return cnt;
//        }
//    }
}


//class Solution {
//    Map<Integer, List<Integer>> map;
//    Set<Integer> visited;
//
//    public int longestConsecutive(int[] nums) {
//        final int N = nums.length;
//        map = new HashMap<>();
//        visited = new HashSet<>();
//        for (int i = 0; i < N; i++) {
//            map.computeIfAbsent(nums[i], k -> new LinkedList<>()).add(i);
//        }
////        boolean[] visited = new boolean[N];
//        map.forEach((x, indices) -> {
//            if (visited.contains(x)) {
//                return;
//            }
////            int r1 = dfsAdd(x, 0);
////            int r2 = dfsSub(x)
//        });
//    }
//
////    private int dfs(int val, int cnt) {
////        if (map.containsKey(val + 1)) {
////            ans += dfs()
////        }
////    }
//
//    private int dfsAdd(int val, int cnt) {
//        visited.add(val);
//        int ans = cnt;
//        if (map.containsKey(val + 1)) {
//            ans += dfsAdd(val + 1, cnt);
//        }
//        return ans;
//    }
//
//    private void dfsSub(int val, int cnt) {
//        visited.add(val);
//    }
//}

public class Main {
    public static void main(String[] args) {

    }
}
