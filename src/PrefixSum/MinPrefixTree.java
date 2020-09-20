package PrefixSum;

import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * 牛客：最小的唯一前缀
 * 给定一组个字符串，为每个字符串找出能够唯一识别该字符串的最小前缀。
 * 实际找的还是可区分的最大前缀
 *
 * @author Richa
 * @date 2020/9/20 15:19
 */
public class MinPrefixTree {
    public static class TrieNode {
        private int path;
        private int end;
        private HashMap<Character, TrieNode> map;
        public TrieNode() {
            path = 0;
            end = 0;
            map = new HashMap<>();
        }
    }
    
    public static class PrefixTree {
        private TrieNode root;
        public PrefixTree() {
            root = new TrieNode();
        }
        /**
         * 前缀树插入操作，path表示到达此节点的字符串数量
         *
         */
        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] chars = word.toCharArray();
            TrieNode node = root;
            for (int i = 0; i < chars.length; i++) {
                if (!node.map.containsKey(chars[i])) {
                    node.map.put(chars[i], new TrieNode());
                }
                node = node.map.get(chars[i]);
                // 当前字符串到达此节点
                node.path++;
            }
            node.end++;
        }

        public String findMaxPrefix(String word) {
            if (word.isEmpty()) {
                return "";
            }
            TrieNode node = root;
            String res = "";
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (node.map.containsKey(chars[i])) {
                    node = node.map.get(chars[i]);
                    res += chars[i];
                    // 此节点只有该字符串到过，可以作为区分
                    if (node.path == 1) {
                        return res;
                    }
                } else {
                    return "";
                }
            }
            return res;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String[] arr = new String[n];
        PrefixTree tree = new PrefixTree();
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextLine();
            tree.insert(arr[i]);
        }
        for (String s : arr) {
            System.out.println(tree.findMaxPrefix(s));
        }
    }
}
