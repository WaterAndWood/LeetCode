package StringAndChar;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * LeetCode: 394
 * 解析编码的字符串，去除数字
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * 利用栈实现深度优先搜素，]相当于深度优先的终止条件
 *
 * @author Richa
 * @date 2020/8/26 13:57
 */
public class DecodeNumCharString {
    public String decodingString(String s) {
        StringBuilder res = new StringBuilder();

        int multi = 0;
        Deque<Integer> multiStack = new LinkedList<>();
        Deque<String> stringStack = new LinkedList<>();
        for (Character ch : s.toCharArray()) {
            // 表示进入下一层，本层的数字作为下一层的乘积，本层的字符串在下一层计算完成后作为前缀
            if (ch == '[') {
                multiStack.push(multi);
                stringStack.push(res.toString());
                multi = 0;
                res = new StringBuilder();
            } else if (ch == ']') {
                // 乘法当前层的字符串：multi * [...]
                StringBuilder temp = new StringBuilder();
                int curMulti = multiStack.pop();
                while (curMulti-- > 0) {
                    temp.append(res);
                }
                res = new StringBuilder(stringStack.pop() + temp);
            } else if (Character.isDigit(ch)) {
                multi = multi * 10 + (ch - '0');
            } else {
                res.append(ch);
            }
        }
        return res.toString();
    }
}
