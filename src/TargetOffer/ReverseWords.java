package TargetOffer;

/**
 *
 * Offer58：翻转字符串
 * 双指针
 *
 * @author Richa
 * @date 2022/4/14 22:45
 */
public class ReverseWords {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        s = s.trim();
        StringBuilder sb = new StringBuilder();
        int l = s.length() - 1, r = l;
        while (l >= 0) {
            while (l >= 0 && s.charAt(l) != ' ') {
                l--;
            }
            sb.append(s.substring(l + 1, r + 1)).append(" ");
            while (l >= 0 && s.charAt(l) == ' ') {
                l--;
            }
            r = l;
        }
        return sb.toString().trim();
    }

    /**
     * 左旋字符串
     */
    public String reverseLeftWords1(String s, int n) {
        return s.substring(n, s.length()) + s.substring(0, n);
    }
    public String reverseLeftWords2(String s, int n) {
        if (s == null || s.length() == 0) {
            return s;
        }
        StringBuilder res = new StringBuilder();
        for (int i = n; i < s.length(); i++) {
            res.append(s.charAt(i));
        }
        for (int i = 0; i < n; i++) {
            res.append(s.charAt(i));
        }
        return res.toString();
    }
}
