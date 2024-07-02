package org.leetcode.p2285;

import org.leetcode.utils.LeetCodeUtils;

import java.util.*;

class Solution {
    public long maximumImportance(int n, int[][] roads) {
        HashMap<Integer, Integer> edgeCntMap = new HashMap<>();
        for (int[] road : roads) {
            edgeCntMap.put(road[0], edgeCntMap.getOrDefault(road[0], 0) + 1);
            edgeCntMap.put(road[1], edgeCntMap.getOrDefault(road[1], 0) + 1);
        }
        List<List<Integer>> cntList = new ArrayList<>();
        edgeCntMap.forEach((k, v) -> {
            cntList.add(List.of(k, v));
        });
        cntList.sort(new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o2.get(1) - o1.get(1);
            }
        });
        long ans = 0;
        for (int i = 0; i < n; i++) {
            long val = n - i;
            if (i < cntList.size()) {
                ans += val * cntList.get(i).get(1);
            }
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
        test(5, "[[0,1],[1,2],[2,3],[0,2],[1,3],[2,4]]", 43);
        test(5, "[[0,3],[2,4],[1,3]]", 20);
    }

    private static void test(int n, String roadsStr, long expect) {
        Solution solution = new Solution();
        int[][] roads = LeetCodeUtils.make2DIntArray(roadsStr);
        long output = solution.maximumImportance(n, roads);
        String desc = String.format("maximum importance n=%d, roads=%s", n, roadsStr);
        LeetCodeUtils.test(desc, output, expect);
    }
}
