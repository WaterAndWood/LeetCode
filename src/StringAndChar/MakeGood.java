package StringAndChar;

/**
 * LeetCode
 * 整理大小写的字符串，两个相邻字符 s[i] 和 s[i + 1] 不会同时满足下述条件：
 * 0 <= i <= s.length - 2
 * s[i] 是小写字符，但 s[i + 1] 是相同的大写字符；反之亦然 。
 * 将字符串整理好，每次你都可以从字符串中选出满足上述条件的 两个相邻 字符并删除，直到字符串整理好为止。
 *
 * @author Richa
 * @date 2020/8/9 14:55
 */
public class MakeGood {
    public String makeGood(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        int diff = 'a' - 'A';
        while (true) {
            boolean flag = false;
            for (int i = 0; i < s.length() - 1; i++) {
                if (Math.abs(s.charAt(i) - s.charAt(i + 1)) == diff) {
                    s = delete(s, i, i + 1);
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
        return s;
    }

    private String delete(String s, int l, int r) {
        return s.substring(0, l) + s.substring(r + 1);
    }
}
