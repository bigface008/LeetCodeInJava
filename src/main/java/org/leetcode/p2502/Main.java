package org.leetcode.p2502;

import org.leetcode.utils.InvalidInputException;
import org.leetcode.utils.LeetCodeUtils;

import java.util.*;

// https://leetcode.cn/problems/design-memory-allocator/?envType=daily-question&envId=2025-02-25
class Allocator {
    private LinkedList<int[]> freeList = new LinkedList<>();
    private Map<Integer, List<int[]>> allocMap = new HashMap<>();
    private int N = 0;

    public Allocator(int n) {
        N = n;
        freeList.add(new int[]{0, n - 1});
    }

    public int allocate(int size, int mID) {
        Iterator<int[]> it = freeList.iterator();
        int res = -1;
        while (it.hasNext()) {
            int[] part = it.next();
            int l = part[0], r = part[1];
            if (l + size < r + 1) {
                res = l;
                part[0] = l + size;
                allocMap.computeIfAbsent(mID, k -> new ArrayList<>()).add(new int[]{l, l + size - 1});
                break;
            } else if (l + size == r + 1) {
                res = l;
                it.remove();
                allocMap.computeIfAbsent(mID, k -> new ArrayList<>()).add(part);
                break;
            }
        }
        return res;
    }

    public int freeMemory(int mID) {
        List<int[]> parts = allocMap.get(mID);
        if (parts == null || parts.isEmpty()) {
            return 0;
        }
        parts.sort((a, b) -> {
            return a[0] - b[0];
        });
        int ans = 0;
        ListIterator<int[]> it = freeList.listIterator();
        for (int[] part : parts) {
            int l = part[0], r = part[1];
            ans += r - l + 1;
            boolean processed = false;
            while (it.hasNext()) {
                int[] part2 = it.next();
                int l2 = part2[0], r2 = part2[1];
                if (r < l2) {
                    processed = true;
                    it.previous();
                    it.add(part);
                    break;
                }
            }
            if (!processed) {
                it.add(part);
            }
        }
        it = freeList.listIterator();
        int[] prev = null;
        while (it.hasNext()) {
            int[] part = it.next();
            if (prev != null) {
                if (prev[1] + 1 == part[0]) {
                    prev[1] = part[1];
                    it.remove();
                } else {
                    prev = part;
                }
            } else {
                prev = part;
            }
        }
        allocMap.remove(mID);
        return ans;
    }
}

