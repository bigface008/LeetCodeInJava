package org.leetcode.p2807;

import org.leetcode.utils.LeetCodeUtils;
import org.leetcode.utils.ListNode;

// https://leetcode.com/problems/insert-greatest-common-divisors-in-linked-list/description/
class Solution {
    public ListNode insertGreatestCommonDivisors(ListNode head) {
        ListNode node = head;
        while (node != null && node.next != null) {
            int val = gcd(node.val, node.next.val);
            ListNode next = node.next;
            node.next = new ListNode(val);
            node.next.next = next;
            node = next;
        }
        return head;
    }

    int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}

public class Main {
    public static void main(String[] args) {
        test(new int[]{18, 6, 10, 3}, new int[]{18, 6, 6, 2, 10, 1, 3});
        test(new int[]{7}, new int[]{7});
    }

    private static void test(int[] headArr, int[] expectArr) {
        ListNode head = ListNode.makeList(headArr);
        ListNode expect = ListNode.makeList(expectArr);
        String desc = String.format("insert greatest common divisors head=%s", head);
        Solution solution = new Solution();
        ListNode output = solution.insertGreatestCommonDivisors(head);
        LeetCodeUtils.test(desc, output, expect);
    }
}
