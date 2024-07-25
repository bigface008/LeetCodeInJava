package org.leetcode.p1380;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/problems/lucky-numbers-in-a-matrix/?envType=daily-question&envId=2024-07-19
class Solution {
    public List<Integer> luckyNumbers (int[][] matrix) {
        List<Integer> ans = new LinkedList<>();
        final int M = matrix.length;
        final int N = matrix[0].length;
        final int INF = Integer.MAX_VALUE / 2;
        for (int r = 0; r < M; r++) {
            int minVal = INF;
            int minCol = 0;
            for (int c = 0; c < N; c++) {
                if (matrix[r][c] < minVal) {
                    minVal = matrix[r][c];
                    minCol = c;
                }
            }
            boolean valid = true;
            for (int r2 = 0; r2 < M; r2++) {
                if (matrix[r2][minCol] > minVal) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                ans.add(minVal);
            }
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {

    }
}
