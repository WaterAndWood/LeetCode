package StringAndChar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * 牛客：鸡鸭分类
 * 输入一个长度为N，且只包含C和D的非空字符串。
 * 农场有n只鸡鸭排为一个队伍，鸡用“C”表示，鸭用“D”表示。当鸡鸭挨着时会产生矛盾。需要对所排的队伍进行调整，使鸡鸭各在一边。
 * 每次调整只能让相邻的鸡和鸭交换位置，现在需要尽快完成队伍调整，你需要计算出最少需要调整多少次可以让上述情况最少。
 * 
 * C 或者D 移动到左端最少的步数
 * 
 * @author Richa
 * @date 2020/8/13 12:26
 */
public class CharClassification {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        char[] chars = bf.readLine().toCharArray();
        System.out.println(Math.min(solve('C', chars), solve('D', chars)));
    }
    
    public static int solve(char ch, char[] chars) {
        int count = 0;
        int step = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ch) {
                /**
                 * 先计算step，再count++
                 *
                 */
                step += (i - count);
                count++;
            }
        }
        return step;
    }
}
