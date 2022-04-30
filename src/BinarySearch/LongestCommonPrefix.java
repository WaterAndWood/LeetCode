package BinarySearch;

/**
 *
 * Leetcode14:最长公共前缀
 * 
 * @author Richa
 * @date 2022/4/30 15:09
 */
public class LongestCommonPrefix {
    /**
     * 先找出最短的字符串，然后strs中每个字符串比较
     * 时间复杂度O(mn)，m 是字符串数组中的字符串的平均长度，n 是字符串的数量
     */
    public String longestCommonPrefix1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        String min = strs[0];

        for (int i = 1; i < strs.length; i++) {
            if (strs[i].isEmpty()) {
                return "";
            }
            if (min.length() > strs[i].length()) {
                min = strs[i];
            }
        }

        for (int j = 0; j < strs.length; j++) {
            if (min.equals(strs[j])) {
                continue;
            }
            for (int i = 0; i < min.length(); i++) {
                if (min.charAt(i) != strs[j].charAt(i)) {
                    min = min.substring(0, i);
                }
            }
        }
        return min;
    }

    /**
     * 最短字符串长度minLength，在[0, minLength]长度范围内二分查找最长公共前缀长度，每次查找mid
     * 比较每个字符串前mid个字符是否相同
     */
    public String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int minLength = Integer.MAX_VALUE;
        for (String s : strs) {
            minLength = Math.min(minLength, s.length());
        }

        int low = 0, high = minLength;
        while (low < high) {
            int mid = low + (high - low + 1) / 2;
            if (isCommonPrefix(strs, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return strs[0].substring(0, low);
    }

    private boolean isCommonPrefix(String[] strs, int length) {
        String str0 = strs[0].substring(0, length);
        int count = strs.length;
        for (int i = 1; i < count; i++) {
            for (int j = 0; j < length; j++) {
                if (str0.charAt(j) != strs[i].charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }
}
