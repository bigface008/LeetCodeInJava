package org.leetcode.p0721;

import org.leetcode.utils.LeetCodeUtils;

import java.util.*;

// https://leetcode.cn/problems/accounts-merge/description/?envType=daily-question&envId=2024-07-15
class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, List<Integer>> emailToIdx = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            List<String> group = accounts.get(i);
            for (int j = 1; j < group.size(); j++) {
                emailToIdx.computeIfAbsent(group.get(j), k -> new ArrayList<>()).add(i);
            }
        }
        List<List<String>> ans = new ArrayList<>();
        boolean[] visited = new boolean[accounts.size()];
        Set<String> emailSet = new HashSet<>();
        for (int i = 0; i < accounts.size(); i++) {
            if (visited[i]) {
                continue;
            }
            emailSet.clear();
            dfs(i, accounts, emailToIdx, visited, emailSet);
            List<String> res = new ArrayList<>(emailSet);
            Collections.sort(res);
            res.add(0, accounts.get(i).get(0));
            ans.add(res);
        }
        return ans;
    }

    private void dfs(int i, List<List<String>> accounts, Map<String, List<Integer>> emailToIdx, boolean[] visited, Set<String> emailSet) {
        visited[i] = true;
        List<String> currAccount = accounts.get(i);
        for (int k = 1; k < currAccount.size(); k++) {
            String email = currAccount.get(k);
            if (emailSet.contains(email)) {
                continue;
            }
            emailSet.add(email);
            for (int j : emailToIdx.get(email)) {
                if (!visited[j]) {
                    dfs(j, accounts, emailToIdx, visited, emailSet);
                }
            }
        }
    }
}

// 这题居然是一个图+dfs的题，所以你这个方法不够好，太慢了
class SolutionV2 {
    static class Info {
        String name;
        Set<String> emails;

        Info(String name, Set<String> emails) {
            this.name = name;
            this.emails = emails;
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Info> mp = new HashMap<>(); // email -> info
        for (List<String> group : accounts) { // group: name, emails
            List<String> commonEmails = new ArrayList<>();
            for (int i = 1; i < group.size(); i++) {
                String email = group.get(i);
                if (mp.containsKey(email)) {
                    commonEmails.add(email);
                }
            }
            if (!commonEmails.isEmpty()) {
                if (commonEmails.size() == 1) {
                    Info info = mp.get(commonEmails.get(0));
                    for (int i = 1; i < group.size(); i++) {
                        String email = group.get(i);
                        info.emails.add(email);
                        mp.put(email, info);
                    }
                } else {
                    // merge info
                    Set<String> emails = new HashSet<>();
                    for (String email : commonEmails) {
                        emails.addAll(mp.get(email).emails);
                    }
                    for (int i = 1; i < group.size(); i++) {
                        emails.add(group.get(i));
                    }
                    Info newInfo = new Info(group.get(0), emails);
                    for (String email : newInfo.emails) {
                        mp.put(email, newInfo);
                    }
                }
            } else {
                Set<String> emails = new HashSet<>();
                for (int j = 1; j < group.size(); j++) {
                    emails.add(group.get(j));
                }
                Info newInfo = new Info(group.get(0), emails);
                for (int j = 1; j < group.size(); j++) {
                    mp.put(group.get(j), newInfo);
                }
            }
        }
        Set<Info> groups = new HashSet<>(mp.values());
        List<List<String>> ans = new ArrayList<>();
        for (Info info : groups) {
            List<String> temp = new ArrayList<>();
            temp.addAll(info.emails);
            Collections.sort(temp);
            temp.add(0, info.name);
            ans.add(temp);
        }
        return ans;
    }
}

public class Main {
    static class Info {
        String name;
        int age;

        Info(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return String.format("Info{name: %s, age: %d}", name, age);
        }
    }

    public static void main(String[] args) {
        test("[[\"John\", \"johnsmith@mail.com\", \"john00@mail.com\"], [\"John\", \"johnnybravo@mail.com\"], [\"John\", \"johnsmith@mail.com\", \"john_newyork@mail.com\"], [\"Mary\", \"mary@mail.com\"]]", "[[\"John\", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  [\"John\", \"johnnybravo@mail.com\"], [\"Mary\", \"mary@mail.com\"]]");
//        test("[[\"Gabe\",\"Gabe0@m.co\",\"Gabe3@m.co\",\"Gabe1@m.co\"],[\"Kevin\",\"Kevin3@m.co\",\"Kevin5@m.co\",\"Kevin0@m.co\"],[\"Ethan\",\"Ethan5@m.co\",\"Ethan4@m.co\",\"Ethan0@m.co\"],[\"Hanzo\",\"Hanzo3@m.co\",\"Hanzo1@m.co\",\"Hanzo0@m.co\"],[\"Fern\",\"Fern5@m.co\",\"Fern1@m.co\",\"Fern0@m.co\"]]", "[[\"Ethan\",\"Ethan0@m.co\",\"Ethan4@m.co\",\"Ethan5@m.co\"],[\"Gabe\",\"Gabe0@m.co\",\"Gabe1@m.co\",\"Gabe3@m.co\"],[\"Hanzo\",\"Hanzo0@m.co\",\"Hanzo1@m.co\",\"Hanzo3@m.co\"],[\"Kevin\",\"Kevin0@m.co\",\"Kevin3@m.co\",\"Kevin5@m.co\"],[\"Fern\",\"Fern0@m.co\",\"Fern1@m.co\",\"Fern5@m.co\"]]");
//        var i1 = new Info("dude", 20);
//        var i2 = new Info("guy", 21);
//        Map<Integer, Info> mp = new HashMap<>();
//        mp.put(1, i1);
//        mp.put(2, i1);
//        mp.put(3, i2);
//        mp.put(4, i2);
//        System.out.println(mp.values());
//        System.out.println(new HashSet<>(mp.values()));
    }

    private static void test(String accountsStr, String expectStr) {
        List<List<String>> accounts = LeetCodeUtils.make2DStringList(accountsStr);
        List<List<String>> expect = LeetCodeUtils.make2DStringList(expectStr);
//        System.out.println(accounts);;
//        System.out.println(expect);
        Solution solution = new Solution();
        List<List<String>> output = solution.accountsMerge(accounts);
        String desc = String.format("accounts merge accounts=%s", accountsStr);
        LeetCodeUtils.test(desc, output, expect);
    }
}
