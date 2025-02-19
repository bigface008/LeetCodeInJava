package org.leetcode.p1352;

import java.util.ArrayList;
import java.util.List;

class ProductOfNumbers {
    private List<Integer> preProd = new ArrayList<>();

    public ProductOfNumbers() {
        preProd.add(1);
    }

    public void add(int num) {
        if (num == 0) {
            preProd.clear();
            preProd.add(1);
            return;
        }
        int N = preProd.size();
        preProd.add(preProd.get(N - 1) * num);
    }

    public int getProduct(int k) {
        int N = preProd.size();
        if (k >= N) {
            return 0;
        }
        return preProd.get(N - 1) / preProd.get(N - k - 1);
    }
}

public class Main {
    public static void main(String[] args) {
        ProductOfNumbers test = new ProductOfNumbers();
        test.add(3);
        test.add(0);
        test.add(2);
        test.add(5);
        test.add(4);
        System.out.println(test.getProduct(2));
    }
}
