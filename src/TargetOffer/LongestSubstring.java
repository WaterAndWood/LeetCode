package TargetOffer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * Offer48: 最长不含重复字符的子字符串
 *
 * @author Richa
 * @date 2022/4/5 15:49
 */
public class LongestSubstring {
    /**
     * 动态规划：
     * dp[j]表示以字符s[j]为结尾的最长不重复子字符串的长度
     * 固定右边界 j ，设字符 s[j] 左边距离最近的相同字符为 s[i]，即 s[i] = s[j]
     * i<0或者dp[j-1] < j-i, 前者是左边无相同字符，后者是s[i]不在子字符串范围内，dp[j] = dp[j-1]+1
     * dp[j-1]>= j-i，字符s[i]在子字符串范围内，dp[j]的左边界由s[i]决定，dp[j] = j-i
     */
    public int longestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        // 变量temp存储dp[j]，res存储最大结果
        int res = 0, temp = 0;
        for (int j = 0; j < s.length(); j++) {
            int i = map.getOrDefault(s.charAt(j), -1);
            map.put(s.charAt(j), j);
            if (temp < j-i) {
                temp += 1;
            } else {
                temp = j-i;
            }
            res = Math.max(res, temp);
        }
        return res;
    }

    /**
     * 滑动窗口：
     * 左右两个指针组成窗口，中间保持不重复的元素
     */
    public int longestSubstring2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        // 保持窗口内不重复的值
        Set<Character> set = new HashSet<>();
        int res = 0;
        for (int l = 0, r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            while (set.contains(c)) {
                set.remove(s.charAt(l++));
            }
            set.add(c);
            res = Math.max(res, r - l + 1);
        }
        return res;
    }
}
