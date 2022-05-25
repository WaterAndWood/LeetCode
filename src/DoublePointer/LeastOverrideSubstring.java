package DoublePointer;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * LeetCode 76: 最小覆盖子串
 * 双指针 + 哈希表
 * 哈希表记录出现的字符以及个数
 * 
 * @author Richa
 * @date 2020/11/5 18:17
 */
public class LeastOverrideSubstring {
    public String minWindow(String s, String t) {
        if (s.length() == 0 || t.length() == 0 || s.length() < t.length()) {
            return "";
        }
        // 记录t的map，记录每个字符以及次数
        Map<Character, Integer> tMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            int count = tMap.getOrDefault(t.charAt(i), 0);
            tMap.put(t.charAt(i), count + 1);
        }
        // t覆盖时满足的条件数，即t中字符种类
        int required = tMap.size();
        int l = 0, r = 0;
        // 窗口中的满足覆盖条件数
        int formed = 0;
        // 记录窗口的map
        Map<Character, Integer> windowMap = new HashMap<>();
        // 窗口描述，0：子串的长度；1：左指针索引；2：右指针索引
        int[] ans = {-1, 0, 0};
        while (r < s.length()) {
            // 右指针右移添加字符
            char c = s.charAt(r);
            int count = windowMap.getOrDefault(c, 0);
            windowMap.put(c, count + 1);
            // 窗口map满足t覆盖的条件之一，formed+1
            if (tMap.containsKey(c) && tMap.get(c).equals(windowMap.get(c))) {
                formed++;
            }
            // 窗口此时满足覆盖t，缩减左边界减少子串长度
            while (l <= r && formed == required) {
                c = s.charAt(l);
                if (ans[0] == -1 || r - l + 1 < ans[0]) {
                    ans[0] = r - l + 1;
                    ans[1] = l;
                    ans[2] = r;
                }
                windowMap.put(c, windowMap.get(c) - 1);
                if (tMap.containsKey(c) && tMap.get(c) < windowMap.get(c)) {
                    formed--;
                }
                l++;
            }

            r++;
        }
        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }
}
