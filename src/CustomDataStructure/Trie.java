package CustomDataStructure;

/**
 *
 * LeetCode 208: 实现前缀树
 *
 * @author Richa
 * @date 2020/9/16 19:24
 */
public class Trie {
    private TrieNode root;
    
    public Trie() {
        root = new TrieNode();
    }
    
    /**
     * 插入字符串，不存在的字符构造TrieNode，最后的node设为end
     *
     */
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char curChar = word.charAt(i);
            if (!node.containsKey(curChar)) {
                node.put(curChar, new TrieNode());
            }
            node = node.get(curChar);
        }
        node.setEnd();
    }
    
    /**
     * 找到的字符串必须结尾是end
     *
     */
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }
    
    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }
    
    /**
     * 存在连接，移动到下一个节点找下一个字符
     * 字符串长度大于前缀树，返回null，找不到节点；字符串长度小于前缀树，返回节点，判断是否是字符串结尾。
     * 如果不是end，查找的字符串只是前缀树中某个字符串的前缀
     */
    private TrieNode searchPrefix(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char curChar = word.charAt(i);
            if (node.containsKey(curChar)) {
                node = node.get(curChar);
            } else {
                return null;
            }
        }
        return node;
    }
}

/**
 * 前缀树以TrieNode为节点组成链表，每个节点包含26个字母的数组
 *
 */
class TrieNode {
    private TrieNode[] next;
    private final int r = 26;
    private boolean isEnd = false;
    
    public TrieNode() {
        next = new TrieNode[r];
    }
    
    public boolean containsKey(char ch) {
        return next[ch - 'a'] != null;
    }
    
    public TrieNode get(char ch) {
        return next[ch - 'a'];
    }
    
    public void put(char ch, TrieNode node) {
        next[ch - 'a'] = node;
    }
    
    public void setEnd() {
        isEnd = true;
    }
    
    public boolean isEnd(){
        return isEnd;
    }
}
