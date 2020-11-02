package DoublePointer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * LeetCode 3: 无重复字符的最长子串
 * 子串是连续的而非子序列，子序列不连续
 *
 * @author Richa
 * @date 2020/11/2 22:10
 */
public class LengthOfLongestSubstring {
    /**
     * 滑动窗口其实就是左右两个指针，保持子串的连续性
     *
     */
    public int findLongestLength(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Set<Character> set = new HashSet<>();
        int n = s.length();
        // i相当于左指针，r相当于右指针
        int r = -1;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            // 左指针向右移动，set中去掉前一个字符
            if (i != 0) {
                set.remove(s.charAt(i - 1));
            }
            // 右指针向右移动，set加入不含的字符
            while ((r + 1 < n) && !set.contains(s.charAt(r + 1))) {
                set.add(s.charAt(r + 1));
                r++;
            }
            ans = Math.max(ans, r - i + 1);
        }
        return ans;
    }

    /**
     * 使用HashMap记录位置，减少内层循环
     * key是字符，value是字符位置+1，加1表示从字符位置后一个才开始不重复
     * 不重复子串的开始位置为 start，结束位置为 end
     *
     */
    public int findLength(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        // end不断向右移动
        for (int end = 0, start = 0; end < n; end++) {
            char ch = s.charAt(end);
            /**
             * map中包含ch，需要从上一个ch处重新截取子串
             * 遇到[start, end]区间内字符相同的情况，字符作为key，获取value（位置）
             * 更新start，使得更新后[start, end]内不存在重复字符
             */
            if (map.containsKey(ch)) {
                // map.get(ch)上一个ch位置
                start = Math.max(map.get(ch), start);
            }
            // 始终更新map和ans
            ans = Math.max(ans, end - start + 1);
            map.put(s.charAt(end), end + 1);
        }
        return ans;
    }
}
