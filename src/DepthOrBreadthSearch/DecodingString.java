package DepthOrBreadthSearch;

import java.util.LinkedList;

/**
 *
 * LeetCode 394
 * 辅助栈
 * 深度优先dfs（递归）
 *
 * @author Richa
 * @date 2020/8/12 13:09
 */
public class DecodingString {
    /**
     * 辅助栈的入栈条件[ 和出栈条件]
     * 3[a2[c]] -> 3[acc] -> accaccacc
     *
     */
    public String decodeString1(String s) {
        StringBuilder res = new StringBuilder();
        LinkedList<Integer> multiStack = new LinkedList<>();
        LinkedList<String> resStack = new LinkedList<>();
        int multi = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '[') {
                multiStack.addLast(multi);
                multi = 0;
                resStack.addLast(res.toString());
                res = new StringBuilder();
            } else if (ch == ']') {
                int curMulti = multiStack.pollLast();
                StringBuilder temp = new StringBuilder();
                while (curMulti-- > 0) {
                    temp.append(res);
                }
                res = new StringBuilder(resStack.pollLast() + temp);
            } else if (ch >= '0' && ch <= '9') {
                multi = 10 * multi + Integer.parseInt(String.valueOf(ch));
            } else {
                res.append(ch);
            }
        }
        return res.toString();
    }

    /**
     * [和]分别作为递归开启和终止的条件
     * 当 s[i] == ']' 时，返回当前括号内记录的 res 字符串与 ] 的索引 i （更新上层递归指针位置）；
     * 当 s[i] == '[' 时，开启新一层递归，记录此 [...] 内字符串 tmp 和递归后的最新索引 i，并执行 res + multi * tmp 拼接字符串。
     *
     */
    public String decodeString2(String s) {
        return dfs(s, 0)[0];
    }

    private String[] dfs(String s, int i) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        while (i < s.length()) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                multi = 10 * multi + Integer.parseInt(String.valueOf(s.charAt(i)));
            } else if (s.charAt(i) == '[') {
                String[] strings = dfs(s, i + 1);
                i = Integer.valueOf(strings[0]);
                while (multi-- > 0) {
                    res.append(strings[1]);
                }
            } else if (s.charAt(i) == ']') {
                return new String[] {String.valueOf(i), res.toString()};
            } else {
                res.append(s.charAt(i));
            }
            i++;
        }
        return new String[] {res.toString()};
    }

    public static void main(String[] args) {
        String str = "3[a2[c]]";
        DecodingString ds = new DecodingString();
        System.out.println(ds.decodeString2(str));
    }
}
