package TargetOffer;


import java.util.LinkedList;
import java.util.List;

/**
 *
 * Offer9： 两个队列实现栈
 * 一个List始终保持空，将有元素的队列的最后的元素之前的元素移入
 * remove操作会改变size
 *
 * @author Richa
 * @date 2022/2/20 20:33
 */
public class MyStack {
    private List<Integer> list1;
    private List<Integer> list2;

    public MyStack() {
        list1 = new LinkedList<>();
        list2 = new LinkedList<>();
    }

    public void push(int x) {
        if (list1.isEmpty()) {
            list2.add(x);
        } else {
            list1.add(x);
        }
    }

    public int pop() {
        if (list1.isEmpty()) {
            int size = list2.size();
            for (int i = 0; i < size -1; i++) {
                list1.add(list2.remove(0));
            }
            return list2.remove(0);
        } else {
            int size = list1.size();
            for (int i = 0; i < size - 1; i++) {
                list2.add(list1.remove(0));
            }
            return list1.remove(0);
        }
    }
}
