package TargetOffer;

import java.util.LinkedList;

/**
 *
 * Offer9: 用两个栈实现队列
 * stack2不为空，栈顶是先进入的元素，可以弹出；stack2为空，把stack1的元素弹出压入stack2
 *
 * @author Richa
 * @date 2022/2/20 19:59
 */
public class CQueue {
    private LinkedList<Integer> stack1;
    private LinkedList<Integer> stack2;
    int size;

    public CQueue() {
        stack1 = new LinkedList<>();
        stack2 = new LinkedList<>();
        int size = 0;
    }

    /**
     * 队列入，压入stack1
     */
    public void appendTail(int value) {
        stack1.push(value);
        size++;
    }

    /**
     * 对列出，弹出stack2
     */
    public int deleteHead() {
        if (size == 0) {
            return -1;
        }
        if (stack2.isEmpty()) {
            while(!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        size--;
        return stack2.pop();
    }
}
