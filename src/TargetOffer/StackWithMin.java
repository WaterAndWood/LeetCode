package TargetOffer;

import java.util.LinkedList;

/**
 *
 * Offer30: 包含min函数的栈
 * 添加辅助栈，辅助栈中存最小值
 *
 * @author Richa
 * @date 2022/2/20 22:49
 */
public class StackWithMin {
    LinkedList<Integer> stack;
    LinkedList<Integer> minStack;

    public StackWithMin() {
        this.stack = new LinkedList<>();
        this.stack = new LinkedList<>();
    }

    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty()) {
            minStack.push(x);
        } else {
            int top = minStack.peek();
            if (x < top) {
                minStack.push(x);
            } else {
                minStack.push(top);
            }
        }
    }

    public int pop() {
        minStack.pop();
        return stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return minStack.peek();
    }
}
