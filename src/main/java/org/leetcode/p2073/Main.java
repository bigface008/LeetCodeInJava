package org.leetcode.p2073;

import org.leetcode.utils.LeetCodeUtils;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

class Solution {
    public int timeRequiredToBuy(int[] tickets, int k) {
        int ans = 0;
        for (int i = 0; i < tickets.length; i++) {
            if (i <= k) {
                ans += Math.min(tickets[k], tickets[i]);
            } else {
                ans += Math.min(tickets[k] - 1, tickets[i]);
            }
        }
        return ans;
    }
}

//class Solution {
//    public int timeRequiredToBuy(int[] tickets, int k) {
//        int ans = 0;
//        Deque<int[]> deque = new LinkedList<>();
//        for (int i = 0; i < tickets.length; i++) {
//            deque.add(new int[]{i, tickets[i]});
//        }
//        while (true) {
//            int[] first = deque.peekFirst();
//            int idx = first[0], remainCnt = first[1];
//            ans++;
//            if (idx == k && remainCnt == 1) {
//                break;
//            }
//            deque.pollFirst();
//            first[1]--;
//            if (first[1] > 0) {
//                deque.offer(first);
//            }
//        }
//        return ans;
//    }
//}

public class Main {
    public static void main(String[] args) {
        test(new int[]{5, 1, 1, 1}, 0, 8);
    }

    private static void test(int[] tickets, int k, int expect) {
        Solution solution = new Solution();
        int output = solution.timeRequiredToBuy(tickets, k);
        String desc = String.format("tickets = %s, k = %d", tickets, k);
        LeetCodeUtils.test(desc, output, expect);
    }
}
