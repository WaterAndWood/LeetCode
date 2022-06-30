package Backtracking;

/**
 *
 * LeetCode1559: 二维网格图中探测环
 * 有环的条件是搜索到该次dfs访问过的坐标
 * 搜索的过程中加上上一层搜索过来的方向，不搜索该方向的反方向
 *
 * @author Richa
 * @date 2022/6/28 8:47
 */
public class ContainCycle {
    private boolean[][] visited;
    private char[][] grid;
    private int m, n;
    private boolean hasCycle;

    public boolean containsCycle(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return false;
        }
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    // 开头的字符grid[i][j]
                    dfs(i, j, grid[i][j], 'L');
                    if (hasCycle) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void dfs(int i, int j, char ch, char from) {
        // 不相同的字符直接返回，不会判断是否visited
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != ch) {
            return;
        }
        if (visited[i][j]) {
            hasCycle = true;
            return;
        }
        visited[i][j] = true;
        if (from != 'L') {
            dfs(i, j - 1, ch, 'R');
        }
        if (from != 'R') {
            dfs(i, j + 1, ch, 'L');
        }
        if (from != 'U') {
            dfs(i - 1, j, ch, 'D');
        }
        if (from != 'D') {
            dfs(i + 1, j, ch, 'U');
        }
    }
}
