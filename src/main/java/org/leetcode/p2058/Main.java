package org.leetcode.p2058;

import org.leetcode.utils.LeetCodeUtils;
import org.leetcode.utils.ListNode;

// https://leetcode.com/problems/find-the-minimum-and-maximum-number-of-nodes-between-critical-points/description
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int[] emptyRes = new int[]{-1, -1};
        if (head == null || head.next == null) {
            return emptyRes;
        }
        int minDist = Integer.MAX_VALUE / 2;
        int maxDist = Integer.MIN_VALUE / 2;
        ListNode n1 = head;
        ListNode n2 = head.next;
        int firstCriticalIdx = -1;
        int prevCriticalIdx = -1;
        int lastCriticalIdx = -1;
        int i = 1;
        while (n2 != null) {
            if (n2.next != null) {
                if ((n2.val > n2.next.val && n2.val > n1.val)
                        || (n2.val < n2.next.val && n2.val < n1.val)) {
                    if (firstCriticalIdx == -1) {
                        firstCriticalIdx = i;
                    }
                    prevCriticalIdx = lastCriticalIdx;
                    lastCriticalIdx = i;
                    if (prevCriticalIdx != -1) {
                        minDist = Math.min(i - prevCriticalIdx, minDist);
                    }
                }
            }
            n1 = n1.next;
            n2 = n2.next;
            i++;
        }
        if (lastCriticalIdx == firstCriticalIdx || prevCriticalIdx == -1) {
            return emptyRes;
        }
        return new int[]{minDist, lastCriticalIdx - firstCriticalIdx};
    }
}

public class Main {
    public static void main(String[] args) {
        test(new int[]{3, 1}, new int[]{-1, -1});
        test(new int[]{5, 3, 1, 2, 5, 1, 2}, new int[]{1, 3});
        test(new int[]{1, 3, 2, 2, 3, 2, 2, 2, 7}, new int[]{3, 3});
    }

    private static void test(int[] list, int[] expect) {
        ListNode head = ListNode.makeList(list);
        Solution solution = new Solution();
        int[] output = solution.nodesBetweenCriticalPoints(head);
        String desc = String.format("nodes between critical points head=%s", head);
        LeetCodeUtils.test(desc, output, expect);
    }
}
