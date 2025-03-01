package org.leetcode.p2353;

import java.util.*;

// https://leetcode.cn/problems/design-a-food-rating-system/?envType=daily-question&envId=2025-02-28
class FoodRatings {
    static class FoodInfo {
        String name;
        String cuisine;
        int rating;
        boolean ratingValid = true;

        FoodInfo(String name, String cuisine, int rating) {
            this.name = name;
            this.cuisine = cuisine;
            this.rating = rating;
        }
    }

    private Map<String, FoodInfo> name2Info = new HashMap<>();
    private Map<String, PriorityQueue<FoodInfo>> cuisine2Infos = new HashMap<>(); //

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        final int N = foods.length;
        Comparator<FoodInfo> cmp = (o1, o2) -> {
            if (o1.rating != o2.rating) {
                return o2.rating - o1.rating;
            } else {
                return o1.name.compareTo(o2.name);
            }
        };

        for (int i = 0; i < N; i++) {
            FoodInfo info = new FoodInfo(foods[i], cuisines[i], ratings[i]);
            name2Info.put(foods[i], info);
            cuisine2Infos.computeIfAbsent(cuisines[i], k -> new PriorityQueue<>(cmp)).add(info);
        }
    }

    public void changeRating(String food, int newRating) {
        FoodInfo info = name2Info.get(food);
        if (info == null) {
            return;
        }
        info.ratingValid = false;
        FoodInfo newInfo = new FoodInfo(info.name, info.cuisine, newRating);
        PriorityQueue<FoodInfo> heap = cuisine2Infos.get(info.cuisine);
        heap.offer(newInfo);
        name2Info.put(food, newInfo);
    }

    public String highestRated(String cuisine) {
        PriorityQueue<FoodInfo> heap = cuisine2Infos.get(cuisine);
        if (heap == null) {
            return "";
        }
        while (!heap.peek().ratingValid) {
            heap.poll();
        }
        return heap.peek().name;
    }
}

public class Main {
    public static void main(String[] args) {
        {
            FoodRatings foodRatings = new FoodRatings(
                    new String[]{"tjokfmxg", "xmiuwozpmj", "uqklk", "mnij", "iwntdyqxi", "cduc", "cm", "mzwfjk"},
                    new String[]{"waxlau", "ldpiabqb", "ldpiabqb", "waxlau", "ldpiabqb", "waxlau", "waxlau", "waxlau"},
                    new int[]{9, 13, 7, 16, 10, 17, 16, 17}
            );
            foodRatings.changeRating("tjokfmxg", 19);
            System.out.println(foodRatings.highestRated("waxlau"));
            foodRatings.changeRating("uqklk", 7);
            System.out.println(foodRatings.highestRated("waxlau"));
            System.out.println(foodRatings.highestRated("waxlau"));
            foodRatings.changeRating("tjokfmxg", 14);
            System.out.println(foodRatings.highestRated("waxlau"));
            System.out.println(foodRatings.highestRated("waxlau"));
        }
//        {
//            FoodRatings foodRatings = new FoodRatings(new String[]{"kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"}, new String[]{"korean", "japanese", "japanese", "greek", "japanese", "korean"}, new int[]{9, 12, 8, 15, 14, 7});
//            foodRatings.highestRated("korean"); // 返回 "kimchi"
//            // "kimchi" 是分数最高的韩式料理，评分为 9 。
//            foodRatings.highestRated("japanese"); // 返回 "ramen"
//            // "ramen" 是分数最高的日式料理，评分为 14 。
//            foodRatings.changeRating("sushi", 16); // "sushi" 现在评分变更为 16 。
//            foodRatings.highestRated("japanese"); // 返回 "sushi"
//            // "sushi" 是分数最高的日式料理，评分为 16 。
//            foodRatings.changeRating("ramen", 16); // "ramen" 现在评分变更为 16 。
//            foodRatings.highestRated("japanese"); // 返回 "ramen"
//            // "sushi" 和 "ramen" 的评分都是 16 。
//            // 但是，"ramen" 的字典序比 "sushi" 更小。
//        }
    }
}
