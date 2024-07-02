package org.leetcode.p2816;

import org.leetcode.utils.LeetCodeUtils;
import org.leetcode.utils.ListNode;

import java.util.List;

// https://leetcode.com/problems/intersection-of-two-arrays-ii/description
class Solution {
    public ListNode doubleIt(ListNode head) {
        ListNode node1 = head;
        ListNode extra = new ListNode();
        ListNode node2 = extra;
        while (node1 != null) {
            node2.next = new ListNode();
            int val = node1.val * 2;
            if (val >= 10) {
                if (node2 == extra) {
                    node2.next.val = 1;
                    node2.next.next = new ListNode();
                    node2 = node2.next;
                }
                node2.next.val = val - 10;
            } else {
                node2.next.val = val;
            }
            if (node1.next != null) {
                int nextVal = node1.next.val * 2;
                if (nextVal >= 10) {
                    if (node2.next.val == 9) {
                        node2.next.val = 1;
                        node2.next.next = new ListNode();
                        node2 = node2.next;
                        node2.next.val = 0;
                    } else {
                        node2.next.val++;
                    }
                }
            }
            node2 = node2.next;
            node1 = node1.next;
        }
        return extra.next;
    }
}

public class Main {
    public static void main(String[] args) {
        test(new int[]{1, 8, 9}, new int[]{3, 7, 8});
        test(new int[]{9, 9, 9}, new int[]{1, 9, 9, 8});
    }

    private static void test(int[] headArr, int[] expectArr) {
        ListNode head = ListNode.makeList(headArr);
        ListNode expect = ListNode.makeList(expectArr);
        Solution solution = new Solution();
        ListNode output = solution.doubleIt(head);
        String desc = String.format("double it head=%s", head);
        LeetCodeUtils.test(desc, output, expect);
    }
}
