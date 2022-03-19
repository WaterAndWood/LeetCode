package TargetOffer;

import java.util.LinkedList;

/**
 *
 * Offer31: 栈的压入和弹出序列
 * 辅助栈
 *
 * @author Richa
 * @date 2022/3/19 15:17
 */
public class StackSequence {
    public boolean validStackSequence(int[] pushed, int[] poped) {
        if (pushed == null && poped == null) {
            return true;
        }
        if (pushed == null || poped == null) {
            return false;
        }
        if (pushed.length != poped.length) {
            return false;
        }

        LinkedList<Integer> stack = new LinkedList<>();
        int j = 0;
        for (int i = 0; i < pushed.length; i++) {
            if (pushed[i] != pushed[j]) {
                stack.push(pushed[i]);
            } else {
                // 不压入栈直接处理
                j++;
                while (j < poped.length) {
                    if (!stack.isEmpty() && (stack.peek() == poped[j])) {
                        stack.pop();
                        j++;
                    } else {
                        break;
                    }
                }
            }

        }
        return stack.isEmpty();
    }

    public boolean validStackSequence2(int[] pushed, int[] poped) {
        if (pushed == null && poped == null) {
            return true;
        }
        if (pushed == null || poped == null) {
            return false;
        }
        if (pushed.length != poped.length) {
            return false;
        }

        int j = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i < pushed.length; i++) {
            // 先压入栈再处理
            stack.push(pushed[i]);
            while (!stack.isEmpty() && j < poped.length && (stack.peek() == poped[j])) {
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }
}
