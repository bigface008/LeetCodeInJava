package org.leetcode.p2227;

import java.util.*;

class Encrypter {
    private String[] map = new String[26];
    private Map<String, Integer> cnt = new HashMap<>();

    public Encrypter(char[] keys, String[] values, String[] dictionary) {
        for (int i = 0; i < keys.length; i++) {
            map[keys[i] - 'a'] = values[i];
        }
        for (var s : dictionary) {
            var e = encrypt(s);
            cnt.put(e, cnt.getOrDefault(e, 0) + 1);
        }
    }

    public String encrypt(String word1) {
        var res = new StringBuilder();
        for (int i = 0; i < word1.length(); i++) {
            var s = map[word1.charAt(i) - 'a'];
            if (s == null) {
                return "";
            }
            res.append(s);
        }
        return res.toString();
    }

    public int decrypt(String word2) {
        return cnt.getOrDefault(word2, 0);
    }
}

// 用前缀树的普通方法
class EncrypterV2 {
    private char[] keys;
    private String[] values;
    private HashSet<String> dictionary;
    private TrieTree tree;

    private HashMap<Character, Integer> keysMap;
    private HashMap<String, HashSet<Integer>> valuesMap;

    public EncrypterV2(char[] keys, String[] values, String[] dictionary) {
        this.keys = keys;
        this.values = values;
        this.dictionary = new HashSet<>();

        keysMap = new HashMap<>();
        for (int i = 0; i < keys.length; i++) {
            keysMap.put(keys[i], i);
        }
        valuesMap = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            valuesMap.computeIfAbsent(values[i], k -> new HashSet<Integer>()).add(i);
        }
        tree = new TrieTree();
        for (String word : dictionary) {
            tree.add(word);
        }
//        this.dictionary.addAll(Arrays.asList(dictionary));
    }

    public String encrypt(String word1) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < word1.length(); i++) {
            char ch = word1.charAt(i);
            int idx = keysMap.getOrDefault(ch, -1);
            if (idx == -1) {
                return "";
            }
            builder.append(values[idx]);
        }
        return builder.toString();
    }

    public int decrypt(String word2) {
        ans = 0;
        List<List<Character>> list = new ArrayList<>(word2.length());
        for (int i = 0; i < word2.length(); i += 2) {
            String subStr = word2.substring(i, Math.min(i + 2, word2.length()));
            HashSet<Integer> indices = valuesMap.get(subStr);
            if (indices == null) {
                return 0;
            }
            List<Character> curr = new ArrayList<>(indices.size());
            for (int idx : indices) {
                curr.add(keys[idx]);
            }
            list.add(curr);
        }
        backtrack(tree.root, 0, list);
        return ans;
    }

    int ans = 0;

    void backtrack(TrieNode node, int idx, List<List<Character>> list) {
        if (idx == list.size()) { // TODO: idx val?
            if (node.isWord) {
                ans++;
            }
            return;
        }
        List<Character> chList = list.get(idx);
        for (char ch : chList) {
            int offset = ch - 'a';
            if (node.children[offset] != null) {
                backtrack(node.children[offset], idx + 1, list);
            }
        }
    }

    class TrieNode {
        boolean isWord;
        TrieNode[] children;

        TrieNode() {
            isWord = false;
            children = new TrieNode[26];
        }
    }

    class TrieTree {
        static char[] chArr = "abcdefghijklmnopqrstuvwxyz".toCharArray();

        static final int TREE_CNT = 26;

        TrieNode root;

        TrieTree() {
            root = new TrieNode();
        }

        void add(String s) {
            TrieNode n = root;
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                int offset = ch - 'a';
                if (n.children[offset] == null) {
                    n.children[offset] = new TrieNode();
                }
                n = n.children[offset];
            }
            n.isWord = true;
        }

        boolean find(String s) {
            TrieNode n = root;
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                int offset = ch - 'a';
                if (n.children[offset] == null) {
                    return false;
                }
                n = n.children[offset];
            }
            return n.isWord;
        }
    }
}

public class Main {
    public static void main(String[] args) {
//        {
//            char[] keys = new char[]{'a', 'b', 'c', 'd'};
//            String[] values = new String[]{"ei", "zf", "ei", "am"};
//            String[] dictionary = new String[]{"abcd", "acbd", "adbc", "badc", "dacb", "cadb", "cbda", "abad"};
//            Encrypter encrypter = new Encrypter(keys, values, dictionary);
//            System.out.println(encrypter.encrypt("abcd"));
//            System.out.println(encrypter.decrypt("eizfeiam"));
//        }

        {
            char[] keys = new char[]{'a', 'b', 'c', 'd'};
            String[] values = new String[]{"aa","bb","cc","zz"};
            String[] dictionary = new String[]{"aa","aaa","aaaa","aaaaa","aaaaaaa"};
            Encrypter encrypter = new Encrypter(keys, values, dictionary);
            System.out.println(encrypter.decrypt("aaaa"));
            System.out.println(encrypter.decrypt("aa"));
//            System.out.println(encrypter.decrypt("aaaa"));
//            System.out.println(encrypter.decrypt("aaaaaaaa"));
//            System.out.println(encrypter.decrypt("aaaaaaaaaaaaaa"));
//            System.out.println(encrypter.decrypt("aefagafvabfgshdthn"));
        }
    }


}
