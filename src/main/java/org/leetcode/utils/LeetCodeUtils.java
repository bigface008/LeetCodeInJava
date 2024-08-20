package org.leetcode.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class LeetCodeUtils {
    public static <T> void test(String fmt, T output, T expect) {
        StringBuilder builder = new StringBuilder();
        if (output.equals(expect)) {
            builder.append("[PASSED] ");
        } else {
            builder.append("[FAILED] ");
        }
        builder.append(fmt);
        builder.append(" output=");
        builder.append(output);
        builder.append(' ');
        builder.append("expect=");
        builder.append(expect);
        System.out.printf(builder.toString());
        System.out.println();
    }

    public static void test(String fmt, int[] output, int[] expect) {
        StringBuilder builder = new StringBuilder();
        if (Arrays.equals(output, expect)) {
            builder.append("[PASSED] ");
        } else {
            builder.append("[FAILED] ");
        }
        builder.append(fmt);
        builder.append(" output=");
        builder.append(Arrays.toString(output));
        builder.append(' ');
        builder.append("expect=");
        builder.append(Arrays.toString(expect));
        System.out.printf(builder.toString());
        System.out.println();
    }

    public static void test(String fmt, Object[] output, Object[] expect) {
        StringBuilder builder = new StringBuilder();
        if (Arrays.equals(output, expect)) {
            builder.append("[PASSED] ");
        } else {
            builder.append("[FAILED] ");
        }
        builder.append(fmt);
        builder.append(" output=");
        builder.append(Arrays.toString(output));
        builder.append(' ');
        builder.append("expect=");
        builder.append(Arrays.toString(expect));
        System.out.printf(builder.toString());
        System.out.println();
    }

    public static void test(String fmt, int[][] output, int[][] expect) {
        StringBuilder builder = new StringBuilder();
        if (Arrays.deepEquals(output, expect)) {
            builder.append("[PASSED] ");
        } else {
            builder.append("[FAILED] ");
        }
        builder.append(fmt);
        builder.append("\n output=");
        builder.append(Arrays.deepToString(output));
        builder.append('\n');
        builder.append(" expect=");
        builder.append(Arrays.deepToString(expect));
        System.out.printf(builder.toString());
        System.out.println();
    }

    public static List<Integer> makeIntList(String input) {
        List<Integer> res = new ArrayList<>();
        int i = 0;
        while (i < input.length()) {
            char c = input.charAt(i);
            if (Character.isDigit(c) || c == '-') {
                int j = i + 1;
                for (; j < input.length(); j++) {
                    if (!Character.isDigit(input.charAt(j))) {
                        break;
                    }
                }
                String numStr = input.substring(i, j);
                int val = Integer.parseInt(numStr);
                res.add(val);
                i = j;
            } else {
                i++;
            }
        }
        return res;
    }

    public static List<List<Integer>> make2DIntList(String input) {
        int i = 0;
        int level = 0;
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        while (i < input.length()) {
            char c = input.charAt(i);
            if (c == '[') {
                level++;
            } else if (c == ']') {
                if (level == 2) {
                    res.add(temp);
                    temp = new ArrayList<>();
                }
                level--;
            } else {
                if (level == 2) {
                    if (Character.isDigit(c) || c == '-') {
                        int j = i + 1;
                        for (; j < input.length(); j++) {
                            if (!Character.isDigit(input.charAt(j))) {
                                break;
                            }
                        }
                        String numStr = input.substring(i, j);
//                        System.out.printf("i %d j %d numStr %s\n", i, j, numStr);
                        int val = Integer.parseInt(numStr);
                        temp.add(val);
                        i = j;
                        continue;
                    }
                }
            }
            i++;
        }
        return res;
    }

    public static int[][] make2DIntArray(String input) {
        List<List<Integer>> list = make2DIntList(input);
        int rowCnt = list.size();
        int colCnt = 0;
        if (!list.isEmpty()) {
            colCnt = list.get(0).size();
        }
        int[][] res = new int[rowCnt][colCnt];
        for (int i = 0; i < rowCnt; i++) {
            for (int j = 0; j < colCnt; j++) {
                res[i][j] = list.get(i).get(j);
            }
        }
        return res;
    }

    public static char[][] make2DCharArray(String input) {
        List<List<Character>> list = make2DCharList(input);
        char[][] res = new char[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            int rowLen = list.get(i).size();
            res[i] = new char[rowLen];
            for (int j = 0; j < rowLen; j++) {
                res[i][j] = list.get(i).get(j);
            }
        }
        return res;
    }

    public static List<List<String>> make2DStringList(String input) {
        int i = 0;
        int level = 0;
        List<List<String>> res = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        while (i < input.length()) {
            char c = input.charAt(i);
            if (c == '[') {
                level++;
            } else if (c == ']') {
                if (level == 2) {
                    res.add(temp);
                    temp = new ArrayList<>();
                }
                level--;
            } else {
                if (level == 2) {
                    if (c == '"') {
                        int j = i + 1;
                        for (; j < input.length(); j++) {
                            if (input.charAt(j) == '"') {
                                break;
                            }
                        }
                        String s = input.substring(i + 1, j);
                        temp.add(s);
                        i = j;
                    }
                }
            }
            i++;
        }
        return res;
    }

    public static List<List<Character>> make2DCharList(String input) {
        int i = 0;
        int level = 0;
        List<List<Character>> res = new ArrayList<>();
        List<Character> temp = new ArrayList<>();
        while (i < input.length()) {
            char c = input.charAt(i);
            if (c == '[') {
                level++;
            } else if (c == ']') {
                if (level == 2) {
                    res.add(temp);
                    temp = new ArrayList<>();
                }
                level--;
            } else {
                if (level == 2) {
                    if (c == '"' || c == '\'') {
                        char nc = input.charAt(i + 1);
                        if (nc == '"' || nc == '\'') {
                            i = i + 2;
                        } else {
                            temp.add(nc);
                            i = i + 3;
                        }
                        continue;
                    }
                }
            }
            i++;
        }
        return res;
    }

    public static List<String> makeStrList(String input) {
        List<String> res = new ArrayList<>();
        return res;
    }

    private static void testMakeIntList() {
        List<Integer> t = LeetCodeUtils.makeIntList("[12, 13, -12, -13, 0, 55]");
        System.out.println(t);
        t = makeIntList("[]");
        System.out.println(t);
    }

    private static void testMake2DIntList() {
        List<List<Integer>> t = LeetCodeUtils.make2DIntList("[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]");
        System.out.println(t);
        t = LeetCodeUtils.make2DIntList("[[-1000, 999, 21, -5],[-123, 123, 44]]");
        System.out.println(t);
        t = LeetCodeUtils.make2DIntList("[[2,2,2,2]]");
        System.out.println(t);
        t = LeetCodeUtils.make2DIntList("[[]]");
        System.out.println(t);
        t = LeetCodeUtils.make2DIntList("[]");
        System.out.println(t);
    }

    private static void testMake2DCharList() {
        List<List<Character>> list = make2DCharList("[[\".\",\".\",\".\",\"B\",\".\",\".\",\".\",\".\"],[\".\",\".\",\".\",\"W\",\".\",\".\",\".\",\".\"],[\".\",\".\",\".\",\"W\",\".\",\".\",\".\",\".\"],[\".\",\".\",\".\",\"W\",\".\",\".\",\".\",\".\"],[\"W\",\"B\",\"B\",\".\",\"W\",\"W\",\"W\",\"B\"],[\".\",\".\",\".\",\"B\",\".\",\".\",\".\",\".\"],[\".\",\".\",\".\",\"B\",\".\",\".\",\".\",\".\"],[\".\",\".\",\".\",\"W\",\".\",\".\",\".\",\".\"]]");
        System.out.println(list);
        char[][] arr = make2DCharArray("[[\".\",\".\",\".\",\"B\",\".\",\".\",\".\",\".\"],[\".\",\".\",\".\",\"W\",\".\",\".\",\".\",\".\"],[\".\",\".\",\".\",\"W\",\".\",\".\",\".\",\".\"],[\".\",\".\",\".\",\"W\",\".\",\".\",\".\",\".\"],[\"W\",\"B\",\"B\",\".\",\"W\",\"W\",\"W\",\"B\"],[\".\",\".\",\".\",\"B\",\".\",\".\",\".\",\".\"],[\".\",\".\",\".\",\"B\",\".\",\".\",\".\",\".\"],[\".\",\".\",\".\",\"W\",\".\",\".\",\".\",\".\"]]");
        System.out.println(Arrays.toString(arr));
    }

    private static void testMake2DStringList() {
        List<List<String>> list = make2DStringList("[[\"John\", \"johnsmith@mail.com\", \"john00@mail.com\"], [\"John\", \"johnnybravo@mail.com\"], [\"John\", \"johnsmith@mail.com\", \"john_newyork@mail.com\"], [\"Mary\", \"mary@mail.com\"]]");
        System.out.println(list);
    }

    public static void main(String[] args) {
//        testMake2DIntList();
//        testMakeIntList();
//        testMake2DCharList();
        testMake2DStringList();
    }
}