public class Main {
    public static void main(String[] args) {
//        {
//            Allocator loc = new Allocator(10); // 初始化一个大小为 10 的内存数组，所有内存单元都是空闲的。
//            System.out.println(loc.allocate(1, 1)); // 最左侧的块的第一个下标是 0 。内存数组变为 [1, , , , , , , , , ]。返回 0 。
//            System.out.println(loc.allocate(1, 2)); // 最左侧的块的第一个下标是 1 。内存数组变为 [1,2, , , , , , , , ]。返回 1 。
//            System.out.println(loc.allocate(1, 3)); // 最左侧的块的第一个下标是 2 。内存数组变为 [1,2,3, , , , , , , ]。返回 2 。
//            System.out.println(loc.freeMemory(2)); // 释放 mID 为 2 的所有内存单元。内存数组变为 [1, ,3, , , , , , , ] 。返回 1 ，因为只有 1 个 mID 为 2 的内存单元。
//            System.out.println(loc.allocate(3, 4)); // 最左侧的块的第一个下标是 3 。内存数组变为 [1, ,3,4,4,4, , , , ]。返回 3 。
//            System.out.println(loc.allocate(1, 1)); // 最左侧的块的第一个下标是 1 。内存数组变为 [1,1,3,4,4,4, , , , ]。返回 1 。
//            System.out.println(loc.allocate(1, 1)); // 最左侧的块的第一个下标是 6 。内存数组变为 [1,1,3,4,4,4,1, , , ]。返回 6 。
//            System.out.println(loc.freeMemory(1)); // 释放 mID 为 1 的所有内存单元。内存数组变为 [ , ,3,4,4,4, , , , ] 。返回 3 ，因为有 3 个 mID 为 1 的内存单元。
//            System.out.println(loc.allocate(10, 2)); // 无法找出长度为 10 个连续空闲内存单元的空闲块，所有返回 -1 。
//            System.out.println(loc.freeMemory(7)); // 释放 mID 为 7 的所有内存单元。内存数组保持原状，因为不存在 mID 为 7 的内存单元。返回 0 。
//        }
//        {
//            Allocator loc = new Allocator(7); // 初始化一个大小为 10 的内存数组，所有内存单元都是空闲的。
//            System.out.println(loc.allocate(7, 8)); // 最左侧的块的第一个下标是 0 。内存数组变为 [1, , , , , , , , , ]。返回 0 。
//            System.out.println(loc.allocate(8, 7)); // 最左侧的块的第一个下标是 1 。内存数组变为 [1,2, , , , , , , , ]。返回 1 。
//            System.out.println(loc.allocate(6, 2)); // 最左侧的块的第一个下标是 2 。内存数组变为 [1,2,3, , , , , , , ]。返回 2 。
//            System.out.println(loc.freeMemory(9)); // 释放 mID 为 2 的所有内存单元。内存数组变为 [1, ,3, , , , , , , ] 。返回 1 ，因为只有 1 个 mID 为 2 的内存单元。
//            System.out.println(loc.freeMemory(8));
//            System.out.println(loc.allocate(7, 6)); // 最左侧的块的第一个下标是 1 。内存数组变为 [1,1,3,4,4,4, , , , ]。返回 1 。
//            System.out.println(loc.freeMemory(9));
//            System.out.println(loc.allocate(10, 9)); // 无法找出长度为 10 个连续空闲内存单元的空闲块，所有返回 -1 。
//        }
//        {
//            Allocator loc = new Allocator(50);
//            System.out.println(loc.allocate(12, 6));
//            System.out.println(loc.allocate(28, 16));
//            System.out.println(loc.allocate(17, 23));
//            System.out.println(loc.allocate(50, 23));
//            System.out.println(loc.freeMemory(6));
//        }
        LinkedList<String> list = new LinkedList<>();
        list.add("bob");
        list.add("alice");
        list.add("ben");
        list.add("ted");
        list.add("rob");

        ListIterator<String> it = list.listIterator();
        while (it.hasNext()) {
            String item = it.next();
            System.out.println(item);
            if ("alice".equals(item)) {
                it.previous();
                it.add("justwe");
                String temp = it.next();
                System.out.println("temp " + temp);
            }
        }
        System.out.println(list);
//        test(new String[]{"allocate", "allocate", "allocate", "allocate", "freeMemory", "freeMemory", "freeMemory", "allocate", "allocate", "allocate", "allocate", "freeMemory", "freeMemory", "freeMemory", "freeMemory", "freeMemory", "freeMemory", "freeMemory", "allocate", "freeMemory", "freeMemory", "allocate", "freeMemory", "allocate", "allocate", "freeMemory", "freeMemory", "freeMemory", "allocate", "allocate", "allocate", "allocate", "freeMemory", "allocate", "freeMemory", "freeMemory", "allocate", "allocate", "allocate", "allocate", "allocate", "allocate", "allocate", "freeMemory", "freeMemory", "freeMemory", "freeMemory"}, "[[50],[12,6],[28,16],[17,23],[50,23],[6],[10],[10],[16,8],[17,41],[44,27],[12,45],[33],[8],[16],[23],[23],[23],[29],[38,32],[29],[6],[40,11],[16],[22,33],[27,5],[3],[10],[29],[16,14],[46,47],[48,9],[36,17],[33],[14,24],[16],[8],[2,50],[31,36],[17,45],[46,31],[2,6],[16,2],[39,30],[33],[45],[30],[27]]", new int[]{0, 12, -1, -1, 12, 0, 0, -1, -1, -1, 0, 0, 0, 28, 0, 0, 0, 0, 12, 0, 0, -1, 0, -1, -1, 0, 0, 0, -1, -1, -1, -1, 0, -1, 0, 0, -1, -1, -1, -1, -1, -1, -1, 0, 12, 0, 0});
//        test(new String[]{"freeMemory", "freeMemory", "freeMemory", "freeMemory", "freeMemory", "allocate", "allocate", "allocate", "freeMemory", "freeMemory", "freeMemory", "allocate", "allocate", "freeMemory", "freeMemory", "freeMemory", "allocate", "allocate", "freeMemory", "allocate", "allocate", "allocate", "freeMemory", "freeMemory", "freeMemory", "freeMemory", "freeMemory", "freeMemory", "freeMemory", "allocate", "freeMemory", "allocate", "freeMemory", "allocate", "allocate", "freeMemory", "allocate", "allocate", "freeMemory", "freeMemory", "allocate", "freeMemory", "freeMemory", "allocate", "allocate", "allocate", "freeMemory", "allocate", "allocate", "freeMemory", "freeMemory", "allocate", "allocate", "allocate", "freeMemory", "allocate", "allocate", "freeMemory", "freeMemory", "freeMemory", "allocate", "freeMemory", "freeMemory", "freeMemory", "freeMemory", "allocate", "allocate", "allocate", "freeMemory", "allocate", "freeMemory", "freeMemory", "allocate", "freeMemory", "allocate", "freeMemory", "freeMemory"}, "[[100],[27],[12],[53],[61],[80],[21,78],[81,40],[50,76],[40],[76],[63],[25,100],[96,12],[92],[92],[84],[19,71],[22,90],[60],[42,79],[26,41],[59,33],[79],[58],[97],[92],[97],[92],[40],[52,74],[40],[53,17],[17],[36,32],[51,13],[41],[5,87],[44,66],[71],[53],[74,14],[78],[14],[32,54],[45,28],[84,47],[16],[100,78],[5,99],[33],[100],[62,79],[31,32],[85,81],[78],[34,45],[47,7],[7],[84],[6],[35,55],[94],[87],[20],[87],[96,60],[40,66],[28,96],[28],[25,2],[100],[96],[19,35],[16],[92,42],[80],[79]]", new int[]{0, 0, 0, 0, 0, 0, -1, 21, 0, 50, 0, 21, -1, 0, 0, 0, 46, 65, 0, -1, -1, -1, 0, 0, 0, 0, 0, 0, 0, -1, 0, -1, 0, -1, -1, 0, 87, -1, 19, 0, -1, 21, 0, -1, -1, -1, 0, -1, 0, 0, 25, -1, 5, -1, 0, -1, -1, 0, 0, 0, -1, 0, 5, 0, 0, -1, -1, 36, 0, -1, 0, 28, 36, 0, -1, 0, 0});
//        test(new String[]{"allocate","allocate","allocate","allocate","freeMemory","allocate","freeMemory","freeMemory","freeMemory","freeMemory","freeMemory","freeMemory","freeMemory","allocate","allocate","freeMemory","freeMemory","allocate","freeMemory","allocate","allocate","allocate","allocate","allocate","allocate","freeMemory","freeMemory","freeMemory","allocate","allocate","freeMemory","allocate","allocate","allocate","allocate","freeMemory","allocate","allocate","allocate","allocate","allocate","allocate","freeMemory","allocate","allocate","freeMemory","allocate","freeMemory","freeMemory","allocate","allocate","freeMemory","freeMemory","freeMemory","freeMemory","allocate","freeMemory","freeMemory","allocate","freeMemory","allocate","allocate","freeMemory","freeMemory","allocate","allocate","freeMemory","allocate","allocate","freeMemory","freeMemory","allocate","allocate","freeMemory","allocate","allocate","freeMemory","allocate","allocate","allocate","allocate","allocate","freeMemory","allocate","allocate","allocate","allocate","allocate","allocate","allocate","freeMemory","freeMemory","freeMemory","allocate","freeMemory","allocate","freeMemory","freeMemory","allocate","freeMemory","allocate","allocate","freeMemory","freeMemory","allocate","freeMemory","freeMemory","allocate","allocate","freeMemory","allocate","allocate","allocate","allocate","freeMemory","freeMemory","allocate","freeMemory","allocate","allocate","freeMemory","freeMemory","freeMemory","freeMemory","allocate","freeMemory","freeMemory","allocate","freeMemory","allocate","freeMemory","allocate","freeMemory","allocate","allocate","freeMemory","freeMemory","allocate","freeMemory","allocate","allocate","allocate","allocate","freeMemory","freeMemory","freeMemory","freeMemory","freeMemory","freeMemory","freeMemory","freeMemory","allocate","freeMemory","allocate","freeMemory","allocate","freeMemory","allocate","freeMemory","freeMemory","freeMemory","allocate","allocate","freeMemory","freeMemory","freeMemory","allocate","allocate","allocate","freeMemory","allocate","allocate","allocate","freeMemory","allocate","allocate","allocate","freeMemory","allocate","allocate","freeMemory","freeMemory","allocate","freeMemory","allocate","freeMemory","freeMemory"}, "[[114],[170,38],[31,137],[200,18],[156,296],[9],[108,202],[158],[137],[18],[9],[87],[137],[111],[260,67],[172,63],[251],[38],[114,48],[48],[83,167],[150,250],[224,222],[191,66],[88,207],[290,240],[50],[251],[137],[57,290],[82,99],[290],[51,154],[6,12],[91,274],[131,267],[149],[87,78],[266,70],[232,171],[34,287],[164,32],[241,300],[244],[34,83],[2,113],[219],[129,254],[66],[267],[116,143],[160,39],[278],[171],[143],[74],[95,276],[94],[288],[153,262],[276],[65,160],[129,10],[23],[83],[284,119],[95,24],[278],[164,201],[117,56],[83],[24],[82,299],[76,272],[272],[116,286],[172,122],[299],[56,67],[39,120],[209,284],[220,117],[67,174],[48],[43,81],[95,110],[20,153],[193,77],[175,95],[200,206],[229,49],[95],[19],[70],[36,171],[251],[139,245],[63],[8],[3,153],[245],[261,258],[143,245],[77],[151],[120,227],[234],[94],[192,146],[225,96],[95],[224,230],[212,13],[147,164],[210,89],[12],[204],[268,3],[201],[169,109],[117,124],[270],[239],[154],[57],[280,249],[8],[56],[282,34],[39],[144,41],[151],[107,93],[110],[177,169],[39,35],[94],[94],[209,284],[41],[6,14],[230,76],[225,244],[62,218],[299],[96],[284],[119],[114],[290],[244],[57],[216,110],[287],[97,1],[153],[102,121],[203],[220,27],[124],[174],[108],[96,246],[191,269],[206],[121],[13],[34,199],[66,245],[33,229],[244],[14,44],[23,194],[197,195],[110],[285,253],[179,82],[131,217],[57],[106,63],[218,207],[70],[8],[102,184],[229],[87,283],[176],[158]]", new int[]{100});
    }

