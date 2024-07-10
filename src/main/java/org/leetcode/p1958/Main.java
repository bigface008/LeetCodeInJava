package org.leetcode.p1958;

import org.leetcode.utils.LeetCodeUtils;

// https://leetcode.com/problems/check-if-move-is-legal/description/
class Solution {
    public boolean checkMove(char[][] board, int rMove, int cMove, char color) {
        final int M = board.length;
        final int N = board[0].length;
        board[rMove][cMove] = color;
        // top
        if (rMove - 1 >= 0 && isMiddlePoint(board[rMove - 1][cMove], color)) {
            for (int r = rMove - 2; r >= 0; r--) {
                if (board[r][cMove] == color) {
                    return true;
                } else if (board[r][cMove] == '.') {
                    break;
                }
            }
        }
        // bottom
        if (rMove + 1 < M && isMiddlePoint(board[rMove + 1][cMove], color)) {
            for (int r = rMove + 2; r < M; r++) {
                if (board[r][cMove] == color) {
                    return true;
                } else if (board[r][cMove] == '.') {
                    break;
                }
            }
        }
        // right
        if (cMove + 1 < M && isMiddlePoint(board[rMove][cMove + 1], color)) {
            for (int c = cMove + 2; c < N; c++) {
                if (board[rMove][c] == color) {
                    return true;
                } else if (board[rMove][c] == '.') {
                    break;
                }
            }
        }
        // left
        if (cMove - 1 >= 0 && isMiddlePoint(board[rMove][cMove - 1], color)) {
            for (int c = cMove - 2; c >= 0; c--) {
                if (board[rMove][c] == color) {
                    return true;
                } else if (board[rMove][c] == '.') {
                    break;
                }
            }
        }
        // bottom right
        if (rMove + 1 < M && cMove + 1 < N && isMiddlePoint(board[rMove + 1][cMove + 1], color)) {
            for (int d = 2; rMove + d < M && cMove + d < N; d++) {
                if (board[rMove + d][cMove + d] == color) {
                    return true;
                } else if (board[rMove + d][cMove + d] == '.') {
                    break;
                }
            }
        }
        // bottom left
        if (rMove + 1 < M && cMove - 1 >= 0 && isMiddlePoint(board[rMove + 1][cMove - 1], color)) {
            for (int d = 2; rMove + d < M && cMove - d >= 0; d++) {
                if (board[rMove + d][cMove - d] == color) {
                    return true;
                } else if (board[rMove + d][cMove - d] == '.') {
                    break;
                }
            }
        }
        // top right
        if (rMove - 1 >= 0 && cMove + 1 < N && isMiddlePoint(board[rMove - 1][cMove + 1], color)) {
            for (int d = 2; rMove - d >= 0 && cMove + d < N; d++) {
                if (board[rMove - d][cMove + d] == color) {
                    return true;
                } else if (board[rMove - d][cMove + d] == '.') {
                    break;
                }
            }
        }
        // top left
        if (rMove - 1 >= 0 && cMove - 1 >= 0 && isMiddlePoint(board[rMove - 1][cMove - 1], color)) {
            for (int d = 2; rMove - d >= 0 && cMove - d >= 0; d++) {
                if (board[rMove - d][cMove - d] == color) {
                    return true;
                } else if (board[rMove - d][cMove - d] == '.') {
                    break;
                }
            }
        }
        return false;
    }

    private boolean isMiddlePoint(char point, char endColor) {
        return point != endColor && point != '.';
    }
}

public class Main {
    public static void main(String[] args) {
        test("[[\".\",\".\",\".\",\"B\",\".\",\".\",\".\",\".\"],[\".\",\".\",\".\",\"W\",\".\",\".\",\".\",\".\"],[\".\",\".\",\".\",\"W\",\".\",\".\",\".\",\".\"],[\".\",\".\",\".\",\"W\",\".\",\".\",\".\",\".\"],[\"W\",\"B\",\"B\",\".\",\"W\",\"W\",\"W\",\"B\"],[\".\",\".\",\".\",\"B\",\".\",\".\",\".\",\".\"],[\".\",\".\",\".\",\"B\",\".\",\".\",\".\",\".\"],[\".\",\".\",\".\",\"W\",\".\",\".\",\".\",\".\"]]", 4, 3, 'B', true);
        test("[[\".\",\".\",\".\",\".\",\".\",\".\",\".\",\".\"],[\".\",\"B\",\".\",\".\",\"W\",\".\",\".\",\".\"],[\".\",\".\",\"W\",\".\",\".\",\".\",\".\",\".\"],[\".\",\".\",\".\",\"W\",\"B\",\".\",\".\",\".\"],[\".\",\".\",\".\",\".\",\".\",\".\",\".\",\".\"],[\".\",\".\",\".\",\".\",\"B\",\"W\",\".\",\".\"],[\".\",\".\",\".\",\".\",\".\",\".\",\"W\",\".\"],[\".\",\".\",\".\",\".\",\".\",\".\",\".\",\"B\"]]", 4, 4, 'W', false);
    }

    private static void test(String boardStr, int rMove, int cMove, char color, boolean expect) {
        char[][] board = LeetCodeUtils.make2DCharArray(boardStr);
        String desc = String.format("check move board=%s, rMove=%d, cMove=%d, color=%c", boardStr, rMove, cMove, color);
        Solution solution = new Solution();
        boolean output = solution.checkMove(board, rMove, cMove, color);
        LeetCodeUtils.test(desc, output, expect);
    }
}
