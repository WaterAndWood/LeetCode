package CustomDataStructure;

/**
 *
 * LeetCode 706:设计哈希映射
 * 地址加链表，模仿hashmap
 *
 * @author Richa
 * @date 2022/5/18 15:29
 */
public class MyHashMap {
    class Node {
        private int key;
        private int value;
        private Node next;
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    Node[] nodes = new Node[10009];

    public MyHashMap() {
    }

    public void put(int key, int value) {
        int idx = hash(key);
        Node loc = nodes[idx], temp = loc;
        // 索引处有链表
        if (loc != null) {
            Node prev = null;
            while (temp != null) {
                if (temp.key == key) {
                    temp.value = value;
                    return;
                }
                prev = temp;
                temp = temp.next;
            }
            temp = prev;
        }
        Node newNode = new Node(key, value);
        if (temp != null) {
            temp.next = newNode;
        } else {
            nodes[idx] = newNode;
        }
    }

    public void remove(int key) {
        int idx = hash(key);
        Node loc = nodes[idx];
        if (loc != null) {
            Node prev = null;
            while (loc != null) {
                if (loc.key == key) {
                    if (prev != null) {
                        prev.next = loc.next;
                    } else {
                        nodes[idx] = loc.next;
                    }
                    return;
                }
                prev = loc;
                loc = loc.next;
            }
        }
    }

    public int get(int key) {
        int idx = hash(key);
        Node loc = nodes[idx];
        if (loc != null) {
            while (loc != null) {
                if (loc.key == key) {
                    return loc.value;
                }
                loc = loc.next;
            }
        }
        return -1;
    }

    private int hash(int key) {
        // 简单的话可以直接对数组长度取模
        int hash = Integer.hashCode(key);
        hash ^= (hash >>> 16);
        return hash % nodes.length;
    }


}
