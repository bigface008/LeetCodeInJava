package org.leetcode.p1472;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// https://leetcode.cn/problems/design-browser-history/?envType=daily-question&envId=2025-02-26
class BrowserHistory {
    private List<String> urls = new ArrayList<>();
    private int idx = 0;

    public BrowserHistory(String homepage) {
        urls.add(homepage);
    }

    public void visit(String url) {
        if (idx != urls.size() - 1) {
            urls = urls.subList(0, idx + 1);
        }
        urls.add(url);
        idx++;
    }

    public String back(int steps) {
        if (idx + 1 <= steps) {
            idx = 0;
        } else {
            idx -= steps;
        }
        return urls.get(idx);
    }

    public String forward(int steps) {
        if (idx + 1 + steps <= urls.size()) {
            idx += steps;
        } else {
            idx = urls.size() - 1;
        }
        return urls.get(idx);
    }
}

public class Main {
}
