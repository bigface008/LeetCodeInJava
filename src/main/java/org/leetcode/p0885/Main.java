package org.leetcode.p0885;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/problems/spiral-matrix-iii/description/?envType=daily-question&envId=2024-08-08
class Solution {
    int cordIdx = 0;
    int rows = 0;
    int cols = 0;
    int N = 0;
    int[][] ans;

    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        this.rows = rows;
        this.cols = cols;
        this.N = rows * cols;
        this.ans = new int[N][2];
        this.cordIdx = 0;
        int r = rStart, c = cStart, stepLen = 1;
        int direction = 0;
        while (cordIdx < N) {
            for (int i = 0; i < stepLen; i++) {
                if (cordIdx >= N) {
                    break;
                }
                tryAdd(r, c);
                switch (direction) {
                    case 0: // right
                        c++;
                        break;
                    case 1: // down
                        r++;
                        break;
                    case 2: // left
                        c--;
                        break;
                    default: // 3 up
                        r--;
                        break;
                }
            }
            switch (direction) {
                case 0: // right
                    break;
                case 1: // down
                    stepLen++;
                    break;
                case 2: // left
                    break;
                default: // 3 up
                    stepLen++;
                    break;
            }
            direction = (direction + 1) % 4;
        }
        return ans;
    }

    void tryAdd(int r, int c) {
        if (cordIdx >= N) {
            return;
        }
        if (r < 0 || r >= rows) {
            return;
        }
        if (c < 0 || c >= cols) {
            return;
        }
        ans[cordIdx][0] = r;
        ans[cordIdx][1] = c;
        cordIdx++;
    }
}

public class Main {
    public static void main(String[] args) {
        test(1, 4, 0, 0, "[[0,0],[0,1],[0,2],[0,3]]");
        test(5, 6, 1, 4, "[[1,4],[1,5],[2,5],[2,4],[2,3],[1,3],[0,3],[0,4],[0,5],[3,5],[3,4],[3,3],[3,2],[2,2],[1,2],[0,2],[4,5],[4,4],[4,3],[4,2],[4,1],[3,1],[2,1],[1,1],[0,1],[4,0],[3,0],[2,0],[1,0],[0,0]]");
    }

    private static void test(int rows, int cols, int rStart, int cStart, String expectStr) {
        int[][] expect = LeetCodeUtils.make2DIntArray(expectStr);
        Solution solution = new Solution();
        int[][] output = solution.spiralMatrixIII(rows, cols, rStart, cStart);
        String desc = String.format("spiral matrix III rows=%d, cols=%d, rStart=%d, cStart=%d", rows, cols, rStart, cStart);
        LeetCodeUtils.test(desc, output, expect);
    }
}
