package org.leetcode.utils;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode makeList(int[] nums) {
        ListNode extra = new ListNode();
        ListNode head = extra;
        for (int num : nums) {
            head.next = new ListNode();
            head.next.val = num;
            head = head.next;
        }
        return extra.next;
    }

    @Override
    public String toString() {
        String curr;
        if (next == null) {
            return String.format("%d -> null", val);
        } else {
            return String.format("%d -> %s", val, next.toString());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (this.getClass() != obj.getClass()) return false;
        ListNode other = (ListNode) obj;
        if (this.val != other.val) return false;
        if (this.next == null) {
            return other.next == null;
        } else {
            return this.next.equals(other.next);
        }
    }

    public static void main(String[] args) {
        ListNode head = makeList(new int[]{1, 2, 3, 4});
        System.out.println(head);
        ListNode re = reverse(head);
        System.out.println(re);
    }

    private static ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
        return prev;
    }
}
