package DepthOrBreadthSearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * LeetCode 210: 课程表
 * 返回一种学习顺序，其实就是一种拓扑排序
 *
 * @author Richa
 * @date 2020/8/23 16:16
 */
public class CourseSchedule {
    // 存储有向图的邻接表
    private static List<List<Integer>> edges;
    // 标记每个节点的状态：0=未搜索，1=搜索中，2=已完成
    private static int[] visited;
    // 用数组来模拟栈，下标 n-1 为栈底，0 为栈顶。返回值是数组
    private int[] result;
    // 判断有向图中是否有环
    boolean valid = true;
    // 栈下标
    int index;
    // 进度表，存储每个顶点的进度
    int[] indegree;

    public int[] findOrderDFS(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<Integer>());
        }
        visited = new int[numCourses];
        result = new int[numCourses];
        index = numCourses - 1;
        // 构建邻接表
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }
        // 挑选一个「未搜索」的节点，开始进行深度优先搜索
        for (int i = 0; i < numCourses && valid; i++) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        if (!valid) {
            return new int[0];
        }
        return result;
    }

    private void dfs(int u) {
        // 当前节点=1
        visited[u] = 1;
        // 搜索其相邻节点，发现存在环（状态1）即停止
        for (int v : edges.get(u)) {
            if (visited[v] == 0) {
                dfs(v);
                if (!valid) {
                    return;
                }
            } else if (visited[v] == 1) {
                valid = false;
                return;
            }
        }
        // 其相邻节点搜索完成，当前节点标记为完成=2
        visited[u] = 2;
        // 搜索完的顶点入栈
        result[index--] = u;
    }

    public int[] findOrderBFS(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<Integer>());
        }
        indegree = new int[numCourses];
        result = new int[numCourses];
        // 拓扑排序从前向后开始，index = 0
        index = 0;
        // 构建邻接表和入度表
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
            indegree[info[0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int u = queue.poll();
            result[index++] = u;
            // 遍历相邻节点
            for (int v : edges.get(u)) {
                indegree[v]--;
                if (indegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        if (index != numCourses) {
            return new int[0];
        }
        return result;
    }
}
