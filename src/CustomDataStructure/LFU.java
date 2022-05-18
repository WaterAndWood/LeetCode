package CustomDataStructure;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * LeetCode 460: LFU缓存
 *
 * LRU(Least Recently Used) 最近最少使用算法，它是根据时间维度来选择将要淘汰的元素，即删除掉最长时间没被访问的元素。所以需要双链表保存
 * 最长时间没有用到的ListNode（双链表末尾）
 *
 * LFU(Least Frequently Used) 最近最不常用算法，它是根据频率维度来选择将要淘汰的元素，即删除访问频率最低的元素。如果两个元素的访问频率相同，则淘汰最久没被访问的元素。
 * 所以两个哈希表保存key-value，另一个哈希表保存频率，相同频率的ListNode在一个value（链表中）
 *
 *
 * @author Richa
 * @date 2022/5/17 20:27
 */
public class LFU {
    // 缓存的key-value
    private Map<Integer, Node> cache;
    // 存储每个频次对应的双向链表
    private Map<Integer, DoublyLinkedList> freqMap;
    int size;
    int capacity;
    // 存储当前最小频次
    int min;

    public LFU(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>(capacity);
        this.freqMap = new HashMap<>();
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        freqInc(node);
        return node.value;
    }

    /**
     * 更新node的freq，把node移动到加1频率的双链表
     * @param node
     */
    public void freqInc(Node node) {
        int freq = node.freq;
        DoublyLinkedList linkedList = freqMap.get(freq);
        linkedList.removeNode(node);
        /**
         * 删除节点频率是最小值而且已经为空，更新最小值
         */
        if (freq == min && linkedList.head.post == linkedList.tail) {
            min = freq + 1;
        }
        node.freq++;
        linkedList = freqMap.get(freq + 1);
        if (linkedList == null) {
            linkedList = new DoublyLinkedList();
            freqMap.put(freq + 1, linkedList);
        }
        linkedList.addNode(node);
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        Node node = cache.get(key);
        if (node != null) {
            node.value = value;
            freqInc(node);
        } else {
            if (size == capacity) {
                DoublyLinkedList minFreqLinkedList = freqMap.get(min);
                // 要删除节点是minFreqLinkedList.tail.pre.
                cache.remove(minFreqLinkedList.tail.pre.key);
                // 不需要维护min，下面add后min=1
                minFreqLinkedList.removeNode(minFreqLinkedList.tail.pre);
                size--;
            }
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            DoublyLinkedList linkedList = freqMap.get(1);
            if (linkedList == null) {
                linkedList = new DoublyLinkedList();
                freqMap.put(1, linkedList);
            }
            linkedList.addNode(newNode);
            size++;
            min = 1;
        }
    }

    class Node {
        private int key;
        private int value;
        private int freq = 1;
        private Node pre;
        private Node post;

        public Node() {}
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    class DoublyLinkedList {
        Node head;
        Node tail;
        public DoublyLinkedList() {
            this.head = new Node();
            this.tail = new Node();
            head.post = tail;
            tail.pre = head;
        }

        public void removeNode(Node node) {
            node.post.pre = node.pre;
            node.pre.post = node.post;
        }

        /**
         * Node加到head后面
         */
        public void addNode(Node node) {
            node.post = head.post;
            head.post.pre = node;
            head.post = node;
            node.pre = head;
        }
    }

}
