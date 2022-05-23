package Backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * LeetCode 51: N 皇后
 * 主对角线：行号 - 列号 = 常数；副对角线：行号 + 列号 = 常数
 * 回溯算法主流程：
 * result = []
 * def backtrack(路径， 选择列表):
 *     if 满足结束条件:
 *        result.add(路径)
 *        return
 *    for 选择 in 选择列表：
 *        做选择
 *        backtrack(路径，选择列表)
 *        撤销选择
 *
 * @author Richa
 * @date 2020/8/1 10:12
 */
public class NQueen {
    // 存放每一列皇后所在的行
    int[] rows;
    // 副对角线方向 y = x + a
    int[] secondary;
    // 主对角线方向 y = -x + b
    int[] mains;
    int n;
    // 存放每一行皇后所在的列
    int[] queens;
    private List<List<String>> output = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        rows = new int[n];
        /**
         * 主对角线和副对角线与(row, col) 映射，分别映射为 2n - 1 条不同的主副对角线
         * 主对角线映射关系：row - col + n - 1
         * 副对角线映射关系：row + col
         */
        secondary = new int[2 * n - 1];
        mains = new int[2 * n - 1];
        queens = new int[n];
        // 第一行开始回溯
        backtrack(0);
        return output;
    }

    /**
     * 回溯过程：对于每一行，逐列判断是否可以放置皇后。如果当前行是最后一行，找到一个solution；否则递归下一行
     * 当前列回溯失败，移除之前放置的皇后，判断下一列
     */
    public void backtrack(int row) {
        if (row >= n) {
            return;
        }
        for (int col = 0; col < n; col++) {
            if (isNotUnderAttack(row, col)) {
                placeQueen(row, col);
                if (row == n - 1) {
                    addSolution();
                }
                backtrack(row + 1);
                removeQueen(row, col);
            }
        }
    }

    private boolean isNotUnderAttack(int row, int col) {
        // 当前位置所在列、主对角线和副对角线存在皇后就false
        int res = rows[col] + mains[row - col + n - 1] + secondary[row + col];
        return res == 0;
    }

    private void placeQueen(int row, int col) {
        // 在row行，col列放置皇后
        queens[row] = col;
        // 当前位置的列方向已经有皇后了
        rows[col] = 1;
        // 当前位置所在主对角线存在皇后
        mains[row - col + n - 1] = 1;
        // 当前位置所在副对角线存在皇后
        secondary[row + col] = 1;
    }

    private void removeQueen(int row, int col) {
        queens[row] = 0;
        rows[col] = 0;
        mains[row - col + n - 1] = 0;
        secondary[row + col] = 0;
    }

    private void addSolution() {
        List<String> solution = new ArrayList<>();
        for (int i  = 0; i < n; i++) {
            /**
             * 每个index表示一行，一行包含一个Q，queens[i] 是该行皇后位置
             * 每行对应一个string
             */
            int col = queens[i];
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < col; j++) {
                sb.append(".");
            }
            sb.append("Q");
            for (int j = 0; j < n - col - 1; j++) {
                sb.append(".");
            }
            solution.add(sb.toString());
        }
        output.add(solution);
    }
}
