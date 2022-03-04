package TargetOffer;

/**
 *
 * Offer17：打印从1到最大的n位数
 * 大数无法使用int或者long表示，必须使用字符串。使用字符串加法效率太低（改变高位数字需要循环次数多）
 * 转化为每一位数字从0到9的全排列，分治法
 *
 * @author Richa
 * @date 2022/3/4 21:31
 */
public class Increment {
    /**
     * 递归生成全排列，先固定高位，向低位递归，当个位固定，添加数字的字符串
     * 声明变量 start 规定字符串的左边界，以保证添加的数字字符串 num[start:] 中无高位多余的 00
     */
    int[] res;
    int nine = 0, count = 0, start, n;
    char[] num, loop = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    public int[] printNumbers(int n) {
        this.n = n;
        res = new int[(int)Math.pow(10, n) - 1];
        num = new char[n];
        start = n - 1;
        dfs(0);
        return res;
    }

    void dfs(int x) {
        if (n == x) {
            String s = String.valueOf(num).substring(start);
            if (!"0".equals(s)) {
                res[count++] = Integer.parseInt(s);
            }
            if (n - start == nine) {
                start--;
            }
            return;
        }
        for (char c : loop) {
            // 当第x位出现9，表示该进位
            if (c == '9') {
                nine++;
            }
            num[x] = c;
            dfs(x + 1);
        }
        // start--
        nine--;
    }
}
