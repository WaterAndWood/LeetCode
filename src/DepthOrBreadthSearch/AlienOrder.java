package DepthOrBreadthSearch;

import java.util.*;

/**
 *
 * LeetCode 269: 火星词典
 * BFS 遍历 拓扑排序
 *
 * @author Richa
 * @date 2020/8/22 22:07
 */
public class AlienOrder {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        if (words.length == 1) {
            return words[0];
        }
        // 输入的字符串中不同的字符组成的list，且按照字符串数组中的顺序
        List<Character> book = new ArrayList<>();
        for (String word : words) {
            for (Character c : word.toCharArray()) {
                if (!book.contains(c)) {
                    book.add(c);
                }
            }
        }
        /**
         * 生成邻接表，表示有向无环图
         * 比较相邻的字符串，确定字符的先后顺序，即 a -> b
         * 存在无法比较的字符，返回""
         */
        List<char[]> lists = new ArrayList<>();
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                char[] temp = compareWord(words[i], words[j]);
                if (temp.length == 2 && !hasKey(lists, temp)) {
                    lists.add(temp);
                }
                if (temp.length == 0) {
                    if (words[i].length() > words[j].length()) {
                        return "";
                    }
                }
            }
        }
        /**
         * 生成入度表，找出邻接表中所有字符的入度
         * char[] 中char[0] -> char[1]，寻找char[1] 入度
         */
        Map<Character, Integer> indegree = new HashMap<>();
        for (int i = 0; i < lists.size(); i++) {
            char[] chars = lists.get(i);
            int step = 1;
            if (indegree.containsKey(chars[1])) {
                step = 1 + indegree.get(chars[1]);
            }
            indegree.put(chars[1], step);
            if (!indegree.containsKey(chars[0])) {
                indegree.put(chars[0], 0);
            }
        }

        Deque<Character> queue = new ArrayDeque<>();
        StringBuilder ret = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : indegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
            }
        }
        while (!queue.isEmpty()) {
            char ch = queue.poll();
            // ret是拓扑排序列表
            ret.append(ch);
            for (int i = 0; i < lists.size(); i++) {
                char[] listChar = lists.get(i);
                // 邻接表中入度为0的字符指向的字符的入度-1，如果它的入度为0，加入queue
                if (listChar[0] == ch) {
                    indegree.put(listChar[1], indegree.get(listChar[1]) - 1);
                    if (indegree.get(listChar[1]) == 0) {
                        queue.add(listChar[1]);
                    }
                }
            }
        }
        // 入度存在不为0，不成立
        for (Map.Entry<Character, Integer> entry : indegree.entrySet()) {
            if (entry.getValue() > 0) {
                return "";
            }
        }
        // 输入字符串中未加入的
        for (int i = 0; i < book.size(); i++) {
            if (!containerChar(ret, book.get(i))) {
                ret.append(book.get(i));
            }
        }
        return ret.toString();
    }

    public boolean containerChar(StringBuilder sb, char c) {
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == c) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断字符数组是否在列表中
     *
     */
    public boolean hasKey(List<char[]> list, char[] chars) {
        if (list.isEmpty()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            char[] charInList = list.get(i);
            if (charInList[0] == chars[0] && charInList[1] == chars[1]) {
                return true;
            }
        }
        return false;
    }

    /**
     * 比较相邻字符串，找出两个字符串中第一个不相同的字符，确定先后关系
     * 未找到第一个不用字符则无法判断先后关系
     */
    public char[] compareWord(String s1, String s2) {
        char[] res = new char[2];
        boolean flag = false;
        for (int i = 0; i < s1.length() && i < s2.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                res[0] = s1.charAt(i);
                res[1] = s2.charAt(i);
                flag = true;
                break;
            }
        }
        if (flag) {
            return res;
        }
        return new char[0];
    }
}
