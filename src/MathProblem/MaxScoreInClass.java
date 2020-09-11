package MathProblem;

import java.util.*;

/**
 *
 * 牛客：考试成绩
 * 班级一共n个学生，考试有m个问题。每个题目都有5个可选答案（A，B，C，D，E）。并且每个题目只有一个正确答案。
 * 每个题目的分数并不一样，第i个题目的分数用a[i]表示。如果题目没答对该题会获得0分。
 *
 * 输入：
 * 第一行包含2个正整数，n和m。(1 <= n, m <= 1000，n是班级中学生数量，m是题目的数量)
 * 下面n行数据每行包含一个string si，表示第i个学生的答案。si的第j个字符表示该学生第j个题目的答案。
 * 输出：
 * 最大分数
 *
 * @author Richa
 * @date 2020/9/8 22:17
 */
public class MaxScoreInClass {
    /**
     * 求班级同学每道题公共答案最多的选项并乘以分值就是最大分数
     *
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();
        char[][] ans = new char[n][m];
        for (int i = 0; i < n; i++) {
            String s = sc.nextLine();
            ans[i] = Arrays.copyOf(s.toCharArray(), m);
        }
        int[] score = new int[m];
        for (int j = 0; j < m; j++) {
            score[j] = sc.nextInt();
        }

        int res = 0;
        // 按列统计，按题目统计答案个数
        for (int j = 0; j < m; j++) {
            int num = 0;
            Map<Integer, Integer> map = new HashMap<>();
            // 统计人数最多的答案
            for (int i = 0; i < n; i++) {
                int key = ans[i][j] - 'A';
                if (map.containsKey(key)) {
                    map.put(key, map.get(key) + 1);
                } else {
                    map.put(key, 1);
                }
                num = Math.max(num, map.get(key));
            }
            res += score[j] * num;
        }
        System.out.println(res);
    }
}
