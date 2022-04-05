package TargetOffer;

/**
 *
 * Offer46：把数组翻译成字符串
 *
 * @author Richa
 * @date 2022/4/4 21:51
 */
public class TranslateNum {
    /**
     * 动态规划：
     * 对于num[i]两种选择：只翻译自己与和前面的数组合翻译
     * dp(i)表示前i个数字的翻译方法数
     * 如果和前面的数在10-25之间，两种可能之和dp(i) = dp(i-1) + dp(i-2)
     * 如果只翻译自己，则dp(i) = dp(i-1)
     * 边界条件：dp(0) = dp(1) = 1
     * 时间复杂度：O(n)
     */
    public int translateNum(int num) {
        String s = String.valueOf(num);
        int[] dp = new int[s.length() + 1];
        // 边界条件，没有数字翻译方法数是1
        dp[0] = 1;
        // 1个数字，翻译方法数是1
        dp[1] = 1;
        for (int i = 2; i <= s.length(); i++) {
            String temp = s.substring(i - 2, i);
            if (Integer.parseInt(temp) <= 25 && Integer.parseInt(temp) >= 10) {
                dp[i] = dp[i-1] + dp[i-2];
            } else {
                dp[i] = dp[i-1];
            }
        }
        return dp[dp.length - 1];
    }

    public int translateNumDfs(int num) {
        String s = String.valueOf(num);
        return dfs(s, 0);
    }

    /**
     * 时间复杂度：O(2^n)
     */
    private int dfs(String s, int index) {
        // 递归到最后的字符为出口
        if (s.length() == index) {
            return 1;
        }
        if (index <= s.length() - 2) {
            String temp = s.substring(index, index + 2);
            if (Integer.parseInt(temp) <= 25 && Integer.parseInt(temp) >= 10) {
                return dfs(s,index + 1) + dfs(s, index + 2);
            } else {
                return dfs(s, index + 1);
            }
        } else {
            return 1;
        }

    }
}
