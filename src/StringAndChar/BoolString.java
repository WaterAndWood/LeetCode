package StringAndChar;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 *
 * 牛客：表达式求值
 *
 * 输入第一行包含布尔表达式字符串s，s只包含true、false、and、or几个单词（不会出现其它的任何单词），
 * 且单词之间用空格分隔。 (1 ≤ |s| ≤ 103)
 * 输出true、false或error，true表示布尔表达式计算为真，false表示布尔表达式计算为假，error表示一个不合法的表达式。
 *
 * 入栈过程中，消除and
 *
 * @author Richa
 * @date 2020/8/23 21:34
 */
public class BoolString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        boolString(s);
    }
    public static void boolString(String[] s) {
        Deque<String> stack = new ArrayDeque<>();
        int len = s.length;
        if ("and".equals(s[0]) || "or".equals(s[0]) || "and".equals(s[len - 1]) || "or".equals(s[len - 1])) {
            System.out.println("error");
            return;
        }

        for (int i = 0; i < len; i++) {
            String cur = s[i];
            if ("true".equals(cur) || "false".equals(cur)) {
                if (stack.isEmpty()) {
                    stack.offerFirst(cur);
                } else {
                    String top = stack.peek();
                    // 连续true/false
                    if ("true".equals(top) || "false".equals(top)) {
                        System.out.println("error");
                        return;
                    } else {
                        if ("or".equals(top)) {
                            stack.offerFirst(cur);
                        } else {
                            // and计算之后在入栈
                            stack.poll();
                            String pre = stack.poll();
                            if ("false".equals(cur) || "false".equals(pre)) {
                                stack.push("false");
                            } else {
                                stack.push("true");
                            }
                        }
                    }
                }
            } else {
                if (stack.isEmpty()) {
                    System.out.println("error");
                    return;
                } else {
                    String peek = stack.peek();
                    // 连续or/and
                    if ("or".equals(peek) || "and".equals(peek)) {
                        System.out.println("error");
                        return;
                    } else {
                        stack.push(cur);
                    }
                }
            }
        }
        while (!stack.isEmpty()) {
            if ("true".equals(stack.pop())) {
                System.out.println("true");
                break;
            }
            if (stack.isEmpty()) {
                System.out.println("false");
            }
        }

    }
}
