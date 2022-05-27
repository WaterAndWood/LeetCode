package DynamicProgramming;

/**
 *
 * LeetCode 718: 最长重复子数组
 *
 * @author Richa
 * @date 2020/7/30 23:07
 */
public class MaxLengthSameArray {
    /**
     * 滑动窗口
     */
    public int findLength1(int[] a, int[] b) {
        // a, b长度可能不一样。较长的是b
        return a.length < b.length ? findMax(a, b) : findMax(b, a);
    }

    public int findMax(int[] a, int[] b) {
        int max = 0;
        int aLen = a.length, bLen = b.length;
        /**
         * a, b的相对位置依次是：
         *       aaaaa
         * bbbbbbb
         *
         *   aaaaa
         * bbbbbbb
         *
         * aaaaa
         *   bbbbbbb
         *
         */
        for (int i = 1; i <= aLen; i++) {
            max = Math.max(max, maxLen(a, 0, b, bLen - i, i));
        }
        for (int j = bLen - aLen; j >= 0; j--) {
            max = Math.max(max, maxLen(a, 0, b, j, aLen));
        }
        for (int i = 1; i < aLen; i++) {
            max = Math.max(max, maxLen(a, i, b, 0, aLen - i));
        }
        return max;
    }

    public int maxLen(int[] a, int i, int[] b, int j, int len) {
        int count = 0, max = 0;
        // 重叠部分最长相等字符串的长度
        for (int k = 0; k < len; k++) {
            if (a[i + k] == b[j + k]) {
                count++;
            } else if (count > 0) {
                max = Math.max(count, max);
                count = 0;
            }
        }
        return count > 0 ? Math.max(max, count) : max;
    }

    /**
     * 动态规划：
     * dp[i][j] ：长度为i，末尾项为A[i-1]的子数组，与长度为j，末尾项为B[j-1]的子数组，二者的最大公共后缀子数组长度。
     * 如果 A[i-1] != B[j-1]， 有 dp[i][j] = 0
     * 如果 A[i-1] == B[j-1] ， 有 dp[i][j] = dp[i-1][j-1] + 1
     */
    public int findLength2(int[] a, int[] b) {
        int aLen = a.length, bLen = b.length;
        int[][] dp = new int[aLen + 1][bLen + 1];
        int ans = 0;
        for (int i = 1; i <= aLen; i++) {
            for (int j = 1; j <= bLen; j++) {
                if (a[i - 1] == b[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }
}
