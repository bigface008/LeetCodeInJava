package org.leetcode.p0432;

import java.util.*;

// https://leetcode.com/problems/all-oone-data-structure/?envType=daily-question&envId=2024-09-29
class AllOne {
    static class Node {
        Node left;
        Node right;
        int cnt;
        Set<String> set = new HashSet<>();

        Node(int cnt) {
            this.cnt = cnt;
        }
    }

    Node head, tail;
    Map<String, Node> map = new HashMap<>();

    AllOne() {
        head = new Node(-1000);
        tail = new Node(-1000);
        head.right = tail;
        tail.left = head;
    }

    public void inc(String key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.set.remove(key);
            int cnt = node.cnt;
            Node next = null;
            if (node.right.cnt == cnt + 1) {
                next = node.right;
            } else {
                next = new Node(cnt + 1);
                next.right = node.right;
                next.left = node;
                node.right.left = next;
                node.right = next;
            }
            next.set.add(key);
            map.put(key, next);
            clearIfNeeded(node);
        } else {
            Node node = null;
            if (head.right.cnt == 1) {
                node = head.right;
            } else {
                node = new Node(1);
                node.right = head.right;
                node.left = head;
                head.right.left = node;
                head.right = node;
            }
            node.set.add(key);
            map.put(key, node);
        }
    }

    public void dec(String key) {
        Node node = map.get(key);
        if (node == null) {
            return;
        }
        int cnt = node.cnt - 1;
        node.set.remove(key);
        if (cnt == 0) {
            map.remove(key);
        } else {
            Node prev = null;
            if (cnt == node.left.cnt) {
                prev = node.left;
            } else {
                prev = new Node(cnt);
                prev.right = node;
                prev.left = node.left;
                node.left.right = prev;
                node.left = prev;
            }
            prev.set.add(key);
            map.put(key, prev);
        }
        clearIfNeeded(node);
    }

    public String getMaxKey() {
        for (String s : tail.left.set) {
            return s;
        }
        return "";
    }

    public String getMinKey() {
        for (String s : head.right.set) {
            return s;
        }
        return "";
    }

    void clearIfNeeded(Node node) {
        if (node.set.isEmpty()) {
            node.left.right = node.right;
            node.right.left = node.left;
        }
    }
}

//class AllOne {
//    static class Node {
//        Node left;
//        Node right;
//        int val;
//        int add;
//    }
//
//    AllOne() {}
//
//    public void inc(String key) {}
//
//    public void dec(String key) {}
//
//    public String getMaxKey() {}
//
//    public String getMinKey() {}
//
//    private void update(Node node, int start, int end, int l, int r, int val) {
//        if (l <= start && end <= r) {
//
//        }
//    }
//
//    private int find(Node node, int start, int end, int l, int r) {
//        if (l <= start && end <= r) {
//            return node.val;
//        }
//        int mid = (start + end) / 2;
//        int ans = 0;
//        if (l <= mid) {
//            ans = find(node.left, start, mid, l, r);
//        }
//        if (mid < r) {
//            ans = Math.min(ans, find(node.right, mid + 1, end, l, r));
//        }
//        return ans;
//    }
//
//    private void pushDown(Node node) {
//        if (node.left == null) {
//            node.left = new Node();
//        }
//        if (node.right == null) {
//            node.right = new Node();
//        }
//        if (node.add == 0) {
//            return;
//        }
//        node.left.val += node.add;
//        node.right.val += node.add;
//        node.left.add += node.add;
//        node.right.add += node.add;
//        node.add = 0;
//    }
//
//    private void pushUp(Node node) {
//        node.val = node.left.val + node.right.val;
//    }
//}


//class AllOne {
//    static class Pair {
//        int cnt;
//        String str;
//
//        Pair(int cnt, String str) {
//            this.str = str;
//            this.cnt = cnt;
//        }
//    }
//
//    Map<String, Pair> map = new HashMap<>();
//    Pair minPair1 = null;
//    Pair minPair2 = null;
//    Pair maxPair1 = null;
//    Pair maxPair2 = null;
//
//    public AllOne() {}
//
//    public void inc(String key) {
//        int cnt = 0;
//        Pair p;
//        if (map.containsKey(key)) {
//            p = map.get(key);
//            cnt = p.cnt + 1;
//        } else {
//            p = new Pair(1, key);
//            map.put(key, p);
//            cnt = 1;
//        }
//        p.cnt = cnt;
//        if (minPair1 == null) {
//            minPair1 = p;
//            maxPair1 = p;
//            return;
//        }
//        if (minPair2 == null) {
//            if (key.equals(minPair1.str)) {
//                return;
//            }
//            if (minPair1.cnt > cnt) {
//                minPair2 = minPair1;
//                minPair1 = p;
//                maxPair2 = p;
//            } else if (minPair1.cnt == cnt) {
//                minPair2 = p;
//                maxPair2 = p;
//            }
//            return;
//        }
//        if (maxPair1.cnt < cnt) {
//            maxPair1 = p;
//        } else if (maxPair2.cnt < cnt) {
//            maxPair2 = p;
//        }
//    }
//
//    public void dec(String key) {
//        int cnt = 0;
//        Pair p;
//        if (map.containsKey(key)) {
//            p = map.get(key);
//            cnt = p.cnt - 1;
//            p.cnt = cnt;
//            if (cnt == 0) {
//                map.remove(key);
//            }
//        } else {
//            return; // impossible!
//        }
//        if (cnt == 0) {
//            if (minPair2 == null) {
//                minPair1 = null;
//                maxPair1 = null;
//            } else {
//                if (key.equals(minPair1.str)) {
//                    minPair1 = null;
//                    maxPair1 = null;
//                }
//            }
//            return;
//        }
//        if (minPair1.cnt > cnt) {
//            minPair1 = p;
//        } else if (minPair2.cnt > cnt) {
//            minPair2 = p;
//        }
//    }
//
//    public String getMaxKey() {
//        if (maxPair1 == null) {
//            return "";
//        } else {
//            return maxPair1.str;
//        }
//    }
//
//    public String getMinKey() {
//        if (minPair1 == null) {
//            return "";
//        } else {
//            return minPair1.str;
//        }
//    }
//}

public class Main {
    public static void main(String[] args) {
        {
            AllOne allOne = new AllOne();
            allOne.inc("hello");
            allOne.inc("goodbye");
            allOne.inc("hello");
            allOne.inc("hello");

            allOne.inc("leet");
            allOne.inc("code");
            allOne.inc("leet");

            allOne.dec("hello");

            allOne.inc("leet");
            allOne.inc("code");
            allOne.inc("code");

            System.out.println(allOne.getMaxKey().equals("leet"));
        }
//        {
//            AllOne allOne = new AllOne();
//            allOne.inc("hello");
//            allOne.inc("hello");
//            allOne.inc("leet");
//            allOne.getMinKey();
//        }
//        {
//            AllOne allOne = new AllOne();
//            allOne.inc("a");
//            allOne.inc("b");
//            allOne.inc("b");
//            allOne.inc("c");
//            allOne.inc("c");
//            allOne.inc("c");
//            allOne.dec("b");
//            allOne.dec("b");
//            allOne.getMinKey();
//        }
    }
}