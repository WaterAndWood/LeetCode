package TargetOffer;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Offer 50: 第一次只出现一次的字符
 * 
 * @author Richa
 * @date 2022/4/7 23:48
 */
public class FirstUniqueChar {
    public char firstUniqueChar(String s) {
        if (s == null || s.length() == 0) {
            return ' ';
        }
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : s.toCharArray()) {
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
            } else {
                map.put(ch, 1);
            }
        }
        for (char ch : s.toCharArray()) {
            if (map.get(ch) == 1) {
                return ch;
            }
        }
        return ' ';
    }
}
