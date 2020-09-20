package DynamicProgramming;

/**
 *
 * LeetCode 5: 最长回文子串
 * 动态规划的状态转移性：如果头尾字符不相等，不是回文串；如果头尾字符相等，继续判断：如果里面子串是回文，整体是回文，否则不是回文
 * dp[i][j] 表示子串s[i j]是否未回文子串，s[i j] 闭区间
 * 状态转移方程：
 * dp[i][j] = (s[i] == s[j]) and dp[i + 1][j - 1]
 * 最小子问题：
 * 子串s[i + 1  j - 1]只有1个字符，中间是回文（子串长度是3）
 * 子串s[i + 1  j - 1]为空串，中间是回文（子串长度是2）
 *
 * @author Richa
 * @date 2020/9/19 22:39
 */
public class LongestPalindrome {
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int maxLen = 1;
        int begin = 0;
        // dp[i][j]表示s[i j]是否是回文串
        boolean[][] dp = new boolean[len][len];
        char[] chars = s.toCharArray();

        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        /**
         * j是右边界，i是左边界，在二维表中j是列，i是行
         *
         */
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < len; i++) {
                if (chars[i] != chars[j]) {
                    dp[i][j] = false;
                } else {
                    // s[i] s[j]相等，长度小于等于2，直接判断尾回文串
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }
}
