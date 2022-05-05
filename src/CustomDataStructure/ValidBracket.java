package CustomDataStructure;

import java.util.LinkedList;

/**
 *
 * LeetCode 20:有效的括号
 * 设置一个辅助栈
 * @author Richa
 * @date 2022/5/5 19:31
 */
public class ValidBracket {
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        LinkedList<Character> stack = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if (stack.isEmpty()) {
                stack.push(c);
            } else {
                char top = stack.peek();
                if (isMatch(c, top)) {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }

        }
        return stack.isEmpty();
    }

    private boolean isMatch(char input, char top) {
        if (input == ')') {
            return top == '(';
        } else if (input == ']') {
            return top == '[';
        } else if (input == '}') {
            return top == '{';
        } else {
            return false;
        }
    }
}
