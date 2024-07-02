package org.leetcode.p2130;

import org.leetcode.utils.LeetCodeUtils;
import org.leetcode.utils.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/
class Solution {
    public int pairSum(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode prev = null;
        ListNode curr = slow;
        while (curr != null) {
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }

        int ans = Integer.MIN_VALUE;
        ListNode node1 = head;
        ListNode node2 = prev;
        while (node1 != null && node2 != null) {
            ans = Math.max(node1.val + node2.val, ans);
            node1 = node1.next;
            node2 = node2.next;
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
        test(new int[]{5, 4, 2, 1}, 6);
        test(new int[]{4, 2, 2, 3}, 7);
        test(new int[]{1, 100000}, 100001);
    }

    private static void test(int[] arr, int expect) {
        Solution solution = new Solution();
        ListNode head = ListNode.makeList(arr);
        int output = solution.pairSum(head);
        String desc = String.format("pari sum head=%s", Arrays.toString(arr));
        LeetCodeUtils.test(desc, output, expect);
    }
}
