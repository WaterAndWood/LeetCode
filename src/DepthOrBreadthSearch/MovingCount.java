package DepthOrBreadthSearch;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * 机器人运动范围
 * 返回值是可以覆盖到的方格数量
 *
 * @author Richa
 * @date 2020/6/20 11:15
 */
class MovingCount {
    private boolean[][] visited;
    private int rows;
    private int cols;

    public int movingCountDepthSearch(int m, int n, int k) {
        if(m < 0 || n < 0 || k < 0) {
            return 0;
        }
        this.rows = m;
        this.cols = n;
        visited = new boolean[rows][cols];
        return dfs(0, 0,  k);

    }
    private int dfs(int row, int col, int k){
        // 搜索到边界或者已经搜索过或者和>k
        if (row == rows || col == cols || visited[row][col] || sums(row, col) > k) {
            return 0;
        } else {
            // 尾递归，触底反弹
            visited[row][col] = true;
            // 向右或者向下深度搜索
            return 1 + dfs(row + 1, col, k) + dfs( row,col + 1, k);
        }
    }

    public int movingCountBreadthSearch(int m, int n, int k) {
        Deque<int[]> deque = new LinkedList<>();
        visited = new boolean[m][n];
        // int[] 数组中存横坐标，纵坐标
        deque.offer(new int[]{0,0});
        int count = 0;
        visited[0][0] = true;
        while (!deque.isEmpty()) {
            int[] pre = deque.poll();
            count++;
            int preRow = pre[0];
            int preCol = pre[1];
            int row = pre[0] + 1;
            int col = pre[1] + 1;
            // 向下移动1位，纵坐标加1
            if (row < m && !visited[row][preCol] && sums(row, preCol) <= k) {
                visited[row][preCol] = true;
                deque.offer(new int[]{row, preCol});
            }
            // 向右移动1位，横坐标加1
            if (col < n && !visited[preRow][col] && sums(preRow, col) <= k) {
                visited[preRow][col] = true;
                deque.offer(new int[]{preRow, col});
            }
        }
        return count;
    }
    /**
     *
     * 计算行和列数位和
     *
     * @author Richa on 2020/6/14 17:09
     * @param x 横坐标
     * @param y 纵坐标
     * @return 和
     * @throws
     */
    public int sums(int x,int y){
        int ans=0;
        while (x != 0) {
            ans+=x%10;
            x/=10;
        }
        while (y != 0) {
            ans+=y%10;
            y/=10;
        }
        return ans;
    }

    public static void main(String[] args) {
        MovingCount movingCount = new MovingCount();
        int ans = movingCount.movingCountBreadthSearch(3, 2, 2);
        System.out.println(ans);
    }
}