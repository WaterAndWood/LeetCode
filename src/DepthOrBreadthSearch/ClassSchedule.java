package DepthOrBreadthSearch;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 拓扑排序：给定一个包含 n 个节点的有向图 G，我们给出它的节点编号的一种排列，如果满足：
 * 对于图 G 中的任意一条有向边 (u,v)，u 在排列中都出现在 v 的前面。称为拓扑排序。
 * 拓扑排序方法的最优时间复杂度为 O(n+m)，其中 n 和 m分别是有向图 G 的节点数和边数；
 * 图 G 是否存在拓扑排序，至少也要对其进行一次完整的遍历，时间复杂度也为 O(n+m)
 *
 * 图的表示方法：
 * 邻接矩阵：可以表示有向图和无向图，G[i][j] 定为vi, vj 的权重，方便计算出入度；保存无向图可以节省一半空间
 * 出度：从该点发出的边数为出度；入度：指向该点的边数为入度
 * 如果两节点之间有一条弧，则邻接矩阵中对应的元素为1；否则为0
 * 权值需要构建同维度的权值矩阵
 *
 * 邻接表：是一个数组，储存邻接矩阵的每一行，每个数组元素arr[i] 是一个链表，表示和arr[i] 连接的顶点vertex
 * 邻接表表示法就是对图的每个顶点，用一个单向链表列出从该节点出发的所有弧，链表中每个单元对应于一条出弧。
 * 可以保存无向图，对于有向图只能计算出度，需要构造逆邻接表（指向自己的边）计算入度
 * 权值需要顶点加入数据域
 *
 * LeetCode 207: 课程表
 * 深度优先搜索：用一个栈来存储所有已经搜索完成的节点。
 * 对于一个节点 u，如果它的所有相邻节点都已经搜索完成，那么在搜索回溯到 u 的时候，u 本身也会变成一个已经搜索完成的节点。
 * 这里的「相邻节点」指的是从 u 出发通过一条有向边可以到达的所有节点。
 * 假设当前搜索到节点u，如果它的所有相邻节点都已经搜索完成，那这些相邻节点均在栈中，u入栈。从栈顶看，u在栈顶并且出现在
 * 它的所有相邻节点的前面，从栈顶到栈底是一种拓扑排序
 *
 * 广度搜索优先：
 * 考虑拓扑排序中最前面的顶点，入度为0，不需要前面的课程。一个顶点加入答案后，移除它的出边，相邻顶点入度-1。
 * 如果相邻节点的入度为0，表示也没有前面的课程，可以加入答案
 * 入度为0的顶点依次放入答案，如果答案中包含n个顶点，说明图中无环
 *
 * @author Richa
 * @date 2020/8/22 15:47
 */
public class ClassSchedule {
    /**
     * 对于图中的任意一个节点，它在搜索的过程中有三种状态，即：
     *
     * 「未搜索」：没有搜索到这个节点，0 表示；
     *
     * 「搜索中」：搜索过这个节点，但还没有回溯到该节点，即该节点还没有入栈，还有相邻的节点没有搜索完成，1 表示；
     *
     * 「已完成」：搜索过并且回溯过这个节点，即该节点已经入栈，并且所有该节点的相邻节点都出现在栈的更底部的位置，满足拓扑排序的要求。
     *
     * 通过上述的三种状态，我们就可以给出使用深度优先搜索得到拓扑排序的算法流程，在每一轮的搜索搜索开始时，我们任取一个「未搜索」的节点开始进行深度优先搜索。
     *
     * 对于未搜索的v，开始搜索v，搜索结束回溯v；如果v 是正在搜索，图中出现环，不存在拓扑排序；已完成的v 不需要操作
     * 只需等u 所有相邻节点都搜索完成，将u 加入栈。本题只记录每个节点的状态，而省去对应的栈。
     *
     */
    // 表示图的邻接表
    List<List<Integer>> edges;

    // 每个顶点的入度数组
    int[] indeg;
    public boolean canFinishDFS(int numcourses, int[][] prerequisites) {
        // 构建邻接表存储prerequisites图结构
        edges = new ArrayList<>();
        for (int i = 0; i < numcourses; i++) {
            edges.add(new ArrayList<>());
        }
        for (int[] arr : prerequisites) {
            // arr[1]出发到arr[0]，arr[1] -> arr[0]
            edges.get(arr[1]).add(arr[0]);
        }
        /**
         * 顶点的状态：0, -1, 1
         * 0: 未被其他DFS访问
         * -1：被其他节点启动的DFS访问
         * 1：被当前节点的DFS访问到
         */
        int[] visited = new int[numcourses];
        // dfs递归该课的先修课路径上是否存在回路
        for (int i = 0; i < numcourses; i++) {
            if (!dfs(edges, visited, i)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(List<List<Integer>> edges, int[] visited, int i) {
        // 等于1说明被本轮DFS节点i第2次访问到，说明有环
        if (visited[i] == 1) {
            return false;
        }
        // 等于-1说明被其他节点访问过，不需要再次访问
        if (visited[i] == -1) {
            return true;
        }
        // 本轮dfs访问置为1
        visited[i] = 1;
        // 访问节点i的相邻节点j
        for (Integer j : edges.get(i)) {
            if (!dfs(edges, visited, j)) {
                return false;
            }
        }
        // 当前节点的相邻节点访问完没有环，置为-1，返回true
        visited[i] = -1;
        return true;
    }

    public boolean canFinishBFS(int numcourses, int[][] prerequisites) {
        edges = new ArrayList<>();
        for (int i = 0; i < numcourses; i++) {
            edges.add(new ArrayList<>());
        }
        indeg = new int[numcourses];
        /**
         * 构建邻接表和入度表
         *
         */
        for (int[] arr : prerequisites) {
            edges.get(arr[1]).add(arr[0]);
            // arr[1] -> arr[0]，arr[0]的入度+1
            indeg[arr[0]]++;
        }
        // BFS标志性的队列
        Deque<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numcourses; i++) {
            if (indeg[i] == 0) {
                queue.offer(i);
            }
        }
        int visited = 0;
        while (!queue.isEmpty()) {
            visited++;
            int u = queue.poll();
            for (int v : edges.get(u)) {
                indeg[v]--;
                if (indeg[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        // 拓扑排序未保留，只判断答案中顶点数是否等于课程数
        return visited == numcourses;
    }
}
