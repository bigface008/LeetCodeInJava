package org.leetcode.p0037;

import java.util.BitSet;

// https://leetcode.com/problems/sudoku-solver/
//class Solution {
//    private BitSet[] rows;
//    private BitSet[] cols;
//    private BitSet[][] cells;
//
//    public void solveSudoku(char[][] board) {
//        final int N = 9;
//        rows = new BitSet[N];
//        cols = new BitSet[N];
//        cells = new BitSet[N][N];
//
//        int emptyCnt = 0;
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                if (board[i][j] == '.') {
//                    emptyCnt++;
//                    continue;
//                }
//                int temp = 1 << (board[i][j] - '1');
//                rows[i].or(temp);
//            }
//        }
//    }
//}

public class Main {
    public static void main(String[] args) {

    }
}
