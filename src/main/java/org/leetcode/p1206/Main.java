package org.leetcode.p1206;

// https://leetcode.com/problems/design-skiplist/
class Skiplist {
    static class SkipNode {
        int val;
        SkipNode[] next;

        SkipNode(int val, int levelCnt) {
            this.val = val;
            this.next = new SkipNode[levelCnt];
        }
    }

    static final int MAX_LEVEL = 32;
    int levelCnt = 0;
    SkipNode head = new SkipNode(Integer.MIN_VALUE, MAX_LEVEL);

    Skiplist() {}

    public boolean search(int target) {
        SkipNode node = head;
        for (int i = levelCnt - 1; i >= 0; i--) {
            node = findNearest(node, i, target);
            if (node.next[i] != null && node.next[i].val == target) {
                return true;
            }
        }
        return false;
    }

    public void add(int num) {
        if (search(num)) {
            return;
        }

        final int level = genLevel();
        SkipNode newNode = new SkipNode(num, level);
        SkipNode node = head;
        for (int i = levelCnt - 1; i >= 0; i--) {
            node = findNearest(node, i, num);
//            if (i == levelCnt - 1 && node.next[i] != null && node.next[i].val == num) {
//                return;
//            }
            if (i < level) {
                if (node.next[i] == null) {
                    node.next[i] = newNode;
                } else {
                    SkipNode temp = node.next[i];
                    node.next[i] = newNode;
                    newNode.next[i] = temp;
                }
            }
        }
        if (level > levelCnt) {
            for (int i = levelCnt; i < level; i++) {
                head.next[i] = newNode;
            }
            levelCnt = level;
        }
    }

    public boolean erase(int num) {
        boolean found = false;
        SkipNode node = head;
        for (int i = levelCnt - 1; i >= 0; i--) {
            node = findNearest(node, i, num);
            if (node.next[i] != null && node.next[i].val == num) {
                node.next[i] = node.next[i].next[i];
                found = true;
            }
        }
        return found;
    }

    private SkipNode findNearest(SkipNode node, int level, int target) {
        while (node.next[level] != null && node.next[level].val < target) {
            node = node.next[level];
        }
        return node;
    }

    private int genLevel() {
        int level = 1;
        while (level < MAX_LEVEL && Math.random() < 0.5) {
            level++;
        }
        return level;
    }
}

public class Main {
    public static void main(String[] args) {
        Skiplist skiplist = new Skiplist();
        skiplist.add(1);
        skiplist.add(2);
        skiplist.add(3);
        skiplist.add(4);
        skiplist.add(4);
//        skiplist.search(0); // return False
//        skiplist.add(4);
//        skiplist.search(1); // return True
//        skiplist.erase(0);  // return False, 0 is not in skiplist.
//        skiplist.erase(1);  // return True
//        skiplist.search(1); // return False, 1 has already been erased.
    }
}
