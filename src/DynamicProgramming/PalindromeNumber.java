package DynamicProgramming;

/**
 *
 * LeetCode 647: 回文子串
 * 动态规划
 * base case: 单个字符
 * 状态转移方程：字符相等时:字符相邻（由2个字符组成）j - i < 2 或者向内剩余子串是回文串dp[i + 1][j - 1]
 * @author Richa
 * @date 2020/9/21 20:32
 */
public class PalindromeNumber {
    public int countSubStrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        boolean[][] dp = new boolean[len][len];

        int ans = 0;
        for (int j = 0; j < len; j++) {
            for (int i = 0; i <= j; i++) {
                if (s.charAt(j) == s.charAt(i) && (j - i < 2 || dp[i + 1][j - 1])) {
                    ans++;
                    dp[i][j] = true;
                }
            }
        }
        return ans;
    }

    // 转移方程拆分表示
    public int countSubStrings2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        boolean[][] dp = new boolean[len][len];

        int count = 0;
        // 只算上三角部分
        for (int j = 0; j < len; j++) {
            for (int i = 0; i <= j; i++) {
                if(i == j) {
                    // 单个字符
                    dp[i][j] = true;
                    count++;
                } else if (j - i == 1 && s.charAt(i) == s.charAt(j)) {
                    // 相邻字符而且字符相同
                    dp[i][j] = true;
                    count++;
                } else if (j - i > 1 && s.charAt(i) == s.charAt(j) && dp[i+1][j-1]) {
                    // 需要靠dp[i+1][j-1]决定
                    dp[i][j] = true;
                    count++;
                }
            }
        }
        return count;
    }
}
