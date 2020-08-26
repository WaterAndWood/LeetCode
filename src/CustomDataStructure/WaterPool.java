package CustomDataStructure;

import java.util.*;

/**
 *
 * 牛客：蓄水池大作战
 *
 * 在你面前有n个蓄水池，他们组成了树形结构（由n-1条边连接）。蓄水池节点编号从1开始到n。对每个蓄水池节点来说，他的儿子蓄水池节点都摆放在他的下面，并且和它用水管相连，根据重力，水会向下流动。现在我们要在蓄水池上做一些操作：
 * 1. 把节点v填满水。然后v的所有儿子节点水也会被填满
 * 2. 清空节点v的水。然后v所有的父亲节点水都会被清空
 * 3. 询问每个蓄水池节点是否有水。
 * 初始状态时候，每个节点都是空的。
 *
 * 第一行包含一个正整数n(1<=n<=1000)，表示蓄水池节点的数量。
 *
 * 后面n-1行，每行有两个数字a[i], b[i]。（1<=a[i], b[i]<= n, a[i]!=b[i])表示蓄水池的连接关系。
 *
 * 接下来的一行包含一个整数q(1<=q<=1000)，表示我们要进行的操作的数量。
 *
 * 最后的q行中，每行包含两个数字c[i] (1<=c[i]<=3)和v[i](1<=v[i]<=n)。其中c[i]表示操作类型(1,2或者3)。v[i]表示操作对应的蓄水池节点。
 *
 * 自建模拟树的数据结构：一种是利用HashSet的数组，一种是利用链表；模拟递归操作
 *
 * @author Richa
 * @date 2020/8/8 14:51
 */

/**
 * 节点中记录子节点和父节点
 */
class Node {
    boolean water = false;
    List<Integer> sonList;
    List<Integer> parentList;

    public Node() {
        sonList = new ArrayList<>();
        parentList = new ArrayList<>();
    }
}
public class WaterPool {

    /**
     * HashSet数组实现树
     */
    /*static HashSet<Integer>[] adj;
    static boolean[] isFull;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // 0位置不使用，从1开始对应
        adj = new HashSet[n + 1];
        isFull = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            adj[i] = new HashSet<Integer>();
        }
        // 每个HashSet存放父节点和子节点，父节点比当前索引小，当前索引即树的节点编号。父节点<子节点
        for (int i = 0; i < n - 1; i++) {
            int a = sc.nextInt(), b = sc.nextInt();
            adj[a].add(b);
            adj[b].add(a);
        }
        int q = sc.nextInt();
        while (q-- > 0) {
            // 操作方法类型和节点编号
            int method = sc.nextInt(), v = sc.nextInt();
            switch(method) {
                case 1:
                    flood(v);
                    break;
                case 2:
                    clear(v);
                    break;
                case 3:
                    System.out.println(isFull[v] ? 1 : 0);
                    break;
                default:
                    break;
            }
        }
    }

    public static void flood(int v) {
        isFull[v] = true;
        // 大于v的是子节点
        for (int n : adj[v]) {
            if (n > v) {
                flood(n);
            }
        }
    }

    public static void clear(int v) {
        isFull[v] = false;
        // 小于v的是父节点
        for (int n : adj[v]) {
            if (n < v) {
                clear(v);
            }
        }
    }*/


    static HashMap<Integer, Node> map = new HashMap<>();
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        // 连接关系
        int[] a = new int[m];
        int[] b = new int[m];
        for (int i = 0; i < m - 1; i++) {
            a[i] = sc.nextInt();
            b[i] = sc.nextInt();
        }
        // 操作
        int op = sc.nextInt();
        int[] c = new int[op];
        int[] v = new int[op];
        for (int i = 0; i < op; i++) {
            c[i] = sc.nextInt();
            v[i] = sc.nextInt();
        }
        for (int i = 1; i <= m; i++) {
            map.put(i, new Node());
        }

        for (int i = 0; i < m - 1; i++) {
            /**
             * 连接默认小数的是大数的父节点
             *
             */
            int max = Math.max(a[i], b[i]);
            int min = Math.min(a[i], b[i]);
            map.get(max).parentList.add(min);
            map.get(min).sonList.add(max);
        }

        for (int i = 0; i < op; i++) {
            if (c[i] == 1) {
                freight(v[i]);
            } else if (c[i] == 2) {
                clean(v[i]);
            } else {
                System.out.println(map.get(v[i]).water ? 1 : 0);
            }
        }
    }

    /**
     * 注入水，子节点都注入水
     *
     */
    static void freight(int v) {
        map.get(v).water = true;
        if (map.get(v).sonList.isEmpty()) {
            return;
        } else {
            for (Integer son : map.get(v).sonList) {
                freight(son);
            }
        }
    }

    /**
     * 清空水，父节点都清空
     *
     */
    static void clean(int v) {
        map.get(v).water = false;
        if (map.get(v).parentList.isEmpty()) {
            return;
        } else {
            for (Integer parent : map.get(v).parentList) {
                clean(parent);
            }
        }
    }
}
