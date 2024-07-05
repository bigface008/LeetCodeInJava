package org.leetcode.p2181;

import org.leetcode.utils.LeetCodeUtils;
import org.leetcode.utils.ListNode;

import java.util.List;

// https://leetcode.com/problems/merge-nodes-in-between-zeros
class Solution {
    public ListNode mergeNodes(ListNode head) {
        ListNode n1 = head;
        ListNode extra = new ListNode();
        ListNode n2 = extra;
        int sum = 0;
        while (n1 != null) {
            if (n1.val == 0) {
                if (n1 != head) {
                    n2.next = new ListNode(sum);
                    n2 = n2.next;
                }
                sum = 0;
            } else {
                sum += n1.val;
            }
            n1 = n1.next;
        }
        return extra.next;
    }
}

public class Main {
    public static void main(String[] args) {
        test(new int[]{0, 3, 1, 0, 4, 5, 2, 0}, new int[]{4, 11});
        test(new int[]{0, 1, 0, 3, 0, 2, 2, 0}, new int[]{1, 3, 4});
    }

    private static void test(int[] headArr, int[] expectArr) {
        ListNode head = ListNode.makeList(headArr);
        ListNode expect = ListNode.makeList(expectArr);
        Solution solution = new Solution();
        ListNode output = solution.mergeNodes(head);
        String desc = String.format("merge nodes in between zeros head=%s", head);
        LeetCodeUtils.test(desc, output, expect);
    }
}
