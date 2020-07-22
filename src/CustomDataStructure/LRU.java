package CustomDataStructure;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * LeetCode 146
 * LRU 缓存机制实现
 * 使用LinkedList性能较低
 *
 * @author Richa
 * @date 2020/7/22 0:35
 */
/*public class LRU {
    // 存储的哈希表，用于存取key-value
    private Map<Integer, ListNode> map;
    // 表示最少使用的双向列表，只有缓存达到上限时删除
    private LinkedList<ListNode> list;

    private class ListNode {
        private Integer key;
        private Integer value;

        public ListNode() {}

        public ListNode(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;

    public LRU(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>(capacity);
        this.list = new LinkedList<>();
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            ListNode node = map.get(key);
            int value = node.value;
            list.remove(node);
            // list开头是最近使用的节点
            list.offerFirst(node);
            return value;
        } else {
            return -1;
        }
    }

    public void put (int key, int value) {
        if (map.containsKey(key)) {
            ListNode node = map.get(key);
            node.value = value;
            map.put(key, node);
            list.remove(node);
            list.addFirst(node);
            return;
        }
        // 哈希表capacity达到上限
        if (map.size() >= capacity) {
            ListNode removeNode = list.removeLast();
            map.remove(removeNode.key);
        }
        ListNode newNode = new ListNode(key, value);
        map.put(key, newNode);
        list.addFirst(newNode);
    }

}*/
public class LRU {
    // 直接存取的哈希表
    private Map<Integer, ListNode> map;
    // 自定义双向列表
    private class ListNode {
        private Integer key;
        private Integer value;
        private ListNode pre;
        private ListNode next;

        public ListNode() {}
        public ListNode(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private ListNode dummyHead;
    private ListNode dummyTail;

    public LRU(Integer capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.dummyHead = new ListNode(-1, -1);
        this.dummyTail = new ListNode(-1, -1);
        dummyHead.next = dummyTail;
        dummyTail.pre = dummyHead;
    }

    // 双向链表中从原位置删除，再移到链表头作为最近访问
    private void moveToHead(ListNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
        addNodeToListHead(node);
    }
    // 节点添加到列表头
    private void addNodeToListHead(ListNode node) {
        ListNode oldHead = dummyHead.next;
        dummyHead.next = node;
        node.pre = dummyHead;
        node.next = oldHead;
        oldHead.pre = node;
    }
    // 容量达到上限时删除链表原尾节点
    private ListNode removeTailNode() {
        ListNode oldTail = dummyTail.pre;
        ListNode newTail = oldTail.pre;
        newTail.next = dummyTail;
        dummyTail.pre = newTail;
        oldTail.next = null;
        oldTail.pre = null;
        return oldTail;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            ListNode node = map.get(key);
            moveToHead(node);
            return node.value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            ListNode node = map.get(key);
            node.value = value;
            map.put(key, node);
            moveToHead(node);
        } else {
            if (map.size() == capacity) {
                ListNode removeTail = removeTailNode();
                map.remove(removeTail.key);
            }
            ListNode newNode = new ListNode(key, value);
            addNodeToListHead(newNode);
            map.put(key, newNode);
        }

    }
}
