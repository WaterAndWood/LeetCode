package TargetOffer;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * Offer13: 机器人的运动范围
 *
 * @author Richa
 * @date 2022/2/26 22:30
 */
public class RobotMovingCount {
    private boolean[][] visited;
    /**
     * 限定边界
     */
    private int rows;
    private int cols;

    /**
     * DFS
     */
    public int moveCount1(int m, int n, int k) {
        if (m < 0 || n < 0 || k < 0) {
            return 0;
        }
        visited = new boolean[m][n];
        rows = m;
        cols = n;
        return dfs(0, 0, k);
    }

    private int dfs(int row, int col, int k) {
        if (row < 0 || row >= rows || col < 0 || col >= cols || visited[row][col] || getDigitSum(row, col) > k) {
            return 0;
        }
        visited[row][col] = true;
        /**
         * 能到达的范围是上三角，只需要向下和向右移动即可
         */
        int result = 1 + dfs(row + 1, col, k) + dfs(row, col + 1, k);
        return result;
    }

    private int getDigitSum(int x, int y) {
        int ans = 0;
        while (x > 0) {
            ans += x % 10;
            x /= 10;
        }
        while (y > 0) {
            ans += y % 10;
            y /= 10;
        }
        return ans;
    }

    /**
     * BFS: 使用队列实现向外逐圈扩张
     */
    public int movingCount2(int m, int n, int k) {
        if (m < 0 || n < 0 || k < 0) {
            return 0;
        }
        int result = 0;
        /**
         * 取出当前位置坐标，加入下一圈位置坐标
         */
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int[] pre = queue.poll();
            int row = pre[0];
            int col = pre[1];
            if (row < 0 || row >= m || col < 0 || col >= n || visited[row][col] || getDigitSum(row, col) > k) {
                continue;
            }
            visited[row][col] = true;
            result++;
            queue.add(new int[]{row + 1, col});
            queue.add(new int[]{row, col + 1});
        }
        return result;
    }
}
