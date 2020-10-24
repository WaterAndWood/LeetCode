package StringAndChar;

/**
 *
 * 字符串大数加法
 *
 * @author Richa
 * @date 2020/10/19 23:48
 */
public class StringAdd {
    public static String add(String s1, String s2) {
        //先将输入的两个串逆序生成字符数组，低位在前
        char[] a = new StringBuilder(s1).reverse().toString().toCharArray();
        char[] b = new StringBuilder(s2).reverse().toString().toCharArray();

        int lenA = a.length;
        int lenB = b.length;
        int maxLen = Math.max(lenA, lenB);
        int[] result = new int[maxLen + 1];

        // 超过字符串的长度补零
        for (int i = 0; i < maxLen + 1; i++) {
            int aint = i < lenA ? (a[i] - '0') : 0;
            int bint = i < lenB ? (b[i] - '0') : 0;
            result[i] = aint + bint;
        }
        //遍历结果数组，大于10进位，当前位取10的模
        for (int i = 0; i < result.length; i++) {
            if (result[i] >= 10) {
                result[i + 1] += result[i] / 10;
                result[i] %= 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        // 最高位可能是0
        if (result[maxLen] != 0) {
            sb.append(result[maxLen]);
        }
        for (int i = maxLen - 1; i >= 0; i--) {
            sb.append(result[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s1 = "1234567890";
        String s2 = "32110";
        String ret = add(s1,s2);
        System.out.println("ret is: " + ret);
    }
}
