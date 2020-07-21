package DoublePointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 *
 * 去除字符串中重复的字符，牛客：聪明的编辑
 * AAA -> AA
 * AABB -> AAB
 * AABBCC -> AABCC
 * 第一行包括一个数字N，表示本次用例包括多少个待校验的字符串。
 * 后面跟随N行，每行为一个待校验的字符串。
 *
 * @author Richa
 * @date 2020/7/22 0:12
 */
public class RemoveDuplicateCharacters {
    public void removeDuplicateCharacters() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        // 输入行数
        int n = Integer.parseInt(bf.readLine());

        for (int i = 0; i < n; i++) {
            String str = bf.readLine();
            // 操作方便
            char[] ch = str.toCharArray();
            int len = str.length();
            if (len < 3) {
                System.out.println(str);
            } else {
                // 慢指针
                int s = 0;
                for (int f = 0; f < len; f++) {
                    if (f > 1 && ch[f] == ch[s - 1] && ch[s - 1] == ch[s - 2]) {
                        // AAA模式，f = s = 第三个A
                        ch[f] = '\0';
                    } else if (f > 2 && ch[f] == ch[s - 1] && ch[s - 2] == ch[s - 3]){
                        // AABB模式，f = s = 第二个B
                        ch[f] = '\0';
                    } else {
                        ch[s] = ch[f];
                        s++;
                    }
                }
                System.out.println(String.valueOf(ch).substring(0, s));
            }

        }
    }

    public static void main(String[] args) throws IOException{
        RemoveDuplicateCharacters rm = new RemoveDuplicateCharacters();
        rm.removeDuplicateCharacters();
    }
}
