package org.leetcode.p2296;

import java.util.LinkedList;
import java.util.ListIterator;

// https://leetcode.com/problems/design-a-text-editor/description/
//class TextEditor {
//    class Node {
//        char ch;
//        Node prev;
//        Node next;
//
//        Node() {
//        }
//
//        Node(char ch) {
//            this.ch = ch;
//        }
//    }
//
//    Node head;
//    Node cursor;
//
//    public TextEditor() {
//        head = new Node();
//        cursor = head;
//    }
//
//    public void addText(String text) {
//        for (int i = 0; i < text.length(); i++) {
//            Node next = cursor.next;
//            Node newNode = new Node(text.charAt(i));
//            newNode.next = next;
//            newNode.prev = cursor;
//            if (next != null) {
//                next.prev = newNode;
//            }
//            cursor.next = newNode;
//            cursor = cursor.next;
//        }
//    }
//
//    public int deleteText(int k) {
//        int cnt = 0;
//        Node after = cursor.next;
//        while (cnt < k && cursor != head) {
//            cursor = cursor.prev;
//            cnt++;
//        }
//        cursor.next = after;
//        after.prev = cursor;
//        return cnt;
//    }
//
//    public String cursorLeft(int k) {
//        int cnt = 0;
//        while (cnt < k && cursor != head) {
//            cursor = cursor.prev;
//            cnt++;
//        }
//        return getCursorLeftStr();
//    }
//
//    public String cursorRight(int k) {
//        int cnt = 0;
//        while (cnt < k && cursor.next != null) {
//            cursor = cursor.next;
//            cnt++;
//        }
//        return getCursorLeftStr();
//    }
//
//    String getCursorLeftStr() {
//        Node node = cursor;
//        int cnt = 0;
//        StringBuilder builder = new StringBuilder();
//        while (cnt < 10 && node != head) {
//            builder.insert(0, node.ch);
//            node = node.prev;
//            cnt++;
//        }
//        return builder.toString();
//    }
//}

class TextEditor {
    LinkedList<Character> content = new LinkedList<>();
    ListIterator<Character> it;

    public TextEditor() {
        it = content.listIterator();
    }

    public void addText(String text) {
        for (char ch : text.toCharArray()) {
            it.add(ch);
        }
    }

    public int deleteText(int k) {
        int step = 0;
        while (step < k && it.hasPrevious()) {
            it.previous();
            it.remove();
//            it.previous();
            step++;
        }
        return step;
    }

    public String cursorLeft(int k) {
        int step = 0;
        while (step < k && it.hasPrevious()) {
            it.previous();
            Character ch = it.next();
            it.previous();
            step++;
        }
        int curr = it.nextIndex();
        final int len = Math.min(10, curr);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            it.previous();
        }
        for (int i = 0; i < len; i++) {
            builder.append(it.next());
        }
        return builder.toString();
    }

    public String cursorRight(int k) {
        for (int i = 0; i < k && it.hasNext(); i++) {
            it.next();
        }
        int curr = it.nextIndex();
        final int len = Math.min(10, curr);
        for (int i = 0; i < len; i++) {
            it.previous();
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            builder.append(it.next());
        }
        return builder.toString();
    }
}


public class Main {
    public static void main(String[] args) {
//        LinkedList<Character> list = new LinkedList<>();
//        list.add('1');
//        list.add('2');
//        list.add('3');
//        list.add('4');
//        list.add('5');
//        list.add('6');
//        ListIterator<Character> it = list.listIterator();
//        it.next();
        try {
            TextEditor textEditor = new TextEditor(); // 当前 text 为 "|" 。（'|' 字符表示光标）
            textEditor.addText("leetcode"); // 当前文本为 "leetcode|" 。
            textEditor.deleteText(4); // 返回 4
            // 当前文本为 "leet|" 。
            // 删除了 4 个字符。
            textEditor.addText("practice"); // 当前文本为 "leetpractice|" 。
            textEditor.cursorRight(3); // 返回 "etpractice"
            // 当前文本为 "leetpractice|".
            // 光标无法移动到文本以外，所以无法移动。
            // "etpractice" 是光标左边的 10 个字符。
            textEditor.cursorLeft(8); // 返回 "leet"
            // 当前文本为 "leet|practice" 。
            // "leet" 是光标左边的 min(10, 4) = 4 个字符。
            textEditor.deleteText(10); // 返回 4
            // 当前文本为 "|practice" 。
            // 只有 4 个字符被删除了。
            textEditor.cursorLeft(2); // 返回 ""
            // 当前文本为 "|practice" 。
            // 光标无法移动到文本以外，所以无法移动。
            // "" 是光标左边的 min(10, 0) = 0 个字符。
            textEditor.cursorRight(6); // 返回 "practi"
            // 当前文本为 "practi|ce" 。
            // "practi" 是光标左边的 min(10, 6) = 6 个字符。
        } catch (Exception e) {
            e.printStackTrace();
        }

//        TextEditor textEditor = new TextEditor(); // The current text is "|". (The '|' character represents the cursor)
//        textEditor.addText("leetcode"); // The current text is "leetcode|".
//        textEditor.deleteText(4); // return 4
//        // The current text is "leet|".
//        // 4 characters were deleted.
//        textEditor.addText("practice"); // The current text is "leetpractice|".
//        System.out.println(textEditor.cursorRight(3)); // return "etpractice"
//        // The current text is "leetpractice|".
//        // The cursor cannot be moved beyond the actual text and thus did not move.
//        // "etpractice" is the last 10 characters to the left of the cursor.
//        System.out.println(textEditor.cursorLeft(8)); // return "leet"
//        // The current text is "leet|practice".
//        // "leet" is the last min(10, 4) = 4 characters to the left of the cursor.
//        System.out.println(textEditor.deleteText(10)); // return 4
//        // The current text is "|practice".
//        // Only 4 characters were deleted.
//        System.out.println(textEditor.cursorLeft(2)); // return ""
//        // The current text is "|practice".
//        // The cursor cannot be moved beyond the actual text and thus did not move.
//        // "" is the last min(10, 0) = 0 characters to the left of the cursor.
//        System.out.println(textEditor.cursorRight(6)); // return "practi"
//        // The current text is "practi|ce".
//        // "practi" is the last min(10, 6) = 6 characters to the left of the cursor.
    }
}
