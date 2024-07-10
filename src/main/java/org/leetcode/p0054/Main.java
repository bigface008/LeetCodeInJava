package org.leetcode.p0054;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/spiral-matrix/
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix.length == 0) {
            return new ArrayList<>();
        }
        List<Integer> ans = new ArrayList<>();
        int row = 0, col = 0;
        int width = matrix[0].length;
        int height = matrix.length;
        while (width > 0 && height > 0) {
            // top
            for (int i = 0; i < width; i++) {
                ans.add(matrix[row][col + i]);
            }
            // right
            for (int i = 1; i < height; i++) {
                ans.add(matrix[row + i][col + width - 1]);
            }
            // bottom
            if (height > 1) {
                for (int i = width - 2; i >= 0; i--) {
                    ans.add(matrix[row + height - 1][col + i]);
                }
            }
            // left
            if (width > 1) {
                for (int i = height - 2; i > 0; i--) {
                    ans.add(matrix[row + i][col]);
                }
            }
            row++;
            col++;
            width -= 2;
            height -= 2;
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {

    }
}
