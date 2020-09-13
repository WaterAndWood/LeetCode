package Backtracking;

/**
 *
 * LeetCode 79: 单词搜索
 *
 * @author Richa
 * @date 2020/9/13 15:27
 */
public class WordExist {

    private static final int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    private boolean[][] marked;
    private int rows;
    private int cols;
    private String word;
    private char[][] board;

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0) {
            return false;
        }

        rows = board.length;
        cols = board[0].length;
        marked = new boolean[rows][cols];
        this.word = word;
        this.board = board;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (dfs(i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int i, int j, int start) {
        // 回溯终止条件，字符串最后一位且相等
        if (start == word.length() - 1) {
            return board[i][j] == word.charAt(start);
        }
        if (board[i][j] == word.charAt(start)) {
            marked[i][j] = true;
            // 四个方向进行下一步回溯
            for (int k = 0; k < 4; k++) {
                int newX = i + directions[k][0];
                int newY = j + directions[k][1];
                if (inArea(newX, newY) && !marked[newX][newY]) {
                    if (dfs(newX, newY, start + 1)) {
                        return true;
                    }
                }
            }
            // 四个方向回溯不匹配，回溯标记
            marked[i][j] = false;
        }
        return false;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}
