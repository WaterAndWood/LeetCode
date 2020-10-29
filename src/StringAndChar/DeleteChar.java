package StringAndChar;

import java.util.HashSet;
import java.util.Set;
/**
 *
 * 按照字典序删除字符串中的字母，每次删除一个，一共删除k个结束
 * 
 * @author Richa
 * @date 2020/10/29 9:01
 */
public class DeleteChar {
    public String deleteChar(String s, int k) {
        // 统计字符串中26个字母的出现次数
        int[] charNum = new int[26];
        for (int i = 0; i < s.length(); i++) {
            charNum[s.charAt(i) - 'a'] += 1;
        }
        // 先找出需要全部删除的字母
        Set<Character> allDeleteChar = new HashSet<>();
        int index = 0;
        for (int i = 0; i < charNum.length; i++) {
            if (charNum[i] <= k) {
                allDeleteChar.add((char)(i + 'a'));
                k -= charNum[i];
            } else {
                index = i;
                break;
            }
        }
        StringBuilder sb = new StringBuilder();
        // 第一次删除，删除应该全部删除的字符
        for (int i = 0; i < s.length(); i++) {
            if (allDeleteChar.contains(s.charAt(i))) {
                continue;
            }
            sb.append(s.charAt(i));
        }
        s = sb.toString();

        StringBuilder result = new StringBuilder();
        // 第二次删除，删除不全部删除的字符
        for (int i = 0; i < s.length(); i++) {
            if (k > 0 && s.charAt(i) == (char)('a' + index)) {
                k--;
                continue;
            }
            result.append(s.charAt(i));
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String s = "abcabc";
        DeleteChar deleteChar = new DeleteChar();
        System.out.println(deleteChar.deleteChar(s, 3));
    }
}
