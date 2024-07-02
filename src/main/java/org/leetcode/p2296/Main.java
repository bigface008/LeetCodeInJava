package org.leetcode.p2296;

import java.util.LinkedList;

// https://leetcode.com/problems/design-a-text-editor/description/
class TextEditor {
    class Node {
        char ch;
        Node prev;
        Node next;

        Node() {
        }

        Node(char ch) {
            this.ch = ch;
        }
    }

    Node head;
    Node cursor;

    public TextEditor() {
        head = new Node();
        cursor = head;
    }

    public void addText(String text) {
        for (int i = 0; i < text.length(); i++) {
            Node next = cursor.next;
            Node newNode = new Node(text.charAt(i));
            newNode.next = next;
            newNode.prev = cursor;
            if (next != null) {
                next.prev = newNode;
            }
            cursor.next = newNode;
            cursor = cursor.next;
        }
    }

    public int deleteText(int k) {
        int cnt = 0;
        Node after = cursor.next;
        while (cnt < k && cursor != head) {
            cursor = cursor.prev;
            cnt++;
        }
        cursor.next = after;
        after.prev = cursor;
        return cnt;
    }

    public String cursorLeft(int k) {
        int cnt = 0;
        while (cnt < k && cursor != head) {
            cursor = cursor.prev;
            cnt++;
        }
        return getCursorLeftStr();
    }

    public String cursorRight(int k) {
        int cnt = 0;
        while (cnt < k && cursor.next != null) {
            cursor = cursor.next;
            cnt++;
        }
        return getCursorLeftStr();
    }

    String getCursorLeftStr() {
        Node node = cursor;
        int cnt = 0;
        StringBuilder builder = new StringBuilder();
        while (cnt < 10 && node != head) {
            builder.insert(0, node.ch);
            node = node.prev;
            cnt++;
        }
        return builder.toString();
    }
}


public class Main {
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor(); // The current text is "|". (The '|' character represents the cursor)
        textEditor.addText("leetcode"); // The current text is "leetcode|".
        textEditor.deleteText(4); // return 4
        // The current text is "leet|".
        // 4 characters were deleted.
        textEditor.addText("practice"); // The current text is "leetpractice|".
        System.out.println(textEditor.cursorRight(3)); // return "etpractice"
        // The current text is "leetpractice|".
        // The cursor cannot be moved beyond the actual text and thus did not move.
        // "etpractice" is the last 10 characters to the left of the cursor.
        System.out.println(textEditor.cursorLeft(8)); // return "leet"
        // The current text is "leet|practice".
        // "leet" is the last min(10, 4) = 4 characters to the left of the cursor.
        System.out.println(textEditor.deleteText(10)); // return 4
        // The current text is "|practice".
        // Only 4 characters were deleted.
        System.out.println(textEditor.cursorLeft(2)); // return ""
        // The current text is "|practice".
        // The cursor cannot be moved beyond the actual text and thus did not move.
        // "" is the last min(10, 0) = 0 characters to the left of the cursor.
        System.out.println(textEditor.cursorRight(6)); // return "practi"
        // The current text is "practi|ce".
        // "practi" is the last min(10, 6) = 6 characters to the left of the cursor.
    }
}
