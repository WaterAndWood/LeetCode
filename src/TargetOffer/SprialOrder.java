package TargetOffer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Offer29：顺时针打印矩阵
 *
 * @author Richa
 * @date 2022/3/19 8:58
 */
public class SprialOrder {
    public int[] sprialOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }
        List<Integer> ans = new ArrayList<>();
        // 下边界
        int rows = matrix.length - 1;
        // 右边界
        int cols = matrix[0].length - 1;
        // 上边界和左边界
        int row = 0, col = 0;
        while (row <= rows && col <= cols) {
            // 从左向右
            for (int c = col; c <= cols; c++) {
                ans.add(matrix[row][c]);
            }
            // 从上往下
            for (int r = row + 1; r <= rows; r++) {
                ans.add(matrix[r][cols]);
            }
            if (col < cols && row < rows) {
                // 从左往右
                for (int c = cols - 1; c >= col; c--) {
                    ans.add(matrix[rows][c]);
                }
                // 从下往上
                for (int r = rows - 1; r > row; r--) {
                    ans.add(matrix[r][col]);
                }
            }
            rows--;
            cols--;
            row++;
            col++;
        }
        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i);
        }
        return res;
    }
}
