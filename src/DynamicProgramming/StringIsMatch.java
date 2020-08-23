package DynamicProgramming;

/**
 *
 * 牛客：字符串模式匹配
 *
 * 给出两个字符串，分别是模式串P和目标串T，判断模式串和目标串是否匹配，匹配输出 1，不匹配输出 0。
 * 模式串中‘？’可以匹配目标串中的任何字符，模式串中的 ’*’可以匹配目标串中的任何长度的串，模式串的其它字符必须和目标串的字符匹配。
 * 输入第一行包含一个字符串p， (1 ≤ |p| ≤ 20).
 * 输入第二行包含一个字符串t， (1 ≤ |t| ≤ 20).
 * 输出仅包含0和1的整数，0表示p和t不匹配，1表示p和t匹配。
 *
 *
 * @author Richa
 * @date 2020/8/20 14:55
 */
public class StringIsMatch {
    public boolean isMatch(String s, String p) {
        int pLen = p.length(), sLen = s.length();
        boolean[][] dp = new boolean[pLen + 1][sLen + 1];
        dp[0][0] = true;
        for (int i = 1; i <= pLen; i++) {
            if (p.charAt(i - 1) != '*') {
                break;
            }
            dp[i][0] = true;
        }
        /**
         * 动态规划：状态转移方程
         *
         */
        for (int i = 1; i <= pLen; i++) {
            for (int j = 1; j <= sLen; j++) {
                if (p.charAt(i - 1) == s.charAt(j - 1) || p.charAt(i - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(i - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }
        return dp[pLen][sLen];
    }

    /**
     * 回溯法: 超时
     * i是pattern索引，j是target索引
     *
     */
    public boolean match(String pattern, String target, int i, int j) {
        if (i >= pattern.length() && j >= target.length()) {
            return true;
        }
        // target字符串剩余
        if (j < target.length() && i >= pattern.length()) {
            return false;
        }
        // *匹配后面的target或者*不匹配任何字符
        if (pattern.charAt(i) == '*') {
            return (j < target.length() && match(pattern, target, i, j + 1)) || match(pattern, target, i + 1, j);
        }
        if ((j < target.length() && target.charAt(j) == pattern.charAt(i)) || (pattern.charAt(i) == '?' && j < target.length())) {
            return match(pattern, target, i + 1, j + 1);
        }
        return false;
    }

    public static void main(String[] args) {
        StringIsMatch sm = new StringIsMatch();
        boolean flag = sm.isMatch("adceb", "*a*b");
        System.out.println(flag);
    }
}
