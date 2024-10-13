package org.leetcode.p0023;

import org.leetcode.utils.ListNode;

import java.util.PriorityQueue;

// https://leetcode.com/problems/merge-k-sorted-lists/
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (ListNode list : lists) {
            ListNode node = list;
            while (node != null) {
                pq.offer(node.val);
                node = node.next;
            }
        }
        ListNode extra = new ListNode();
        ListNode node = extra;
        while (!pq.isEmpty()) {
            node.next = new ListNode(pq.poll());
            node = node.next;
        }
        return extra.next;
    }
}

public class Main {
}
