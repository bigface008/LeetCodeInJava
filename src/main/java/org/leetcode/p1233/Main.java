package org.leetcode.p1233;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/remove-sub-folders-from-the-filesystem/description
class Solution {
    static class Folder {
        String name;
        Map<String, Folder> subFolders;
        boolean isFolder;

        Folder() {
            name = "";
            subFolders = new HashMap<>();
            isFolder = false;
        }
    }

    public List<String> removeSubfolders(String[] folder) {
        Folder root = new Folder();
        for (String path : folder) {
            buildFolder(root, path, 0);
        }
        List<String> res = new LinkedList<>();
        for (Folder f : root.subFolders.values()) {
            dfs(f, res);
        }
        return res;
    }

    void buildFolder(Folder folder, String path, int idx) {
        if (idx == path.length()) {
            folder.isFolder = true;
            return;
        }
        int i = idx + 1;
        for (; i < path.length(); i++) {
            if (path.charAt(i) == '/') {
                break;
            }
        }
        String name = path.substring(0, i);
        Folder subFolder = folder.subFolders.get(name);
        if (subFolder == null) {
            subFolder = new Folder();
            subFolder.name = name;
            folder.subFolders.put(name, subFolder);
        }
        buildFolder(subFolder, path, i);
    }

    void dfs(Folder folder, List<String> res) {
        if (folder.isFolder) {
            res.add(folder.name);
            return;
        }
        for (Folder subFolder : folder.subFolders.values()) {
            dfs(subFolder, res);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        test(new String[]{"/a", "/a/b", "/c/d", "/c/d/e", "/c/f"});
    }

    private static void test(String[] folder) {
        System.out.println(new Solution().removeSubfolders(folder));
    }
}
