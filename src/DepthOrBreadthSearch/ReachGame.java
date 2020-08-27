package DepthOrBreadthSearch;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 * LeetCode : 1306 跳跃游戏III
 *
 * @author Richa
 * @date 2020/8/27 14:43
 */
public class ReachGame {
    // dfs
    public boolean canReach1(int[] arr, int start) {
        if (arr == null || start >= arr.length || start < 0) {
            return false;
        }
        boolean[] visited = new boolean[arr.length];
        return dfs(arr, start, visited);
    }
    private boolean dfs(int[] arr, int curPos, boolean[] visited) {
        // 终止条件：超出[0, arr.length - 1]边界，被访问过或者找到0
        if (curPos < 0 || curPos >= arr.length || visited[curPos]) {
            return false;
        }
        if (arr[curPos] == 0) {
            return true;
        }
        visited[curPos] = true;
        return dfs(arr, curPos + arr[curPos], visited) || dfs(arr, curPos - arr[curPos], visited);
    }

    // bfs
    public boolean canReach2(int[] arr, int start) {
        if (arr == null || start >= arr.length || start < 0) {
            return false;
        }
        boolean res = false;

        boolean[] visited = new boolean[arr.length];
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offerLast(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int curPos = queue.pollFirst();
            if (arr[curPos] == 0) {
                res = true;
                break;
            }
            int curPosLeft = curPos - arr[curPos];
            if (curPosLeft >= 0 && curPosLeft < arr.length && !visited[curPosLeft]) {
                queue.offerLast(curPosLeft);
                visited[curPosLeft] = true;
            }
            int curPosRight = curPos + arr[curPos];
            if (curPosRight >= 0 && curPosRight < arr.length && !visited[curPosRight]) {
                queue.offerLast(curPosRight);
                visited[curPosRight] = true;
            }
        }
        return res;
    }
}
