package org.leetcode.p1656;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.cn/problems/design-an-ordered-stream/?envType=daily-question&envId=2025-02-24
class OrderedStream {
    class Info {
        int idKey;
        String value;

        Info(int idKey, String value) {
            this.idKey = idKey;
            this.value = value;
        }
    }

    Info[] arr;
    int ptr = 0;

    public OrderedStream(int n) {
        arr = new Info[n];
    }

    public List<String> insert(int idKey, String value) {
        Info info = new Info(idKey, value);
        arr[idKey - 1] = info;
        List<String> res = new ArrayList<>();
        while (ptr < arr.length && arr[ptr] != null) {
            res.add(arr[ptr].value);
            ptr++;
        }
        return res;
    }
}

public class Main {
    public static void main(String[] args) {
        {
            OrderedStream os= new OrderedStream(5);
            System.out.println(os.insert(3, "ccccc")); // 插入 (3, "ccccc")，返回 []
            System.out.println(os.insert(1, "aaaaa")); // 插入 (1, "aaaaa")，返回 ["aaaaa"]
            System.out.println(os.insert(2, "bbbbb")); // 插入 (2, "bbbbb")，返回 ["bbbbb", "ccccc"]
            System.out.println(os.insert(5, "eeeee")); // 插入 (5, "eeeee")，返回 []
            System.out.println(os.insert(4, "ddddd")); // 插入 (4, "ddddd")，返回 ["ddddd", "eeeee"]
        }
    }

}