    private static void test(String[] methods, String paramStr, int[] expect) {
        int[][] paramsArr = LeetCodeUtils.make2DIntArray(paramStr);
        Allocator allocator = new Allocator(paramsArr[0][0]);
        int N = paramsArr.length - 1;
        int[] res = new int[N];
        for (int i = 0; i < N; i++) {
            int temp = 0;
            int[] params = paramsArr[i + 1];
            try {
                if (methods[i].equals("allocate")) {
                    temp = allocator.allocate(params[0], params[1]);
                } else {
                    temp = allocator.freeMemory(params[0]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            res[i] = temp;
        }
        System.out.printf("output=%s\nexpect=%s\n", Arrays.toString(res), Arrays.toString(expect));
    }

    // (12,39) (40,49)
    // 45->(0,11)


//    private static void test(String[] methods, String paramStr, int[] expect) {
//        Object[][] paramsArr = LeetCodeUtils.make2DObjectArray(paramStr);
//        Object sz = paramsArr[0][0];
//        if (sz instanceof Integer) {
//            Integer val = (Integer) sz;
//            Allocator allocator = new Allocator(val);
//            List<Object> outputs = LeetCodeUtils.invokeMethods(allocator, methods, paramsArr);
//            int size = expect.length;
//            if (size != outputs.size()) {
//                throw new InvalidInputException("invalid expect length");
//            }
//            for (int i = 0; i < size; i++) {
//                Object res = outputs.get(i);
//                if (res instanceof Integer) {
//                    Integer res2 = (Integer) res;
//
//                }
//            }
//        }
//    }
}
