package MathProblem;

import java.util.Scanner;

/**
 *
 * 牛客：球队打平
 * 有三只球队，每只球队编号分别为球队1，球队2，球队3，这三只球队一共需要进行 n 场比赛。
 * 现在已经踢完了k场比赛，每场比赛不能打平，踢赢一场比赛得一分，输了不得分不减分。
 * 已知球队1和球队2的比分相差d1分，球队2和球队3的比分相差d2分，每场比赛可以任意选择两只队伍进行。求
 * 如果打完最后的 (n-k) 场比赛，有没有可能三只球队的分数打平。  
 *
 * 第一行包含一个数字 t (1 <= t <= 10)
 * 接下来的t行每行包括四个数字 n, k, d1, d2(1 <= n <= 10^12; 0 <= k <= n, 0 <= d1, d2 <= k)
 * 能够打平输出"yes"，否则输出"no"
 *
 * @author Richa
 * @date 2020/8/6 22:40
 */
public class TeamEquals {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            long n = sc.nextLong();
            long k = sc.nextLong();
            long d1 = sc.nextLong();
            long d2 = sc.nextLong();

            if (n % 3 != 0) {
                System.out.println("no");
                continue;
            }
            /**
             * 如果最后3只球队得分相等，每个队得分是 n / 3
             * 以第二只球队看，设第二只球队得分b，则第一只球队得分b +/- d1；第三只球队得分 b +/- d2
             * 三只球队现在得分大于等于0，最终得分小于等于res就有可能
             */
            long res = n / 3;
            long[][] dir = new long[][] {{d1, d2}, {d1, -d2}, {-d1, d2}, {-d1, -d2}};
            boolean finished = false;
            for (int j = 0; j < 4; j++) {
                if ((k - dir[j][0] - dir[j][1]) % 3 == 0) {
                    long secondTeam = (k - dir[j][0] - dir[j][1]) / 3;
                    if (secondTeam >= 0 && secondTeam + dir[j][0] >= 0 && secondTeam + dir[j][1] >= 0) {
                        if (res >= secondTeam && res >= secondTeam + dir[j][0] && res >= secondTeam + dir[j][1]) {
                            finished = true;
                            break;
                        }
                    }
                }
            }
            if (finished) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        }
        sc.close();
    }
}
