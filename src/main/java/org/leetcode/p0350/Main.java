package org.leetcode.p0350;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/problems/intersection-of-two-arrays-ii/description/?envType=daily-question&envId=2024-07-02
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map1 = new HashMap<>();
        HashMap<Integer, Integer> map2 = new HashMap<>();
        for (int num : nums1) {
            map1.put(num, map1.getOrDefault(num, 0) + 1);
        }
        for (int num : nums2) {
            map2.put(num, map2.getOrDefault(num, 0) + 1);
        }
        List<Integer> ans = new LinkedList<>();
        for (Integer num : map1.keySet()) {
            int cnt1 = map1.get(num);
            int cnt2 = map2.getOrDefault(num, 0);
            int cnt = Math.min(cnt1, cnt2);
            for (int i = 0; i < cnt; i++) {
                ans.add(num);
            }
        }
        int[] res = new int[ans.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = ans.get(i);
        }
        return res;
    }
}

public class Main {
    public static void main(String[] args) {

    }
}
