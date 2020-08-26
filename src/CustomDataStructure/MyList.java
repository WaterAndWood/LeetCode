package CustomDataStructure;

import java.util.Stack;
/**
 *
 * LeetCode: 232 用栈实现队列
 * 与用队列实现栈不同，最好固定一个栈入，一个栈出
 * 两次入栈即使顺序为正
 *
 * @author Richa
 * @date 2020/8/26 16:31
 */
public class MyList {
    private Stack<Integer> stackPush;
    private Stack<Integer> stackPop;

    public MyList() {
        stackPush = new Stack<>();
        stackPop = new Stack<>();
    }

    // 只向一个栈push
    public void push(int x) {
        stackPush.push(x);
    }

    // 只有stackPop为空的时候，将stackPush中的元素全部加入stackPop，否则一直pop stackPop栈中的元素
    public int pop() {
        if (stackPop.isEmpty()) {
            while (!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }
        if (!stackPop.isEmpty()) {
            return stackPop.pop();
        }
        throw new RuntimeException("队列无元素");
    }

    // 只有stackPop为空的时候，将stackPush中的元素全部加入stackPop，否则一直peek stackPop栈中的元素
    public int peek() {
        if (stackPop.isEmpty()) {
            while (!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }
        if (!stackPop.isEmpty()) {
            return stackPop.peek();
        }
        throw new RuntimeException("队列无元素");
    }

    public boolean empty() {
        return stackPop.isEmpty() && stackPush.isEmpty();
    }
}
