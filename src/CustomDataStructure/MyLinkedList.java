package CustomDataStructure;

/**
 *
 * LeetCode 707：设计链表
 * 双链表实现性能好，但比较复杂
 *
 * @author Richa
 * @date 2022/5/18 14:14
 */
public class MyLinkedList {
    class ListNode {
        private int val;
        private ListNode prev;
        private ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    private ListNode dummyHead;
    private ListNode dummyTail;
    int size;

    public MyLinkedList() {
        this.dummyHead = new ListNode(0);
        this.dummyTail = new ListNode(0);
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }

    private ListNode getNode(int index) {
        ListNode cur = dummyHead;
        if (index + 1 < size - index) {
            for (int i = 0; i <= index; i++) {
                cur = cur.next;
            }
        } else {
            cur = dummyTail;
            for (int i = 0; i < size - index; i++) {
                cur = cur.prev;
            }
        }
        return cur;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        ListNode node = getNode(index);
        return node.val;
    }

    public void addAtHead(int val) {
        ListNode oldHead = dummyHead.next;
        ListNode node = new ListNode(val);
        oldHead.prev = node;
        node.next = oldHead;
        dummyHead.next = node;
        node.prev = dummyHead;
        size++;
    }

    // 比较单链表实现尾插更快
    public void addAtTail(int val) {
        ListNode oldTail = dummyTail.prev;
        ListNode node = new ListNode(val);
        oldTail.next = node;
        node.prev = oldTail;
        node.next = dummyTail;
        dummyTail.prev = node;
        size++;
    }

    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }
        if (index < 0) {
            index = 0;
        }
        // 新节点插入在pred与succ之间
        ListNode pred, succ;
        if (index < size - index) {
            pred = getNode(index - 1);
            succ = pred.next;
        } else {
            succ = getNode(index);
            pred = succ.prev;
        }
        size++;
        ListNode node = new ListNode(val);
        pred.next = node;
        node.prev = pred;
        node.next = succ;
        succ.prev = node;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        ListNode pred, succ;
        // pred与succ之间的节点是被删除的
        if (index < size - index) {
            pred = getNode(index - 1);
            succ = pred.next.next;
        } else {
            succ = getNode(index + 1);
            pred = succ.prev.prev;
        }
        size--;
        pred.next = succ;
        succ.prev = pred;
    }
}
