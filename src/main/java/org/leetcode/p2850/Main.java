package org.leetcode.p2850;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.cn/problems/minimum-moves-to-spread-stones-over-grid/description/?envType=daily-question&envId=2024-07-20
class Solution {
    public int minimumMoves(int[][] grid) {
        List<int[]> zeros = new ArrayList<>(9);
        List<int[]> gtOnes = new ArrayList<>(9);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int val = grid[i][j];
                if (val == 0) {
                    zeros.add(new int[]{i, j});
                } else if (val > 1) {
                    gtOnes.add(new int[]{i, j});
                }
            }
        }
        return dfs(zeros, gtOnes, grid);
    }

    int dfs(List<int[]> zeros, List<int[]> gtOnes, int[][] grid) {
        if (zeros.isEmpty()) {
            return 0;
        }
        int[] posZero = zeros.remove(0);
        int minCost = Integer.MAX_VALUE / 2;
        for (int[] posOne : gtOnes) {
            int x2 = posOne[0], y2 = posOne[1];
            if (grid[x2][y2] <= 1) {
                continue;
            }
            grid[x2][y2]--;
            int step = Math.abs(posZero[0] - posOne[0]) + Math.abs(posZero[1] - posOne[1]);
            int cost = dfs(zeros, gtOnes, grid) + step;
            minCost = Math.min(minCost, cost);
            grid[x2][y2]++;
        }
        zeros.add(posZero);
        return minCost;
    }
}

public class Main {
    public static void main(String[] args) {

    }
}
