package org.leetcode.p1436;

import java.util.*;

// https://leetcode.cn/problems/destination-city/description/?envType=daily-question&envId=2024-10-08
class Solution {
    public String destCity(List<List<String>> paths) {
        Map<String, String> map = new HashMap<>();
        for (List<String> path : paths) {
            map.put(path.get(0), path.get(1));
        }
        String s = paths.get(0).get(0);
        while (map.containsKey(s)) {
            s = map.get(s);
        }
        return s;
    }
}

public class Main {
    public static void main(String[] args) {

    }
}
