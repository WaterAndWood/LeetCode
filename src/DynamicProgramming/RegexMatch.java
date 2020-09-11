package DynamicProgramming;

public class RegexMatch {
    /**
     * 剑指offer：正则表达式匹配
     * 从前到后递归
     * 第一个字符不能是*
     *
     */
    public boolean isMatch1(String s, String p) {
        // 如果正则串p为空字符串，目标s也是空字符串，则匹配成功；如果正则串p为空，但s不为空，则匹配失败
        if (p.isEmpty()) {
            return s.isEmpty();
        }
        // 判断s和p的首字符是否匹配（字符相等或者p的字符是.），注意s不能为空
        boolean headCharMatched = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
        // 如果p的第一个元素的下一个元素是*
        if (p.length() >= 2 && p.charAt(1) == '*') {
            // *匹配0个字符 || 继续匹配s中相同的字符（headCharMatch判断出s递归时下一个字符是否和*的前一个字符匹配）
            return isMatch1(s, p.substring(2)) || (headCharMatched && isMatch1(s.substring(1), p));
        } else if (headCharMatched) {
            // 非*可直接匹配s和p的下一个字符
            return isMatch1(s.substring(1), p.substring(1));
        } else {
            return false;
        }
    }

    // https://leetcode-cn.com/problems/zheng-ze-biao-da-shi-pi-pei-lcof/solution/zhu-xing-xiang-xi-jiang-jie-you-qian-ru-shen-by-je/
    /**
     * dp[i][j] 表示s 的前i个和p 的前j个能否匹配
     *
     */
    public boolean isMatch2(String s, String p) {
        int n = s.length();
        int m = p.length();

        boolean[][] dp = new boolean[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (j == 0) {
                    // 非空正则匹配，只有空字符串s匹配
                    dp[i][j] = i == 0;
                } else {
                    // 正则表达不是*
                    if (p.charAt(j - 1) != '*') {
                        if (i > 0 && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.')) {
                            dp[i][j] = dp[i - 1][j - 1];
                        } else {
                            // *的两种情况：*不匹配s中的字符或者匹配字符
                            if (j >= 2) {
                                dp[i][j] |= dp[i][j - 2];
                            }
                            if (i >= 1 && j >= 2 && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.')) {
                                dp[i][j] |= dp[i - 1][j];
                            }
                        }
                    }
                }
            }
        }
        return dp[n][m];
    }
}
