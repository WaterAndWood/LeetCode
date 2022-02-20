package TargetOffer;

/**
 *
 * Offer5: 替换空格
 * 从向前移动的双指针，时间复杂度O(n)，用于合并数组或者字符串
 *
 * @author Richa
 * @date 2022/2/18 23:06
 */
public class ReplaceSpaceInString {
    public static String replaceSpace(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        int spaceCount = 0;
        for (char c :  s.toCharArray()) {
            if (c == ' ') {
                spaceCount++;
            }
        }
        char[] nStr = new char[s.length() + spaceCount * 2];
        int p1 = s.length() - 1;
        int p2 = nStr.length - 1;
        // 从各自尾端向前复制，p1指向原字符串，p2指向新字符串
        while (p1 >= 0) {
            if (s.charAt(p1) != ' ') {
                nStr[p2--] = s.charAt(p1);
            } else {
                nStr[p2--] = '0';
                nStr[p2--] = '2';
                nStr[p2--] = '%';
            }
            p1--;
        }
        return new String(nStr);
    }
}
