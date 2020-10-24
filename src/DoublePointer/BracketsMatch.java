package DoublePointer;

import java.util.LinkedList;

/**
 *
 * LeetCode 20: 有效的括号
 * 利用栈的性质做括号匹配
 * 
 * @author Richa
 * @date 2020/10/22 18:20
 */
public class BracketsMatch {

    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        LinkedList<Character> stack = new LinkedList<>();
        for (char ch : s.toCharArray()) {
            if (stack.isEmpty()) {
                stack.push(ch);
                continue;
            }
            char top = stack.peek();
            if (isMatch(ch, top)) {
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }

    private boolean isMatch(char input, char top) {
        if (input == ')') {
            return top ==  '(';
        } else if (input == '}') {
            return top == '{';
        } else if (input == ']') {
            return top == '[';
        }
        return false;
    }

}
