package DepthOrBreadthSearch;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * LeetCode542: 01矩阵
 *
 * @author Richa
 * @date 2022/6/2 9:45
 */
public class ZeroOneMatrix {
    public int[][] updateMatrix(int[][] mat) {
        if (mat == null || mat.length == 0) {
            return mat;
        }
        Queue<int[]> queue = new LinkedList<>();
        int m = mat.length, n = mat[0].length;
        /**
         * 相比于Tree，需要多个起始点
         */
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    queue.offer(new int[] {i, j});
                } else {
                    mat[i][j] = -1;
                }
            }
        }
        int[] dx = new int[] {-1, 1, 0, 0};
        int[] dy = new int[] {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int x = point[0], y = point[1];
            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                if (newX >= 0 && newX < m && newY > 0 && newY <= n && mat[newX][newY] == -1) {
                    mat[newX][newY] = mat[x][y] + 1;
                    queue.offer(new int[] {newX, newY});
                }
            }
        }
        return mat;
    }
}
