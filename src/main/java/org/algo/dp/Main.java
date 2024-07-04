package org.algo.dp;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

class CanSum {
    static void test() {
        System.out.println(solution(new int[]{2, 5, 5}, 8));
        System.out.println(solution(new int[]{2, 5, 5}, 14));
    }

    private static boolean solution(int[] nums, int target) {
        HashMap<Integer, Boolean> memo = new HashMap<>();
        return rec(nums, target, memo);
    }

    private static boolean rec(int[] nums, int target, HashMap<Integer, Boolean> memo) {
        if (target == 0) {
            return true;
        }
        if (target < 0) {
            return false;
        }
        if (memo.containsKey(target)) {
            return memo.get(target);
        }
        for (int num : nums) {
            int remain = target - num;
            if (rec(nums, remain, memo)) {
                memo.put(target, true);
                return true;
            }
        }
        memo.put(target, false);
        return false;
    }
}

class HowSum {
    static void test() {
        System.out.println(solution(new int[]{3, 2}, 7));
        System.out.println(solution(new int[]{2, 5, 5}, 7));
    }

    private static List<Integer> solution(int[] nums, int target) {
        HashMap<Integer, List<Integer>> memo = new HashMap<>();
        return rec(nums, target, memo);
    }

    private static List<Integer> rec(int[] nums, int target, HashMap<Integer, List<Integer>> memo) {
        if (target == 0) {
            return new LinkedList<>();
        }
        if (target < 0) {
            return null;
        }
        if (memo.containsKey(target)) {
            return memo.get(target);
        }
        for (int num : nums) {
            int remain = target - num;
            List<Integer> res = rec(nums, remain, memo);
            if (res != null) {
                List<Integer> temp = new LinkedList<>(res);
                temp.add(num);
                memo.put(target, temp);
                return temp;
            }
        }
        memo.put(target, null);
        return null;
    }
}

class ShortSum {
    static void test() {
//        System.out.println(solution(new int[]{2, 3}, 8));
//        System.out.println(solution(new int[]{2, 5, 5}, 7));
        System.out.println(solution(new int[]{5, 10, 20}, 40));
        System.out.println(solution(new int[]{1, 5, 25}, 100));
    }

    private static List<Integer> solution(int[] nums, int target) {
        HashMap<Integer, List<Integer>> memo = new HashMap<>();
        return rec(nums, target, memo);
    }

    private static List<Integer> rec(int[] nums, int target, HashMap<Integer, List<Integer>> memo) {
        if (target == 0) {
            return new LinkedList<>();
        }
        if (target < 0) {
            return null;
        }
        if (memo.containsKey(target)) {
            return memo.get(target);
        }
        List<Integer> shortest = null;
        for (int num : nums) {
            int remain = target - num;
            List<Integer> res = rec(nums, remain, memo);
            if (res == null) {
                continue;
            }
            List<Integer> temp = new LinkedList<>(res);
            temp.add(num);
            if (shortest == null || shortest.size() > temp.size()) {
                shortest = temp;
            }
        }
        memo.put(target, shortest);
        return shortest;
    }
}

class CanConstruct {
    static void test() {
        System.out.println(solution("abcdef", List.of("ab", "abc", "cd", "def", "abcd")));
        System.out.println(solution("skateboard", List.of("bo", "rd", "ate", "t", "ska", "sk", "boar")));
    }

    static boolean solution(String target, List<String> strs) {
        HashMap<String, Boolean> memo = new HashMap<>();
        return rec(target, strs, memo);
    }

    static boolean rec(String target, List<String> strs, HashMap<String, Boolean> memo) {
        if (target.isEmpty()) {
            return true;
        }
        if (strs.isEmpty()) {
            return false;
        }
        if (memo.containsKey(target)) {
            return memo.get(target);
        }
        for (int i = 0; i < strs.size(); i++) {
            String s = strs.get(i);
            String remainTarget = null;
            if (target.startsWith(s)) {
                remainTarget = target.substring(s.length());
            } else if (target.endsWith(s)) {
                remainTarget = target.substring(0, target.length() - s.length());
            }
            if (remainTarget == null) {
                continue;
            }
            List<String> remainStrs = new LinkedList<>();
            for (int j = 0; j < strs.size(); j++) {
                if (j == i) {
                    continue;
                }
                remainStrs.add(strs.get(j));
            }
            if (rec(remainTarget, remainStrs, memo)) {
                memo.put(target, true);
                return true;
            }
        }
        memo.put(target, false);
        return false;
    }
}

class CountConstruct {
    static void test() {
        System.out.println(solution("purple", List.of("purp", "p", "ur", "le", "purpl")));
        System.out.println(solution("abcdef", List.of("ab", "abc", "cd", "def", "abcd")));
        System.out.println(solution("skateboard", List.of("bo", "rd", "ate", "t", "ska", "sk", "boar")));
        System.out.println(solution("enterapotentpot", List.of("a", "p", "ent", "enter", "ot", "o", "t")));
//        System.out.println(solution("skateboard", List.of("bo", "rd", "ate", "t", "ska", "sk", "boar")));
    }

    static int solution(String target, List<String> strs) {
        HashMap<String, Integer> memo = new HashMap<>();
        return rec(target, strs, memo);
    }

    static int rec(String target, List<String> strs, HashMap<String, Integer> memo) {
        if (target.isEmpty()) {
            return 1;
        }
        if (memo.containsKey(target)) {
            return memo.get(target);
        }
        int total = 0;
        for (int i = 0; i < strs.size(); i++) {
            String s = strs.get(i);
            if (target.startsWith(s)) {
                String remainTarget = target.substring(s.length());
                total += rec(remainTarget, strs, memo);
            }
        }
        memo.put(target, total);
        return total;
    }
}

class AllConstruct {
    static void test() {
        System.out.println(solution("purple", List.of("purp", "p", "ur", "le", "purpl")));
        System.out.println(solution("abcdef", List.of("ab", "abc", "cd", "def", "abcd")));
        System.out.println(solution("skateboard", List.of("bo", "rd", "ate", "t", "ska", "sk", "boar")));
        System.out.println(solution("enterapotentpot", List.of("a", "p", "ent", "enter", "ot", "o", "t")));
    }

    static List<List<String>> solution(String target, List<String> strs) {
        HashMap<String, List<List<String>>> memo = new HashMap<>();
        return rec(target, strs, memo);
    }

    static List<List<String>> rec(String target, List<String> words, HashMap<String, List<List<String>>> memo) {
        if (target.isEmpty()) {
            return List.of(new LinkedList<>());
        }
        if (memo.containsKey(target)) {
            return memo.get(target);
        }
        List<List<String>> ways = new LinkedList<>();
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            if (target.startsWith(word)) {
                String remainTarget = target.substring(word.length());
                List<List<String>> remainTargetWays = rec(remainTarget, words, memo);
                for (List<String> way : remainTargetWays) {
                    List<String> temp = new LinkedList<>();
                    temp.add(word);
                    temp.addAll(way);
                    ways.add(temp);
                }
            }
        }
        memo.put(target, ways);
        return ways;
    }
}

public class Main {
    public static void main(String[] args) {
//        CanSum.test();
//        HowSum.test();
//        ShortSum.test();
//        CanConstruct.test();
//        CountConstruct.test();
        AllConstruct.test();
    }
}
