package StringAndChar;


/**
 *
 * 字符串实现大数相减
 * 首先判断差的位数，不大于最大的整数位数
 * 被减数小于减数位数差为负
 * 各位相减，统一处理借位
 * 默认正数，a - b
 * 
 * @author Richa
 * @date 2020/10/24 15:24
 */
public class StringMinus {
    public static String minusString(String a, String b) {
        // 字符串反转转化为字符数组
        char[] aArray = new StringBuilder(a).reverse().toString().toCharArray();
        char[] bArray = new StringBuilder(b).reverse().toString().toCharArray();

        int aLen = aArray.length, bLen = bArray.length;
        int maxLength = aLen > bLen ? aLen : bLen;
        int[] result = new int[maxLength];

        // 判断符号
        char sign = '+';
        if (aLen < bLen) {
            sign = '-';
        } else if (aLen == bLen) {
            int i = maxLength - 1;
            while (i > 0 && aArray[i] == bArray[i]) {
                i--;
            }
            if (aArray[i] < bArray[i]) {
                sign = '-';
            }
        }
        // 计算结果
        for (int i = 0; i < maxLength; i++) {
            int aInt = i < aLen ? aArray[i] : 0;
            int bInt = i < bLen ? bArray[i] : 0;
            if (sign == '-') {
                result[i] = bInt - aInt;
            } else {
                result[i] = aInt - bInt;
            }
        }
        // 处理结果
        for (int i = 0; i < maxLength; i++) {
            if (result[i] < 0) {
                result[i] += 10;
                result[i+1] -= 1;
            }
        }

        StringBuilder realResult = new StringBuilder();
        if (sign == '-') {
            realResult.append(sign);
        }
        boolean isBeginning = true;
        for (int i = maxLength - 1; i >= 0; i--) {
            if (result[i] == 0 && isBeginning) {
                continue;
            } else {
                isBeginning = false;
            }
            realResult.append(result[i]);
        }
        if (realResult.toString().equals("")) {
            realResult.append(0);
        }
        return realResult.toString();
    }
}
