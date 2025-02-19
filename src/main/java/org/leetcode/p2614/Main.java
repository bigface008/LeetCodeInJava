package org.leetcode.p2614;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;

// https://leetcode.cn/problems/prime-in-diagonal/
class Solution {
    public int diagonalPrime(int[][] nums) {
        int M = nums.length, N = nums[0].length;
        boolean[][] prime = new boolean[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                prime[i][j] = isPrime(nums[i][j]);
            }
        }
        // 左上角到右下角
        for (int diff = -(N - 1); diff <= M - 1; diff++) {
//            for (int i = 0; i < )
        }
//        for (int j = 0; j < N; j++) {
//            for (int x = 0, y = j; x < M && y < N; x++, y++) {
//                System.out.print(nums[x][y] + " ");
//            }
//            System.out.println();
//        }
//        for (int i = 1; i < M; i++) {
//            for (int x = i, y = 0; x < M && y < N; x++, y++) {
//                System.out.print(nums[x][y] + " ");
//            }
//            System.out.println();
//        }
        // 右上角到左下角
        for (int sum = 0; sum <= M + N - 1; sum++) {
            for (int i = 0; i < M; i++) {

            }
        }
        return 0;
    }

    boolean isPrime(int num) {
        return false;
    }
}

public class Main {
    public static void main(String[] args) {
        test(LeetCodeUtils.make2DIntArray("[[1,2,3],[5,6,7],[9,10,11]]"), 11);
    }

    private static void test(int[][] nums, int expect) {
        int output = new Solution().diagonalPrime(nums);
        String desc = String.format("nums=%s", Arrays.toString(nums));
        LeetCodeUtils.test(desc, output, expect);
    }
}
