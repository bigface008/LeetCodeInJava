package org.leetcode.p3175;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

// https://leetcode.cn/problems/find-the-first-player-to-win-k-games-in-a-row/?envType=daily-question&envId=2024-10-24
class Solution {
    public int findWinningPlayer(int[] skills, int k) {
        final int N = skills.length;
        if (k > N) {
            k = N;
        }
        int winIdx = 0;
        int winCnt = 0;
        for (int i = 1; i < N; i++) {
            if (skills[i] < skills[winIdx]) {
                winCnt++;
            } else {
                winCnt = 1;
                winIdx = i;
            }
            if (winCnt >= k) {
                return winIdx;
            }
        }
        return winIdx;
    }
}

//class Solution {
//    public int findWinningPlayer(int[] skills, int k) {
//        final int N = skills.length;
//        Deque<Integer> deque = new LinkedList<>();
//        if (k > N) {
//            k = N;
//        }
//        for (int i = 1; i < N; i++) {
//            deque.add(i);
//        }
//        int winnerIdx = 0;
//        int winCnt = 0;
//        while (true) {
//            int currPos = deque.pollFirst();
//            if (skills[currPos] < skills[winnerIdx]) {
//                winCnt += 1;
//                deque.add(currPos);
//            } else {
//                winCnt = 1;
//                deque.add(winnerIdx);
//                winnerIdx = currPos;
//            }
//            if (winCnt >= k) {
//                return winnerIdx;
//            }
//        }
//    }
//}

public class Main {
    public static void main(String[] args) {
        test(new int[]{4, 2, 6, 3, 9}, 2, 2);
    }

    private static void test(int[] skills, int k, int expect) {
        int output = new Solution().findWinningPlayer(skills, k);
        String desc = String.format("find win skills=%s", Arrays.toString(skills));
        LeetCodeUtils.test(desc, output, expect);
    }
}
