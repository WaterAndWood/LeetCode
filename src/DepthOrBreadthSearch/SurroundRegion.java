package DepthOrBreadthSearch;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * LeetCode130: 被围绕的区域
 * BFS和DFS都是从边界O开始搜索，不需要变的O改成A作为标记，最后将O变成X，A变成O
 *
 * @author Richa
 * @date 2022/6/2 20:37
 */
public class SurroundRegion {
    private int[] dx = new int[]{-1, 1, 0, 0};
    private int[] dy = new int[]{0, 0, -1, 1};
    /**
     * BFS先将4个边的O加入队列
     */
    public void solveWithBfs(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        int rows = board.length, cols = board[0].length;

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < rows; i++) {
            if (board[i][0] == 'O') {
                queue.offer(new int[]{i, 0});
                board[i][0] = 'A';
            }
            if (board[i][cols - 1] == 'O') {
                queue.offer(new int[] {i, cols - 1});
                board[i][cols - 1] = 'A';
            }
        }
        for (int j = 0; j < cols; j++) {
            if (board[0][j] == 'O') {
                queue.offer(new int[]{0, j});
                board[0][j] = 'A';
            }
            if (board[rows - 1][j] == 'O') {
                queue.offer(new int[]{rows - 1, j});
                board[rows - 1][j] = 'A';
            }
        }

        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int x = point[0], y = point[1];
            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                if (newX >= rows || newX < 0 || newY >= cols || newY < 0 || board[newX][newY] != 'O') {
                    continue;
                }
                board[newX][newY] = 'A';
                queue.offer(new int[]{newX, newY});
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    /**
     * 从四个边开始递归
     * @param board
     */
    public void solveWithDfs(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        int m = board.length, n = board[0].length;

        for (int i = 0; i < m; i++) {
            dfs(board, i, 0, m, n);
            dfs(board, i, n - 1, m, n);
        }
        for (int j = 0; j < n; j++) {
            dfs(board, 0, j, m, n);
            dfs(board, m - 1, j, m, n);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void dfs(char[][] board, int x, int y, int rows, int cols) {
        if (x < 0 || x >= rows || y < 0 || y >= cols || board[x][y] != 'O') {
            return;
        }
        board[x][y] = 'A';
        dfs(board, x - 1, y, rows, cols);
        dfs(board, x + 1, y, rows, cols);
        dfs(board, x, y - 1, rows, cols);
        dfs(board, x, y + 1, rows, cols);
    }
}
