package TargetOffer;

import java.util.LinkedList;

/**
 *
 * Offer 30: 包含min函数的栈
 *
 * @author Richa
 * @date 2022/3/19 13:48
 */
public class MinStack {
    LinkedList<Integer> stack;
    LinkedList<Integer> minStack;

    public MinStack() {
        this.stack = new LinkedList<>();
        this.stack = new LinkedList<>();
    }

    public void push(int x) {
        if (stack.isEmpty() && minStack.isEmpty()) {
            stack.push(x);
            minStack.push(x);
        } else {
            Integer top = minStack.peek();
            if (x < top) {
                minStack.push(x);
            } else {
                minStack.push(top);
            }
            stack.push(x);
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
