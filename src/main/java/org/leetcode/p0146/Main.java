package org.leetcode.p0146;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

// https://leetcode.com/problems/lru-cache/
class LRUCache {
    static class Node {
        int key;
        int val;
        Node prev;
        Node next;
    }

    static class NodeList {
        Node head;
        Node tail;

        NodeList() {
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.prev = head;
        }

        void remove(Node node) {
            Node prev = node.prev;
            Node next = node.next;
            if (prev != null) {
                prev.next = next;
            }
            if (next != null) {
                next.prev = prev;
            }
            node.prev = null;
            node.next = null;
        }

        void addFirst(Node node) {
            Node next = head.next;
            node.next = next;
            head.next = node;
            node.prev = head;
            if (next != null) {
                next.prev = node;
            }
        }

        Node removeLast() {
            Node last = tail.prev;
            tail.prev = last.prev;
            if (last.prev != null) {
                last.prev.next = tail;
            }
            last.prev = null;
            last.next = null;
            return last;
        }
    }

    int capacity;
    Map<Integer, Node> map = new HashMap<>();
    NodeList list = new NodeList();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        list.remove(node);
        list.addFirst(node);
        return node.val;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node == null) {
            if (map.size() == capacity) {
                Node drop = list.removeLast();
                map.remove(drop.key);
            }
            node = new Node();
            node.key = key;
            node.val = value;
            map.put(key, node);
            list.addFirst(node);
        } else {
            node.val = value;
            list.remove(node);
            list.addFirst(node);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);
        cache.put(3, 3);
        cache.get(2);
        cache.put(4, 4);
        cache.get(1);
        cache.get(3);
        cache.get(4);
    }
}
