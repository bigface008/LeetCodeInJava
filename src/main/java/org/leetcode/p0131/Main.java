package org.leetcode.p0131;

import java.util.*;

// https://leetcode.cn/problems/palindrome-partitioning/?envType=daily-question&envId=2025-03-01
//class Solution {
//    List<List<String>> ans = new ArrayList<>();
//    Map<Character, List<Integer>> val2Indices = new HashMap<>();
//    String s;
//
//    public List<List<String>> partition(String s) {
//        this.s = s;
//        for (int i = 0; i < s.length(); i++) {
//            val2Indices.computeIfAbsent(s.charAt(i), k -> new ArrayList<>()).add(i);
//        }
//        List<String> plan = new ArrayList<>();
//        dfs(0, plan);
//        return ans;
//    }
//
//    private void dfs(int i, List<String> plan) {
//        if (i == s.length()) {
//            ans.add(new ArrayList<>(plan));
//            return;
//        }
//        char curr = s.charAt(i);
//        List<Integer> indices = val2Indices.get(curr);
//        int idx = Collections.binarySearch(indices, i);
//        for (int j = idx; j < indices.size(); j++) {
//            boolean valid = true;
//            int end = indices.get(j);
//            for (int l = i, r = end; l <= r; l++, r--) {
//                if (s.charAt(l) != s.charAt(r)) {
//                    valid = false;
//                    break;
//                }
//            }
//            if (valid) {
//                plan.add(s.substring(i, end + 1));
//                dfs(end + 1, plan);
//                plan.remove(plan.size() - 1);
//            }
//        }
//    }
//}

class Solution {
    List<List<String>> ans = new ArrayList<>();
    String s;

    public List<List<String>> partition(String s) {
        this.s = s;
        List<String> plan = new ArrayList<>();
        dfs(0, plan);
        return ans;
    }

    private void dfs(int i, List<String> plan) {
        if (i == s.length()) {
            ans.add(new ArrayList<>(plan));
            return;
        }
        for (int j = i; j < s.length(); j++) {
            int end = j;
            if (isPalidrome(i, end)) {
                plan.add(s.substring(i, end + 1));
                dfs(end + 1, plan);
                plan.remove(plan.size() - 1);
            }
        }
    }

    private boolean isPalidrome(int start, int end) {
        int l = start, r = end;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}

public class Main {
    public static void main(String[] args) {

    }
}
