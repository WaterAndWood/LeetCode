package DepthOrBreadthSearch;

import java.util.LinkedList;

/**
 *
 * LeetCode 200: 岛屿的数量
 *
 * @author Richa
 * @date 2020/7/29 16:43
 */
public class IslandsNumber {
    /**
     * 方向数组，它表示了相对于当前位置的 4 个方向的横、纵坐标的偏移量
     * 左， 下， 右， 上
     */
    private static final int[][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    /**
     * 标记数组，标记了 grid 的坐标对应的格子是否被访问过
     */
    private boolean[][] marked;
    private int rows;
    private  int cols;

    private char[][] grid;

    /**
     * 深度优先搜索
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        rows = grid.length;
        cols = grid[0].length;
        marked = new boolean[rows][cols];
        this.grid = grid;
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!marked[i][j] && grid[i][j] == '1') {
                    count++;
                    dfs(i, j);
                }
            }
        }
        return count;
    }


    private void dfs(int i, int j) {
        marked[i][j] = true;
        for (int k = 0; k < 4; k++) {
            int newX = i + directions[k][0];
            int newY = j + directions[k][1];
            if (inArea(newX, newY) && grid[newX][newY] == '1' && !marked[newX][newY]) {
                dfs(newX, newY);
            }
        }
    }

    /**
     * 广度优先搜索
     */
    public int numIslands2(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        rows = grid.length;
        cols = grid[0].length;
        marked = new boolean[rows][cols];
        this.grid = grid;
        int count = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (!marked[i][j] && grid[i][j] == '1') {
                    count++;
                    LinkedList<Integer> queue = new LinkedList<>();
                    /**
                     * 坐标转换成二维数组中的索引位置
                     */
                    queue.add(i * cols + j);
                    marked[i][j] = true;
                    while (!queue.isEmpty()) {
                        int cur = queue.pollFirst();
                        int curX = cur / cols;
                        int curY = cur % cols;
                        for (int k = 0; k < 4; k++) {
                            int newX = curX + directions[k][0];
                            int newY = curY + directions[k][1];
                            if (inArea(newX, newY) && !marked[newX][newY] && grid[newX][newY] == '1') {
                                marked[newX][newY] = true;
                                queue.add(newX * cols + newY);
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    public static void main(String[] args) {
        IslandsNumber solution = new IslandsNumber();
        char[][] grid1 = {
                {'1', '1', '1'},
                {'0', '1', '0'},
                {'1', '1', '1'}};
        int numIslands1 = solution.numIslands2(grid1);
        System.out.println(numIslands1);

        char[][] grid2 = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}};
        int numIslands2 = solution.numIslands(grid2);
        System.out.println(numIslands2);
    }
}
