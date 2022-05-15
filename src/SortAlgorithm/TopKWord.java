package SortAlgorithm;

import java.util.*;

/**
 *
 * LeetCode 692: 前K个高频单词
 *
 * @author Richa
 * @date 2020/10/28 22:03
 */
public class TopKWord {
    /**
     * 统计出现次数，排序后取K
     * 时间复杂度O(NlogN)，N是words的长度
     */
    public List<String> topKWord(String[] words, int k) {
        Map<String, Integer> count = new HashMap<>();
        for (String word : words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        
        List<String> candidates = new ArrayList<>(count.keySet());
        // 从大到小排序
        Collections.sort(candidates, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (count.get(o1).equals(count.get(o2))) {
                    return o1.compareTo(o2);
                } else {
                    return count.get(o2) - count.get(o1);
                }
            }
        });
        return candidates.subList(0, k);
    }
    
    /**
     * 最好使用堆排序，使用空间为k的小根堆，频率最小的放在堆顶部
     * 堆中弹出最多 k 次，并反转结果，就可以得到前 k 个高频单词。
     * 时间复杂度O(Nlogk)，N是words的长度
     * 用O(N) 的时间计算每个单词的频率，然后将 N 个单词添加到堆中，添加每个单词的时间为 O(logk)
     */
    public List<String> topKWordHeap(String[] words, int k) {
        Map<String, Integer> count = new HashMap<>();
        for (String word : words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }

        // 小根堆，按照括号中顺序相减为升序排序，反之为降序排列
        // lambda中用了compareTo函数也是同理，(a, b) -> reruen a.compareTo(b)是按照字典序升序排列，(a, b) -> reruen b.compareTo(a)是按照字典序降序排列
        PriorityQueue<String> minHeap = new PriorityQueue<String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (count.get(o1).equals(count.get(o2))) {
                    // 同等次数的值降序是因为后面会逆序
                    return o2.compareTo(o1);
                } else {
                    return count.get(o1) - count.get(o2);
                }

            }
        });

        for (String word : count.keySet()) {
            minHeap.offer(word);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        List<String> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll());
        }
        // 小根堆，次数少的先出queue，所以需要逆序
        Collections.reverse(result);
        return result;
    }

    public static void main(String[] args) {
        String[] words = {"a", "a", "b", "a", "b", "c", "c", "d"};
        int k = 2;
        TopKWord topKWord = new TopKWord();
        System.out.println(topKWord.topKWordHeap(words, k));
    }
}
