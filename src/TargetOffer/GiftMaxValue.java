package TargetOffer;

/**
 *
 * Offer47: 礼物的最大价值
 * 动态规划：
 * f(i, j)是从左上角走到单元格(i, j)的礼物最大累计价值
 * f(i,j)=max[f(i,j−1),f(i−1,j)]+grid(i,j)
 * 状态转移方程：
 * i = 0, j = 0: dp(i,j) = grid(i,j)
 * i = 0, j 不等于 0：dp(i,j) = dp(i,j-1) + grid(i,j)
 * i 不等于 0， j = 0: dp(i,j) = dp(i-1,j) + grid(i,j)
 * i 不等于0， j 不等于0：dp(i,j) = dp(i-1,j-1) + grid(i,j)
 *
 * @author Richa
 * @date 2022/4/5 10:05
 */
public class GiftMaxValue {
    /**
     * 使用二维数组表示dp(i,j)
     */
    public int maxValue(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                } else if (i == 0 && j != 0) {
                    grid[i][j] += grid[i][j-1];
                } else if (i != 0 && j == 0) {
                    grid[i][j] += grid[i-1][j];
                } else {
                    grid[i][j] += Math.max(grid[i-1][j], grid[i][j-1]);
                }
            }
        }
        return grid[m-1][n-1];
    }

    /**
     * 一维数组记录，每遍历grid中一行，更新一遍dp
     * dp[j]在计算之前保存的是上一行计算到j列的值；dp[j-1]在计算前保存的是同一行计算到j-1列的值
     */
    public int maxValue2(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n+1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[j] = Math.max(dp[j], dp[j-1]) + grid[i-1][j-1];
            }
        }
        return dp[n];
    }
}
