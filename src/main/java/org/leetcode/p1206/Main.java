package org.leetcode.p1206;

// https://leetcode.com/problems/design-skiplist/
class Skiplist {

    public Skiplist() {
        // TODO
    }

    public boolean search(int target) {
        return false;
    }

    public void add(int num) {

    }

    public boolean erase(int num) {
        return false;
    }
}

public class Main {
    public static void main(String[] args) {
        Skiplist skiplist = new Skiplist();
        skiplist.add(1);
        skiplist.add(2);
        skiplist.add(3);
        skiplist.search(0); // return False
        skiplist.add(4);
        skiplist.search(1); // return True
        skiplist.erase(0);  // return False, 0 is not in skiplist.
        skiplist.erase(1);  // return True
        skiplist.search(1); // return False, 1 has already been erased.
    }
}
