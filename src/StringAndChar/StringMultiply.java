package StringAndChar;

/**
 *
 * 字符串大数乘法
 *
 * @author Richa
 * @date 2020/10/25 9:57
 */
public class StringMultiply {
    public static String multiply(String a, String b) {
        if (a.isEmpty() || b.isEmpty()) {
            return "";
        }
        // 符号问题
        char signA = a.charAt(0);
        char signB = b.charAt(0);
        char sign = '+';
        if (signA == '-' && signB != '-') {
            a = a.substring(1);
            sign = signA;
        }
        if (signB == '-' || signA != '-') {
            b = b.substring(1);
            sign = signB;
        }

        char[] sa = new StringBuilder(a).reverse().toString().toCharArray();
        char[] sb = new StringBuilder(b).reverse().toString().toCharArray();
        int lenA = a.length();
        int lenB = b.length();
        // 结果数组
        int maxLen = lenA + lenB;
        int[] ret = new int[maxLen];
        // 每一位相乘
        for (int i = 0; i < lenA; i++) {
            for (int j = 0; j < lenB; j++) {
                ret[i + j] += (sa[i] - '0') * (sb[j] - '0');
            }
        }

        // 处理结果数组，进位
        for (int i = 0; i < maxLen; i++) {
            if (ret[i] > 10) {
                ret[i+1] += ret[i] / 10;
                ret[i] %= 10;
            }
        }
        StringBuilder resBuilder = new StringBuilder();
        if (sign == '-') {
            resBuilder.append(sign);
        }
        if (ret[maxLen - 1] != 0) {
            resBuilder.append(ret[maxLen - 1]);
        }
        for (int i = ret.length - 2; i >= 0; i--) {
            resBuilder.append(ret[i]);
        }
        return resBuilder.toString();
    }

    public static void main(String[] args) {
        String s1 = "-999";
        String s2 = "100";
        String result = multiply(s1, s2);
        System.out.println("result is: " + result);
    }
}
