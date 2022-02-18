package TargetOffer;

/**
 *
 * Offer4: 二维数组中的查找
 * 从右上角开始缩减范围，如果右上角数字大于目标，删除该数字所在列；如果小于目标，删除该数字所在行
 *
 * @author Richa
 * @date 2022/2/18 22:07
 */
public class FindInTwoDimension {
    public boolean findNumberInTwoDimension(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int row = 0;
        int col = cols - 1;
        while (row < rows && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }
}
