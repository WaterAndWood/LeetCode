package CustomDataStructure;

import java.util.LinkedList;
/**
 *
 * LeetCode 225: 队列实现栈操作
 * 
 * @author Richa
 * @date 2020/8/26 16:58
 */
public class MyStack {
    // 始终一个list空，一个list存元素，队尾元素是栈顶
    private LinkedList<Integer> list1;
    private LinkedList<Integer> list2;

    public MyStack() {
        list1 = new LinkedList<>();
        list2 = new LinkedList<>();
    }

    /**
     * 非空队列存栈的元素，队尾是栈顶，push直接add 队尾
     *
     */
    public void push(int x) {
        if (list1.isEmpty()) {
            list2.add(x);
        } else {
            list1.add(x);
        }
    }

    /**
     * 弹出非空队列的队尾元素（栈顶），需要将队首至队尾前的元素移动到空队列
     *
     */
    public int pop() {
        if (list1.isEmpty()) {
            while (list2.size() > 1) {
                list1.add(list2.removeFirst());
            }
            return list2.removeFirst();
        } else {
            while (list1.size() > 1) {
                list2.add(list1.removeFirst());
            }
            return list1.remove();
        }
    }

    /**
     * 类似pop()，找出非空队列的队尾元素
     *
     */
    public int top() {
        int top = 0;
        if (list1.isEmpty()) {
            while (list2.size() > 1) {
                list1.add(list2.removeFirst());
            }
            top = list2.remove();
            list1.add(top);
        } else {
            while(list1.size() > 1) {
                list2.add(list1.removeFirst());
            }
            top = list1.remove();
            list2.add(top);
        }
        return top;
    }

    /**
     * 两个队列空即为空
     *
     */
    public boolean empty() {
        return list1.isEmpty() && list2.isEmpty();
    }
}
