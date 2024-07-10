package org.leetcode.p3102;

import org.leetcode.utils.LeetCodeUtils;

import java.util.*;

// https://leetcode.com/problems/minimize-manhattan-distances/
class Solution {
    public int minimumDistance(int[][] points) {
        final int INF = Integer.MAX_VALUE / 2;
        int maxX1 = -INF, maxX2 = -INF, minX1 = INF, minX2 = INF, maxXi = -1, minXi = -1;
        int maxY1 = -INF, maxY2 = -INF, minY1 = INF, minY2 = INF, maxYi = -1, minYi = -1;
        for (int i = 0; i < points.length; i++) {
            int x = points[i][0] + points[i][1];
            int y = points[i][1] - points[i][0];
            // max x
            if (x > maxX1) {
                maxX2 = maxX1;
                maxX1 = x;
                maxXi = i;
            } else if (x > maxX2) {
                maxX2 = x;
            }
            // min x
            if (x < minX1) {
                minX2 = minX1;
                minX1 = x;
                minXi = i;
            } else if (x < minX2) {
                minX2 = x;
            }
            // max y
            if (y > maxY1) {
                maxY2 = maxY1;
                maxY1 = y;
                maxYi = i;
            } else if (y > maxY2) {
                maxY2 = y;
            }
            // min x
            if (y < minY1) {
                minY2 = minY1;
                minY1 = y;
                minYi = i;
            } else if (y < minY2) {
                minY2 = y;
            }
        }
        int ans = INF;
        for (int i : new int[]{maxXi, minXi, maxYi, minYi}) {
            int dx = (i == maxXi ? maxX2 : maxX1) - (i == minXi ? minX2 : minX1);
            int dy = (i == maxYi ? maxY2 : maxY1) - (i == minYi ? minY2 : minY1);
            ans = Math.min(ans, Math.max(dx, dy));
        }
        return ans;
    }
}


// 根据灵山大佬的解法，复杂版
class SolutionV1 {
    public int minimumDistance(int[][] points) {
        ArrayList<Integer> xList = new ArrayList<>(points.length);
        ArrayList<Integer> yList = new ArrayList<>(points.length);
        ArrayList<Boolean> xRemoved = new ArrayList<>(points.length);
        ArrayList<Boolean> yRemoved = new ArrayList<>(points.length);
        for (int i = 0; i < points.length; i++) {
            int x = points[i][0];
            int y = points[i][1];
            xList.add(x + y);
            yList.add(y - x);
            xRemoved.add(false);
            yRemoved.add(false);
        }
        Collections.sort(xList);
        Collections.sort(yList);
        int ans = Integer.MAX_VALUE / 2;
        for (int i = 0; i < points.length; i++) {
            int x = points[i][0];
            int y = points[i][1];
            int newX = x + y;
            int newY = y - x;
            int idx1 = findFromList(xList, newX);
            int idx2 = findFromList(yList, newY);
            xRemoved.set(idx1, true);
            yRemoved.set(idx2, true);
            ans = Math.min(ans, Math.max(getDiff(xList, xRemoved), getDiff(yList, yRemoved)));
//            ans = Math.min(ans, Math.max(xList.get(xList.size() - 1) - xList.get(0), yList.get(yList.size() - 1) - yList.get(0)));
            xRemoved.set(idx1, false);
            yRemoved.set(idx2, false);
//            xList.add(idx1, newX);
//            yList.add(idx2, newY);
        }
        return ans;
    }

    int getDiff(ArrayList<Integer> list, ArrayList<Boolean> removed) {
        int minIdx = 0;
        for (int i = 0; i < list.size(); i++) {
            if (!removed.get(i)) {
                minIdx = i;
                break;
            }
        }
        int maxIdx = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            if (!removed.get(i)) {
                maxIdx = i;
                break;
            }
        }
        return list.get(maxIdx) - list.get(minIdx);
    }

    int findFromList(ArrayList<Integer> list, int x) {
        int start = 0, end = list.size() - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            int val = list.get(mid);
            if (val == x) {
                return mid;
            } else if (val > x) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    int removeFromList(ArrayList<Integer> list, int x) {
        int start = 0, end = list.size() - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            int val = list.get(mid);
            if (val == x) {
                list.remove(mid);
                return mid;
            } else if (val > x) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }
}

// 暴力解法
class SolutionV2 {
    //    HashMap<String, Integer> distanceMap = new HashMap<>();
    HashMap<Integer, HashMap<Integer, Info>> distanceMap = new HashMap<>();

    class Info {
        Integer distance;
        Set<Integer> pointIndices;
        boolean removed;

        Info(Integer distance, Set<Integer> pointIndices) {
            this.distance = distance;
            this.pointIndices = pointIndices;
            this.removed = false;
        }
    }

    public int minimumDistance(int[][] points) {
        List<Info> list = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                }
                if (distanceMap.containsKey(i) && distanceMap.get(i).containsKey(j)) {
                    continue;
                }
                int distance = md(points[i], points[j]);
                HashSet<Integer> set = new HashSet<>();
                set.add(i);
                set.add(j);
                Info info = new Info(distance, set);
                distanceMap.computeIfAbsent(i, k -> new HashMap<>()).put(j, info);
                distanceMap.computeIfAbsent(j, k -> new HashMap<>()).put(i, info);
                list.add(info);
            }
        }
        list.sort(new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                return o2.distance - o1.distance;
            }
        });

        int ans = Integer.MAX_VALUE / 2;
        for (int i = 0; i < points.length; i++) {
            int[] pt = points[i];
            int maxDistance = -1;
            distanceMap.get(i).forEach((k, v) -> {
                v.removed = true;
            });
//            distanceMap.get(pt[0]).get(pt[1]).removed = true;
//            distanceMap.get(pt[1]).get(pt[0]).removed = true;
            for (int j = 0; j < list.size(); j++) {
                if (!list.get(j).removed) {
                    maxDistance = list.get(j).distance;
                    break;
                }
            }
            ans = Math.min(ans, maxDistance);
            distanceMap.get(i).forEach((k, v) -> {
                v.removed = false;
            });
        }
        return ans;
    }

    private int md(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }

    private String getDistanceKey(int i1, int i2) {
        return String.format("%d.%d", i1, i2);
    }
}

public class Main {
    public static void main(String[] args) {
        test("[[3,10],[5,15],[10,2],[4,4]]", 12);
        test("[[1,1],[1,1],[1,1]]", 0);
    }

    private static void test(String pointsStr, int expect) {
        Solution solution = new Solution();
        int[][] points = LeetCodeUtils.make2DIntArray(pointsStr);
        int output = solution.minimumDistance(points);
        String desc = String.format("minimum distance points=%s", pointsStr);
        LeetCodeUtils.test(desc, output, expect);
    }
}
