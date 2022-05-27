package DynamicProgramming;

/**
 *
 * LeetCode 1143: 最长公共子串
 * 二维动态规划：
 * dp[i][j]表示text1[0:i-1]和text2[0:j-1]的最长公共子序列长度
 * dp[i][j] = dp[i-1][j-1] + 1(s1[i-1] == s2[j-1])
 * 否则 dp[i][j] = max(dp[i-1][j], dp[i][j-1])
 * @author Richa
 * @date 2020/8/2 10:35
 *
 */
public class LongestCommonSubstring {
    public int longestCommonSubstring(String text1, String text2) {
        if (text1.length() == 0 || text2.length() == 0) {
            return 0;
        }
        int len1 = text1.length();
        int len2 = text2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        /**
         * base case: dp[0][...] = 0, dp[...][0] = 0
         * 表示其中一个字符串是”“
         * text1或者text2为空，空字符串和任何字符串的最长公共子序列长度都是0
         */
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[len1][len2];
    }

    public static void main(String[] args) {
        LongestCommonSubstring ls = new LongestCommonSubstring();
        String s1 = "aceg";
        String s2 = "abcde";
        System.out.println(ls.longestCommonSubstring(s1, s2));
    }
}
