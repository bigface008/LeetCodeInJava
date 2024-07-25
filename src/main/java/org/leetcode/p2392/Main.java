package org.leetcode.p2392;

import org.leetcode.utils.LeetCodeUtils;

import java.util.*;

// https://leetcode.com/problems/build-a-matrix-with-conditions
// 拓扑排序
class Solution {
    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        int[][] ans = new int[k][k];
        List<Integer> rowList = topoSort(k, rowConditions);
        List<Integer> colList = topoSort(k, colConditions);
        if (rowList == null || colList == null) {
            return new int[][]{};
        }
        Map<Integer, Integer> numToCol = new HashMap<>();
        for (int i = 0; i < k; i++) {
            numToCol.put(colList.get(i), i);
        }
        for (int i = 0; i < k; i++) {
            int num = rowList.get(i);
            int col = numToCol.get(num);
            ans[i][col] = num;
        }
        return ans;
    }

    private List<Integer> topoSort(int k, int[][] edges) {
        List<Integer> ans = new ArrayList<>(k);
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] inDegree = new int[k];
        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0] - 1, key -> new ArrayList<>()).add(edge[1] - 1);
            inDegree[edge[1] - 1]++;
        }
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            if (inDegree[i] == 0) {
                deque.offer(i);
            }
        }
        while (!deque.isEmpty()) {
            int curr = deque.poll();
            ans.add(curr);
            List<Integer> children = graph.get(curr);
            if (children == null) {
                continue;
            }
            for (int child : children) {
                inDegree[child]--;
                if (inDegree[child] == 0) {
                    deque.offer(child);
                }
            }
        }
        for (int i = 0; i < ans.size(); i++) {
            ans.set(i, ans.get(i) + 1);
        }
        return ans.size() == k ? ans : null;
    }
}

public class Main {
    public static void main(String[] args) {
        test(3, "[[1,2],[3,2]]", "[[2,1],[3,2]]", "[[3,0,0],[0,0,1],[0,2,0]]");
        test(3, "[[1,2],[2,3],[3,1],[2,3]]", "[[2,1]]", "[]");
    }

    private static void test(int k, String rowConditionStr, String colConditionStr, String expectStr) {
        Solution solution = new Solution();
        int[][] rowConditions = LeetCodeUtils.make2DIntArray(rowConditionStr);
        int[][] colConditions = LeetCodeUtils.make2DIntArray(colConditionStr);
        int[][] output = solution.buildMatrix(k, rowConditions, colConditions);
        int[][] expect = LeetCodeUtils.make2DIntArray(expectStr);
        String desc = String.format("build matrix k=%d rowConditions=%s colConditions=%s", k, rowConditionStr, colConditionStr);
        LeetCodeUtils.test(desc, output, expect);
    }
}
