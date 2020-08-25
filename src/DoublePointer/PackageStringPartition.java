package DoublePointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * 牛客：包裹区间分组
 * 输入数据只有一行，为一个字符串(不包含引号)，长度不超过1000，只包含大写字母'A'到'Z'，字符之间无空格
 * 输出每个分割成片段的包裹组的长度，每个长度之间通过空格隔开，片段数量尽可能多
 *
 * 滑动窗口
 *
 * @author Richa
 * @date 2020/8/25 23:34
 */
public class PackageStringPartition {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> ans = new ArrayList<>();
        String str = bf.readLine();
        int left = 0, right = 0;
        int pre = 0;

        for (int i = 0; i < str.length();) {
            char ch = str.charAt(i);
            // 判断最后一个当前字符出现位置
            right = str.lastIndexOf(ch);
            while (left < right) {
                // 窗口中间字符的最右位置，它们属于一个片段，使得中间字符在后面不会出现
                char temp = str.charAt(left + 1);
                if (str.lastIndexOf(temp) > right) {
                    right = str.lastIndexOf(temp);
                }
                left++;
            }
            right++;
            i = right;
            ans.add(right);
        }
        StringBuilder sb = new StringBuilder();
        for (int i : ans) {
            sb.append(i - pre).append(" ");
            pre = i;
        }
        System.out.println(sb.toString());
    }
}
