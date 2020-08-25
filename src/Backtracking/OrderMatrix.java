package Backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * 牛客：订单分配
 * 结果保存在N*N的矩阵A， 其中Aij 代表订单i司机j匹配的分值。行表示订单，列表示司机
 * 第一行包含一个整数N，2≤N≤10。第二行至第N+1行包含N*N的矩阵。
 * 输出分值累加结果和匹配列表，结果四舍五入保留小数点后两位
 *
 * 回溯算法，找出最大值和最大结果组成
 * 订单从小到大的顺序匹配司机
 *
 * @author Richa
 * @date 2020/8/26 0:12
 */
public class OrderMatrix {
    static double[][] nums;
    static boolean[] visited;
    static double max = -1000;
    static int n;
    static List<int[]> maxRelation = new ArrayList<>();
    static List<int[]> tempRelation = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        visited = new boolean[n];
        nums = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                nums[i][j] = sc.nextDouble();
            }
        }
        // 回溯算法也是一种深度搜索
        dfs(0, 0);
        System.out.println(String.format("%.2f", max));
        for (int i = 0; i < maxRelation.size(); i++) {
            System.out.println((i + 1) + " " + maxRelation.get(i)[1]);
        }
    }
    private static void dfs(int row, double count) {
        // 回溯终止条件
        if (row >= n) {
            if (count >= max) {
                // 特殊情况，直接1 - 1,2 - 2，3 - 3
                if (count == 0) {
                    List<int[]> list = new ArrayList<>();
                    for (int i = 0; i < n; i++) {
                        int[] temp = new int[2];
                        temp[0] = i;
                        temp[1] = i;
                        list.add(temp);
                    }
                    max = count;
                    maxRelation = new ArrayList<>(list);
                } else {
                    max = count;
                    maxRelation.clear();
                    maxRelation = new ArrayList<>(tempRelation);
                }
            }
            return;
        }
        // 按列回溯
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                // 记录坐标
                int[] tempArr = new int[2];
                tempArr[0] = row;
                tempArr[1] = i;
                tempRelation.add(tempArr);

                dfs(row + 1, count + nums[row][i]);

                tempRelation.remove(maxRelation.size() - 1);
                visited[i] = false;
            }
        }
    }
}
