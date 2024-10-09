package org.leetcode.p1227;

import java.util.Arrays;

// 1/n
// if 1 chose 1 -> 2 chose 2 -> 3 chose 3 -> ... n -> n
// if 1 chose 2 -> todo
//     2 chose 1, 3, 4, ... n = 1/n-1
//     if 2 chose 1 -> 3 chose 3 -> ... n -> n
//     if 2 chose 3 -> todo
// if 1 chose 3 -> 2 chose 2 -> f(


// if k chose k -> k-1 chose k-1 -> ... 1 chose 1
// if k chose k-1:
//     if k-1 chose k -> k-2 chose k-2 -> 1 chose 1
//     if k-1 chose k-2 ->
// if k chose k-2:
//     -> k-1 chose k-1
//     if k-2 chose k -> .... chose 1
//     if k-2 chose k-3


// https://leetcode.cn/problems/airplane-seat-assignment-probability/?envType=daily-question&envId=2024-10-04
class Solution {
    double totalCnt = 0;
    double correctCnt = 0;
    int[] seats;

    public double nthPersonGetsNthSeat(int n) {
        seats = new int[n];
        Arrays.fill(seats, -1);
        backtrack(0);
        if (totalCnt == 0) {
            return 0;
        }
        return correctCnt / totalCnt;
    }

//    double dfs(int n) {
//        if (n == 1) {
//            return 1;
//        } else if (n == 2) {
//            return 0.5;
//        }
//
//    }

    void backtrack(int idx) {
        if (idx == seats.length) {
            if (seats[seats.length - 1] == seats.length) {
                correctCnt++;
            }
            totalCnt++;
            return;
        }
        if (idx == 0) {
            for (int i = 0; i < seats.length; i++) {
                seats[i] = idx + 1;
                backtrack(1);
                seats[i] = -1;
            }
            return;
        }
        if (seats[idx] != -1) {
            for (int i = 0; i < seats.length; i++) {
                if (seats[i] == -1) {
                    seats[i] = idx + 1;
                    backtrack(idx + 1);
                    seats[i] = -1;
                }
            }
        } else {
            seats[idx] = idx + 1;
            backtrack(idx + 1);
            seats[idx] = -1;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        run(1);
        run(2);
        run(3);
        run(4);
        run(5);
        run(6);
    }

    static void run(int n) {
        System.out.printf("n=%d solution(n)=%f\n", n, new Solution().nthPersonGetsNthSeat(n));
    }
}
