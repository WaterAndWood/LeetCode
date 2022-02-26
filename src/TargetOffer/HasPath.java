package TargetOffer;

/**
 * @author wangzhen
 * @creatTime 2022/2/26 4:25 下午
 * @description Offer12: 矩阵中路径
 * 回溯法：适合解决一个问题有很多步，每一步还有多个选择
 * 可以看成是深度优先搜索（dfs)+剪枝
 */
public class HasPath {
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length < 1 || board[0].length < 1 || word == null || word.isEmpty()) {
            return false;
        }

        char[] arr = word.toCharArray();
        int rows = board.length;
        int cols = board[0].length;
        /**
         * 标记是否搜索过，剪枝条件之一
         */
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                /**
                 * 首先第一个字符要匹配
                 */
                if (board[i][j] == arr[0]) {
                    if (dfs(board, visited, arr, i, j, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     *
     * @param board
     * @param visited
     * @param arr
     * @param row
     * @param col
     * @param index 当前已经匹配到字符串的字符的位置
     * @return
     */
    private boolean dfs(char[][] board, boolean[][] visited, char[] arr, int row, int col, int index) {
        if (index == arr.length) {
            return true;
        }
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || visited[row][col] || board[row][col] != arr[index]) {
            return false;
        }
        /**
         * 搜索的坐标处于界内，未曾访问到且字符匹配
         * visited置为true，表示在这一步之后的搜索步骤中，该坐标已经搜索过
         * 然后开始向四个方向搜索
         */
        visited[row][col] = true;
        boolean ans = dfs(board, visited, arr, row + 1, col, index + 1)
                || dfs(board, visited, arr, row - 1, col, index + 1)
                || dfs(board, visited, arr, row, col + 1, index + 1)
                || dfs(board, visited, arr, row, col - 1, index + 1);
        /**
         * 从该坐标开始的搜索步骤结束，有结果ans（不管true或false），要向上一步返回，返回前将visited的坐标置为false。表示
         * 在上一步（或者几步）的搜索路径中，该坐标是未被搜索过的
         */
        visited[row][col] = false;
        return ans;
    }
}
