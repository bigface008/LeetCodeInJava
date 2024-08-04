package org.leetcode.p0239;

import org.leetcode.utils.LeetCodeUtils;

import java.util.*;

// https://leetcode.com/problems/sliding-window-maximum/description/
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && deque.getLast() < nums[i]) {
                deque.removeLast();
            }
            deque.add(nums[i]);
        }
        ans[0] = deque.getFirst();
        for (int i = k; i < nums.length; i++) {
            int dropVal = nums[i - k];
            int newVal = nums[i];
            if (deque.getFirst() == dropVal) {
                deque.removeFirst();
            }
            while (!deque.isEmpty() && deque.getLast() < newVal) {
                deque.removeLast();
            }
            deque.add(newVal);
            ans[i - k + 1] = deque.getFirst();
        }
        return ans;
    }
}

class Solution2 {
    public int[] maxSlidingWindow(int[] nums, int k) {
//        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2 - o1;
//            }
//        });
//        for (int i = 0; i < k; i++) {
//            maxHeap.add(nums[i]);
//        }
//        int[] ans = new int[nums.length - k + 1];
//        for (int i = k; i < nums.length; i++) {
//            ans[i - k] = maxHeap.peek();
//            maxHeap.remove(nums[i - k]);
//            maxHeap.add(nums[i]);
//        }
//        ans[ans.length - 1] = maxHeap.peek();
//        return ans;
        int[] ans = new int[nums.length - k + 1];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < k; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        ans[0] = map.lastKey();
        for (int i = k; i < nums.length; i++) {
            int dropVal = nums[i - k];
            int dropCnt = map.get(dropVal);
            if (dropCnt == 1) {
                map.remove(dropVal);
            } else {
                map.put(dropVal, dropCnt - 1);
            }
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            ans[i - k + 1] = map.lastKey();
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
//        test(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3, new int[]{3, 3, 5, 5, 6, 7});
        test(new int[]{3, 2, 4, 0}, 2, new int[]{3, 4, 4});
//        test(new int[]{1}, 1, new int[]{1});
    }

    private static void test(int[] nums, int k, int[] expect) {
        Solution solution = new Solution();
        int[] output = solution.maxSlidingWindow(nums, k);
        String desc = String.format("max sliding window nums=%s, k=%d", Arrays.toString(nums), k);
        LeetCodeUtils.test(desc, output, expect);
    }
}
